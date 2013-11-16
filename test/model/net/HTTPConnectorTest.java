package model.net;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skuarch
 */
public class HTTPConnectorTest {
    
    public HTTPConnectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of openConnection method, of class HTTPConnector.
     */
    @Test
    public void testOpenConnection() throws Exception {
        
        HTTPConnector httpc = new HTTPConnector("http://hoyradios.com", "POST", 110);
        httpc.openConnection();        
        System.out.println(httpc.getResponseCode());
        System.out.println(httpc.getContent());
        httpc.closeConnection();
        
    }
    
}
