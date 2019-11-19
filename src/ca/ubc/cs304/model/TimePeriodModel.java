package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class TimePeriodModel {
    private final Date fromDate;
    private final Time fromTime;
    private final Date toDate;
    private final Time toTime;

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
