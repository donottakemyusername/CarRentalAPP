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
public interface TerminalTransactionsDelegate {
	public void deleteBranch(BranchModel b);
	public void deleteVehicle(VehicleModel v);
	public void deleteVehicleType(VehicleTypeModel vt);
	public void deleteCustomer(CustomerModel u);
	public void deleteReservation(ReservationModel r);
	public void deleteReturn(ReturnModel r);
	public void deleteRental(RentalModel r);
	public void deleteTimePeriod(TimePeriodModel t);
	public void insertBranch(BranchModel b);
	public void insertVehicle(VehicleModel v);
	public void insertVehicleType(VehicleTypeModel vt);
	public void insertCustomer(CustomerModel u);
	public void insertReservation(ReservationModel r);
	public void insertReturn(ReturnModel r);
	public void insertRental(RentalModel r);
	public void insertTimePeriod(TimePeriodModel t);
	public void showBranch();
	public VehicleSearchResults[] customerSearchVehicle(Boolean hasCarType, Boolean hasLocation, Boolean hasTimePeriod,
								 String carType, String location, Date fromDate, Time fromTime, Date toDate, Time toTime);
	public int makeReservation(String dlicense, String cname, String phoneNum, String address, String location, String vtname, Date fromDate, Time fromTime, Date toDate, Time toTime);
	// TODO: implement the Customer query transactions

	
	public void terminalTransactionsFinished();
}
