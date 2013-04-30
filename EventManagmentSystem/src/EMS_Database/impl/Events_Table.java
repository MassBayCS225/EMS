package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import EMS_Database.Interface_EventData;
import EMS_Database.InputEventData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * A class to control the Events Data.
 *
 * @author mike
 */
public class Events_Table extends InitDB implements Interface_EventData {

    /////////////////////SPECIAL FUNCTIONS///////////////////////////
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
     * Input a new event into the events using InputEvent class to create a
     * valid input object
     *
     * @param event of type InputEvent for row insertion.
     * @return the UID of the created event.
     * @throws DuplicateInsertionException
     */
    @Override
    public int createEvent(InputEventData event) throws DuplicateInsertionException {
        int newUID = nextValidUID();

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO EVENTS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, newUID);
            AddAddressStmt.setString(2, event.getDescription());
            AddAddressStmt.setTimestamp(3, event.getStartDate());
            AddAddressStmt.setTimestamp(4, event.getEndDate());
            AddAddressStmt.setInt(5, event.getComplete());
            AddAddressStmt.setString(6, event.getStreet());
            AddAddressStmt.setString(7, event.getCity());
            AddAddressStmt.setString(8, event.getState());
            AddAddressStmt.setString(9, event.getZipcode());
            AddAddressStmt.setString(10, event.getCountry());
            AddAddressStmt.setString(11, listToString(event.getOrganizerList())); //inserted as a string
            AddAddressStmt.setString(12, listToString(event.getSubEventList())); //inserted as a string
            AddAddressStmt.setString(13, listToString(event.getParticipantList())); //inserted as a string
            AddAddressStmt.setString(14, listToString(event.getCommittee())); //inserted as a string

            //Execute Statement
            AddAddressStmt.executeUpdate();

            //checking to see if UID already exists.
            for (int uid : currentUIDList()) {
                if (newUID == uid) {
                    throw new DuplicateInsertionException("EventsTable");
                }
            }

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        } finally {
            return newUID;
        }
    }

    /**
     * Gets the next vaild UID in the Events table
     *
     * @return the next valid UID that should be used.
     */
    @Override
    public int nextValidUID() {
        int newUID = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS");
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
     * Debug function to return a string to view the entire table contents.
     *
     * @return the entire table as a formatted string.
     */
    @Override
    public String queryEntireTable() {
        StringBuilder returnQuery = new StringBuilder();
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS");
            ResultSet rs = idQueryStmt.executeQuery();

            while (rs.next()) {
                returnQuery.append(rs.getString("UID"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("DESCRIPTION"));
                returnQuery.append(",");
                returnQuery.append(rs.getTimestamp("STARTDATE"));
                returnQuery.append(",");
                returnQuery.append(rs.getTimestamp("ENDDATE"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("COMPLETE"));
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
                returnQuery.append(rs.getString("ORGANIZER"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("SUBEVENT"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("PARTICIPANT"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("COMMITTEE"));
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }

    @Override
    public boolean removeEvent(int uid) throws DoesNotExistException {
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM EVENT WHERE UID=?");
            idQueryStmt.setInt(1, uid);            
            idQueryStmt.executeUpdate();                        
            
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new DoesNotExistException("User does not exist.");
        }
        return true;
    }
    
    
    

    //////////////////////GETTERS////////////////////////////
    /**
     * Returns the specified users description.
     *
     * @param uid the user being searched for
     * @return the description as a String.
     * @throws DoesNotExistException if the user you are searching for does not
     * exist.
     */
    @Override
    public String getDescription(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("DESCRIPTION"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getTimestamp("STARTDATE"); //Should not have two uids with the same name                            
            }
            
            //checking if that uid exists
            if(returnQuery.equals(null)){
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getTimestamp("ENDDATE"); //Should not have two uids with the same name                            
            }
            
            //checking if that uid exists
            if(returnQuery.equals(null)){
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getInt("COMPLETE"); //Should not have two uids with the same name                            
            }
            
            if(returnQuery == 3){
                throw new DoesNotExistException("Event");
            } else {
                return returnQuery;
            }            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return returnQuery; //should never get here.
    }

    @Override
    public String getStreet(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("STREET"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("CITY"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("STATE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("ZIPCODE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("COUNTRY"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("Event");
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
    public ArrayList<Integer> getOrganizerList(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("ORGANIZER"); //Should not have two uids with the same name                            
            }
            
            if("".equals(returnQuery)){
                throw new DoesNotExistException("Event");
            } else {
                return stringToList(returnQuery); //return an arraylist
            }            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return stringToList(returnQuery); //should never get here.
    }

    @Override
    public ArrayList<Integer> getSubEventList(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("SUBEVENT"); //Should not have two uids with the same name                            
            }
            
            if("".equals(returnQuery)){
                throw new DoesNotExistException("Event");
            } else {
                return stringToList(returnQuery); //return an arraylist
            }            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return stringToList(returnQuery); //should never get here.
    }

    @Override
    public ArrayList<Integer> getParticipantList(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("PARTICIPANT"); //Should not have two uids with the same name                            
            }
            
            if("".equals(returnQuery)){
                throw new DoesNotExistException("Event");
            } else {
                return stringToList(returnQuery); //return an arraylist
            }            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return stringToList(returnQuery); //should never get here.
    }

    @Override
    public ArrayList<Integer> getCommittee(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EVENTS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("COMMITTEE"); //Should not have two uids with the same name                            
            }
            
            if("".equals(returnQuery)){
                throw new DoesNotExistException("Event");
            } else {
                return stringToList(returnQuery); //return an arraylist
            }            

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        return stringToList(returnQuery); //should never get here.
    }
    
    
    

    ////////////////////////SETTERS///////////////////////////////
    @Override
    public void setDescription(int uid, String description) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET DESCRIPTION=? WHERE UID=?");
            idQueryStmt.setString(1, description);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setStartDate(int uid, Timestamp time) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET STARTDATE=? WHERE UID=?");
            idQueryStmt.setTimestamp(1, time);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setEndDate(int uid, Timestamp time) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET ENDDATE=? WHERE UID=?");
            idQueryStmt.setTimestamp(1, time);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setComplete(int uid, int complete) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET COMPLETE=? WHERE UID=?");
            idQueryStmt.setInt(1, complete);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setStreet(int uid, String street) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET STREET=? WHERE UID=?");
            idQueryStmt.setString(1, street);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setCity(int uid, String city) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET CITY=? WHERE UID=?");
            idQueryStmt.setString(1, city);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setState(int uid, String state) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET STATE=? WHERE UID=?");
            idQueryStmt.setString(1, state);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET ZIPCODE=? WHERE UID=?");
            idQueryStmt.setString(1, zipcode);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setCountry(int uid, String country) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET COUNTRY=? WHERE UID=?");
            idQueryStmt.setString(1, country);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setOrganizerList(int uid, ArrayList<Integer> organizerList) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET ORGANIZER=? WHERE UID=?");
            idQueryStmt.setString(1, listToString(organizerList));
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setSubEventList(int uid, ArrayList<Integer> subEventList) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET SUBEVENT=? WHERE UID=?");
            idQueryStmt.setString(1, listToString(subEventList));
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setParticipantList(int uid, ArrayList<Integer> participantList) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET PARTICIPANT=? WHERE UID=?");
            idQueryStmt.setString(1, listToString(participantList));
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }

    @Override
    public void setCommittee(int uid, ArrayList<Integer> committeeList) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE EVENTS SET COMMITTEE=? WHERE UID=?");
            idQueryStmt.setString(1, listToString(committeeList));
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("User does not exist in EVENTS table.");
        }
    }
}
