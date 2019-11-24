package ca.ubc.cs304.model;

public class CustomerModel {
    private String dlicense;
    private String name;
    private String phoneNum;
    private String address;
    private int numPoints;

    public CustomerModel(String dlicense, String name, String phoneNum, String address, int numPoints){
        this.dlicense = dlicense;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.numPoints = numPoints;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDlicense(String dlicense) {
        this.dlicense = dlicense;
    }

    public String getDlicense() {return dlicense;}

    public String getName() {return name;}

    public String getPhoneNum() {return phoneNum;}

    public String getAddress() {return address;}

    public int getNumPoints() {return numPoints;}

    public void setNumPoints(int numPoints) {this.numPoints = numPoints;}
}
