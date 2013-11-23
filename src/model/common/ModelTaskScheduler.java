package model.common;

import model.beans.Task;

/**
 *
 * @author skuarch
 */
public class ModelTaskScheduler {
  
    //==========================================================================
    private ModelTaskScheduler(){    
    } // end ModelTaskScheduler
    
    //==========================================================================
    public static TaskScheduler getTaskScheduler(String name) {

        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("name is null");
        }

        TaskScheduler ts = null;
        ts = TaskContainer.getTaskScheduler(name);
        return ts;

    } // end getTaskScheduler
    
    //==========================================================================
    public static void holdTaskScheduler(TaskScheduler ts) {

        if (ts == null) {
            throw new IllegalArgumentException("taskScheduler is null");
        }

        TaskContainer.addTaskScheduler(ts);
    } // end holdTask

    //==========================================================================
    public static TaskScheduler createTaskScheduler(Task task) {

        if (task == null) {
            throw new IllegalArgumentException("task is null");
        }

        return new TaskScheduler(task);
    }

    //==========================================================================
    public static void runTaskScheduler(TaskScheduler ts) {

        if (ts == null) {
            throw new IllegalArgumentException("taskScheduler is null");
        }

        System.out.println("running task " + ts.getName());
        
        Thread t = new Thread(ts);
        t.start();
        t.setName(ts.getName());
        
    } // end runTaskScheduler

    //==========================================================================
    public static void stopTaskScheduler(String name) {

        if (name == null || name.length() < 0) {
            throw new IllegalArgumentException("name is null");
        }

        TaskScheduler ts = TaskContainer.getTaskScheduler(name);
        ts.stopSchedule();

    } // end stopTaskScheduler
    
} // end class