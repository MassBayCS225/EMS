package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import EMS_Database.Interface_UserData;
import EMS_Database.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database implementation of all user related data.
 * <p>
 *      This class implements all methods surrounding the table which holds
 * the data for all the users in the EventManagmentSystem project. It functions
 * as follows, there are 2 special case methods which allow you to insert a 
 * user into the database based on the parameters specified by User class and 
 * there is a special case to look up users by there effective username.
 * Unless otherwise specified all Accessor/Mutator methods will reference the 
 * UID of the user to access or mutate data.
 * 
 * @author Mike Meding
 */
public class DB_UserData extends InitDB implements Interface_UserData {

    /**
     * Adds a new user to the database.
     *
     * @param user The user to be added to the database.
     * <p>
     * This function requires that you use the User class as a way of correctly
     * inserting data. null is perfectly acceptable to use as a field of user
     * when it is created. UID is required and is not checked by this function.
     * It is your responsibility to make sure that you do not end up with
     * duplicate UID's.
     *
     * @return true upon successful insertion of user into database
     */
    @Override
    public boolean createUser(User user) {

        boolean complete = false;

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, user.getUid());
            AddAddressStmt.setInt(2, user.getLevel());
            AddAddressStmt.setString(3, user.getName());
            AddAddressStmt.setString(4, user.getPwd());
            AddAddressStmt.setString(5, user.getEmail());
            AddAddressStmt.setString(6, user.getPhone());
            AddAddressStmt.setString(7, user.getStreet());
            AddAddressStmt.setString(8, user.getCity());
            AddAddressStmt.setString(9, user.getState());
            AddAddressStmt.setString(10, user.getZipcode());
            AddAddressStmt.setString(11, user.getCountry());
            

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

    /**
     * A method to look up a UID by username.
     *
     * @param uname String of the username to search for
     *
     * @return int of the UID if successful.
     * @throws DoesNotExistException if user does not exist.
     */
    @Override
    public int getUIDByName(String uname) throws DoesNotExistException {
        int returnQuery = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UNAME=?");
            idQueryStmt.setString(1, uname);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getInt("UID");

            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public String queryEntireTable() {
        StringBuilder returnQuery = new StringBuilder();
        try {
            
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = idQueryStmt.executeQuery();

            while (rs.next()) {
                returnQuery.append(rs.getString("UID"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("LEVEL"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("UNAME"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("PWD"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("EMAIL"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("PHONE"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("STREET"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("CITY"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("STATE"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("ZIPCODE"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("COUNTRY"));
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }
    
    ///////////////////// GETTERS ////////////////////////////

    /**
     * Returns the username of the user with the UID specified
     *
     * @param uid The UID of the user in question.
     * @return String of the username that was specified.
     *
     */
    @Override
    public String getName(int uid) throws DoesNotExistException {
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

    /**
     * A function to return the email of the user specified by UID.
     *
     * @param uid The UID of the user in question.
     * @return String of the email of the user in question.
     * @throws DoesNotExistException if user does not exist.
     */
    @Override
    public String getEmail(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("EMAIL"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * A method to return the password of the user specified by UID.
     * <p>
     * This method should have passwords hashed into it. No security is
     * implemented with this method. Cyphers and Hashes are your responsibility.
     * This is simply a String method.
     *
     * @param uid The UID of the user in question
     * @return String of the password.
     * @throws DoesNotExistException if the UID does not exist.
     */
    @Override
    public String getPwd(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("PWD"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * Returns the user privilege level of the user specified by uid. (LEVEL
     * CANNOT EQUAL 0!)
     *
     * @param uid The unique user id of the user in question.
     * @return Integer user level.
     * @throws DoesNotExistException if user does not exist
     */
    @Override
    public int getLevel(int uid) throws DoesNotExistException {
        int returnQuery = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getInt("LEVEL");

            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * Returns the users phone number as a String.
     * @param uid
     * @return String phone number
     * @throws DoesNotExistException if user does not exist.
     */
    @Override
    public String getPhone(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("PHONE"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
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


    ////////////////////// SETTERS ///////////////////////////////
    
    @Override
    public void setUID(int uid, int suid) throws DuplicateInsertionException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET UID=? WHERE UID=?");
            idQueryStmt.setInt(1, suid);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DuplicateInsertionException("UserData");            
        }        
    }

    /**
     * Updates the name of the user specified by UID
     *
     * @param uid The UID of the user to have its name updated
     * @param uname Specifies the new username to replace the one specified by
     * uid
     * @throws DoesNotExistException if the UID specified does not exist.
     */
    @Override
    public void setName(int uid, String uname) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET UNAME=? WHERE UID=?");
            idQueryStmt.setString(1, uname);
            idQueryStmt.setInt(2, uid);
            int count = idQueryStmt.executeUpdate();
            System.out.println("executeUpdate count = " + count);
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");            
        }        
    }

    @Override
    public void setEmail(int uid, String email) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(int uid, String pwd) throws DoesNotExistException {        
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET PWD=? WHERE UID=?");
            idQueryStmt.setString(1, pwd);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");            
        }        
    }

    @Override
    public void setLevel(int uid, int level) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhone(int uid, String phone) throws DoesNotExistException {
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
