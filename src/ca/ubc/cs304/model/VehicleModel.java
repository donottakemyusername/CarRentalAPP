package ca.ubc.cs304.model;

public class VehicleModel {

    enum Status {
        RENTED,
        MAINTENANCE,
        AVAILABLE
    }

    //private final int vid; // TODO: double check what the type of vid is
    private final String vlicense;
    private final String make;
    private final String model;
    private final int year;
    private final String color;
    private final String odometer;
    private final Status status;
    private final String vtname; // TODO: figure out if I should exchange for VehicleTypeObject instead
    private final String location; // TODO: figure out if for location and city should be Branch object but keep simple for now
    private final String city;

    public VehicleModel(String vlicense, String make, String model, int year, String color, String odometer,
                        Status status, String vtname, String location, String city) {
        //this.vid = vid;
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

   /* public int getVid() {
        return vid;
    }*/

    public String getVlicense() {
        return vlicense;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Status getStatus() {
        return status;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getOdometer() {
        return odometer;
    }

    public String getVtname() {
        return vtname;
    }


}
