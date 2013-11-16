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

            //create bean the task
            task = ModelTask.createTask(jsonString);

            //validations
            if (ModelTask.existsTask(task.getName())) {
                //the task already exists
                modelSocket.send(getJsonResponse("the task already exists", false));
                return;
            }

            //save the task in DB
            ModelTask.saveTask(task);
            //create a scheduler
            ts = ModelTask.createTaskScheduler(task);
            //save the scheduler in a static context
            ModelTask.holdTaskScheduler(ts);
            //run the scheduler
            ModelTask.runTaskScheduler(ts);
            //send response to the client            
            modelSocket.send(getJsonResponse("the task was created succefully", true));            

        } catch (ParseException pe) {
            logger.error("makeTaskProccess", pe);
            modelSocket.send(getJsonResponse(pe.getMessage(), false));
        } catch (Exception e) {
            logger.error("makeTaskProccess", e);
            modelSocket.send(getJsonResponse(e.getMessage(), false));
        } finally {
            modelSocket.closeStreams();
        }

    } // end makeTaskProccessCreate

    //==========================================================================
    public void getTasks(String jsonString, ModelSocket modelSocket) throws Exception {

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
    private String getJsonResponse(String message, boolean isGood) {

        JSONObject jsono = new JSONObject();
        jsono.put("message", message);
        jsono.put("created", isGood);

        return jsono.toString();

    } // end getJsonResponse

} // end class
