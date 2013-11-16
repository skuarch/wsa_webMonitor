package model.common;

import java.io.IOException;
import java.net.ServerSocket;
import org.apache.log4j.Logger;
import model.util.IOUtilities;

/**
 * start a server and relegate the socket to RequestDispatcher
 *
 * @author skuarch
 */
public class SocketProccessor {

    private static final Logger logger = Logger.getLogger(SocketProccessor.class);

    //==========================================================================
    /**
     * create a instance
     */
    public SocketProccessor() {
    } // end SocketProccessor

    //==========================================================================
    /**
     * start to listen a socket.
     *
     * @param port integer number of port.
     */
    public void startServer(int port) {

        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(port);
            logger.info("listening on port " + port);

            while (true) {
                //dispatch the client in another thread in order to continue listening
                new Thread(new RequestDispatcher(serverSocket.accept())).start();
            }
            
        } catch (IOException e) {
            logger.error("the process is running or another process is using the same port");
            System.exit(0);
        } finally {
            IOUtilities.closeServerSocket(serverSocket);
        }

    } // end startServer
} // end class
