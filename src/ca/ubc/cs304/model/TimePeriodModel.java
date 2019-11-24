package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class TimePeriodModel {
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public TimePeriodModel(Date fromDate, Time fromTime, Date toDate, Time toTime) {
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public Date getFromDate() {return fromDate;}

    public Date getToDate() {return toDate;}

    public Time getFromTime() {return fromTime;}

    public Time getToTime() {return toTime;}
}
