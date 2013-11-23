package model.common;

import java.io.IOException;
import model.net.HTTPConnector;

/**
 *
 * @author skuarch
 */
public class SiteChecker extends HTTPConnector {

    private int code = 522;
    
    //==========================================================================
    public SiteChecker(String stringUrl, String method, int timeout) {
        super(stringUrl, method, timeout);
    } // end SiteChecker

    //==========================================================================
    public synchronized boolean isSiteAlive() {

        boolean flag = false;

        try {

            openConnection();            
            code = super.getResponseCode();
            getContent();
            
            flag = true;

        } catch (IOException e) {
            flag = false;
        } finally {
            closeConnections();
        }

        return flag;

    } // end isSiteAlive

     //=========================================================================
    @Override
    public int getResponseCode() throws IOException {
        return code;
    }

    //==========================================================================
    private void closeConnections() {
        try {
            closeConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

} // end class
