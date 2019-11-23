package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;

import java.sql.Date;
import java.sql.Time;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Main implements LoginWindowDelegate, TerminalTransactionsDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public Main() {
		dbHandler = new DatabaseConnectionHandler();
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

			TerminalTransactions transaction = new TerminalTransactions();
			transaction.showMainMenu(this);
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
    public void insertBranch(BranchModel b) {
    	dbHandler.insertBranch(b);
    }

    public void insertVehicle(VehicleModel v) {
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

    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteBranch(BranchModel branchModel) {
    	dbHandler.deleteBranch(branchModel);
    }

	public void deleteVehicle(VehicleModel v) {
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

	public VehicleSearchResults[] customerSearchVehicle(Boolean hasCarType, Boolean hasLocation, Boolean hasTimePeriod,
									  String carType, String location, Date fromDate, Time fromTime, Date toDate, Time toTime) {
    	if (!hasTimePeriod) {
    		return dbHandler.customerSearchVehicle(carType, location, null);
		} else {
    		TimePeriodModel t = new TimePeriodModel(fromDate, fromTime, toDate, toTime);
    		return dbHandler.customerSearchVehicle(carType, location, t);
		}
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
									  public void showBranch() {
    	BranchModel[] models = dbHandler.getBranchInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		BranchModel model = models[i];
    		
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
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Main bank = new Main();
		bank.start();
	}
}
