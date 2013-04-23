package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.InitDB;
import EMS_Database.Interface_UserData;
import EMS_Database.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mike Meding
 */
public class DB_UserData extends InitDB implements Interface_UserData {

    @Override
    public boolean createUser(User user) {
        /* 
         * null is an acceptable type to enter into addUser()
         * returns true if insertion completed succesfully
         */


        boolean complete = false;

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?)");
            AddAddressStmt.setInt(1, user.getUid());
            AddAddressStmt.setInt(2, user.getLevel());
            AddAddressStmt.setString(3, user.getName());
            AddAddressStmt.setString(4, user.getPwd());
            AddAddressStmt.setString(5, user.getEmail());

            //Execute Statement
            AddAddressStmt.executeUpdate();
            complete = true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        } finally {
            return complete;
        }
    }

    @Override
    public int getUIDByName(String uname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNameByUID(int uid) throws DoesNotExistException {
        //returns entire row data based on ID#

        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("UNAME"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public String getEmailByUID(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPwdByUID(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLevelByUID(int uid) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
