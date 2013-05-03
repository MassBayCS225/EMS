package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import static EMS_Database.InitDB.debugLog;
import EMS_Database.InputIncome;
import EMS_Database.Interface_BudgetData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author mike
 */
public class Income_Table extends InitDB implements Interface_BudgetData {

    //SPECIAL FUNCTIONS
    @Override
    public ArrayList<Integer> currentUIDList() {
	int newUID = 0;
	ArrayList<Integer> UIDList = new ArrayList<Integer>();
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		newUID = rs.getInt("UID");
		UIDList.add(newUID);
	    }
	    return UIDList;

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	return UIDList; // should not be zero
    }

    @Override
    public int nextValidUID() {
	int newUID = 0;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		newUID = rs.getInt("UID");
		//System.out.println(newUID);
	    }
	    return (newUID + 1);

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	return newUID; // should not be zero
    }

    @Override
    public String queryEntireTable() {
	StringBuilder returnQuery = new StringBuilder();
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		returnQuery.append(rs.getInt("UID"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("DESCRIPTION"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("DATE"));
		returnQuery.append(",");
		returnQuery.append(rs.getDouble("VALUE"));
		returnQuery.append("\n");
	    }

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}

	return returnQuery.toString();
    }

    @Override
    public void removeBudgetItem(int uid) throws DoesNotExistException {
	boolean exists = false;
	for (int users : currentUIDList()) {
	    if (uid == users) {
		exists = true;
	    }
	}
	if (exists = false) {
	    debugLog.log(Level.WARNING, "Cannot remove user UID={0}", uid);
	    throw new DoesNotExistException("Cannot remove user UID=" + uid);
	}
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM INCOME WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    idQueryStmt.executeUpdate();

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    throw new DoesNotExistException("User does not exist.");
	}
    }

    public int insertBudgetItem(InputIncome input) {
	int newUID = nextValidUID();
	try {
	    //Creating Statement
	    PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO INCOME VALUES(?,?,?,?)");
	    AddAddressStmt.setInt(1, newUID);
	    AddAddressStmt.setString(2, input.getDescription());
	    AddAddressStmt.setTimestamp(3, input.getDate());
	    AddAddressStmt.setDouble(4, input.getValue());
	    //Execute Statement
	    AddAddressStmt.executeUpdate();

	    //checking to see if UID already exists.
	    for (int uid : currentUIDList()) {
		if (newUID == uid) {
		    throw new DuplicateInsertionException("Income table error"); //should not get here.
		}
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	} finally {
	    return newUID;
	}
    }

    @Override
    public double total() {
	double returnQuery = 0.0;
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME");
	    ResultSet rs = idQueryStmt.executeQuery();
	    while (rs.next()) {
		returnQuery += rs.getDouble("VALUE");
	    }
	    return returnQuery;

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	return returnQuery;
    }

    //GETTERS
    @Override
    public String getDescription(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("DESCRIPTION"); //Should not have two uids with the same name                            
	    }
	    return returnQuery;

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	debugLog.warning("UID=" + uid + " does not exist in INCOME table.");
	return null;
    }

    @Override
    public double getValue(int uid) throws DoesNotExistException {
	double returnQuery = -1.0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getDouble("VALUE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if (returnQuery == -1.0) {
		debugLog.warning("UID="+uid+" does not exist in INCOME table.");
                throw new DoesNotExistException("UID does not exist in INCOME table.");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
	debugLog.warning("UID="+uid+" does not exist in INCOME table.");
        throw new DoesNotExistException("UID does not exist in INCOME table.");
    }

    @Override
    public Timestamp getDate(int uid) throws DoesNotExistException {
	Timestamp returnQuery = null;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM INCOME WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		//UNAME = coulmn name.
		returnQuery = rs.getTimestamp("DATE"); //Should not have two uids with the same name                            
	    }

	    //checking if that uid exists	  
	    if (returnQuery == null) {
		debugLog.warning("UID=" + uid + " does not exist in INCOME table.");
		throw new DoesNotExistException("expense date");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	debugLog.warning("UID=" + uid + " does not exist in INCOME table.");
	return null;
    }
    
    

    //SETTERS
    @Override
    public void setDescription(int uid, String description) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE INCOME SET DESCRIPTION=? WHERE UID=?");
		idQueryStmt.setString(1, description);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in INCOME table.", uid);
		throw new DoesNotExistException("User does not exist in INCOME table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in INCOME table.");
	    throw new DoesNotExistException("User does not exist in INCOME table.");
	}
    }

    @Override
    public void setValue(int uid, double value) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE INCOME SET VALUE=? WHERE UID=?");
		idQueryStmt.setDouble(1, value);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in INCOME table.", uid);
		throw new DoesNotExistException("User does not exist in INCOME table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in INCOME table.");
	    throw new DoesNotExistException("User does not exist in INCOME table.");
	}	
    }

    @Override
    public void setDate(int uid, Timestamp date) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE INCOME SET DATE=? WHERE UID=?");
		idQueryStmt.setTimestamp(1, date);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in INCOME table.", uid);
		throw new DoesNotExistException("User does not exist in INCOME table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in INCOME table.");
	    throw new DoesNotExistException("User does not exist in INCOME table.");
	}
    }
        
}
