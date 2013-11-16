package model.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author skuarch
 */
public class TaskContainer {

    private static final ConcurrentHashMap<String, TaskScheduler> chm = new ConcurrentHashMap<>();

    //==========================================================================
    private TaskContainer() {
    } // end TaskContainer

    //==========================================================================
    public static void addTaskScheduler(TaskScheduler ts) {

        if (ts.getName() == null || ts.getName().length() < 1) {
            throw new IllegalArgumentException("taskScheduler doesn't have a name");
        }

        chm.put(ts.getName(), ts);

    } // end addTaskScheduler

    //==========================================================================
    public static void removeTaskScheduler(String name) {

        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("name is null or empty");
        }

        System.out.println("removing from container: " + name);
        chm.remove(name);

    } // end removeTaskScheduler

    //==========================================================================
    public static TaskScheduler getTaskScheduler(String name) {

        if (name == null || name.length() < 1) {
            throw new IllegalArgumentException("name is null or empty");
        }

        TaskScheduler ts = chm.get(name);

        System.out.println("returning: " + ts.getName());

        return ts;
    } // end getTaskScheduler

    //==========================================================================
    public static ConcurrentHashMap<String, TaskScheduler> getCHM() {
        return chm;
    }

} // end class
