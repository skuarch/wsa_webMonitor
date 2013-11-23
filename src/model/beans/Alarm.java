package model.beans;

/**
 *
 * @author skuarch
 */
public class Alarm {

    private String text = null;
    private int level = 0;
    private int serverType = 0;
    private String date = null;
    private String description = null;
    private int code = 0;
    private String serverName = null;

    //==========================================================================
    public Alarm() {
    } // end Alarm

    //==========================================================================
    public Alarm(String text, int level, int serverType, String date, String description, int code, String serverName) {
        this.text = text;
        this.level = level;
        this.serverType = serverType;
        this.date = date;
        this.description = description;
        this.code = code;
        this.serverName = serverName;
    } // end Alarm

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getServerType() {
        return serverType;
    }

    public void setServerType(int serverType) {
        this.serverType = serverType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

} // end class
