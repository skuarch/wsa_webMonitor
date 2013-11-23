package application;

import java.io.IOException;
import model.common.RegisterApplication;
import model.common.StartServer;
import model.common.TaskRunner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author skuarch
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            
            PropertyConfigurator.configure("configuration/log4j.properties");
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            logger.info("*** starting wsa_urlMonitor ***");
            
            //register this server into the main server
            //and print the message returned by the main server
            new RegisterApplication().register();  
            
            //if this program has a tasks enables, those tasks should be running.
            new TaskRunner().runTasks();
  
            //start server
            new StartServer().run();
            
        } catch (IOException e) {
            logger.error("main", e);
        }
        
    } // end main

    //==========================================================================
    /**
     * display a message before the program ends
     */
    private static final Thread shutdownHook = new Thread() {
        @Override
        public void run() {
            logger.info("*** wsa_urlMonitor programm finished ***");
        }
    }; // end shutdownHook

} // end class
