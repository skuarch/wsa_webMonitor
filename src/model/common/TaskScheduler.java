package model.common;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import model.beans.Alarm;
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
    private SiteChecker sc = null;
    private int responseCode = 0;

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

                    //setName of this thread
                    setName(task.getName());                    

                    //proccess alarm
                    proccessAlarm();

                } catch (IOException e) {
                    logger.error("runScheduler", e);
                }

            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, task.getPeriod() * 1000);

    } // end runScheduler 

    //==========================================================================
    private boolean isAlive(Task task) throws IOException {

        boolean isAlive = false;
        sc = new SiteChecker(task.getUrl(), task.getMethod(), task.getTimeout());
        isAlive = sc.isSiteAlive();

        return isAlive;
    }

    //==========================================================================
    private void proccessAlarm() throws IOException {

        boolean isAlive = false;

        //check if the site is alive
        isAlive = isAlive(task);

        //get response code
        responseCode = getResponseCode();

        if (!isAlive) {

            failures++;
            System.out.println("failures " + failures  + " task " +task.getName());

            if (task.getAlarmLevel() != 0) {

                if (failures >= task.getTrigger() - 1) {

                    failures = 0;                    

                    Alarm alarm = ModelAlarm.createDefaultAlarm(
                            "web monitor " + task.getName() + " threw an alarm",
                            task.getAlarmLevel(),
                            "web " + task.getUrl() + " is responding code " + responseCode
                    );
                    
                    System.out.println("sending alarm task " + task.getName());
                    sendAlarm(alarm);

                }

            }
        }

    } // end proccessAlarm

    //==========================================================================
    private void sendAlarm(Alarm alarm) {

        if (alarm == null) {
            throw new IllegalArgumentException("alarm is null");
        }

        //AlarmSender
        new AlarmSender().sendAlarm(alarm);

    } // end sendAlarm

    //==========================================================================
    private int getResponseCode() throws IOException {
        return sc.getResponseCode();
    }

    //==========================================================================
    public void stopSchedule() {
        timer.cancel();
    } // end stopSchedule

} // end class
