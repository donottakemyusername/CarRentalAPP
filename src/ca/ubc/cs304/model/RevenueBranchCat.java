package ca.ubc.cs304.model;

public class RevenueBranchCat {
    private String city;
    private String location;
    private String vtname;
    private int count;
    private int revenue;

    public RevenueBranchCat(){}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
