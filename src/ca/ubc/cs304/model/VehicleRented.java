package ca.ubc.cs304.model;

import javax.print.DocFlavor;

public class VehicleRented {
    private String vlicense;
    private String make;
    private String model;
    private String vtname;

    public VehicleRented(){}

    public VehicleRented(String vlicense, String make, String model, String vtname) {
        this.vlicense = vlicense;
        this.make = make;
        this.model = model;
        this.vtname = vtname;
    }

    public String getVtname() {
        return vtname;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getVlicense() {
        return vlicense;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }
}
