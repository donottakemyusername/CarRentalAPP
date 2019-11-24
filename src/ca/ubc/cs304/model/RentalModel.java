package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Time;

public class RentalModel {

    private int rid;
    private String vlicense;
    private String dlicense;
    private Date fromDate;
    private Time fromTime;
    private Date toDate;
    private Time toTime;
    private int odometer;
    private String cardName;
    private String cardNo;
    private Date expDate;
    private int confNo;

    public RentalModel(){}

    public RentalModel(int rid, String vlicense, String dlicense, Date fromDate, Time fromTime, Date toDate, Time toTime, int odometer, String cardName, String cardNo, Date expDate, int confNo) {
        this.rid = rid;
        this.vlicense = vlicense;
        this.dlicense = dlicense;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.odometer = odometer;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.confNo = confNo;
    }

    public int getRid() {return rid;}

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getVlicense() {return vlicense;}

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }

    public String getDlicense() {return dlicense;}

    public void setDlicense(String dlicense) {
        this.dlicense = dlicense;
    }

    public Date getFromDate() {return fromDate;}

    public void setFromDate(Date date) {
        date = fromDate;
    }

    public Time getFromTime() {return fromTime;}

    public void setFromTime(Time time) {
        time = fromTime;
    }

    public Date getToDate() {return toDate;}

    public void setToDate(Date date) {
        date = toDate;
    }

    public Time getToTime() {return toTime;}

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public int getOdometer() {return odometer;}

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getCardName() {return cardName;}

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {return cardNo;}

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Date getExpDate() {return expDate;}

    public void setExpDate(Date date) {
        date = expDate;
    }

    public int getConfNo() {return confNo;}

    public void setConfNo(int confNo) {
        this.confNo = confNo;
    }
}
