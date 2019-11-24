package ca.ubc.cs304.database;

import ca.ubc.cs304.model.*;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

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
			ps.setFloat(6, model.getOdometer());
			if (model.getStatus() == VehicleModel.Status.RENTED) {
				ps.setString(7, "Rented");
			} else if (model.getStatus() == VehicleModel.Status.MAINTENANCE) {
				ps.setString(7, "Maintenance");
			} else {
				ps.setString(7, "Available");
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
			ps.setFloat(3, model.getWrate());
			ps.setFloat(4, model.getDrate());
			ps.setFloat(5, model.getHrate());
			ps.setFloat(6, model.getWirate()); // TODO: check that Wirate and dirate are added to sql script
			ps.setFloat(7, model.getDirate());
			ps.setFloat(8, model.getHirate());
			ps.setFloat(9, model.getKrate());

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
			ps.setInt(8, model.getOdometer());
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
			ps.setDate(2, model.getrDate());
			ps.setTime(3, model.getTime());
			ps.setInt(4, model.getOdometer());
			ps.setBoolean(5, model.getFullTank()); // TODO: make sql a boolean
			ps.setFloat(6, model.getValue());

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

	// TODO: write insert for the user model once we figure out waht to name it.
	
	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
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
				BranchModel model = new BranchModel(rs.getString("location"), rs.getString("city"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new BranchModel[result.size()]);
	}

    public void addCustomerDetails(UserModel userModel) {
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

    public UserModel[] getCustomerDetails() {
	    ArrayList<UserModel> customerDetails = new ArrayList();
	    try {
	        Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");

            while(rs.next()) {
                UserModel userModel = new UserModel(rs.getString("dlicense"), rs.getString("name"), rs.getString("phoneNumber"),
                        rs.getString("address"));
                        customerDetails.add(userModel);
                    }

                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(EXCEPTION_TAG + " " + e.getMessage());
                }
            return customerDetails.toArray(new UserModel[customerDetails.size()]);
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
    public TotalBranchModel totalBranches(Date date, String city, String location) {
        TotalBranchModel branchRental = new TotalBranchModel();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT v.city, v.location, Count(*)" +
                    "FROM Vehicle v, Rental r" +
                    "WHERE v.vlicense = r.vlicense AND r.fromDate = ? AND v.city = ? AND v.location = ?" +
                    "GROUP BY v.city, v.location");
            ps.setDate(1,date);
            ps.setString(2,city);
            ps.setString(3,location);
            ResultSet resultSet = ps.executeQuery();
            connection.commit();

            branchRental.setCity(resultSet.getString(1));
            branchRental.setLocation(resultSet.getString(2));
            branchRental.setCount(resultSet.getInt(3));
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return branchRental;
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
            ResultSet rs = stmt.executeQuery("SELECT r.rid, r.vlicense, r.dlicense, r.confNum, r.fromDate, r.toDate FROM Rental r WHERE r.rid NOT IN (SELECT r2.rid FROM Return r2)");

            while(rs.next()) {
                RentalModel rentalModel = new RentalModel();
                rentalModel.setRid(rs.getInt(1));
                rentalModel.setVlicense(rs.getString(2));
                rentalModel.setDlicense(rs.getString(3));
                rentalModel.setConfNo(rs.getInt(4));
                rentalModel.setFromDate(rs.getDate(5));
                rentalModel.setToDate(rs.getDate(6));
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
                //TODO: change car status to available

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
