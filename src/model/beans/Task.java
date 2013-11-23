package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(
            name = "getEnabledTasks",
            query = "from Task t where t.status = 1"
    ),
    @NamedQuery(
            name = "getTaskByName", 
            query = "from Task t where t.name = :name")
})
public class Task implements Serializable {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "task_name", nullable = false)
    private String name = null;
    @Column(name = "task_url", nullable = false)
    private String url = null;
    @Column(name = "task_method", nullable = false)
    private String method = null;
    @Column(name = "task_trigger", nullable = false)
    private int trigger;
    @Column(name = "task_period", nullable = false)
    private int period;
    @Column(name = "task_timeout", nullable = false)
    private int timeout;
    @Column(name = "task_status", nullable = false)
    private int status;
    @Column(name = "task_alarm_level")
    private int alarmLevel;

    //==========================================================================
    public Task() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTrigger() {
        return trigger;
    }

    public void setTrigger(int trigger) {
        this.trigger = trigger;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    @Override
    public String toString() {
        return "id= " + id + " name=" + name + " url="+url + " method=" + method + " trigger=" + trigger + " period=" + period + " timeout=" + timeout + " status=" + status + " alarmLevel=" + alarmLevel;
    }
    
    
} // end class
