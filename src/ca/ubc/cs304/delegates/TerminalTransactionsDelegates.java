package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.*;

import java.sql.Date;
import java.sql.Time;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegates {
	public void deleteBranch(Branches b);
	public void deleteVehicle(Vehicles v);
	public void deleteVehicleType(VehicleTypeModel vt);
	public void deleteCustomer(CustomerModel u);
	public void deleteReservation(ReservationModel r);
	public void deleteReturn(ReturnModel r);
	public void deleteRental(RentalModel r);
	public void deleteTimePeriod(TimePeriodModel t);
	public void insertBranch(Branches b);
	public void insertVehicle(Vehicles v);
	public void insertVehicleType(VehicleTypeModel vt);
	public void insertCustomer(CustomerModel u);
	public void insertReservation(ReservationModel r);
	public void insertReturn(ReturnModel r);
	public void insertRental(RentalModel r);
	public void insertTimePeriod(TimePeriodModel t);
	public void showBranch();
    public int totalRental(Date date);
    public VehicleRented[] getAllRental(int date);
    public TotalCatModel[] totalCatgeory(Date date);
    public TotalBranchModel[] totalBranch(Date date);
    public int totalBranches(Date date, String city, String location);
    public RentalModel[] rentedNotReturned();
    public VehicleRented[] getAllBranchRental(Date date, String city, String location);
    public BranchCat[] getBranchCategory(Date date, String city, String location);
	public ReturnResult returnVehicle(int rentID, int d);
    public RevenueCat[] revenueCat(Date date);
	public VehicleSearchResults[] customerSearchVehicle(Boolean hasCarType, Boolean hasLocation, Boolean hasTimePeriod,
								 String carType, String location, String city, Date fromDate, Time fromTime, Date toDate, Time toTime);
	public int makeReservation(String dlicense, String cname, String phoneNum, String address, String city, String location, String vtname, Date fromDate, Time fromTime, Date toDate, Time toTime);
	public RentalReceipt makeRental(int confNum, String city, String location, String cardName, String cardNo, Date expDate);
	public RentalReceipt makeRental(String dlicense, String cname, String phoneNum, String address, String city, String location, String vtname, Date fromDate, Time fromTime, Date toDate, Time toTime,
									String cardName, String cardNo, Date expDate);
	// TODO: implement the Customer query transactions

	
	public void terminalTransactionsFinished();

    public VehicleTypeModel[] getAllVehicleTypes();

	Branches[] getAllBranches();
	Vehicles[] getAllVehicles();
}
