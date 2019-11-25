package ca.ubc.cs304.database;

import ca.ubc.cs304.exceptions.IllegalTimePeriodException;
import ca.ubc.cs304.exceptions.InvalidDetailsException;
import ca.ubc.cs304.exceptions.InvalidReservationException;
import ca.ubc.cs304.model.*;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles all database related transactions
 */
public class DatabaseHandler {
	//private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private Connection connection = null;

    public DatabaseHandler() {
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

	public void deleteBranch(Branches branchModel) {
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

	public void deleteVehicle(Vehicles vehicles) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Vehicle WHERE vlicense = ?");
			ps.setString(1, vehicles.getVlicense());

			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Vehicle " + vehicles.getVlicense() + " does not exist!");
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

	public void insertBranch(Branches model) {
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

	public void insertVehicle(Vehicles model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Vehicle VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, model.getVlicense());
			ps.setString(2, model.getMake());
			ps.setString(3, model.getModel());
			ps.setInt(4, model.getYear());
			ps.setString(5, model.getColor());
			ps.setDouble(6, model.getOdometer());
			if (model.getStatus() == Vehicles.Status.RENTED) {
				ps.setString(7, "rented");
			} else if (model.getStatus() == Vehicles.Status.MAINTENANCE) {
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

	public VehicleSearchResults[] customerSearchVehicle(String carType, String location, String city, TimePeriodModel timePeriod) {
		ArrayList<VehicleSearchResults> searchResults = new ArrayList<>();

		Boolean hasCarType = false;
		Boolean hasLocation = false;

		if (carType != null && !carType.isEmpty() && !carType.equals("All")) hasCarType = true;
		if (location != null && !location.isEmpty() && city != null && !city.isEmpty()) hasLocation = true;

		try {	
			String queryStringVehicle = "SELECT vtname, location, city, COUNT(*) FROM Vehicle";
			if (hasCarType || hasLocation) queryStringVehicle += " WHERE";
			if (hasCarType) queryStringVehicle += " vtname = ?";
			if (hasCarType && hasLocation) queryStringVehicle += " AND";
			if (hasLocation) queryStringVehicle += " location = ? AND city = ?";
			queryStringVehicle += " GROUP BY vtname, location, city";

			System.out.println(queryStringVehicle);

			PreparedStatement ps = connection.prepareStatement(queryStringVehicle);
			int i = 1;
			if (hasCarType) {
				ps.setString(i++, carType);
			}
			if (hasLocation) {
				ps.setString(i++, location);
				ps.setString(i++, city);
			}

			ResultSet rs1 = ps.executeQuery();

			while (rs1.next()) {
				VehicleSearchResults result = new VehicleSearchResults(rs1.getString("vtname"),
						rs1.getString("location"), rs1.getString("city"), null, rs1.getInt("COUNT(*)"));
				searchResults.add(result);
			}

			if (timePeriod != null) {
				if (timePeriod.getFromDate().compareTo(timePeriod.getToTime()) >= 0 ||
						(timePeriod.getFromDate().compareTo(timePeriod.getToTime()) == 0 && timePeriod.getFromTime().compareTo(timePeriod.getToTime()) >= 0)) {
					throw new IllegalTimePeriodException("time period specified is invalid.");
				}

				Date earliestDate = new Date (1900+1900, 0, 1);
				if (timePeriod.getFromDate().compareTo(earliestDate) < 0 || timePeriod.getToDate().compareTo(earliestDate) < 0) {
					throw new IllegalTimePeriodException("time period specified is unreasonable.");
				}

				String caseEndWithinTP = "((SELECT R.confNo FROM Reservation R WHERE R.toDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime >= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.toDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime <= ?))";
				// fromDate, fromDate fromTime, toDate, toDate, toTime
				String caseStartWithinTP = "((SELECT R.confNo FROM Reservation R WHERE R.fromDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime <= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.toDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.toDate = ? AND R.toTime >= ?))";
				// fromDate, fromDate, fromTime, toDate, toDate, toTime
				String caseEncompassTP = "((SELECT R.confNo FROM Reservation R WHERE R.fromDate > ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime >= ?)) INTERSECT ((SELECT R.confNo FROM Reservation R WHERE R.fromDate < ?) UNION (SELECT R.confNo FROM Reservation R WHERE R.fromDate = ? AND R.fromTime <= ?))";
				// fromDate, fromDate, fromTime, toDate, toDate, toTime

				String queryStringReservation = "SELECT R1.vtname, V.location, V.city, COUNT(DISTINCT(R1.confNo)) FROM Reservation R1, Vehicle V WHERE R1.vtname = V.vtname AND";
				if (hasCarType) queryStringReservation += " R1.vtname = ? AND";
				if (hasLocation) queryStringReservation += " V.location = ? AND V.city = ? AND";

				String fullQueryString = queryStringReservation + " R1.confNo IN (" +
						caseEndWithinTP + " UNION " + caseStartWithinTP + " UNION " + caseEncompassTP + ") GROUP BY R1.vtname, V.location, V.city";

				System.out.println(fullQueryString);

				ps = connection.prepareStatement(fullQueryString);

				i = 1;
				if (hasCarType) {
					ps.setString(i++, carType);
				}
				if (hasLocation) {
					ps.setString(i++, location);
					ps.setString(i++, city);
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
					VehicleSearchResults result = new VehicleSearchResults(rs2.getString(1), rs2.getString(2), rs2.getString(3), null, rs2.getInt(4));
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

			// get the details of all the vehicles that could be available
			for (VehicleSearchResults sr : searchResults) {
				ps = connection.prepareStatement("SELECT * FROM Vehicle WHERE vtname = ? AND location = AND city = ?");
				ps.setString(1, sr.getVehicleType());
				ps.setString(2, sr.getLocation());
				ps.setString(3, sr.getCity());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Vehicles.Status s = Vehicles.Status.AVAILABLE;
					if (rs.getString("status").equals("rented")) s = Vehicles.Status.RENTED;
					else if (rs.getString("status").equals("maintenance")) s = Vehicles.Status.MAINTENANCE;
					Vehicles v = new Vehicles(rs.getString("vlicense"),
							rs.getString("make"),
							rs.getString("model"),
							rs.getInt("year"),
							rs.getString("color"),
							rs.getDouble("odometer"),
							s,
							rs.getString("vtname"),
							rs.getString("location"),
							rs.getString("city"));
					sr.addVehicle(v);
				}
			}
		} catch (SQLException | IllegalTimePeriodException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return searchResults.toArray(new VehicleSearchResults[searchResults.size()]);
	}

	public Vehicles getRentalVehicle(String vtname, String location, String city, TimePeriodModel timePeriod) throws InvalidDetailsException {
		try {
			String caseEndWithinTP = "((SELECT R.vlicense FROM Rental R WHERE R.toDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime >= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.toDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime <= ?))";
			// fromDate, fromDate fromTime, toDate, toDate, toTime
			String caseStartWithinTP = "((SELECT R.vlicense FROM Rental R WHERE R.fromDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime <= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.toDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.toDate = ? AND R.toTime >= ?))";
			// fromDate, fromDate, fromTime, toDate, toDate, toTime
			String caseEncompassTP = "((SELECT R.vlicense FROM Rental R WHERE R.fromDate > ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime >= ?)) INTERSECT ((SELECT R.vlicense FROM Rental R WHERE R.fromDate < ?) UNION (SELECT R.vlicense FROM Rental R WHERE R.fromDate = ? AND R.fromTime <= ?))";
			// fromDate, fromDate, fromTime, toDate, toDate, toTime

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vehicle V WHERE V.vtname = ? AND V.location = ? AND V.city = ? AND V.vlicense NOT IN (" + caseEndWithinTP +" UNION " + caseStartWithinTP + " UNION "+ caseEncompassTP +")" );
			int i = 1;
			ps.setString(i++, vtname);
			ps.setString(i++, location);
			ps.setString(i++, city);
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
				Vehicles.Status s = Vehicles.Status.AVAILABLE;
				if (rs.getString("status") == "rented") s = Vehicles.Status.RENTED;
				else if (rs.getString("status") == "maintenance") s = Vehicles.Status.MAINTENANCE;
				Vehicles result = new Vehicles(rs.getString("vlicense"),
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

			if (!found) {
				throw new InvalidDetailsException("The vehicle type you wish to rent is no longer available.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Branches[] getBranchInfo() {
		ArrayList<Branches> result = new ArrayList<Branches>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");

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
			
			while(rs.next()) {
				Branches model = new Branches(rs.getString("location"), rs.getString("city"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Branches[result.size()]);
	}

    public void addCustomerDetails(CustomerModel userModel) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
            ps.setString(1, userModel.getDlicense());
            ps.setString(2, userModel.getName());
            ps.setString(3, userModel.getPhoneNum());
            ps.setString(4, userModel.getAddress());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public CustomerModel[] getCustomerDetails() {
	    ArrayList<CustomerModel> customerDetails = new ArrayList();
	    try {
	        Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

            while(rs.next()) {
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

	
	public void updateBranch(String branch_location, String branch_city) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET location = ? WHERE city = ?");
		  ps.setString(1, branch_location);
		  ps.setString(2, branch_city);

		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + branch_location + " does not exist!");
		  }

		  connection.commit();

		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

    /*
    Transactions performed by clerk
     */

//    public ReservationModel rentalWithReservation(int confNo) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Reservation r WHERE r.confNo = ?");
//            ps.setInt(1,confNo);
//            ResultSet resultSet = ps.executeQuery();
//
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }

    /*
    The function returns all rented vehicles with the selected date (for report purpose)
    //TODO: Group by branch ---> I assume we just need to order by city & location because if you group by only city and location then the details of the vehciles will not be shown
     */

    // total numbers of cars rented for a specific day by branch
    public int totalBranches(Date date, String city, String location) {
        int cnt = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Count(*)" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ? AND v.city = ? AND v.location = ?" +
                    "GROUP BY v.city, v.location");
            ps.setDate(1,date);
            ps.setString(2,city);
            ps.setString(3,location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            cnt = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return cnt;
    }

    public BranchCat[] getBranchCategory(Date date, String city, String location) {
        ArrayList<BranchCat> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.city, v.location, v.vtname, Count(*)" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ? AND v.city = ? AND v.location = ?" +
                    "GROUP BY v.city, v.location, v.vtname");
            ps.setDate(1,date);
            ps.setString(2,city);
            ps.setString(3,location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                BranchCat branchRental = new BranchCat();
                branchRental.setCity(resultSet.getString(1));
                branchRental.setLocation(resultSet.getString(2));
                branchRental.setVtname(resultSet.getString(3));
                branchRental.setCount(resultSet.getInt(4));

                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new BranchCat[branchRentals.size()]);
    }

    public VehicleRented[] getAllBranchRental(Date date, String city, String location) {
        ArrayList<VehicleRented> vehicles = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.vlicense, v.make, v.model, v.vtname, v.city, v.location" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ? AND v.city = ? AND v.location = ?" +
                    "ORDER BY v.city, v.location, v.vtname");
            ps.setDate(1,date);
            ps.setString(2, city);
            ps.setString(3, location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            while(resultSet.next()) {
                VehicleRented vehicleRented = new VehicleRented();
                vehicleRented.setVlicense(resultSet.getString(1));
                vehicleRented.setMake(resultSet.getString(2));
                vehicleRented.setModel(resultSet.getString(3));
                vehicleRented.setVtname(resultSet.getString(4));
                vehicles.add(vehicleRented);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return vehicles.toArray(new VehicleRented[vehicles.size()]);
    }

    public VehicleRented[] getAllRental(Date date) {
        ArrayList<VehicleRented> vehicles = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.vlicense, v.make, v.model, v.vtname, v.city, v.location" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ?" +
                    "ORDER BY v.city, v.location, v.vtname");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            while(resultSet.next()) {
                VehicleRented vehicleRented = new VehicleRented();
                vehicleRented.setVlicense(resultSet.getString(1));
                vehicleRented.setMake(resultSet.getString(2));
                vehicleRented.setModel(resultSet.getString(3));
                vehicleRented.setVtname(resultSet.getString(4));
                vehicles.add(vehicleRented);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return vehicles.toArray(new VehicleRented[vehicles.size()]);
    }

    // total numbers of cars rented for a specific day by category
    public TotalCatModel[] totalCatgeory(Date date) {
        ArrayList<TotalCatModel> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.vtname, Count(*)" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ?" +
                    "GROUP BY v.vtname");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                TotalCatModel branchRental = new TotalCatModel();
                branchRental.setVtname(resultSet.getString(1));
                branchRental.setCount(resultSet.getInt(2));
                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new TotalCatModel[branchRentals.size()]);
    }

    // total car rentals for a specific day
    public int totalRental(Date date) {
        int cnt = 0;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Count(*) FROM Rental WHERE fromDate = ?");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            cnt = resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return cnt;
    }

    // total numbers of cars rented for a specific day by branch
    public TotalBranchModel[] totalBranch(Date date) {
        ArrayList<TotalBranchModel> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.city, v.location, Count(*)" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ?" +
                    "GROUP BY v.city, v.location");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                TotalBranchModel branchRental = new TotalBranchModel();
                branchRental.setCity(resultSet.getString(1));
                branchRental.setLocation(resultSet.getString(2));
                branchRental.setCount(resultSet.getInt(3));
                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new TotalBranchModel[branchRentals.size()]);
    }


    /*
    a list of rentals that have not been returned
     */
    public RentalModel[] rentedNotReturned() {
        ArrayList<RentalModel> rentalModels = new ArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Rental r WHERE r.rid NOT IN (SELECT r2.rid FROM Return r2)");

            while(rs.next()) {
                RentalModel rentalModel = new RentalModel(rs.getInt(1),
						rs.getString("vlicense"),
						rs.getString("dlicense"),
						rs.getDate("fromDate"),
						rs.getTime("fromTime"),
						rs.getDate("toDate"),
						rs.getTime("toTime"),
						rs.getDouble("odometer"),
						rs.getString("cardName"),
						rs.getString("cardNo"),
						rs.getDate("expDate"),
						rs.getInt("confNo"));
                rentalModels.add(rentalModel);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return rentalModels.toArray(new RentalModel[rentalModels.size()]);
    }

    /*
    return report
     */

    // detail info of vehicles being returned on a particular day
    public VehicleRented[] getReturn(Date date) {
        ArrayList<VehicleRented> vehicles = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.vlicense, v.make, v.model, v.vtname, v.city, v.location" +
                    "FROM Vehicle v, Rental r, Return t" +
                    "WHERE v.vlicense = r.vlicense AND r.rid = t.rid AND r.fromDate = ?" +
                    "ORDER BY v.city, v.location, v.vtname");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            while(resultSet.next()) {
                VehicleRented vehicleRented = new VehicleRented();
                vehicleRented.setVlicense(resultSet.getString(1));
                vehicleRented.setMake(resultSet.getString(2));
                vehicleRented.setModel(resultSet.getString(3));
                vehicleRented.setVtname(resultSet.getString(4));
                vehicles.add(vehicleRented);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return vehicles.toArray(new VehicleRented[vehicles.size()]);
    }

    // detail info of vehicles being returned on a particular day for a specific branch
    public VehicleRented[] getReturnBranch(Date date, String city, String location) {
        ArrayList<VehicleRented> vehicles = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.vlicense, v.make, v.model, v.vtname, v.city, v.location" +
                    "FROM Vehicle v, Rental r, Return t" +
                    "WHERE v.vlicense = r.vlicense AND r.rid = t.rid AND r.fromDate = ? AND v.city = ? AND v.location = ?" +
                    "ORDER BY v.city, v.location, v.vtname");
            ps.setDate(1,date);
            ps.setString(2, city);
            ps.setString(3, location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            while(resultSet.next()) {
                VehicleRented vehicleRented = new VehicleRented();
                vehicleRented.setVlicense(resultSet.getString(1));
                vehicleRented.setMake(resultSet.getString(2));
                vehicleRented.setModel(resultSet.getString(3));
                vehicleRented.setVtname(resultSet.getString(4));
                vehicles.add(vehicleRented);
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return vehicles.toArray(new VehicleRented[vehicles.size()]);
    }

    public RevenueBranch[] revenueBranch(Date date) {
        ArrayList<RevenueBranch> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT city, location, Count(*), Sum(rev) AS Total"+
                    "FROM (SELECT v.city AS city, v.location AS location, vt.drate*(rt.rDate-ra.fromDate) AS rev"+
                    "FROM Vehicle v, Rental r, VehicleType vt"+
                    "WHERE v.vtname = vt.vtname AND r.vlicense = v.vlicense AND rt.rDate = ?)"+
                    "GROUP BY city, location");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                RevenueBranch branchRental = new RevenueBranch();
                branchRental.setCity(resultSet.getString(1));
                branchRental.setLocation(resultSet.getString(2));
                branchRental.setCount(resultSet.getInt(3));
                branchRental.setRevenue(resultSet.getInt(4));
                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new RevenueBranch[branchRentals.size()]);
    }

    public Pair<Integer, Integer> totalRevenue(Date date) {
        Pair<Integer,Integer> cntRevenue = new Pair<>(0,0);
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT Count(*), Sum(rev) AS Total"+
                    "FROM (SELECT vt.drate*(rt.rDate-ra.fromDate) AS rev"+
                    "FROM Vehicle v, Rental r, VehicleType vt"+
                    "WHERE v.vtname = vt.vtname AND r.vlicense = v.vlicense AND rt.rDate = ?)");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            cntRevenue = new Pair<>(resultSet.getInt(1),resultSet.getInt(2));
            } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return cntRevenue;
    }

    public RevenueCat[] revenueCat(Date date) {
        ArrayList<RevenueCat> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT vtname, Count(*), Sum(rev) AS Total"+
                    "FROM (SELECT v.vtname AS vtname, vt.drate*(rt.rDate-ra.fromDate) AS rev"+
                    "FROM Vehicle v, Rental r, VehicleType vt"+
                    "WHERE v.vtname = vt.vtname AND r.vlicense = v.vlicense AND rt.rDate = ?)"+
                    "GROUP BY vtname");
            ps.setDate(1,date);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                RevenueCat branchRental = new RevenueCat();
                branchRental.setVtname(resultSet.getString(1));
                branchRental.setCount(resultSet.getInt(2));
                branchRental.setRevenue(resultSet.getInt(3));
                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new RevenueCat[branchRentals.size()]);
    }

    public RevenueBranch getRevenueBranch(Date date, String city, String location) {
        RevenueBranch branchRental = new RevenueBranch();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT city, location, Count(*), Sum(rev) AS Total"+
                    "FROM (SELECT v.city AS city, v.location AS location, vt.drate*(rt.rDate-ra.fromDate) AS rev"+
                    "FROM Vehicle v, Rental r, VehicleType vt"+
                    "WHERE v.vtname = vt.vtname AND r.vlicense = v.vlicense AND rt.rDate = ? AND city = ? AND location = ?)"+
                    "GROUP BY city, location");
            ps.setDate(1,date);
            ps.setString(2,city);
            ps.setString(3,location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            branchRental.setCity(resultSet.getString(1));
            branchRental.setLocation(resultSet.getString(2));
            branchRental.setCount(resultSet.getInt(3));
            branchRental.setRevenue(resultSet.getInt(4));
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRental;
    }

    public RevenueBranchCat[] getRevenueBranchCat(Date date, String city, String location) {
        ArrayList<RevenueBranchCat> branchRentals = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT city, location, vtname, Count(*), Sum(rev) AS Total"+
                    "FROM (SELECT v.city AS city, v.location AS location, v.vtname AS vtname, vt.drate*(rt.rDate-ra.fromDate) AS rev"+
                    "FROM Vehicle v, Rental r, VehicleType vt"+
                    "WHERE v.vtname = vt.vtname AND r.vlicense = v.vlicense AND rt.rDate = ? AND city = ? AND location = ?)"+
                    "GROUP BY city, location, vtname");
            ps.setDate(1,date);
            ps.setString(2,city);
            ps.setString(3,location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                RevenueBranchCat branchRental = new RevenueBranchCat();
                branchRental.setCity(resultSet.getString(1));
                branchRental.setLocation(resultSet.getString(2));
                branchRental.setVtname(resultSet.getString(3));
                branchRental.setCount(resultSet.getInt(4));
                branchRental.setRevenue(resultSet.getInt(5));
                branchRentals.add(branchRental);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRentals.toArray(new RevenueBranchCat[branchRentals.size()]);
    }

    // TODO: we cannot know the details about odometer & fullTank when the car is returned, maybe we should not display them?
    /*
    when a car is returned, I will first record the rentalID and returnDate into the return table, and then print out the receipt for
    the customer, using dRate (retrieved from VehicleType) * (returnDate - rental.fromDate)
     */
    public ReturnResult returnVehicle(int rid, Date date) {
        ReturnResult returnResult = new ReturnResult();
        if (compareDates(rid, date)) {
            ReturnModel returnModel = new ReturnModel();
            returnResult.setReturnDate(date);
            returnModel.setrDate(date);
            returnModel.setRid(rid);
            insertReturn(returnModel);
            try {
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(
                        "SELECT ra.confNum, ra.fromDate, rt.rdate, vt.drate, rt.rdate-ra.fromDate, vt.drate*(rt.rdate-ra.fromDate) AS total" +
                                "FROM Rental ra, Return rt, VehicleType vt, Vehicle v" +
                                "WHERE v.vlicense = ra.vlicense AND v.vtname = vt.vtname AND rt.rid = ra.rid");
                returnResult.setConfNum(resultSet.getInt(1));
                returnResult.setFromDate(resultSet.getDate(2));
                returnResult.setReturnDate(resultSet.getDate(3));
                returnResult.setDaysRent(resultSet.getInt(5));
                returnResult.setdRate(resultSet.getInt(4));
                returnResult.setPrice(resultSet.getInt(6));

                resultSet.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            }
        } else {
            //TODO: throw an exception
            System.out.println("Please select a return date earlier than the rental date!");
        }
        return returnResult;
    }

    public boolean compareDates(int rid, Date date) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT fromDate FROM Rental WHERE rid = ?");
            ps.setInt(1, rid);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();
            Date fromDate = resultSet.getDate("fromDate");
            if (fromDate.compareTo(date)<0) {
                return true;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return false;
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

	public ReservationModel findReservation(int confNum) throws InvalidReservationException {
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
				throw new InvalidReservationException("reservation confNo does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
		try {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public VehicleTypeModel[] getAllVehicleTypes() {
    	ArrayList<VehicleTypeModel> searchResults = new ArrayList<>();
    	try {
    		PreparedStatement ps = connection.prepareStatement("SELECT * from VehicleType");
    		ResultSet rs = ps.executeQuery();

    		while (rs.next()) {
    			VehicleTypeModel vt = new VehicleTypeModel(rs.getString("vtname"),
						rs.getString("features"),
						rs.getDouble("wrate"),
						rs.getDouble("drate"),
						rs.getDouble("hrate"),
						rs.getDouble("wirate"),
						rs.getDouble("dirate"),
						rs.getDouble("hirate"),
						rs.getDouble("krate"));
    			searchResults.add(vt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResults.toArray(new VehicleTypeModel[searchResults.size()]);
	}

	public Vehicles[] getAllVehicles() {
		ArrayList<Vehicles> searchResults = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * from Vehicle");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vehicles.Status s = Vehicles.Status.AVAILABLE;
				if (rs.getString("status").equals("rented")) s = Vehicles.Status.RENTED;
				else if (rs.getString("status").equals("maintenance")) s = Vehicles.Status.MAINTENANCE;
				Vehicles v = new Vehicles(rs.getString("vlicense"),
						rs.getString("make"),
						rs.getString("model"),
						rs.getInt("year"),
						rs.getString("color"),
						rs.getDouble("odometer"),
						s,
						rs.getString("vtname"),
						rs.getString("location"),
						rs.getString("city"));
				searchResults.add(v);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResults.toArray(new Vehicles[searchResults.size()]);
	}
}
