package model.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import model.util.IOUtilities;

/**
 *
 * @author skuarch
 */
public class ModelSocket {

    private static final Logger logger = Logger.getLogger(ModelSocket.class);
    private Socket socket = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private OutputStream outputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    //==========================================================================
    public ModelSocket(Socket socket) {
        this.socket = socket;
    } // end ModelSocket

    //==========================================================================
    public String receive() {

        if (socket == null || socket.isClosed()) {
            throw new NullPointerException("socket is null or close");
        }

        String msg = null;

        try {
            
            inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            msg = objectInputStream.readUTF();
            
        } catch (IOException e) {
            logger.error("receive", e);
            closeStreams();
        }

        return msg;

    } // end receive

    //==========================================================================
    public void send(String text) {

        if (text == null) {
            throw new NullPointerException("text is null");
        }

        if (socket == null || socket.isClosed()) {
            throw new NullPointerException("socket is null or close");
        }

        try {
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeUTF(text);
            objectOutputStream.flush();
        } catch (IOException e) {
            logger.error("send", e);
            closeStreams();
        }
    } // end send

    //==========================================================================
    public void closeStreams() {
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeInputStream(objectInputStream);
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeOutputStream(objectOutputStream);
        IOUtilities.closeSocket(socket);
    } // end closeStreams

} // end class
