package model.common;

import java.net.Socket;
import org.apache.log4j.Logger;

/**
 *
 * @author skuarch
 */
public class RequestDispatcher extends Thread {

    private static final Logger logger = Logger.getLogger(RequestDispatcher.class);
    private Socket socket = null;
    private ModelSocket ms = null;
    private String jsonString = null;

    //==========================================================================
    public RequestDispatcher(Socket socket) {
        this.socket = socket;
    } // end RequestDispatcher    

    //==========================================================================
    @Override
    public void run() {

        if (socket == null || socket.isClosed()) {
            logger.error("socket is null or close");
            return;
        }

        try {

            ms = new ModelSocket(socket);
            jsonString = ms.receive();

            if (jsonString == null || jsonString.length() < 1) {
                throw new NullPointerException("data received from client is null or empty");
            } else {
                new WebMonitor(ms, jsonString).dispatchClient();
            }
            
        } catch (Exception e) {
            logger.error("run", e);
        }
    } // end run

} // end class
