package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class ReturnModel {

    private final int rid;
    private final Date rDate;
    private final Time time;
    private final Double odometer;
    private final boolean fullTank;
    private final Double value;

    public ReturnModel(int rid, Date rDate, Time time, Double odometer, Boolean fullTank, Double value) {
        this.rid = rid;
        this.rDate = rDate;
        this.time = time;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }

    public int getRid() {return rid;}

    public Date getRDate() {return rDate;}

    public Time getTime() {return time;}

    public Double getOdometer() {return odometer;}

    public Boolean getFullTank() {return fullTank;}

    public Double getValue() {return value;}
}
