package EMS_Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Mike Meding
 *
 * Creates a valid connection to the database server. Sets up database framework
 * if none exists.
 *
 * Needs a connection URL, user and password.
 */
public abstract class InitDB implements Interface_FunctionWrapper {

    protected Connection dbConnection = null;
    public final static Logger debugLog = Logger.getLogger("DebugLog");
    private static FileHandler fh = null;

    static { // Setup logging file        
	try {
	    fh = new FileHandler("debug.log", false);
	    CloseLogger ch = new CloseLogger(fh);
	    Thread t = new Thread(ch);
	    Runtime.getRuntime().addShutdownHook(t);

	} catch (SecurityException e) {
	    System.err.println(e.getMessage());
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	}
	fh.setFormatter(new SimpleFormatter());
	debugLog.addHandler(fh);
	debugLog.setUseParentHandlers(false); //do not use default outputs (console and such)


    }

    public InitDB() {



	// define database properties for connection
	Properties props = new Properties();
	props.put("user", "root");
	props.put("password", "ccaes1");

	// Driver name
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
	    //System.out.println("Database Connection Established.");
	    //debugLog.info("Database Connection Established.");
	} catch (SQLException sqle) {

	    try {
		//create connection if no database exists
		dbConnection = DriverManager.getConnection("jdbc:derby:EMS_DB;create=true", props);
		System.out.println("Database Created Successfully.");
		debugLog.info("Database Created Successfully.");

		//create tables if none exist.
		String createUserTable = "CREATE TABLE USERS (UID INT PRIMARY KEY, "
			+ "LEVEL INT, "
			+ "FNAME VARCHAR(50) DEFAULT NULL, "
			+ "LNAME VARCHAR(50) DEFAULT NULL, "
			+ "PWD VARCHAR(256) NOT NULL, "
			+ "EMAIL VARCHAR(256) NOT NULL, "
			+ "PHONE VARCHAR(30) DEFAULT NULL, "
			+ "STREET VARCHAR(100) DEFAULT NULL, "
			+ "CITY VARCHAR(100) DEFAULT NULL, "
			+ "STATE VARCHAR(50) DEFAULT NULL, "
			+ "ZIPCODE VARCHAR(20) DEFAULT NULL, "
			+ "COUNTRY VARCHAR(100) DEFAULT NULL, "
			+ "PARTICIPANT VARCHAR(100) DEFAULT NULL, "
			+ "EVENTLEVEL INT NOT NULL)";

		String createEventsTable = "CREATE TABLE EVENTS (UID INT PRIMARY KEY, "
			+ "DESCRIPTION VARCHAR(5000) DEFAULT NULL, "
			+ "DETAILS VARCHAR(500) DEFAULT NULL, "
			+ "STARTDATE TIMESTAMP, "
			+ "ENDDATE TIMESTAMP, "
			+ "COMPLETE INT, "
			+ "STREET VARCHAR(100) DEFAULT NULL, "
			+ "CITY VARCHAR(100) DEFAULT NULL, "
			+ "STATE VARCHAR(50) DEFAULT NULL, "
			+ "ZIPCODE VARCHAR(20) DEFAULT NULL, "
			+ "COUNTRY VARCHAR(100) DEFAULT NULL, "
			+ "ORGANIZER VARCHAR(160) DEFAULT NULL, " //organizer list
			+ "SUBEVENT VARCHAR(160) DEFAULT NULL, " //sub-event list
			+ "PARTICIPANT VARCHAR(500) DEFAULT NULL, " //participant list
			+ "COMMITTEE VARCHAR(160) DEFAULT NULL)"; //committee list

		String createSubEventTable = "CREATE TABLE SUBEVENTS (UID INT PRIMARY KEY, "
			+ "DESCRIPTION VARCHAR(5000) DEFAULT NULL, "
			+ "DETAILS VARCHAR(500) DEFAULT NULL, "
			+ "COMPLETE INT, "
			+ "STREET VARCHAR(100) DEFAULT NULL, "
			+ "CITY VARCHAR(100) DEFAULT NULL, "
			+ "STATE VARCHAR(50) DEFAULT NULL, "
			+ "ZIPCODE VARCHAR(20) DEFAULT NULL, "
			+ "COUNTRY VARCHAR(100) DEFAULT NULL, "
			+ "STARTDATE TIMESTAMP, "
			+ "ENDDATE TIMESTAMP)";

		String createCommitteeTable = "CREATE TABLE COMMITTEE (UID INT PRIMARY KEY, "
			+ "TITLE VARCHAR(160) DEFAULT NULL, "
			+ "CHAIRMAN INT, "
			+ "BUDGETACCESS VARCHAR(1000) DEFAULT NULL, " //list of UID #'s
			+ "MEMBERS VARCHAR(1000) DEFAULT NULL, " //list of UID #'s
			+ "TASKS VARCHAR(1000) DEFAULT NULL, " //list of task UID #'s
			+ "INCOME VARCHAR(1000) DEFAULT NULL, " //list of task UID #'s
			+ "EXPENSE VARCHAR(1000) DEFAULT NULL, " //list of task UID #'s						
			+ "BUDGET DOUBLE)";

		String createTasksTable = "CREATE TABLE TASKS (UID INT PRIMARY KEY, "
			+ "DESCRIPTION VARCHAR(5000) DEFAULT NULL, "
			+ "DETAILS VARCHAR(500) DEFAULT NULL, "
			+ "STREET VARCHAR(100) DEFAULT NULL, "
			+ "CITY VARCHAR(100) DEFAULT NULL, "
			+ "STATE VARCHAR(50) DEFAULT NULL, "
			+ "ZIPCODE VARCHAR(20) DEFAULT NULL, "
			+ "COUNTRY VARCHAR(100) DEFAULT NULL, "
			+ "STARTDATE TIMESTAMP, "
			+ "ENDDATE TIMESTAMP, "
			+ "COMPLETE INT, "
			+ "MANAGER VARCHAR(160) DEFAULT NULL)"; //users in charge of task

		String createIncomeTable = "CREATE TABLE INCOME (UID INT PRIMARY KEY, "
			+ "DESCRIPTION VARCHAR(1000) DEFAULT NULL, "
			+ "DATE TIMESTAMP, "
			+ "VALUE DOUBLE)";

		String createExpenseTable = "CREATE TABLE EXPENSE (UID INT PRIMARY KEY, "
			+ "DESCRIPTION VARCHAR(1000) DEFAULT NULL, "
			+ "DATE TIMESTAMP, "
			+ "VALUE DOUBLE)";

		Statement stmt = dbConnection.createStatement();
		stmt.executeUpdate(createUserTable); //takes table string as argument
		debugLog.info("USER table created successfully");
		stmt.executeUpdate(createEventsTable);
		debugLog.info("EVENT table created successfully");
		stmt.executeUpdate(createSubEventTable);
		debugLog.info("SUBEVENT table created successfully");
		stmt.executeUpdate(createCommitteeTable);
		debugLog.info("COMMITTEE table created successfully");
		stmt.executeUpdate(createTasksTable);
		debugLog.info("TASKS table created successfully");
		stmt.executeUpdate(createIncomeTable);
		debugLog.info("INCOME table created successfully");
		stmt.executeUpdate(createExpenseTable);
		debugLog.info("EXPENSE table created successfully");


	    } catch (SQLException sqlee) { //serious errors if this gets thrown
		sqlee.printStackTrace();
		debugLog.severe("TABLE CREATION FAILED!");
	    }

	}

    }

    private static class CloseLogger implements Runnable {

	private final FileHandler fh;

	public CloseLogger(FileHandler fh) {
	    this.fh = fh;
	}

	@Override
	public void run() {
	    fh.flush();
	    fh.close();
	    //System.out.println("closed logger");
	}
    }

    public Connection getConnection() {
	return dbConnection;
    }

    /////////////////////////////SPECIAL FUNCTIONS/////////////////////////////////
    /**
     * A function to generate a list of the current UID's in a table
     *
     * @return ArrayList<Integer> of the current UID's in the table
     */
    public ArrayList<Integer> currentUIDList(String table) {
	int newUID = 0;
	ArrayList<Integer> UIDList = new ArrayList<Integer>();
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table);
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

    public ArrayList<Integer> stringToList(String uidList) throws NumberFormatException {
	if (uidList.equals("") || uidList == null) {
	    return new ArrayList<Integer>();
	} else {
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
    }

    // FUNCTION WRAPPERS. USED TO CALL METHODS.
    @Override
    public String getDBString(String query, String table, int uid) throws DoesNotExistException {
	//checking for existance of that uid
	boolean exists = false;
	for (int validID : currentUIDList(table)) {
	    if (validID == uid) {
		exists = true;
		break;
	    }
	}
	if (exists == false) {
	    debugLog.log(Level.WARNING, "UID={0} does not exist in {1} table. Error occurred while calling {2}", new Object[]{uid, table, query});
	    throw new DoesNotExistException("check debug log. " + table + " table error.");
	}
	//executing query
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    String returnQuery = null;
	    while (rs.next()) {
		returnQuery = rs.getString(query); //Should not have two uids with the same name                            
	    }
	    return returnQuery; //should always return here.

	} catch (SQLException sqle) {
	    debugLog.log(Level.SEVERE, "SERIOUS DATABASE ERROR IN " + table + " TABLE.");
	    sqle.printStackTrace();
	    System.exit(1);
	}
	throw new DoesNotExistException("Should not get here...");
    }

    @Override
    public double getDBDouble(String query, String table, int uid) throws DoesNotExistException {
	//checking for existance of that uid
	boolean exists = false;
	for (int validID : currentUIDList(table)) {
	    if (validID == uid) {
		exists = true;
		break;
	    }
	}
	if (exists == false) {
	    debugLog.log(Level.WARNING, "UID={0} does not exist in {1} table. Error occurred while calling {2}", new Object[]{uid, table, query});
	    throw new DoesNotExistException("check debug log. " + table + " table error.");
	}
	//executing query
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    double returnQuery = 0.0;
	    while (rs.next()) {
		returnQuery = rs.getDouble(query); //Should not have two uids with the same name                            
	    }
	    return returnQuery; //should always return here.

	} catch (SQLException sqle) {
	    debugLog.log(Level.SEVERE, "SERIOUS DATABASE ERROR IN " + table + " TABLE.");
	    sqle.printStackTrace();
	    System.exit(1);
	}
	throw new DoesNotExistException("Should not get here...");
    }

    @Override
    public ArrayList<Integer> getDBArrayList(String query, String table, int uid) throws DoesNotExistException {
	//checking for existance of that uid
	boolean exists = false;
	for (int validID : currentUIDList(table)) {
	    if (validID == uid) {
		exists = true;
		break;
	    }
	}
	if (exists == false) {
	    debugLog.log(Level.WARNING, "UID={0} does not exist in {1} table. Error occurred while calling {2}", new Object[]{uid, table, query});
	    throw new DoesNotExistException("check debug log. " + table + " table error.");
	}
	//executing query
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    String returnQuery = "";
	    while (rs.next()) {
		returnQuery = rs.getString(query); //Should not have two uids with the same name                            
	    }
	    return stringToList(returnQuery); //should always return here.

	} catch (SQLException sqle) {
	    debugLog.log(Level.SEVERE, "SERIOUS DATABASE ERROR IN " + table + " TABLE.");
	    sqle.printStackTrace();
	    System.exit(1);
	}
	throw new DoesNotExistException("Should not get here...");
    }

    @Override
    public int getDBInt(String query, String table, int uid) throws DoesNotExistException {
	//checking for existance of that uid
	boolean exists = false;
	for (int validID : currentUIDList(table)) {
	    if (validID == uid) {
		exists = true;
		break;
	    }
	}
	if (exists == false) {
	    debugLog.log(Level.WARNING, "UID={0} does not exist in {1} table. Error occurred while calling {2}", new Object[]{uid, table, query});
	    throw new DoesNotExistException("check debug log. " + table + " table error.");
	}
	//executing query
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    int returnQuery = 0;
	    while (rs.next()) {
		returnQuery = rs.getInt(query); //Should not have two uids with the same name                            
	    }
	    return returnQuery; //should always return here.

	} catch (SQLException sqle) {
	    debugLog.log(Level.SEVERE, "SERIOUS DATABASE ERROR IN " + table + " TABLE.");
	    sqle.printStackTrace();
	    System.exit(1);
	}
	throw new DoesNotExistException("Should not get here...");
    }

    @Override
    public Timestamp getDBTimestamp(String query, String table, int uid) throws DoesNotExistException {
	//checking for existance of that uid
	boolean exists = false;
	for (int validID : currentUIDList(table)) {
	    if (validID == uid) {
		exists = true;
		break;
	    }
	}
	if (exists == false) {
	    debugLog.log(Level.WARNING, "UID={0} does not exist in {1} table. Error occurred while calling {2}", new Object[]{uid, table, query});
	    throw new DoesNotExistException("check debug log. " + table + " table error.");
	}
	//executing query
	try {
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM " + table + " WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    Timestamp returnQuery = new Timestamp(new Date().getTime()); //using current time as default.
	    while (rs.next()) {
		returnQuery = rs.getTimestamp(query); //Should not have two uids with the same name                            
	    }
	    return returnQuery; //should always return here.

	} catch (SQLException sqle) {
	    debugLog.log(Level.SEVERE, "SERIOUS DATABASE ERROR IN " + table + " TABLE.");
	    sqle.printStackTrace();
	    System.exit(1);
	}
	throw new DoesNotExistException("Should not get here...");
    }
}
