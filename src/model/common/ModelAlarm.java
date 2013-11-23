package model.common;

import java.io.IOException;
import model.beans.Alarm;
import model.util.DateUtilities;
import model.util.PropertyUtilities;

/**
 *
 * @author skuarch
 */
public final class ModelAlarm {

    //==========================================================================
    public synchronized static Alarm createDefaultAlarm(String text, int level, String description) throws IOException {

        PropertyUtilities pu = new PropertyUtilities("configuration/application.properties");

        Alarm a = new Alarm();
        a.setDate(DateUtilities.getCurrentDateTime());
        a.setDescription(description);
        a.setCode(3);
        a.setLevel(level);
        a.setServerType(8);
        a.setText(text);
        a.setServerName(pu.getStringPropertie("url.monitor.name"));

        return a;
    } // end createDefaultAlarm

} // end ModelAlarm
