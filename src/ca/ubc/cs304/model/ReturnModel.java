package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class ReturnModel {

    private int rid;
    private Date rDate;
    private Time time;
    private Double odometer;
    private boolean fullTank;
    private Double value;

    public ReturnModel() {};
    public ReturnModel(int rid, Date rDate, Time time, Double odometer, Boolean fullTank, Double value) {
        this.rid = rid;
        this.rDate = rDate;
        this.time = time;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setFullTank(boolean fullTank) {
        this.fullTank = fullTank;
    }

    public void setOdometer(Double odometer) {
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

    public int getRid() {return rid;}

    public Date getRDate() {return rDate;}

    public Time getTime() {return time;}

    public Double getOdometer() {return odometer;}

    public Boolean getFullTank() {return fullTank;}

    public Double getValue() {return value;}
}
