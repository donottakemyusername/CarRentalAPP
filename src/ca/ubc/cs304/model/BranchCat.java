package ca.ubc.cs304.model;

public class BranchCat {
    private String city;
    private String location;
    private String vtname;
    private int count;

    public BranchCat() {}

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
