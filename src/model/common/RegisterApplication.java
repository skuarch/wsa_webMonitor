package model.common;

import model.beans.Register;
import com.google.gson.Gson;
import java.io.IOException;
import model.net.Connection;
import org.apache.log4j.Logger;
import model.util.PropertyUtilities;

/**
 *
 * @author skuarch
 */
public class RegisterApplication {

    private static final Logger logger = Logger.getLogger(RegisterApplication.class);
    private final String CONFIGURATION_FILE = "configuration/application.properties";
    private Connection connection = null;
    private PropertyUtilities pu = null;
    private String host = null;
    private int port;
    private String context = null;
    private String uri = null;
    private Register register = null;
    private String message = null;

    //==========================================================================
    public RegisterApplication() {
    } // end Register

    //==========================================================================    
    public void register() {

        try {

            pu = new PropertyUtilities(CONFIGURATION_FILE);
            initVariables();

            register = new Register();
            register.setName(pu.getStringPropertie("url.monitor.name"));
            register.setDescription(pu.getStringPropertie("url.monitor.description"));
            register.setIp(pu.getStringPropertie("url.monitor.ip"));
            register.setPort(pu.getIntPropertie("url.monitor.port"));
            register.setStatus(pu.getIntPropertie("url.monitor.status"));
            register.setType(pu.getIntPropertie("url.monitor.type"));

            connection = new Connection(host, port, context, uri);
            connection.openConnection();
            connection.sendText("register", new Gson().toJson(register));

            message = connection.receiveText();

            if (message == null || message.length() < 1) {
                logger.error("null message from server");
                System.exit(-1);
            } else {
                logger.info(message);
            }

        } catch (IOException e) {
            logger.error("registerApplication", e);
            System.exit(-1);
        } finally {
            closeConnection();
        }

    } // end register

    //==========================================================================
    private void initVariables() throws IOException {
        host = pu.getStringPropertie("url.monitor.register.host");
        port = pu.getIntPropertie("url.monitor.register.port");
        context = pu.getStringPropertie("url.monitor.register.context");
        uri = pu.getStringPropertie("url.monitor.register.uri");
    } // end initVariables

    //==========================================================================
    private void closeConnection() {
        if (connection != null) {
            connection.closeConnection();
        }
    } // end closeConnection

} // end class
