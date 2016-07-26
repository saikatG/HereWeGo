package project.hackathon.herewego.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prpau on 7/26/2016.
 */
public class TripDetailsFromSms implements Serializable{

    private String srcName;
    private String destname;
    private Date startDateTime;
    private Date returnDateTime;

    public TripDetailsFromSms(String srcName, String destname, Date startDateTime, Date returnDateTime) {
        this.srcName = srcName;
        this.destname = destname;
        this.startDateTime = startDateTime;
        this.returnDateTime = returnDateTime;
    }


    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getDestname() {
        return destname;
    }

    public void setDestname(String destname) {
        this.destname = destname;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Date returnDateTime) {
        this.returnDateTime = returnDateTime;
    }
}

