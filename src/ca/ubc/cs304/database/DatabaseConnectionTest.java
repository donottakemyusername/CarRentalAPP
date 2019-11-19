package ca.ubc.cs304.database;

import ca.ubc.cs304.model.CustomerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {
    private DatabaseConnectionHandler db;
    private static final String ORACLE_NAME = "ora_bowenwz@stu";
    private static final String ORACLE_PSW = "a42839548";


    @BeforeEach
    void setUp() {
        db = new DatabaseConnectionHandler();
        db.login(ORACLE_NAME,ORACLE_PSW);
    }

    @AfterEach
    void disconnect() {
        db.close();
    }

    @Test
    void customerDetails () {
        CustomerModel customerModel1 = new CustomerModel(123456789, "Jess", "6051233456", "2929 Main Mall");
        db.addCustomerDetails(customerModel1);
        System.out.println(db.getCustomerDetails());
    }
}

