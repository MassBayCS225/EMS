package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import static EMS_Database.InitDB.debugLog;
import EMS_Database.InputExpense;
import EMS_Database.Interface_BudgetData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author mike
 */
public class Expense_Table extends InitDB implements Interface_BudgetData {

    //SPECIAL FUNCTIONS
    @Override
    public ArrayList<Integer> currentUIDList() {
	int newUID = 0;
        ArrayList<Integer> UIDList = new ArrayList<Integer>();
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE");
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

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE");
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
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE");
            ResultSet rs = idQueryStmt.executeQuery();

            while (rs.next()) {
                returnQuery.append(rs.getInt("UID"));
                returnQuery.append(",");
                returnQuery.append(rs.getString("DESCRIPTION"));
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
	for(int users : currentUIDList()){
	    if(uid == users){
		exists = true;
	    }
	}
	if(exists = false){
	    debugLog.log(Level.WARNING, "Cannot remove user UID={0}", uid);
	    throw new DoesNotExistException("Cannot remove user UID="+uid);	    
	}
	try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM EXPENSE WHERE UID=?");
            idQueryStmt.setInt(1, uid);            
            idQueryStmt.executeUpdate();                                    
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new DoesNotExistException("User does not exist.");
        }        
    }
    
    public int insertBudgetItem(InputExpense input) throws DuplicateInsertionException {
	int newUID = nextValidUID();
        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO EXPENSE VALUES(?,?,?)");
            AddAddressStmt.setInt(1, newUID);
            AddAddressStmt.setString(2, input.getDescription());
            AddAddressStmt.setDouble(3, input.getValue());
            //Execute Statement
            AddAddressStmt.executeUpdate();

            //checking to see if UID already exists.
            for (int uid : currentUIDList()) {
                if (newUID == uid) {
                    throw new DuplicateInsertionException("Expense table error."); //should not get here.
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
	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE");
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

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getString("DESCRIPTION"); //Should not have two uids with the same name                            
	    }

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	debugLog.warning("UID=" + uid + " does not exist in INCOME table.");
	return null;
    }

    @Override
    public double getValue(int uid) throws DoesNotExistException {
	double returnQuery = 0.0;
	try {

	    PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM EXPENSE WHERE UID=?");
	    idQueryStmt.setInt(1, uid);
	    ResultSet rs = idQueryStmt.executeQuery();

	    //Gets the row with uid specified
	    while (rs.next()) {
		returnQuery = rs.getDouble("VALUE"); //Should not have two uids with the same name                            
	    }

	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	    System.exit(1);
	}
	debugLog.log(Level.WARNING, "UID={0} does not exist in INCOME table.", uid);
	return returnQuery;
    }
    
    
    //SETTERS
    @Override
    public void setDescription(int uid, String description) throws DoesNotExistException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(int uid, double value) throws DoesNotExistException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
