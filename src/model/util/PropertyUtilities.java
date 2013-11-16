package model.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilities for Properties.
 * @author skuarch
 */
public class PropertyUtilities {

    private String fileProperties = null;

    //==========================================================================
    public PropertyUtilities(String fileProperties) {
        this.fileProperties = fileProperties;
    } // end PropertyUtilities      

    //==========================================================================
    /**
     * return a Properties.
     *
     * @return Properties
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Properties getProperties() throws FileNotFoundException, IOException {

        if (fileProperties == null || fileProperties.length() < 1) {
            throw new NullPointerException("fileProperties is null");
        }

        FileInputStream fis = null;
        Properties properties = null;

        try {

            fis = new FileInputStream(fileProperties);
            properties = new Properties();
            properties.load(fis);

        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            closeInputStream(fis);
            fis = null;
        }

        return properties;

    } // end getProperties

    //==========================================================================
    /**
     * return String with the value of the key.
     *
     * @param key String
     * @return String with the value of the key
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getStringPropertie(String key) throws FileNotFoundException, IOException {

        if (fileProperties == null || fileProperties.length() < 1) {
            throw new NullPointerException("fileProperties is null or empty");
        }

        if (key == null || key.length() < 1) {
            throw new NullPointerException("key is null or empty");
        }

        Properties p = getProperties();
        return p.getProperty(key);

    } // end getStringPropertie

    //==========================================================================
    /**
     * return int with the value of the key.
     *
     * @param key String
     * @return int with the value of the key
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int getIntPropertie(String key) throws FileNotFoundException, IOException {

        if (fileProperties == null || fileProperties.length() < 1) {
            throw new NullPointerException("fileProperties is null or empty");
        }

        if (key == null || key.length() < 1) {
            throw new NullPointerException("key is null or empty");
        }

        int value;

        value = Integer.parseInt(getStringPropertie(key));

        return value;

    } // end getIntPropertie

    //==========================================================================
    /**
     * close the inputStream.
     *
     * @param inputStream
     */
    private static void closeInputStream(InputStream inputStream) {

        try {

            if (inputStream != null) {
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    } // end closeInputStream

} // end class