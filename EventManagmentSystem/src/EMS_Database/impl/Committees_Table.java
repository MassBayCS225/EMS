package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import EMS_Database.InputCommittee;
import EMS_Database.Interface_CommitteeData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class Committees_Table extends InitDB implements Interface_CommitteeData {

    /////////////////////SPECIAL FUNCTIONS/////////////////////////
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM COMMITTEE");
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
     * Adds a new Committee into the committee table based on the the data from
     * InputCommittee object.
     * @param committee the parameters of this object specify what is to be inserted
     * @return the UID of the inserted data.
     * @throws DuplicateInsertionException if all hell breaks loose.
     */
    @Override
    public int createCommittee(InputCommittee committee) throws DuplicateInsertionException {
        int newUID = nextValidUID();

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO COMMITTEE VALUES(?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, newUID);
            AddAddressStmt.setString(2, committee.getTitle());
            AddAddressStmt.setInt(3, committee.getChairman());
            AddAddressStmt.setString(4, committee.getBudgetAcess());
            AddAddressStmt.setString(5, committee.getCommitteeMembers());
            AddAddressStmt.setString(6, committee.getTaskList());
            AddAddressStmt.setDouble(7, committee.getBudget());
            
            //Execute Statement
            AddAddressStmt.executeUpdate();
            
            //check for duplicates
            for(int uid : currentUIDList()){
                if(newUID == uid){
                    throw new DuplicateInsertionException("CommitteeTable");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM COMMITTEE");
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
     * Debug function for returning the entire table as a string.
     * @return a string of the entire table
     */
    @Override
    public String queryEntireTable() {
        StringBuilder returnQuery = new StringBuilder();
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM COMMITTEE");
            ResultSet rs = idQueryStmt.executeQuery();

            while (rs.next()) {
                returnQuery.append(rs.getString("UID"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("TITLE"));
                returnQuery.append(",");
                returnQuery.append(rs.getInt("CHAIRMAN"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("BUDGETACCESS"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("MEMBERS"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("TASKS"));
                returnQuery.append(",");
                returnQuery.append(rs.getDouble("BUDGET"));                                
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }

    ///////////////////GETTERS////////////////////
    @Override
    public String getTitle(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM COMMITTEE WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getString("TITLE"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if ("".equals(returnQuery)) {
                throw new DoesNotExistException("UID does not exist in COMMITTEE table.");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UID does not exist in COMMITTEE table.");
    }

    @Override
    public int getChairman(int uid) throws DoesNotExistException {
        int returnQuery = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM COMMITTEE WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {                
                returnQuery = rs.getInt("CHAIRMAN"); //Should not have two uids with the same name                            
            }
            
            //checking for existance of that uid
            if (returnQuery == 0) {
                throw new DoesNotExistException("UID does not exist in COMMITTEE table.");
            } else {
                return returnQuery;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UID does not exist in COMMITTEE table.");
    }

    
    
    @Override
    public double getBudget(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /////////////////////////SETTERS///////////////////////////
    @Override
    public void setTitle(int uid, String title) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChairman(int uid, int nuid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void setBudget(int uid, double budget) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getBudgetAccessList(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getCommitteeMembers(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getTaskList(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBudgetAccessList(int uid, ArrayList<Integer> accessList) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCommitteeMembers(int uid, ArrayList<Integer> memberList) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTaskList(int uid, ArrayList<Integer> taskList) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
