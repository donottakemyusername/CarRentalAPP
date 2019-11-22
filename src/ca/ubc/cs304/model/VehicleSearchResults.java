package ca.ubc.cs304.model;

public class VehicleSearchResults {
    private Boolean hasVehicleType;
    private Boolean hasLocation;
    private Boolean hasTimePeriod;
    private String vehicleType;
    private String location;
    private TimePeriodModel timePeriod;
    private int numAvailable;

    public VehicleSearchResults(Boolean hasVehicleType, Boolean hasLocation, Boolean hasTimePeriod) {
        this.hasVehicleType = hasVehicleType;
        this.hasLocation = hasLocation;
        this.hasTimePeriod = hasTimePeriod;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public String getLocation() {
        return this.location;
    }

    public TimePeriodModel getTimePeriod() {
        return this.timePeriod;
    }

    public int getNumAvailable() {
        return this.numAvailable;
    }

    public void setVehicleType(String vehicleType) {
        if (this.hasVehicleType) this.vehicleType = vehicleType;
    }

    public void setLocation(String location) {
        if (this.hasLocation) this.location = location;
    }

    public void setTimePeriod(TimePeriodModel timePeriod) {
        if (this.hasTimePeriod) this.timePeriod = timePeriod;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

}
