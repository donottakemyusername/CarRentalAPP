package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class Return {

    enum Full{
        Y,
        N
    }

    private final int rid;
    private final Date rDate;
    private final Time time;
    private final int odometer;
    private final Full fullTank;
    private final int value;

    public Return(int rid, Date rDate, Time time, int odometer, Full fullTank, int value) {
        this.rid = rid;
        this.rDate = rDate;
        this.time = time;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }

    public int getRid() {return rid;}

    public Date getrDate() {return rDate;}

    public Time getTime() {return time;}

    public int getOdometer() {return odometer;}

    public Full getFullTank() {return fullTank;}

    public int getValue() {return value;}
}
