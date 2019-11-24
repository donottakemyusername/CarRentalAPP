package ca.ubc.cs304.model;

public class VehicleModel {

    public enum Status {
        RENTED,
        MAINTENANCE,
        AVAILABLE
    }

    private int vid; // TODO: double check what the type of vid is
    private String vlicense;
    private String make;
    private String model;
    private int year;
    private String color;
    private Float odometer;
    private Status status;
    private String vtname; // TODO: figure out if I should exchange for VehicleTypeObject instead
    private String location; // TODO: figure out if for location and city should be Branch object but keep simple for now
    private String city;

    public VehicleModel() {}

    public VehicleModel(int vid, String vlicense, String make, String model, int year, String color, Float odometer,
                        Status status, String vtname, String location, String city) {
        this.vid = vid;
        this.vlicense = vlicense;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.status = status;
        this.vtname = vtname;
        this.location = location;
        this.city = city;
    }

   public int getVid() {
       return vid;
   }

   public void setVid(int vid) {
       this.vid = vid;
   }

    public String getVlicense() {
        return vlicense;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getOdometer() {
        return odometer;
    }

    public void setOdometer(Float odometer) {
        this.odometer = odometer;
    }

    public String getVtname() {
        return vtname;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }

}
