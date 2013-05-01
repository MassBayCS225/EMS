package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import static EMS_Database.InitDB.debugLog;
import EMS_Database.InputSubEventData;
import EMS_Database.Interface_SubEventData;
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
public class SubEvent_Table extends InitDB implements Interface_SubEventData {

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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS");
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

    /**
     * Inserts a new row into SubEvent Table based on the parameters of InputSubEventData
     * @param subevent the collected data to be inserted
     * @return an int of the UID upon successful creation.
     * @throws DuplicateInsertionException if something goes horribly wrong.
     */
    @Override
    public int createSubEvent(InputSubEventData subevent) throws DuplicateInsertionException {
        int newUID = nextValidUID();

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO SUBEVENTS VALUES(?,?,?,?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, newUID);
            AddAddressStmt.setString(2, subevent.getDescription());
            AddAddressStmt.setInt(3, subevent.getComplete());
            AddAddressStmt.setString(4, subevent.getStreet());
            AddAddressStmt.setString(5, subevent.getCity());
            AddAddressStmt.setString(6, subevent.getState());
            AddAddressStmt.setString(7, subevent.getZipcode());
            AddAddressStmt.setString(8, subevent.getCountry());
            AddAddressStmt.setTimestamp(9, subevent.getStartTime());
            AddAddressStmt.setTimestamp(10, subevent.getEndTime());

            //Execute Statement
            AddAddressStmt.executeUpdate();
            
            for(int uid : currentUIDList()){
                if(newUID == uid){
                    throw new DuplicateInsertionException("SubEventsTable");
                }
            }

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS");
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

    /**
     * A debug function to display the entire contents of this table
     * @return the entire contents of this table as a string.
     */
    @Override
    public String queryEntireTable() {
        StringBuilder returnQuery = new StringBuilder();
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS");
            ResultSet rs = idQueryStmt.executeQuery();

            while (rs.next()) {
                returnQuery.append(rs.getString("UID"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("DESCRIPTION"));
                returnQuery.append(",");
                returnQuery.append(rs.getInt("COMPLETE"));
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
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }

    /**
     * This function removes a subevent specified by the UID.
     * @param uid the UID of the subevent to be removed.
     * @return a boolean. true if removal was successful.
     * @throws DoesNotExistException if the uid does not exist in the table.
     */
    @Override
    public boolean removeSubEvent(int uid) throws DoesNotExistException {
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM SUBEVENT WHERE UID=?");
            idQueryStmt.setInt(1, uid);            
            idQueryStmt.executeUpdate();                        
            
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new DoesNotExistException("User does not exist.");
        }
        return true;
    }
    
    
    

    ////////////////////////GETTERS/////////////////////////
    @Override
    public String getDescription(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("DESCRIPTION"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getStreet(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("STREET"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getCity(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("CITY"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getState(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("STATE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getZipcode(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("ZIPCODE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getCountry(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("COUNTRY"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public Timestamp getStartDate(int uid) throws DoesNotExistException {
        Timestamp returnQuery = null;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getTimestamp("STARTDATE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if (returnQuery == null) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public Timestamp getEndDate(int uid) throws DoesNotExistException {
        Timestamp returnQuery = null;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getTimestamp("ENDDATE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if (returnQuery == null) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public int getComplete(int uid) throws DoesNotExistException {
        int returnQuery = 3;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM SUBEVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getInt("COMPLETE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if (returnQuery == 3) {
                throw new DoesNotExistException("SubEvent");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("SubEvent");
    }
    
    
    

    /////////////////////SETTERS////////////////////////////
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET DESCRIPTION=? WHERE UID=?");
		idQueryStmt.setString(1, description);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET STREET=? WHERE UID=?");
		idQueryStmt.setString(1, street);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET CITY=? WHERE UID=?");
		idQueryStmt.setString(1, city);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET STATE=? WHERE UID=?");
		idQueryStmt.setString(1, state);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET ZIPCODE=? WHERE UID=?");
		idQueryStmt.setString(1, zipcode);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET COUNTRY=? WHERE UID=?");
		idQueryStmt.setString(1, country);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET STARTDATE=? WHERE UID=?");
		idQueryStmt.setTimestamp(1, time);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET ENDDATE=? WHERE UID=?");
		idQueryStmt.setTimestamp(1, time);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
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
		PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE SUBEVENTS SET COMPLETE=? WHERE UID=?");
		idQueryStmt.setInt(1, complete);
		idQueryStmt.setInt(2, uid);
		idQueryStmt.executeUpdate();
	    } else {
		debugLog.log(Level.WARNING, "UID={0} does not exist in SUBEVENT table.", uid);
		throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	    }
	} catch (SQLException sqle) {
	    System.err.println(sqle.getMessage());
	    debugLog.severe("Major SQL-Error in SUBEVENT table.");
	    throw new DoesNotExistException("User does not exist in SUBEVENT table.");
	}
    }
}
