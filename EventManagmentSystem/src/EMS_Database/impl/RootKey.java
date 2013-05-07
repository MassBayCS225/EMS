package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.InitDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mike
 */
public class RootKey extends InitDB {

    /**
     * This table is designed for storage of the password encryption key.
     * @param key the hash key to be stored.
     */
    public void addKey(String key) {
	try {
	    String table = "ROOTKEY";
	    //Creating Statement
	    PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO "+table+" VALUES(?,?)");
	    AddAddressStmt.setInt(1, 1);
	    AddAddressStmt.setString(2, key);
	    //Execute Statement
	    AddAddressStmt.executeUpdate();
	} catch (SQLException sqle) {
	    System.err.println("Seriously dude? This function can only be called once. Call remove key first or rebuild database.");
	}
    }

    /**
     * Pretty self explanitory.
     */
    public void removeKey() {
	String table = "ROOTKEY";
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, 1);
	    idQueryStmt.executeUpdate();

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	}
    }
    /**
     * Also pretty self explanitory,
     * @return the stored key as a String.
     */
    public String getKey() {
	try {
	    return getDBString("PWDKEY", "ROOTKEY", 1);
	} catch (DoesNotExistException dnee) {
	    System.err.println("Getting RootKey Issue...");
	}
	return "Serious Key Issues...";
    }
}
