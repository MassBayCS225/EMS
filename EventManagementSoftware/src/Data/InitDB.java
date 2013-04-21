package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mike Meding
 * 
 * Creates a valid connection to the database server.
 * Sets up database framework if none exists.
 * 
 * Needs a connection URL, user and password.
 */

public abstract class InitDB {
    public static Connection dbConnection = null;
    
    public static void InitalizeDB(String strUrl) {
        //Connects to local javadb server.
        
        //Just for now...
        //strUrl = "jdbc:derby://localhost:1527/EMS_DB";

        //Hardcoded for now...
        //Should be made secure
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "ccaes1");
        
//        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//
//        // connect to DB driver.
//        try {
//            Class.forName(driver);
//        } catch (ClassNotFoundException cnfe) {
//            cnfe.printStackTrace();
//        }

        // connect to DB
        try {
            dbConnection = DriverManager.getConnection(strUrl , props);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        return dbConnection;
    }
    
    // METHOD TO CREATE TABLES IF TABLES DO NOT EXIST.

}
