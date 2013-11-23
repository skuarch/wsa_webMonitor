package model.common;

import com.google.gson.Gson;
import java.io.IOException;
import model.beans.Alarm;
import model.net.Connection;
import org.apache.log4j.Logger;
import model.util.PropertyUtilities;

/**
 *
 * @author skuarch
 */
public class AlarmSender {

    private static final Logger logger = Logger.getLogger(AlarmSender.class);
    private final String CONFIGURATION_FILE = "configuration/application.properties";
    private Connection connection = null;
    private PropertyUtilities pu = null;
    private String host = null;
    private int port;
    private String context = null;
    private String uri = null;    

    //==========================================================================
    public AlarmSender() {
    } // end Register

    //==========================================================================    
    public void sendAlarm(Alarm alarm) {

        if (alarm == null) {
            throw new IllegalArgumentException("alarm is null");
        }

        try {

            pu = new PropertyUtilities(CONFIGURATION_FILE);
            initVariables();

            connection = new Connection(host, port, context, uri);
            connection.openConnection();
            connection.sendText("alarm", new Gson().toJson(alarm));           

        } catch (IOException e) {
            logger.error("registerApplication", e);            
        } finally {
            closeConnection();
        }

    } // end register

    //==========================================================================
    private void initVariables() throws IOException {
        host = pu.getStringPropertie("url.monitor.register.host");
        port = pu.getIntPropertie("url.monitor.register.port");
        context = pu.getStringPropertie("url.monitor.register.context");
        uri = pu.getStringPropertie("url.monitor.alarm.uri");
    } // end initVariables

    //==========================================================================
    private void closeConnection() {
        if (connection != null) {
            connection.closeConnection();
        }
    } // end closeConnection

} // end class
