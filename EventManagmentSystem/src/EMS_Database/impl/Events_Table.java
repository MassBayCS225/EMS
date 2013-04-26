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
 * @author mike
 */
public class Events_Table extends InitDB implements Interface_EventData {

    /////////////////////SPECIAL FUNCTIONS///////////////////////////
    
    /**
     * A special function designed to format integer array lists into a string to 
     * be used for actual integer data return from the database.
     * @param uidList A formatted string from the database representing the UID list.
     * @return A nice array list of the UID's from the database.
     * @throws NumberFormatException if you somehow try to put something that cannot be parsed into the conversion string.
     */
    @Override
    public ArrayList<Integer> stringToList(String uidList) throws NumberFormatException {
        //Split String
        String[] uidStringList;
        uidStringList = uidList.split(",");
        
        ArrayList<Integer> uidIntList = new ArrayList<Integer>();
        
        //parse each item into arraylist
        for(String uid : uidStringList){
            try{
                uidIntList.add(Integer.parseInt(uid));
            } catch(NumberFormatException nfe) {
                throw new NumberFormatException("Parse Error");
            }
        }        
        
        return uidIntList;
    }

    /**
     * Does the opposite of string to list and creates a nicely formatted string for
     * insertion into the database.
     * @param list An ArrayList of Integers representing the UID numbers to be stored.
     * @return A nicely formated String for insertion into the database.
     */
    @Override
    public String listToString(ArrayList<Integer> list) {
        StringBuilder returnQuery = new StringBuilder();
        for(int uid : list){
            returnQuery.append(uid);
            returnQuery.append(",");            
        }
        return returnQuery.toString();
    }

    /**
     * Input a new event into the events using InputEvent class to create a valid input object
     * @param event of type InputEvent for row insertion.
     * @return the UID of the created event.
     * @throws DuplicateInsertionException 
     */
    @Override
    public int createSubEvent(InputEventData event) throws DuplicateInsertionException {        
        int newUID = nextValidUID();

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO EVENTS VALUES(?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, newUID);
            AddAddressStmt.setString(2, event.getDescription());
            AddAddressStmt.setString(3, event.getLocation());
            AddAddressStmt.setTimestamp(4, event.getStartDate());
            AddAddressStmt.setTimestamp(5, event.getEndDate());
            AddAddressStmt.setInt(6, event.getComplete());
            AddAddressStmt.setString(7, event.getCommittee());                                    

            //Execute Statement
            AddAddressStmt.executeUpdate();            

        } catch (SQLException sqle) {
            throw new DuplicateInsertionException("UserData");
        } finally {
            return newUID;
        }
    }

    /**
     * Gets the next vaild UID in the Events table
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
                returnQuery.append(rs.getString("LOCATION"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("STARTDATE"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("ENDDATE"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("COMPLETE"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("MANAGER"));                                
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }
    
    ////////////////////////SETTERS///////////////////////////////
    @Override
    public void setDescription(int uid, String description) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLocation(int uid, String location) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStartDate(int uid, Timestamp time) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEndDate(int uid, Timestamp time) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setComplete(int uid, int complete) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCommittee(int uid, ArrayList<Integer> committeeList) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //////////////////////GETTERS////////////////////////////
    @Override
    public String getDescription() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocation() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timestamp getStartDate() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timestamp getEndDate() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getComplete() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getCommittee() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    

}
