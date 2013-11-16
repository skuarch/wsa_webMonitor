package model.beans;

import model.dao.DAO;
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
public class RegisterTest {
    
    public RegisterTest() {
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
     * Test of getId method, of class Register.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetId() throws Exception {
        
        Register r = new Register();
        r.setDescription("mocos");
        r.setIp("chales");
        r.setName("mcoos");
        r.setPort(50);
        r.setStatus(1);
        r.setType(2);
        
        new DAO().create(r);
        
    }
    
}
