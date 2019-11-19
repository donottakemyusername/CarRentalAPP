package ca.ubc.cs304.model;

public class CustomerModel {
    private final int dlicense;
    private final String name;
    private final String phoneNum;
    private final String address;

    public CustomerModel(int dlicense, String name, String phoneNum, String address){
        this.dlicense = dlicense;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public int getDlicense() {return dlicense;}

    public String getName() {return name;}

    public String getPhoneNum() {return phoneNum;}

    public String getAddress() {return address;}
}
