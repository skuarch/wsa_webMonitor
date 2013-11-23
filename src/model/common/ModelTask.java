package model.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import model.beans.Task;
import model.dao.DAO;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Business logic for Task.
 *
 * @author skuarch
 */
public class ModelTask {

    //==========================================================================
    /**
     * this class doesn't need a public constructor.
     */
    private ModelTask() {
    } // end ModelTask

    //==========================================================================
    /**
     * create a task from string in json format.
     *
     * @param jsonString String
     * @return Task
     * @throws ParseException
     * @throws NoSuchElementException
     * @throws NumberFormatException
     */
    public static Task createTask(String jsonString) throws ParseException, NoSuchElementException, NumberFormatException {

        if (jsonString == null || jsonString.length() < 1) {
            throw new NullPointerException("json is null");
        }

        JSONObject jsono = null;
        Task task = null;

        try {

            jsono = new JSONObject(jsonString);

            task = new Task();
            task.setName(jsono.getString("name"));
            task.setUrl(jsono.getString("url"));
            task.setMethod(jsono.getString("method"));
            task.setPeriod(jsono.getInt("period"));
            task.setStatus(1);
            task.setTimeout(jsono.getInt("timeout"));
            task.setTrigger(jsono.getInt("trigger"));
            task.setAlarmLevel(jsono.getInt("alarmLevel"));

        } catch (ParseException | NoSuchElementException | NumberFormatException ex) {
            throw ex;
        }

        return task;

    } // end createTask

    //==========================================================================
    /**
     * return an ArrayList with all the tasks.
     *
     * @return ArrayList<Task>
     * @throws Exception
     */
    public static ArrayList<Task> getTasks() throws Exception {

        ArrayList<Task> tasks = null;
        tasks = new DAO().getArrayList(new Task());

        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        return tasks;

    } // end getTasks

    //==========================================================================
    /**
     * return a ArrayList with enabled task.
     *
     * @return ArrayList<Task>
     * @throws Exception
     */
    public static ArrayList<Task> getEnableTasks() throws Exception {

        ArrayList<Task> tasks = null;
        tasks = new DAO().query(new Task(), "getEnabledTasks");

        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        return tasks;

    } // end getEnableTasks

    //==========================================================================
    /**
     * search a Task by id in the database and return it.
     *
     * @param id long id of task
     * @return Task or null if the task doesn't exists
     * @throws Exception
     */
    public static Task getTask(long id) throws Exception {

        if (id < 1) {
            throw new IllegalArgumentException("id is incorrect");
        }

        return new DAO().get(id, new Task());

    } // end getTask

    //==========================================================================
    public static String getJsonAllTasks() throws Exception {

        JSONObject response = new JSONObject();
        JSONArray ids = new JSONArray();
        JSONArray names = new JSONArray();
        JSONArray url = new JSONArray();
        JSONArray method = new JSONArray();
        JSONArray trigger = new JSONArray();
        JSONArray period = new JSONArray();
        JSONArray timeout = new JSONArray();
        JSONArray status = new JSONArray();
        JSONArray alarmLevel = new JSONArray();
        ArrayList<Task> tasks = null;

        try {

            tasks = ModelTask.getTasks();

            for (Task task : tasks) {
                ids.put(task.getId());
                names.put(task.getName());
                url.put(task.getUrl());
                method.put(task.getMethod());
                trigger.put(task.getTrigger());
                period.put(task.getPeriod());
                timeout.put(task.getTimeout());
                status.put(task.getStatus());
                alarmLevel.put(task.getAlarmLevel());
            }

            response.put("rows", tasks.size());
            response.put("ids", ids);
            response.put("names", names);
            response.put("url", url);
            response.put("method", method);
            response.put("trigger", trigger);
            response.put("period", period);
            response.put("timeout", timeout);
            response.put("status", status);
            response.put("alarmLevel", alarmLevel);

        } catch (Exception e) {
            throw e;
        }

        return response.toString();

    } // end getJsonAllTasks

    //==========================================================================
    /**
     * remove task from database.
     *
     * @param task Task
     * @throws Exception
     */
    public static void removeTask(Task task) throws Exception {

        if (task == null) {
            throw new IllegalArgumentException("task is null");
        }

        new DAO().delete(task);

    } // end removeTask

    //==========================================================================
    /**
     * check if exists task in the DB.
     *
     * @param name String name of task
     * @return true if the task exists in the DB.
     * @throws Exception
     */
    public static boolean existsTask(String name) throws Exception {

        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("name is null opr empty");
        }

        boolean flag = false;
        List list = null;
        HashMap parameters = new HashMap();
        parameters.put("name", name);
        list = new DAO().query("getTaskByName", parameters, new Task());

        if (list != null && list.size() >= 1) {
            flag = true;
        }

        return flag;

    } // end existsTask

    //==========================================================================
    /**
     * save Task in DB.
     *
     * @param task Task
     * @throws Exception
     */
    public static void saveTask(Task task) throws Exception {

        if (task == null) {
            throw new IllegalArgumentException("task is null");
        }

        new DAO().create(task);

    } // end saveTask

    //==========================================================================
    /**
     * create a json with enabled tasks.
     *
     * @return String in json format
     * @throws Exception
     */
    public static String getJsonTasksEnabled() throws Exception {

        JSONObject response = new JSONObject();
        JSONArray names = new JSONArray();
        JSONArray url = new JSONArray();
        JSONArray method = new JSONArray();
        JSONArray trigger = new JSONArray();
        JSONArray period = new JSONArray();
        JSONArray timeout = new JSONArray();
        JSONArray status = new JSONArray();
        JSONArray alarmLevel = new JSONArray();
        ArrayList<Task> tasks = null;

        try {

            tasks = ModelTask.getEnableTasks();

            for (Task task : tasks) {
                names.put(task.getName());
                url.put(task.getUrl());
                method.put(task.getMethod());
                trigger.put(task.getTrigger());
                period.put(task.getPeriod());
                timeout.put(task.getTimeout());
                status.put(task.getStatus());
                alarmLevel.put(task.getAlarmLevel());
            }

            response.put("rows", tasks.size());
            response.put("names", names);
            response.put("url", url);
            response.put("method", method);
            response.put("trigger", trigger);
            response.put("period", period);
            response.put("timeout", timeout);
            response.put("status", status);
            response.put("alarmLevel", alarmLevel);

        } catch (Exception e) {
            throw e;
        }

        return response.toString();
    } // end getJsonTasks

    //==========================================================================
    public static void updateTask(Task task) throws Exception {

        if (task == null) {
            throw new IllegalArgumentException("task is null");
        }
        
        System.out.println("updating " + task);
        new DAO().update(task);

    } // end updateTask

} // end class
