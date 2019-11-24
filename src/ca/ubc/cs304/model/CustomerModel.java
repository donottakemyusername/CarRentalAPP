package ca.ubc.cs304.model;

public class CustomerModel {
    private final String dlicense;
    private final String name;
    private final String phoneNum;
    private final String address;
    private int numPoints;

    public CustomerModel(String dlicense, String name, String phoneNum, String address, int numPoints){
        this.dlicense = dlicense;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.numPoints = numPoints;
    }

    public String getDlicense() {return dlicense;}

    public String getName() {return name;}

    public String getPhoneNum() {return phoneNum;}

    public String getAddress() {return address;}

    public int getNumPoints() {return numPoints;}

    public void setNumPoints(int numPoints) {this.numPoints = numPoints;}
}
