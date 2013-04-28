package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import EMS_Database.InputTask;
import EMS_Database.Interface_TaskData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

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
            sqle.printStackTrace();
            System.exit(1);
        }
        return UIDList; // should not be zero
    }

    /**
     * Inserts a new task into the Task table based on the parameters specified
     * by InputTask.
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
            AddAddressStmt.setInt(10,task.getComplete());
            AddAddressStmt.setString(11, task.getManager());
            
           
            
            //Execute Statement
            AddAddressStmt.executeUpdate();
            
            //check for duplicates
            for(int uid : currentUIDList()){
                if(newUID == uid){
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
            sqle.printStackTrace();
            System.exit(1);
        }
        return newUID; // should not be zero
    }

    /**
     * Debug function used to return the entire table as a string.
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
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }       
    
    ///////////////////////////GETTERS/////////////////////////////
    @Override
    public String getDescription() throws DoesNotExistException {
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
    public ArrayList<Integer> getAuthority() throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStreet(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCity(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getState(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getZipcode(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCountry(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /////////////////////////////SETTERS//////////////////////////////
    @Override
    public void setDescription(int uid, String description) throws DoesNotExistException {
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
    public void setAuthority(int uid, ArrayList<Integer> committeeList) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStreet(int uid, String street) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCity(int uid, String city) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setState(int uid, String state) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCountry(int uid, String country) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
