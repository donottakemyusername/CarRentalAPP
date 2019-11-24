package ca.ubc.cs304.model;

public class BranchRental {
    private int count;
    private String city;
    private String location;

    public BranchRental() {}

    public BranchRental(int count, String city, String location) {
        this.count = count;
        this.city = city;
        this.location = location;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
