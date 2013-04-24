package EMS_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Mike Meding
 *
 * Creates a valid connection to the database server. Sets up database framework
 * if none exists.
 *
 * Needs a connection URL, user and password.
 */
public abstract class InitDB {

    protected Connection dbConnection = null;

    public InitDB() {
        
        Properties props = new Properties();
        props.put("user", "root");
        props.put("password", "ccaes1");

        String driver = "org.apache.derby.jdbc.EmbeddedDriver";

        // connect to DB driver.
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }

        // connect to DB if one already exists
        try {
            dbConnection = DriverManager.getConnection("jdbc:derby:EMS_DB", props); //if create is needed exception is thrown                                    
        } catch (SQLException sqle) {

            try {
                //create connection if no database exists
                dbConnection = DriverManager.getConnection("jdbc:derby:EMS_DB;create=true", props);
                System.out.println(dbConnection.toString());

                //create tables if none exist.
                String createUserTable = "CREATE TABLE USERS (UID INT PRIMARY KEY, LEVEL INT NOT NULL, UNAME VARCHAR(50) NOT NULL, PWD VARCHAR(256) NOT NULL, EMAIL VARCHAR(256))";
              
                Statement stmt = dbConnection.createStatement();
                stmt.executeUpdate(createUserTable); //takes table string as argument
                // ADD other tables to be created here when structure is known.
                
                
                

            } catch (SQLException sqlee) { //serious errors if this gets thrown
                sqlee.printStackTrace();
            }

        }
    }

    public Connection getConnection() {
        return dbConnection;
    }
    // METHOD TO CREATE TABLES IF TABLES DO NOT EXIST.
}
