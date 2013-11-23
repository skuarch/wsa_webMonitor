package model.common;

import java.text.ParseException;
import model.beans.Task;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public class TaskProccessor {

    private static final Logger logger = Logger.getLogger(TaskProccessor.class);

    //==========================================================================
    public TaskProccessor() {
    } // end TaskProccessor

    //==========================================================================
    public void makeTaskProccessChange(String jsonString, ModelSocket modelSocket) {

        JSONObject jsono = null;
        Task task = null;
        TaskScheduler ts = null;

        try {

            jsono = new JSONObject(jsonString);

            //create bean
            task = ModelTask.getTask(jsono.getLong("id"));

            if (task == null) {
                modelSocket.send(getJsonResponse("the task doesn't exist", false, "changed"));
                return;
            }

            //change status of the task and stop or run scheduler
            if (task.getStatus() == 0) {

                //the task is stoped
                task.setStatus(1);
                
                //run scheduler
                ts = ModelTaskScheduler.createTaskScheduler(task);
                ModelTaskScheduler.runTaskScheduler(ts);
                ModelTaskScheduler.holdTaskScheduler(ts);

            } else {

                task.setStatus(0);
                //stop scheduler
                ts = ModelTaskScheduler.getTaskScheduler(task.getName());
                ts.stopSchedule();
                TaskContainer.removeTaskScheduler(task.getName());

            }

            //update the task in DB
            ModelTask.updateTask(task);
            modelSocket.send(getJsonResponse("the task was changed succefully", true, "changed"));

        } catch (ParseException pe) {
            logger.error("makeTaskProccess", pe);
            modelSocket.send(getJsonResponse(pe.getMessage(), false, "changed"));
        } catch (Exception e) {
            logger.error("makeTaskProccess", e);
            modelSocket.send(getJsonResponse(e.getMessage(), false, "changed"));
        } finally {
            modelSocket.closeStreams();
        }

    } // end makeTaskProccessChange

    //==========================================================================
    /**
     * this method has all the commands or instructions to create all the
     * process for make the task
     *
     * @param jsonString String
     * @param modelSocket ModelSocket
     */
    public void makeTaskProccessCreate(String jsonString, ModelSocket modelSocket) {

        Task task = null;
        TaskScheduler ts = null;

        try {

            //create bean
            task = ModelTask.createTask(jsonString);

            //validations
            if (ModelTask.existsTask(task.getName())) {
                //the task already exists
                modelSocket.send(getJsonResponse("the task already exists", false, "created"));
                return;
            }

            //save the task in DB
            ModelTask.saveTask(task);
            //create a scheduler
            ts = ModelTaskScheduler.createTaskScheduler(task);
            //save the scheduler in a static context
            ModelTaskScheduler.holdTaskScheduler(ts);
            //run the scheduler
            ModelTaskScheduler.runTaskScheduler(ts);
            //send response to the client            
            modelSocket.send(getJsonResponse("the task was created succefully", true, "created"));

        } catch (ParseException pe) {
            logger.error("makeTaskProccess", pe);
            modelSocket.send(getJsonResponse(pe.getMessage(), false, "created"));
        } catch (Exception e) {
            logger.error("makeTaskProccess", e);
            modelSocket.send(getJsonResponse(e.getMessage(), false, "created"));
        } finally {
            modelSocket.closeStreams();
        }

    } // end makeTaskProccessCreate

    //==========================================================================
    public void getEnabledTasks(String jsonString, ModelSocket modelSocket) throws Exception {

        //tasks in json format
        String tasks = null;

        try {

            tasks = ModelTask.getJsonTasksEnabled();
            modelSocket.send(tasks);

        } catch (Exception e) {
            logger.error("getTasks", e);
        } finally {
            modelSocket.closeStreams();
        }

    } // end getJsonTask

    //==========================================================================
    public void getAllTasks(String jsonString, ModelSocket modelSocket) throws Exception {

        //tasks in json format
        String tasks = null;

        try {

            tasks = ModelTask.getJsonAllTasks();
            modelSocket.send(tasks);

        } catch (Exception e) {
            logger.error("getTasks", e);
        } finally {
            modelSocket.closeStreams();
        }

    } // end getJsonTask

    //==========================================================================
    private String getJsonResponse(String message, boolean isGood, String request) {

        JSONObject jsono = new JSONObject();
        jsono.put("message", message);
        jsono.put(request, isGood);

        return jsono.toString();

    } // end getJsonResponse

} // end class
