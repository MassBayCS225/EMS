package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import static EMS_Database.InitDB.debugLog;
import EMS_Database.InputTask;
import EMS_Database.Interface_TaskData;
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
public class Tasks_Table extends InitDB implements Interface_TaskData {

    ///////////////////////SPECIAL FUNCTIONS///////////////////////////
    /**
     * A special function designed to format integer array lists into a string
     * to be used for actual integer data return from the database.
     *
     * @param uidList A formatted string from the database representing the UID
     * list.
     * @return A nice array list of the UID's from the database.
     * @throws NumberFormatException if you somehow try to put something that
     * cannot be parsed into the conversion string.
     */
    @Override
    public ArrayList<Integer> stringToList(String uidList) throws NumberFormatException {
	//Split String
	String[] uidStringList;
	uidStringList = uidList.split(",");

	ArrayList<Integer> uidIntList = new ArrayList<Integer>();

	//parse each item into arraylist
	for (String uid : uidStringList) {
	    try {
		uidIntList.add(Integer.parseInt(uid));
	    } catch (NumberFormatException nfe) {
		throw new NumberFormatException("Parse Error");
	    }
	}

	return uidIntList;
    }

    /**
     * Does the opposite of string to list and creates a nicely formatted string
     * for insertion into the database.
     *
     * @param list An ArrayList of Integers representing the UID numbers to be
     * stored.
     * @return A nicely formated String for insertion into the database.
     */
    @Override
    public String listToString(ArrayList<Integer> list) {
	StringBuilder returnQuery = new StringBuilder();
	for (int uid : list) {
	    returnQuery.append(uid);
	    returnQuery.append(",");
	}
	return returnQuery.toString();
    }

    /**
     * A function to generate a list of the current UID's in a table
     *
     * @return ArrayList<Integer> of the current UID's in the table
     */
    @Override
    public ArrayList<Integer> currentUIDList() {
	int newUID = 0;
	ArrayList<Integer> UIDList = new ArrayList<Integer>();
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		newUID = rs.getInt("UID");
		UIDList.add(newUID);
	    }
	    return UIDList;

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	return UIDList; // should not be zero
    }

    /**
     * Inserts a new task into the Task table based on the parameters specified
     * by InputTask.
     *
     * @param task The collected data to be inserted into the Task table
     * @return an int of the UID of the created task.
     * @throws DuplicateInsertionException when pigs fly. no really.
     */
    @Override
    public int createTask(InputTask task) throws DuplicateInsertionException {
	int newUID = nextValidUID();

	try {
	    //Creating Statement
	    PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO TASKS VALUES(?,?,?,?,?,?,?,?,?,?,?)");
	    AddAddressStmt.setInt(1, newUID);
	    AddAddressStmt.setString(2, task.getDescripton());
	    AddAddressStmt.setString(3, task.getStreet());
	    AddAddressStmt.setString(4, task.getCity());
	    AddAddressStmt.setString(5, task.getState());
	    AddAddressStmt.setString(6, task.getZipcode());
	    AddAddressStmt.setString(7, task.getCountry());
	    AddAddressStmt.setTimestamp(8, task.getStartDate());
	    AddAddressStmt.setTimestamp(9, task.getEndDate());
	    AddAddressStmt.setInt(10, task.getComplete());
	    AddAddressStmt.setString(11, task.getManager());



	    //Execute Statement
	    AddAddressStmt.executeUpdate();

	    //check for duplicates
	    for (int uid : currentUIDList()) {
		if (newUID == uid) {
		    throw new DuplicateInsertionException("TasksTable");
		}
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage()); //seriously bad...
	} finally {
	    return newUID;
	}
    }

    /**
     * Gets the next valid UID in the Events table
     *
     * @return the next valid UID that should be used.
     */
    @Override
    public int nextValidUID() {
	int newUID = 0;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		newUID = rs.getInt("UID");
		//System.out.println(newUID);
	    }
	    return (newUID + 1);

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	return newUID; // should not be zero
    }

    /**
     * Debug function used to return the entire table as a string.
     *
     * @return the entire table.
     */
    @Override
    public String queryEntireTable() {
	StringBuilder returnQuery = new StringBuilder();
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS");
	    ResultSet rs = idQueryStmt.executeQuery();

	    while (rs.next()) {
		returnQuery.append(rs.getInt("UID"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("DESCRIPTION"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("STREET"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("CITY"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("STATE"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("ZIPCODE"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("COUNTRY"));
		returnQuery.append(",");
		returnQuery.append(rs.getTimestamp("STARTDATE"));
		returnQuery.append(",");
		returnQuery.append(rs.getTimestamp("ENDDATE"));
		returnQuery.append(",");
		returnQuery.append(rs.getInt("COMPLETE"));
		returnQuery.append(",");
		returnQuery.append(rs.getString("MANAGER"));
		returnQuery.append("\n");
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}

	return returnQuery.toString();
    }

    /**
     * This function removes a committee specified by the UID.
     *
     * @param uid the UID of the committee to be removed.
     * @return a boolean. true if removal was successful.
     * @throws DoesNotExistException if the uid does not exist in the table.
     */
    @Override
    public boolean removeTask(int uid) throws DoesNotExistException {
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM TASK WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    idQueryStmt.executeUpdate();

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    throw new DoesNotExistException("User does not exist.");
	}
	return true;
    }

    ///////////////////////////GETTERS/////////////////////////////
    @Override
    public String getDescription(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("DESCRIPTION"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public String getStreet(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("STREET"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public String getCity(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("CITY"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public String getState(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("STATE"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public String getZipcode(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("ZIPCODE"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public String getCountry(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("COUNTRY"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public Timestamp getStartDate(int uid) throws DoesNotExistException {
	Timestamp returnQuery = null;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getTimestamp("STARTDATE"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if (returnQuery.equals(null)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public Timestamp getEndDate(int uid) throws DoesNotExistException {
	Timestamp returnQuery = null;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getTimestamp("ENDDATE"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if (returnQuery.equals(null)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public int getComplete(int uid) throws DoesNotExistException {
	int returnQuery = 3;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getInt("COMPLETE"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if (returnQuery == 3) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return returnQuery;
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    @Override
    public ArrayList<Integer> getAuthority(int uid) throws DoesNotExistException {
	String returnQuery = "";
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM TASKS WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("MANAGER"); //Should not have two uids with the same name                            
	    }

	    //checking for existance of that uid
	    if ("".equals(returnQuery)) {
		throw new DoesNotExistException("UID does not exist in TASKS table.");
	    } else {
		return stringToList(returnQuery);
	    }

	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    System.exit(1);
	}
	throw new DoesNotExistException("UID does not exist in TASKS table.");
    }

    /////////////////////////////SETTERS//////////////////////////////
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET DESCRIPTION=? WHERE UID=?");
		idQueryStmt.setString(1, description);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setStreet(int uid, String street) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET STREET=? WHERE UID=?");
		idQueryStmt.setString(1, street);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setCity(int uid, String city) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET CITY=? WHERE UID=?");
		idQueryStmt.setString(1, city);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setState(int uid, String state) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET STATE=? WHERE UID=?");
		idQueryStmt.setString(1, state);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET ZIPCODE=? WHERE UID=?");
		idQueryStmt.setString(1, zipcode);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setCountry(int uid, String country) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET COUNTRY=? WHERE UID=?");
		idQueryStmt.setString(1, country);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setStartDate(int uid, Timestamp time) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET STARTDATE=? WHERE UID=?");
		idQueryStmt.setTimestamp(1, time);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setEndDate(int uid, Timestamp time) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET ENDDATE=? WHERE UID=?");
		idQueryStmt.setTimestamp(1, time);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setComplete(int uid, int complete) throws DoesNotExistException {
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET COMPLETE=? WHERE UID=?");
		idQueryStmt.setInt(1, complete);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}
    }

    @Override
    public void setAuthority(int uid, ArrayList<Integer> committeeList) throws DoesNotExistException {	
	try {
	    boolean exists = false;
	    for (int validID : currentUIDList()) {
		if (validID == uid) {
		    exists = true;
		    break;
		}
	    }
	    if (exists) {
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE TASKS SET MANAGER=? WHERE UID=?");
		idQueryStmt.setString(1, listToString(committeeList));
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in TASKS table.", uid);
		throw new DoesNotExistException("User does not exist in TASKS table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in TASKS table.");
	    throw new DoesNotExistException("User does not exist in TASKS table.");
	}		
    }	
    
}