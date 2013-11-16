package model.common;

import java.io.IOException;
import model.util.PropertyUtilities;

/**
 *
 * @author skuarch
 */
public class StartServer {

    //==========================================================================
    public StartServer() {
    } // end StartServer

    //==========================================================================    
    public void run() throws IOException {

        PropertyUtilities pu = null;
        int port;

        try {

            pu = new PropertyUtilities("configuration/application.properties");
            port = pu.getIntPropertie("url.monitor.port");
            new SocketProccessor().startServer(port);

        } catch (IOException e) {
            throw e;
        }

    } // end run

} // end class
