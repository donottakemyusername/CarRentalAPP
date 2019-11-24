//package ca.ubc.cs304.database;
//
//import ca.ubc.cs304.model.UserModel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//public class DatabaseConnectionTest {
//    private DatabaseConnectionHandler db;
//    private static final String ORACLE_NAME = "ora_bowenwz@stu";
//    private static final String ORACLE_PSW = "a42839548";
//
//
//    @BeforeEach
//    void setUp() {
//        db = new DatabaseConnectionHandler();
//        db.login(ORACLE_NAME,ORACLE_PSW);
//    }
//
//    @AfterEach
//    void disconnect() {
//        db.close();
//    }
//
//    @Test
//    void customerDetails () {
//        LocalDate localDate = LocalDate.now();
//        System.out.println(DateTimeFormatter.ofPattern("yyyy/mm/dd").format(localDate));
//    }
//}
//
