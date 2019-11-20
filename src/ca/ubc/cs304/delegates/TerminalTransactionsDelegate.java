package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.*;

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
	public void deleteUser(UserModel u);
	public void deleteReservation(ReservationModel r);
	public void deleteReturn(ReturnModel r);
	public void deleteRental(RentalModel r);
	public void deleteTimePeriod(TimePeriodModel t);
	public void insertBranch(BranchModel b);
	public void insertVehicle(VehicleModel v);
	public void insertVehicleType(VehicleTypeModel vt);
	public void insertUser(UserModel u);
	public void insertReservation(ReservationModel r);
	public void insertReturn(ReturnModel r);
	public void insertRental(RentalModel r);
	public void insertTimePeriod(TimePeriodModel t);
	public void showBranch();
	public void showVehicle(VehicleModel v);
	public void showVehicleType(VehicleTypeModel vt);
	public void showUser(UserModel u);
	public void showReservation(ReservationModel r);
	public void showReturn(ReturnModel r);
	public void showRental(RentalModel r);
	public void showTimePeriod(TimePeriodModel t);
	// TODO: implement the Customer query transactions

	
	public void terminalTransactionsFinished();

	public void addCustomerDetails(UserModel userModel);
}
