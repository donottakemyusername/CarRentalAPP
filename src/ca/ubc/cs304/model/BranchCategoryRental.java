package ca.ubc.cs304.model;

public class BranchCategoryRental
{
    private String city;
    private String location;
    private String category;
    private int count;

    public BranchCategoryRental() {}

    public BranchCategoryRental(String city, String location, String category, int count) {
        this.city = city;
        this.location = location;
        this.category = category;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
