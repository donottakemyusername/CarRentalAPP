package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegates;
import ca.ubc.cs304.exceptions.InvalidDetailsException;
import ca.ubc.cs304.exceptions.InvalidReservationException;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.RoleChoose;
import javafx.util.Pair;

import java.sql.Date;
import java.sql.Time;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Main implements LoginWindowDelegate, TerminalTransactionsDelegates {

	private DatabaseHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public Main() {
		dbHandler = new DatabaseHandler();
	}
	
	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}
	
	/**
	 * LoginWindowDelegate Implementation
	 * 
     * connects to Oracle database with supplied username and password
     */ 
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();

            RoleChoose rc = new RoleChoose();
            rc.showClerk(this);
			//TerminalTransactions transaction = new TerminalTransactions();
			//transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Insert a branch with the given info
	 */
    public void insertBranch(Branches b) {
    	dbHandler.insertBranch(b);
    }

    public void insertVehicle(Vehicles v) {
    	dbHandler.insertVehicle(v);
	}

	public void insertVehicleType(VehicleTypeModel vt) {
    	dbHandler.insertVehicleType(vt);
	}

	public void insertRental(RentalModel r) {
    	dbHandler.insertRental(r);
	}

	public void insertReservation(ReservationModel r) {
    	dbHandler.insertReservation(r);
	}

	public void insertReturn(ReturnModel r) {
    	dbHandler.insertReturn(r);
	}

	public void insertTimePeriod(TimePeriodModel t) {
    	dbHandler.insertTimePeriod(t);
	}

	// TODO: implement once we figure out what to name our table for User
	public void insertCustomer(CustomerModel u) {
    	dbHandler.insertCustomer(u);
	}

    public ReturnResult returnVehicle(int rentID,int date){
        Date d = new Date(date);
        return (dbHandler.returnVehicle(rentID,d));
    }
    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteBranch(Branches branchModel) {
    	dbHandler.deleteBranch(branchModel);
    }

	public void deleteVehicle(Vehicles v) {
    	dbHandler.deleteVehicle(v);
	}

	public void deleteVehicleType(VehicleTypeModel vt) {
    	dbHandler.deleteVehicleType(vt);
	}

	// TODO: implement once we figure out our table for user
	public void deleteCustomer(CustomerModel u) {
    	dbHandler.deleteCustomer(u);
	}

	public void deleteReservation(ReservationModel r) {
    	dbHandler.deleteReservation(r);
	}

	public void deleteReturn(ReturnModel r) {
    	dbHandler.deleteReturn(r);
	}

	public void deleteRental(RentalModel r) {
    	dbHandler.deleteRental(r);
	}

	public void deleteTimePeriod(TimePeriodModel t) {
    	dbHandler.deleteTimePeriod(t);
	}

    public VehicleRented[] getAllBranchRental(Date date, String city, String location){
        return dbHandler.getAllBranchRental(date,city,location);
    }

    public int totalRental(Date date){
        return dbHandler.totalRental(date);
    }

    public RentalModel[] rentedNotReturned(){
        return dbHandler.rentedNotReturned();
    }

    public BranchCat[] getBranchCategory(Date date, String city, String location){
        return dbHandler.getBranchCategory(date,city,location);
    }

    public int totalBranches(Date date, String city, String location){
        return dbHandler.totalBranches(date,city,location);
    }

    public TotalBranchModel[] totalBranch(Date date){
        return dbHandler.totalBranch(date);
    }

    public TotalCatModel[] totalCatgeory(Date date){
        return dbHandler.totalCatgeory(date);
    }

    public VehicleRented[] getAllRental(int date){
        return dbHandler.getAllRental(new Date(date));
    }
    public RevenueCat[] revenueCat(Date date){
        return dbHandler.revenueCat(date);
    }

    public RevenueBranch[] revenueBranch(Date date){
        return dbHandler.revenueBranch(date);
    }
    public Pair<Integer, Integer> totalRevenue(Date date){
        return dbHandler.totalRevenue(date);
    }

    public VehicleRented[] getReturnBranch(Date date, String city, String location){
        return dbHandler.getReturnBranch(date,city,location);
    }
    public VehicleRented[] getReturn(Date date){
        return dbHandler.getReturn(date);
    }
    public RevenueBranch getRevenueBranch(Date date, String city, String location){
        return dbHandler.getRevenueBranch(date, city, location);
    }
    public RevenueBranchCat[] getRevenueBranchCat(Date date, String city, String location){
        return dbHandler.getRevenueBranchCat(date, city, location);
    }
	public VehicleSearchResults[] customerSearchVehicle(Boolean hasCarType, Boolean hasLocation, Boolean hasTimePeriod,
									  String carType, String location, String city, Date fromDate, Time fromTime, Date toDate, Time toTime) {
    	if (!hasTimePeriod) {
    		return dbHandler.customerSearchVehicle(carType, location, city,null);
		} else {
    		TimePeriodModel t = new TimePeriodModel(fromDate, fromTime, toDate, toTime);
    		return dbHandler.customerSearchVehicle(carType, location, city, t);
		}
	}

	public void checkReservationParameters(String dlicense, String cname, String phoneNum, String address,
										   String city, String location, String vtname, Date fromDate, Time fromTime,
										   Date toDate, Time toTime) throws InvalidDetailsException{
    	if (dlicense == null || cname == null || phoneNum == null || address == null  || location == null || vtname == null || fromDate == null || fromTime == null || toDate == null || toTime == null) {
    		throw new InvalidDetailsException("One or more parameters are null");
		}

    	if (dlicense.isEmpty() || cname.isEmpty() || phoneNum.isEmpty() || address.isEmpty() || location.isEmpty() || vtname.isEmpty()) {
    		throw new InvalidDetailsException("One or more of the required fields are empty.");
		}
	}

	// return -1 if the reservation can't be made
	@Override
	public int makeReservation(String dlicense, String cname, String phoneNum, String address, String city, String location,
							   String vtname, Date fromDate, Time fromTime, Date toDate, Time toTime) throws InvalidDetailsException {
		int nextConfNum = -1;
    		// check that all of our parameters are valid
			checkReservationParameters(dlicense, cname, phoneNum, address, city, location, vtname, fromDate, fromTime, toDate, toTime);

			// check if the customer exists in the database, if not add them
			dbHandler.findOrAddCustomer(dlicense, cname, phoneNum, address);

			// check if our desired reservation can be made
			VehicleSearchResults[] result = customerSearchVehicle(true, true, true, vtname, location, city, fromDate, fromTime, toDate, toTime);
			// if it is now gone, then send a sorry message
			if (result.length == 0) {
				System.out.println("Sorry, the vehicle type you wish to reserve is now gone.");
				return -1;
			}
			// otherwise, make our desired reservation, first by getting the last confirmation number and incrementting (reservation confNo are in numerical order)
			nextConfNum = dbHandler.getNextConfNum();
			ReservationModel rs = new ReservationModel(nextConfNum, vtname, dlicense, fromDate, fromTime, toDate, toTime);
			insertReservation(rs);

		return nextConfNum;
	}


	@Override
	public RentalReceipt makeRental(int confNum, String city, String location, String cardName, String cardNo, Date expDate) throws InvalidReservationException, InvalidDetailsException{
    	// get the reservation if it exists
		checkRentalParameters(confNum, city, location, cardName, cardNo, expDate);
		RentalReceipt receipt = null;
		ReservationModel r = dbHandler.findReservation(confNum);
		// the next rental id is in numerical order so the next one is one higher than the max
		int nextRid = dbHandler.getNextRid();
		// get the first vehicle that satisfies this
		TimePeriodModel tp = new TimePeriodModel(r.getFromDate(), r.getFromTime(), r.getToDate(), r.getToTime());
		Vehicles v = dbHandler.getRentalVehicle(r.getVtname(), location, city, tp);
		RentalModel rent = new RentalModel(nextRid, v.getVlicense(), r.getDlicense(), tp.getFromDate(), tp.getFromTime(), tp.getToDate(), tp.getToTime(),
					v.getOdometer(), cardName, cardNo, expDate, r.getConfNum());
		insertRental(rent);
		// make Receipt and return
		receipt = new RentalReceipt(nextRid, confNum, tp, rent.getVlicense(), location);
		return receipt;
	}

	public void checkRentalParameters(int confNum, String city, String location, String cardName, String cardNo, Date expDate) throws InvalidDetailsException{
		if (confNum == -1 || city == null || location == null || cardName == null  || cardNo == null || expDate == null) {
			throw new InvalidDetailsException("One or more parameters are null\nYou need to either enter (confirmation number, branch city, branch location, card name, card number, expiryDate)\nOR\n" +
					"(Drivers License, Name, Phone Number, Address, City, Location, Vehicle Type, from date, from time, to date, to time, card name, card number, expdate)");
		}

		if (city.isEmpty() || city.isEmpty() || location.isEmpty() || cardName.isEmpty() || cardNo.isEmpty()) {
			throw new InvalidDetailsException("One or more of the required fields are empty.\nYou need to either enter (confirmation number, branch city, branch location, card name, card number, expiryDate)\nOR\n"
			+ "(Drivers License, Name, Phone Number, Address, City, Location, Vehicle Type, from date, from time, to date, to time, card name, card number, expdate)");

		}
	}

	@Override
	public RentalReceipt makeRental(String dlicense, String cname, String phoneNum, String address, String city,
									String location, String vtname, Date fromDate, Time fromTime, Date toDate, Time toTime,
									String cardName, String cardNo, Date expDate) throws InvalidDetailsException, InvalidReservationException {
    	// make a reservation first
		checkReservationParameters(dlicense, cname, phoneNum, address, city, location, vtname, fromDate, fromTime, toDate, toTime);
		int confNum = makeReservation(dlicense, cname, phoneNum, address, city, location, vtname, fromDate, fromTime, toDate,toTime);
		return makeRental(confNum, city, location, cardName, cardNo, expDate);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
	public void showBranch() {
    	Branches[] models = dbHandler.getBranchInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		Branches model = models[i];
    		
    		// simplified output formatting; truncation may occur
			System.out.printf("%-15.15s", model.getLocation());
			System.out.printf("%-15.15s", model.getCity());
    		
    		System.out.println();
    	}
    }

	
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that it is done with what it's 
     * doing so we are cleaning up the connection since it's no longer needed.
     */ 
    public void terminalTransactionsFinished() {
    	dbHandler.close();
    	dbHandler = null;
    	
    	System.exit(0);
    }

    @Override
    public VehicleTypeModel[] getAllVehicleTypes() {
        return dbHandler.getAllVehicleTypes();
    }

    @Override
    public Branches[] getAllBranches() {
        return dbHandler.getBranchInfo();
    }

    @Override
    public Vehicles[] getAllVehicles() {
        return dbHandler.getAllVehicles();
    }

    /**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Main bank = new Main();
		bank.start();
	}
}
