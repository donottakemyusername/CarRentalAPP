package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.TimePeriodModel;
import ca.ubc.cs304.model.VehicleModel;
import ca.ubc.cs304.model.VehicleTypeModel;

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
		BranchModel b1 = new BranchModel("Downtown", "Vancouver");
		this.delegate.insertBranch(b1);

		// example for VehicleType
		VehicleTypeModel vt1 = new VehicleTypeModel("Economy", "", 500.00, 70.00, 15.00, 100.00, 20.00, 5.50, 0.50);
		this.delegate.insertVehicleType(vt1);

		// example for Vehicle
		VehicleModel v1 = new VehicleModel("ABC1234", "Toyota", "Corolla", 2014,
				"green", 10000., VehicleModel.Status.AVAILABLE, "Economy","Downtown","Vancouver");
		this.delegate.insertVehicle(v1);


		
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
