package model.common;

import java.text.ParseException;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author skuarch
 */
public class WebMonitor {

    private static final Logger logger = Logger.getLogger(WebMonitor.class);
    private ModelSocket modelSocket = null;
    private String jsonString = null;

    //==========================================================================
    public WebMonitor(ModelSocket modelSocket, String jsonString) {
        this.modelSocket = modelSocket;
        this.jsonString = jsonString;
    } // end WebMonitor

    //==========================================================================
    public void dispatchClient() throws Exception {

        JSONObject json = null;
        String request = null;

        try {

            json = new JSONObject(jsonString);
            request = json.getString("request");

            switch (request) {
                case "changeStatusTask":
                    new TaskProccessor().makeTaskProccessChange(jsonString, modelSocket);
                    break;                
                case "create task":
                    new TaskProccessor().makeTaskProccessCreate(jsonString, modelSocket);
                    break;
                case "deleteTask":
                    //new TaskProccessor().makeTaskProccessDelete(jsonString, modelSocket);
                    break;
                case "getEnabledTasks":
                    new TaskProccessor().getEnabledTasks(jsonString, modelSocket);
                    break;
                case "getAllTasks":
                    new TaskProccessor().getAllTasks(jsonString, modelSocket);
                    break;
                default:
                    logger.error("undefined request " + jsonString);
                    break;
            }

        } catch (ParseException e) {
            logger.error("dispatchClient", e);
        } catch (Exception ex) {
            logger.error("dispatchClient", ex);
        } finally {
            //modelSocket.closeStreams();
        }
    } // end dispatchClient    

} // end class
