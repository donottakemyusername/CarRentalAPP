package ca.ubc.cs304.model;

public class RentalReceipt {
    int rid;
    int confNo;
    TimePeriodModel timePeriod;
    String vtname;
    String location;

    public RentalReceipt(int rid, int confNo, TimePeriodModel timePeriod, String vtname, String location) {
        this.rid = rid;
        this.confNo = confNo;
        this.timePeriod = timePeriod;
        this.vtname = vtname;
        this.location = location;
    }

    public TimePeriodModel getTimePeriod() {
        return timePeriod;
    }

    public String getLocation() {
        return location;
    }

    public String getVtname() {
        return vtname;
    }

    public int getConfNo() {
        return confNo;
    }

    public int getRid() {
        return rid;
    }
}
