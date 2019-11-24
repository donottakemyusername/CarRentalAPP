package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class ReturnModel {

    private int rid;
    private Date rDate;
    private Time time;
    private int odometer;
    private boolean fullTank;

    public ReturnModel() {}

    public ReturnModel(int rid, Date rDate, Time time, int odometer, Boolean fullTank, int value) {
        this.rid = rid;
        this.rDate = rDate;
        this.time = time;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFullTank(boolean fullTank) {
        this.fullTank = fullTank;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    private int value;

    public int getRid() {return rid;}

    public Date getrDate() {return rDate;}

    public Time getTime() {return time;}

    public int getOdometer() {return odometer;}

    public Boolean getFullTank() {return fullTank;}

    public int getValue() {return value;}
}
