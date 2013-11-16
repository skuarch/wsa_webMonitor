package model.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author skuarch
 */
public class HTTPConnector {

    private String stringUrl = null;
    private String method = null;
    private int timeout = 0;
    private int code = -1;
    private URL url = null;
    private HttpURLConnection hurlc = null;
    private InputStream inputStream = null;
    private DataInputStream dataInputStream = null;

    //==========================================================================
    public HTTPConnector(String stringUrl, String method, int timeout) {

        this.stringUrl = stringUrl;
        this.method = method;
        this.timeout = timeout;

    } // end HTTPConnector

    //==========================================================================
    public void openConnection() throws MalformedURLException, IOException {

        if (!stringUrl.contains("http://")) {
            throw new MalformedURLException("the url doesn't contain the protocol web");
        }

        if (timeout < 10) {
            throw new IllegalArgumentException("timeout is less than 10");
        }

        url = new URL(stringUrl);
        hurlc = (HttpURLConnection) url.openConnection();
        hurlc.setConnectTimeout(timeout);
        hurlc.setReadTimeout(timeout);
        hurlc.setRequestMethod(method);
        hurlc.setDoInput(true);
        hurlc.connect();
        code = hurlc.getResponseCode();
        
    } // end openConnection

    //==========================================================================
    public String getContent() throws IOException {

        StringBuilder pageContent = null;
        String page = null;
        String tmp = null;

        try {

            pageContent = new StringBuilder();
            inputStream = hurlc.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            while ((tmp = dataInputStream.readLine()) != null) {
                pageContent.append(tmp);
            }

            page = pageContent.toString();

        } catch (IOException e) {
            throw e;
        }

        return page;

    } // end getContent

    //==========================================================================
    public int getResponseCode() throws IOException {
        return code;
    } // end getResponseCode

    //==========================================================================
    public void closeConnection() throws IOException {

        try {

            if (hurlc != null) {
                hurlc.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }

            if (dataInputStream != null) {
                dataInputStream.close();
            }

        } catch (IOException ioe) {
            throw ioe;
        } finally {
            url = null;
            hurlc = null;
            inputStream = null;
            dataInputStream = null;
        }

    } // end closeConnection

} // end class
