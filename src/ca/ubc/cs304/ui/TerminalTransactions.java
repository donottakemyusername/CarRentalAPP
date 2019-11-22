package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	public TerminalTransactions() {
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;

		// SET UP SOME EXAMPLES FOR TESTING

		// examples for Time Period
		Date fromDate1 = new Date(2019+1900, 10, 21); // 11/21/2019
		Time fromTime1 = new Time(9,0,0); // 9:00:00 AM
		Date toDate1 = new Date(2019+1900, 10, 28); // 11/28/2019
		Time toTime1 = new Time(21, 0, 0); // 9:00:00 PM
		TimePeriodModel tp1 = new TimePeriodModel(fromDate1, fromTime1, toDate1, toTime1);

		this.delegate.insertTimePeriod(tp1);
		//this.delegate.deleteTimePeriod(tp1);

		// example for Branch
		String location1 = "344 Hornby St.";
		String city1 = "Vancouver";
		BranchModel b1 = new BranchModel(location1, city1);
		this.delegate.insertBranch(b1);

		// example for VehicleType
		String vtname1 = "Economy";
		String features1 = "basic";
		Double wrate1 = 500.00, drate1 = 70.00, hrate1 = 15.00;
		Double wirate1 = 100.00, dirate1 = 20.00, hirate1 = 5.50, krate1 = 0.50;
		VehicleTypeModel vt1 = new VehicleTypeModel(vtname1, features1, wrate1, drate1, hrate1, wirate1, dirate1, hirate1, krate1);
		this.delegate.insertVehicleType(vt1);

		// example for Vehicle
		String vlicense1 = "ABC1234", make1 = "Toyota", model1 = "Corolla", color1 = "green";
		int year1 = 2014;
		Double odometer1 = 10000.00;
		VehicleModel v1 = new VehicleModel(vlicense1, make1, model1, year1, color1, odometer1, VehicleModel.Status.AVAILABLE, vtname1, location1, city1);
		this.delegate.insertVehicle(v1);

		// example for Customer
		String dlicense1 = "BC_ZHANG_231", name1 = "Hong Yang", phoneNum1 = "1234567890", address1 = "1234 Lower Mall";
		CustomerModel c1 = new CustomerModel(dlicense1, name1, phoneNum1, address1);
		this.delegate.insertCustomer(c1);

		// example for Reservation
		int confNum1 = 1;
		ReservationModel r1 = new ReservationModel(confNum1, vtname1, dlicense1, fromDate1, fromTime1, toDate1, toTime1);
		this.delegate.insertReservation(r1);

		// example for Rental
		int rid1 = 1;
		String cardName1 = "Visa";
		String cardNo1 = "1234567891234567";
		Date expDate1 = new Date(2020+19, 5, 1); // 2020/4/1
		RentalModel rent1 = new RentalModel(rid1, vlicense1, dlicense1, fromDate1, fromTime1, toDate1, toTime1, odometer1, cardName1, cardNo1, expDate1, confNum1);
		this.delegate.insertRental(rent1);

		// example for Return
		Date returnDate1 = new Date(2019+1900, 11, 28); // 11/28/2019
		Time returnTime1 = new Time(21, 0, 0); // 9:00:00PM
		Double returnOdometer1 = odometer1+550.12;
		Boolean fullTank1 = true;
		Double value1 = wrate1 + wirate1 + (550.12*krate1);
		ReturnModel return1 = new ReturnModel(rid1, returnDate1, returnTime1, returnOdometer1, fullTank1, value1);
		this.delegate.insertReturn(return1);

		// check deleting works
		this.delegate.deleteReturn(return1);
		this.delegate.deleteRental(rent1);
		this.delegate.deleteReservation(r1);
		this.delegate.deleteVehicle(v1);
		this.delegate.deleteVehicleType(vt1);
		this.delegate.deleteTimePeriod(tp1);
		this.delegate.deleteBranch(b1);
		this.delegate.deleteCustomer(c1);

		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 5) {
			System.out.println();
			System.out.println("1. Insert");
			System.out.println("2. Delete");
			System.out.println("4. Show branch");
			System.out.println("5. Quit");
			System.out.print("Please choose one of the above 5 options: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					handleInsertOption(); 
					break;
				case 2:  
					handleDeleteOption(); 
					break;
//				case 3:
//					handleUpdateOption();
//					break;
				case 4:  
					delegate.showBranch(); 
					break;
				case 5:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
				}
			}
		}		
	}
	
	private void handleDeleteOption() {
		String location = null;
		while (location == null) {
			System.out.print("Please enter the branch location you wish to delete: ");
			location = readLine().trim();
		}

		String city = null;
		while (city == null) {
			System.out.print("Please enter the branch city you wish to delete: ");
			city = readLine().trim();
		}

		if (location != null && city != null) {
			delegate.deleteBranch(new BranchModel(location,city));
		}
	}
	
	private void handleInsertOption() {
		int id = INVALID_INPUT;
		
		// branch address is allowed to be null so we don't need to repeatedly ask for the address
		System.out.print("Please enter the branch location you wish to insert: ");
		String location = readLine().trim();
		if (location.length() == 0) {
			location = null;
		}
		
		String city = null;
		while (city == null || city.length() <= 0) {
			System.out.print("Please enter the branch city you wish to insert: ");
			city = readLine().trim();
		}
		
		BranchModel model = new BranchModel(location,
											city);
		delegate.insertBranch(model);
	}
	
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	
//	private void handleUpdateOption() {
//		String location = null;
//		while (location == null) {
//			System.out.print("Please enter the branch location you wish to update: ");
//			location = readLine().trim();
//		}
//
//		String city = null;
//		while (city == null) {
//			System.out.print("Please enter the branch city you wish to update: ");
//			city = readLine().trim();
//		}
//
//		delegate.updateBranch(location, city);
//	}
	
	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
