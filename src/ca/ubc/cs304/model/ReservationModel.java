package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class ReservationModel {

    private final int confNum;
    private final String vtname;
    private final int dlicense;
    private final Date fromDate;
    private final Time fromTime;
    private final Date toDate;
    private final Time toTime;

    public ReservationModel(int confNum, String vtname, int dlicense, Date fromDate, Time fromTime, Date toDate, Time toTime) {
        this.confNum = confNum;
        this.vtname = vtname;
        this.dlicense = dlicense;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public int getConfNum() {return confNum;}

    public String getVtname() {return vtname;}

    public int getDlicense() {return dlicense;}

    public Date getFromDate() {return fromDate;}

    public Time getFromTime() {return fromTime;}

    public Date getToDate() {return toDate;}

    public Time getToTime() {return toTime;}
}
