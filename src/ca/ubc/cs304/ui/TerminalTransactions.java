package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

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

		//this.delegate.insertTimePeriod(tp1);
		//this.delegate.deleteTimePeriod(tp1);

		// example for Branch
		String location1 = "344 Hornby St.";
		String city1 = "Vancouver";
		BranchModel b1 = new BranchModel(location1, city1);
		//this.delegate.insertBranch(b1);

		// example for VehicleType
		String vtname1 = "Economy";
		String features1 = "basic";
		Double wrate1 = 500.00, drate1 = 70.00, hrate1 = 15.00;
		Double wirate1 = 100.00, dirate1 = 20.00, hirate1 = 5.50, krate1 = 0.50;
		String vtname2 = "Luxury";
		String features2 = "basic";
		Double wrate2 = 1500.00, drate2 = 270.00, hrate2 = 115.00;
		Double wirate2 = 300.00, dirate2 = 50.00, hirate2 = 15.50, krate2 = 1.50;
		VehicleTypeModel vt1 = new VehicleTypeModel(vtname1, features1, wrate1, drate1, hrate1, wirate1, dirate1, hirate1, krate1);
		VehicleTypeModel vt2 = new VehicleTypeModel(vtname2, features2, wrate2, drate2, hrate2, wirate2, dirate2, hirate2, krate2);
		this.delegate.insertVehicleType(vt1);
		this.delegate.insertVehicleType(vt2);

		// example for Vehicle
		String vlicense1 = "ABC1234", make1 = "Toyota", model1 = "Corolla", color1 = "green";
		int year1 = 2014;
		Double odometer1 = 10000.00;
		String vlicense2 = "ABC1235", make2 = "Ford", model2 = "Focus", color2 = "red";
		int year2 = 2012;
		Double odometer2 = 10000.00;
		String vlicense3 = "ABC1111", make3 = "BMW", model3 = "i8", color3 = "white";
		int year3 = 2018;
		Double odometer3 = 10000.00;
		VehicleModel v1 = new VehicleModel(vlicense1, make1, model1, year1, color1, odometer1, VehicleModel.Status.AVAILABLE, vtname1, location1, city1);
		VehicleModel v2 = new VehicleModel(vlicense2, make2, model2, year2, color2, odometer2, VehicleModel.Status.AVAILABLE, vtname1, location1, city1);
		VehicleModel v3 = new VehicleModel(vlicense3, make3, model3, year3, color3, odometer3, VehicleModel.Status.AVAILABLE, vtname2, location1, city1);
		this.delegate.insertVehicle(v1);
		this.delegate.insertVehicle(v2);
		this.delegate.insertVehicle(v3);

		// example for Customer
		String dlicense1 = "BC_ZHANG_231", name1 = "Hong Yang", phoneNum1 = "1234567890", address1 = "1234 Lower Mall";
		CustomerModel c1 = new CustomerModel(dlicense1, name1, phoneNum1, address1, 0);
		this.delegate.insertCustomer(c1);

		// example for Reservation
		int confNum1 = 1;
		int confNum2 = 2;
		ReservationModel r1 = new ReservationModel(confNum1, vtname1, dlicense1, fromDate1, fromTime1, toDate1, toTime1);
		ReservationModel r2 = new ReservationModel(confNum2, vtname2, dlicense1, fromDate1, fromTime1, toDate1, toTime1);
		this.delegate.insertReservation(r1);
		this.delegate.insertReservation(r2);

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
		/**
		this.delegate.deleteReturn(return1);
		this.delegate.deleteRental(rent1);
		this.delegate.deleteReservation(r1);
		this.delegate.deleteVehicle(v1);
		this.delegate.deleteVehicleType(vt1);
		this.delegate.deleteTimePeriod(tp1);
		this.delegate.deleteBranch(b1);
		this.delegate.deleteCustomer(c1); **/

		// Search without time period
		//	--> search with only carType known
		VehicleSearchResults[] vr = this.delegate.customerSearchVehicle(true, false, false, vtname1, null, null, null, null, null);
		printSearchResults(vr);
		//	--> search with only Location known
		vr = this.delegate.customerSearchVehicle(false, true, false, null, location1, null, null,null,null);
		printSearchResults(vr);
		//	--> search with both carType and location known
		vr = this.delegate.customerSearchVehicle(true, true, false, vtname1, location1, null, null, null, null);
		printSearchResults(vr);

		// Search with time period
		//	--> time period that should return no results
		System.out.println("Same time period as reservation:");
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate1, fromTime1, toDate1, toTime1);
		printSearchResults(vr);

		//	--> time period that is before any reservation time period
		System.out.println("\ntime period is before reservation time period:");
		Date fromDate2 = new Date(2018+1900, 10, 21); // 11/21/2018
		Time fromTime2 = new Time(9, 0, 0);
		Date toDate2 = new Date(2018+1900, 10, 28); // 11/28/2018
		Time toTime2 = new Time(9, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which TO ends in the middle of another reservation but FROM is before
		System.out.println("\nmy TO ends in the middle of reservation timeperiod:");
		toDate2 = new Date(2019+1900, 10, 24); // 11/24/2019
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which TO ends on OURFROMDATE but is before the time (and one test that is after)
		System.out.println("\nmy TO ends on reservation FROM but my TOtime is BEFORE:");
		toDate2 = new Date(2019+1900, 10, 21); // 11/21/2019
		toTime2 = new Time(7, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);
		System.out.println("\nmy TO ends on reservation FROM but my TOtime is AFTER:");
		toTime2 = new Time (13, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which TO ends on OURTODATE but is before the time (and one test that is after)
		System.out.println("\nmy TO ends on reservation TO but my TOtime is BEFORE:");
		toDate2 = new Date(2019+1900, 10, 28); // 11/28/2019
		toTime2 = new Time(6, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);
		System.out.println("\nmy TO ends on reservation TO but my TOtime is AFTER:");
		toTime2 = new Time(23, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which FROM ends in the middle of another reservation but TO is after
		System.out.println("\nmy FROM is in the middle of reservation Time Period:");
		fromDate2 = new Date(2019+1900, 10, 24); // 11/24/2019
		toDate2 = new Date(2019+1900, 10, 30); // 11/30/2019
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which FROM ends on OURFROMDATE but is before the time (and one test that is after)
		System.out.println("\nmy FROM ends on reservation FROM but my FROMtime is BEFORE:");
		fromDate2 = new Date(2019+1900, 10, 21); // 11/21/2019
		fromTime2 = new Time(7, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);
		System.out.println("\nmy FROM ends on reservation FROM but my FROMtime is AFTER:");
		fromTime2 = new Time(13, 0, 0 );
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which FROM ends on OURTODATE but is before the time (and one test that is after)
		System.out.println("\nmy FROM ends on reservation TO but my FROMtime is BEFORE:");
		fromDate2 = new Date(2019+1900, 10, 28); // 11/28/2019
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);
		System.out.println("\nmy FROM ends on reservation TO but my FROMtime is AFTER:");
		fromTime2 = new Time(23, 0, 0);
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		//	--> time period in which our chosen is in the middle of another reservation
		System.out.println("\nmy timeperiod is in the middle of reservation time period");
		fromDate2 = new Date(2019+1900, 10, 23); // 11/23/2019
		toDate2 = new Date(2019+1900, 10, 26); // 11/26/2019
		vr = this.delegate.customerSearchVehicle(true, true, true, vtname2, location1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		// make a reservation
		System.out.println("\nMake a reservation that works");
		fromDate2 = new Date(2018+1900, 10, 21); // 11/21/2018
		fromTime2 = new Time(9, 0, 0);
		toDate2 = new Date(2018+1900, 10, 28); // 11/28/2018
		toTime2 = new Time(9, 0, 0);
		this.delegate.makeReservation(dlicense1, name1, phoneNum1, "2388 Western Parkway", location1, vtname1, fromDate2, fromTime2, toDate2, toTime2);
		printSearchResults(vr);

		// make a reservation



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

	private void printSearchResults(VehicleSearchResults[] vr) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyy");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:hh");
		for (VehicleSearchResults each : vr) {
			String out = each.getVehicleType() + "\t" + each.getLocation();
			if (each.getTimePeriod() != null) {
				out += "\t" + dateFormatter.format(each.getTimePeriod().getFromDate())
						+ "\t" + timeFormatter.format(each.getTimePeriod().getFromTime())
						+ "\t" + dateFormatter.format(each.getTimePeriod().getToDate())
						+ "\t" + timeFormatter.format(each.getTimePeriod().getToTime());
			}
			out += "\t" + Integer.toString(each.getNumAvailable());
			System.out.println(out);
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
