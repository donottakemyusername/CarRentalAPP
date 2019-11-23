package ca.ubc.cs304.model;

import java.util.Objects;

public class VehicleSearchResults {
    private String vehicleType;
    private String location;
    private TimePeriodModel timePeriod;
    private int numAvailable;

    public VehicleSearchResults(String vehicleType, String location, TimePeriodModel timePeriod, int numAvailable) {
        // if something is null it means that it was not set (not searched for) except for vehicleType (vehicleType will never be null)
        this.vehicleType = vehicleType;
        this.location = location;
        this.timePeriod = timePeriod;
        this.numAvailable = numAvailable;
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
        this.vehicleType = vehicleType;
    }

    public void setLocation(String location) { this.location = location;}

    public void setTimePeriod(TimePeriodModel timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleSearchResults that = (VehicleSearchResults) o;
        return Objects.equals(vehicleType, that.vehicleType) &&
                Objects.equals(location, that.location) &&
                Objects.equals(timePeriod, that.timePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, location, timePeriod);
    }
}
