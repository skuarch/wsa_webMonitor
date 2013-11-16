package model.common;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import model.beans.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author skuarch
 */
public class TaskScheduler extends Thread {

    private static final Logger logger = Logger.getLogger(TaskScheduler.class);
    private Task task = null;
    private TimerTask timerTask = null;
    private Timer timer = null;
    private int failures = 0;
    private boolean sendNotificationFailure = false;
    private SiteChecker sc = null;

    //==========================================================================
    public TaskScheduler(Task task) {
        this.task = task;
        setName(task.getName());
    } // end TaskScheduler

    //==========================================================================
    @Override
    public void run() {

        runScheduler();

    } // end run

    //=========================================================================
    private synchronized void runScheduler() {

        timerTask = new TimerTask() {

            @Override
            public void run() {

                try {

                    setName(task.getName());
                    sc = new SiteChecker(task.getUrl(), task.getMethod(), task.getTimeout());
                    System.out.println(sc.isSiteAlive());
                    System.out.println(sc.getResponseCode());

                } catch (IOException e) {
                    logger.error("runScheduler", e);
                }

            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, task.getPeriod() * 1000);

    } // end runScheduler 

    //==========================================================================
    public void stopSchedule() {
        timer.cancel();
    }

} // end class
