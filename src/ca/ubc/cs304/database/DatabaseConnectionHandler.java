package ca.ubc.cs304.database;

import ca.ubc.cs304.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	//private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private Connection connection = null;

	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void deleteBranch(BranchModel branchModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE location = ? AND city = ?");
			ps.setString(1, branchModel.getLocation());
			ps.setString(2, branchModel.getCity());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchModel.getLocation() + " in " + branchModel.getCity() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteVehicle(VehicleModel vehicleModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Vehicle WHERE vlicense = ?");
			ps.setString(1, vehicleModel.getVlicense());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Vehicle " + vehicleModel.getVlicense() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteVehicleType(VehicleTypeModel vehicleTypeModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM VehicleType WHERE vtname = ?");
			ps.setString(1, vehicleTypeModel.getVtname());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " VehicleType " + vehicleTypeModel.getVtname() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteRental(RentalModel rentalModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Rental WHERE rid = ?");
			ps.setInt(1, rentalModel.getRid());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " RentType " + rentalModel.getRid() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteReservation(ReservationModel reservationModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Reservation WHERE confNo = ?");
			ps.setInt(1, reservationModel.getConfNum());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Reservation " + reservationModel.getConfNum() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteReturn(ReturnModel returnModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Return WHERE rid = ?");
			ps.setInt(1, returnModel.getRid());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Return " + returnModel.getRid() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteCustomer(CustomerModel customerModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer WHERE dlicense = ?");
			ps.setString(1, customerModel.getDlicense());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Customer " + customerModel.getDlicense() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void deleteTimePeriod(TimePeriodModel timePeriodModel) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM TimePeriod WHERE fromDate = ? AND fromTime = ? AND toDate = ? AND toTime = ?");
			ps.setDate(1, timePeriodModel.getFromDate());
			ps.setTime(2, timePeriodModel.getFromTime());
			ps.setDate(3, timePeriodModel.getToDate());
			ps.setTime(4, timePeriodModel.getToTime());


			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Time Period from:  " + timePeriodModel.getFromDate()
						+ " " + timePeriodModel.getFromTime() + " to: " + timePeriodModel.getToDate()
						+ " " + timePeriodModel.getToTime() + " does not exist!");
			}

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	// TODO: UserModel (decide on table name), currently table name is Customer but should represent both customer or clerk

	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?)");
			ps.setString(1, model.getLocation());
			ps.setString(2, model.getCity());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertVehicle(VehicleModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Vehicle VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, model.getVlicense());
			ps.setString(2, model.getMake());
			ps.setString(3, model.getModel());
			ps.setInt(4, model.getYear());
			ps.setString(5, model.getColor());
			ps.setDouble(6, model.getOdometer());
			if (model.getStatus() == VehicleModel.Status.RENTED) {
				ps.setString(7, "rented");
			} else if (model.getStatus() == VehicleModel.Status.MAINTENANCE) {
				ps.setString(7, "maintenance");
			} else {
				ps.setString(7, "available");
			}
			ps.setString(8, model.getVtname());
			ps.setString(9, model.getLocation());
			ps.setString(10, model.getCity()); //TODO: fix type for city in sql


			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertVehicleType(VehicleTypeModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO VehicleType VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, model.getVtname());
			ps.setString(2, model.getFeatures());
			ps.setDouble(3, model.getWrate());
			ps.setDouble(4, model.getDrate());
			ps.setDouble(5, model.getHrate());
			ps.setDouble(6, model.getWirate()); // TODO: check that Wirate and dirate are added to sql script
			ps.setDouble(7, model.getDirate());
			ps.setDouble(8, model.getHirate());
			ps.setDouble(9, model.getKrate());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertRental(RentalModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Rental VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, model.getRid());
			ps.setString(2, model.getVlicense());
			ps.setString(3, model.getDlicense()); //TODO: driver license in sql should be a string
			ps.setDate(4, model.getFromDate());
			ps.setTime(5, model.getFromTime());
			ps.setDate(6, model.getToDate());
			ps.setTime(7, model.getToTime());
			ps.setDouble(8, model.getOdometer());
			ps.setString(9, model.getCardName());
			ps.setString(10, model.getCardNo());
			ps.setDate(11, model.getExpDate());
			ps.setInt(12, model.getConfNo());


			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertReservation(ReservationModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, model.getConfNum());
			ps.setString(2, model.getVtname());
			ps.setString(3, model.getDlicense()); // TODO: sql dlicense should be a string not an int
			ps.setDate(4, model.getFromDate());
			ps.setTime(5, model.getFromTime());
			ps.setDate(6, model.getToDate());
			ps.setTime(7, model.getToTime());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertReturn(ReturnModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Return VALUES (?,?,?,?,?,?)");
			ps.setInt(1, model.getRid());
			ps.setDate(2, model.getRDate());
			ps.setTime(3, model.getTime());
			ps.setDouble(4, model.getOdometer());
			if (model.getFullTank()) ps.setString(5, "Y");
			else ps.setString(5, "N");
			ps.setDouble(6, model.getValue());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertTimePeriod(TimePeriodModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO TimePeriod VALUES (?,?,?,?)");
			ps.setDate(1, model.getFromDate());
			ps.setTime(2, model.getFromTime());
			ps.setDate(3, model.getToDate());
			ps.setTime(4, model.getToTime());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public void insertCustomer(CustomerModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
			ps.setString(1, model.getDlicense());
			ps.setString(2, model.getName());
			ps.setString(3, model.getPhoneNum());
			ps.setString(4, model.getAddress());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public VehicleSearchResults[] customerSearchVehicle(String carType, String location, TimePeriodModel timePeriod) {
		ArrayList<VehicleSearchResults> searchResults = new ArrayList<>();
		HashMap<VehicleSearchResults, Integer> count;

		Boolean hasCarType = false;
		Boolean hasLocation = false;

		if (carType != null) hasCarType = true;
		if (location != null) hasLocation = true;

		try {
			String queryStringVehicle = "SELECT V.vtname, V.location, COUNT(*) FROM Vehicle V";
			if (hasCarType || hasLocation) queryStringVehicle += " WHERE";
			if (hasCarType) queryStringVehicle += " V.vtname = ?";
			if (hasCarType && hasLocation) queryStringVehicle += " AND";
			if (hasLocation) queryStringVehicle += " V.location = ?";
			queryStringVehicle += " GROUP BY V.vtname, V.location";

			System.out.println(queryStringVehicle);

			PreparedStatement ps = connection.prepareStatement(queryStringVehicle);
			int i = 1;
			if (hasCarType) {
				ps.setString(i++, carType);
			}
			if (hasLocation) {
				ps.setString(i++, location);
			}

			ResultSet rs1 = ps.executeQuery();

			while (rs1.next()) {
				VehicleSearchResults result = new VehicleSearchResults(rs1.getString(1), rs1.getString(2), null, rs1.getInt(3));
				searchResults.add(result);
			}

			if (timePeriod != null) {
				String caseEndWithinTP = "((SELECT R.confNo FROM Reservation R WHERE R.toDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime >= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.toDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime <= ?))";
				// fromDate, fromDate fromTime, toDate, toDate, toTime
				String caseStartWithinTP = "((SELECT R.confNo FROM Reservation R WHERE R.fromDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime <= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.toDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime >= ?))";
				// fromDate, fromDate, fromTime, toDate, toDate, toTime
				String caseEncompassTP = "((SELECT R.confNo FROM Reservation R WHERE R.fromDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime >= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.fromDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime <= ?))";
				// fromDate, fromDate, fromTime, toDate, toDate, toTime

				String queryStringReservation = "SELECT R1.vtname, V.location, COUNT(DISTINCT(R1.confNo)) FROM Reservation R1, Vehicle V WHERE R1.vtname = V.vtname AND";
				if (hasCarType) queryStringReservation += " R1.vtname = ? AND";
				if (hasLocation) queryStringReservation += " V.location = ? AND";

				String fullQueryString = queryStringReservation + " R1.confNo IN (" +
						caseEndWithinTP + " UNION " + caseStartWithinTP + " UNION " + caseEncompassTP + ") GROUP BY R1.vtname, V.location";

				System.out.println(fullQueryString);

				ps = connection.prepareStatement(fullQueryString);

				i = 1;
				if (hasCarType) {
					ps.setString(i++, carType);
				}
				if (hasLocation) {
					ps.setString(i++, location);
				}

				for (int j = 0; j < 3; j++) {
					ps.setDate(i++, timePeriod.getFromDate());
					ps.setDate(i++, timePeriod.getFromDate());
					ps.setTime(i++, timePeriod.getFromTime());
					ps.setDate(i++, timePeriod.getToDate());
					ps.setDate(i++, timePeriod.getToDate());
					ps.setTime(i++, timePeriod.getToTime());
				}

				ResultSet rs2 = ps.executeQuery();

				// compare the reservation counts with the vehicle counts as sorted by vehicletype and location
				while (rs2.next()) {
					VehicleSearchResults result = new VehicleSearchResults(rs2.getString(1), rs2.getString(2), null, rs2.getInt(3));
					if (searchResults.contains(result)) {
						int j = searchResults.indexOf(result);
						int numLeft = searchResults.get(j).getNumAvailable() - result.getNumAvailable();
						if (numLeft > 0) {
							result.setTimePeriod(timePeriod);
							result.setNumAvailable(numLeft);
							searchResults.set(j, result);
						} else {
							searchResults.remove(j);
						}
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResults.toArray(new VehicleSearchResults[searchResults.size()]);
	}

	public VehicleModel getRentalVehicle(String vtname, String location, TimePeriodModel timePeriod) {
		try {
			String caseEndWithinTP = "((SELECT R.vlicense FROM Rental R WHERE R.toDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime >= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.toDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime <= ?))";
			// fromDate, fromDate fromTime, toDate, toDate, toTime
			String caseStartWithinTP = "((SELECT R.vlicense FROM Rental R WHERE R.fromDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime <= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.toDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime >= ?))";
			// fromDate, fromDate, fromTime, toDate, toDate, toTime
			String caseEncompassTP = "((SELECT R.vlicense FROM Rental R WHERE R.fromDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime >= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.fromDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime <= ?))";
			// fromDate, fromDate, fromTime, toDate, toDate, toTime

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vehicle V WHERE V.vtname = ? AND V.location = location AND V.vlicense NOT IN (" + caseEndWithinTP +" UNION " + caseStartWithinTP + " UNION "+ caseEncompassTP +")" );
			int i = 1;
			ps.setString(i++, vtname);
			ps.setString(i++, location);
			for (int j = 0; j < 3; j++) {
				ps.setDate(i++, timePeriod.getFromDate());
				ps.setDate(i++, timePeriod.getFromDate());
				ps.setTime(i++, timePeriod.getFromTime());
				ps.setDate(i++, timePeriod.getToDate());
				ps.setDate(i++, timePeriod.getToDate());
				ps.setTime(i++, timePeriod.getToTime());
			}

			ResultSet rs = ps.executeQuery();
			Boolean found = false;
			while (rs.next()) {
				found = true;
				VehicleModel.Status s = VehicleModel.Status.AVAILABLE;
				if (rs.getString("status") == "rented") s = VehicleModel.Status.RENTED;
				else if (rs.getString("status") == "maintenance") s = VehicleModel.Status.MAINTENANCE;
				VehicleModel result = new VehicleModel(rs.getString("vlicense"),
						rs.getString("make"),
						rs.getString("model"),
						rs.getInt("year"),
						rs.getString("color"),
						rs.getDouble("odometer"),
						s,
						rs.getString("vtname"),
						rs.getString("location"),
						rs.getString("city"));
				// update vehicle status
				updateVehicleStatus(result.getVlicense());
				return result;
			}

			// TODO: throw exception
			if (!found) {
				System.out.println("Sorry, there are no longer vehicles available for your rental.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch WHERE location = ?");

//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}

			while (rs.next()) {
				BranchModel model = new BranchModel(rs.getString("location"),
						rs.getString("city"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new BranchModel[result.size()]);
	}

	public int getNextConfNum() {
		int nextConfNum = 1;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT MAX(confNo) FROM Reservation");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextConfNum = rs.getInt(1)+1;
			}

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return nextConfNum;
	}

	public int getNextRid() {
		int nextRid = 1;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT MAX(rid) FROM Rental");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nextRid = rs.getInt(1)+1;
			}

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return nextRid;
	}

	public ReservationModel findReservation(int confNum) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Reservation WHERE confNo = ?");
			ps.setInt(1, confNum);
			ResultSet rs = ps.executeQuery();

			Boolean found = false;
			while (rs.next()) {
				found = true;
				ReservationModel result = new ReservationModel(rs.getInt("confNo"),
						rs.getString("vtname"),
						rs.getString("dlicense"),
						rs.getDate("fromDate"),
						rs.getTime("fromTime"),
						rs.getDate("toDate"),
						rs.getTime("toTime"));
				return result;
			}

			if (!found) {
				// TODO: throw an exception
				System.out.println("Reservation confirmation number: " + confNum + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CustomerModel[] getCustomerDetails() {
		ArrayList<CustomerModel> customerDetails = new ArrayList();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

			while (rs.next()) {
				CustomerModel userModel = new CustomerModel(rs.getString("dlicense"), rs.getString("name"), rs.getString("phoneNumber"),
						rs.getString("address"), rs.getInt("numPoints"));
				customerDetails.add(userModel);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return customerDetails.toArray(new CustomerModel[customerDetails.size()]);
	}

	public void findOrAddCustomer(String dlicense, String cname, String phoneNum, String address) {
		try {
			ArrayList<CustomerModel> customers = new ArrayList<CustomerModel>();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer WHERE dlicense = ?");
			ps.setString(1, dlicense);

			ResultSet rs = ps.executeQuery();
			Boolean found = false;
			while (rs.next()) {
				found = true;
				CustomerModel cs = new CustomerModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
				// check any inconsistencies with our customer info and update it
				String setClause = "";
				if (!cs.getName().equals(cname)) setClause += " name = ?,";
				if (!cs.getPhoneNum().equals(phoneNum)) setClause += " phoneNumber = ?,";
				if (!cs.getAddress().equals(address)) setClause += " address = ?,";
				if (!setClause.isEmpty()) {
					setClause = setClause.substring(0, setClause.length()-1);
					ps = connection.prepareStatement("UPDATE customer SET " + setClause + " WHERE dlicense = ?");
					int i=1;
					if (!cs.getName().equals(cname)) ps.setString(i++, cname);
					if (!cs.getPhoneNum().equals(phoneNum)) ps.setString(i++, phoneNum);
					if (!cs.getAddress().equals(address)) ps.setString(i++, address);
					ps.setString(i, dlicense);
					int rowCount = ps.executeUpdate();
					if (rowCount == 0) {
						System.out.println(WARNING_TAG + " Customer update failed.");
					}
					connection.commit();
				}
			}

			// if there was no customer, then lets insert it
			if (!found) {
				CustomerModel cs = new CustomerModel(dlicense, cname, phoneNum, address, 0);
				insertCustomer(cs);
			}

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void updateVehicleStatus(String vlicense) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE vehicle SET status = ? WHERE vlicense = ?");
			ps.setString(1, "rented");
			ps.setString(2, vlicense);
			ps.executeUpdate();
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");
			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
