package ca.ubc.cs304.model;

public class RevenueCat {
    private String vtname;
    private int count;
    private int revenue;

    public RevenueCat(){}

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getVtname() {
        return vtname;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }
}
