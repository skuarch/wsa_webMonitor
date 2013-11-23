package model.common;

import java.util.ArrayList;
import model.beans.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author skuarch
 */
public class TaskRunner {

    private static final Logger logger = Logger.getLogger(TaskRunner.class);
    
    public void runTasks() {

        ArrayList<Task> tasks = null;

        try {

            tasks = ModelTask.getEnableTasks();

            for (Task task : tasks) {

                //create a scheduler
                TaskScheduler ts = ModelTaskScheduler.createTaskScheduler(task);
                //save the scheduler in a static context
                ModelTaskScheduler.holdTaskScheduler(ts);
                //run the scheduler
                ModelTaskScheduler.runTaskScheduler(ts);
                
            }

        } catch (Exception e) {
            logger.error("runTasks", e);
        }

    } // end runTasks

} // end class
