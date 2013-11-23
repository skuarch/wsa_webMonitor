package model.common;

import model.beans.Alarm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author skuarch
 */
public class AlarmSenderTest {
    
    public AlarmSenderTest() {
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
     * Test of sendAlarm method, of class AlarmSender.
     */
    @Test
    public void testSendAlarm() {
        
        Alarm a = new Alarm("mocos", 3, 7, "2013-11-21 00:00:00", "test wey", 3, "skuarch-lap url monitor");
        System.out.println(a);
        
        new AlarmSender().sendAlarm(a);
        
    }
    
}
