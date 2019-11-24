package ca.ubc.cs304.model;

import java.sql.Date;

public class ReturnResult {
    private int confNum;
    private Date fromDate;
    private Date returnDate;
    private int daysRent;
    private int dRate;
    private int price;

    public ReturnResult() {}

    public ReturnResult(int confNum, Date fromDate, Date returnDate, int daysRent, int dRate, int price) {
        this.confNum = confNum;
        this.fromDate = fromDate;
        this.returnDate = returnDate;
        this.daysRent = daysRent;
        this.dRate = dRate;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getdRate() {
        return dRate;
    }

    public void setdRate(int dRate) {
        this.dRate = dRate;
    }

    public int getDaysRent() {
        return daysRent;
    }

    public void setDaysRent(int daysRent) {
        this.daysRent = daysRent;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setConfNum(int confNum) {
        this.confNum = confNum;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public int getConfNum() {
        return confNum;
    }
}
