package model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author skuarch
 */
public class DateUtilities {

    //==========================================================================
    /**
     * this class doesn't need a constructor.
     */
    private DateUtilities() {
    } // end DateUtilities
    
    
    //==========================================================================
    public static String getCurrentDateTime(){
    
        String stringDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        stringDate = sdf.format(new Date());       
        return stringDate;
        
    } // end getCurrentDateTime
    
    //==========================================================================
    public static String getCurrentDate(){
    
        String stringDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        stringDate = sdf.format(new Date());       
        return stringDate;
        
    } // end getCurrentDate
    
    
} // end class