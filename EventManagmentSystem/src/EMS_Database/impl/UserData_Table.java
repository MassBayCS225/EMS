package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InitDB;
import EMS_Database.Interface_UserData;
import EMS_Database.InputUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database implementation of all user related data.
 * <p>
 * This class implements all methods surrounding the table which holds the data
 * for all the users in the EventManagmentSystem project. It functions as
 * follows, there are 2 special case methods which allow you to insert a user
 * into the database based on the parameters specified by User class and there
 * is a special case to look up users by there effective username. Unless
 * otherwise specified all Accessor/Mutator methods will reference the UID of
 * the user to access or mutate data.
 *
 * @author Mike Meding
 */
public class UserData_Table extends InitDB implements Interface_UserData {

    ///////////////////// SPECIAL FUNCTIONS //////////////////////////////
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
    public boolean createUser(InputUser user) {

        boolean complete = false;

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            AddAddressStmt.setInt(1, nextValidUID());
            AddAddressStmt.setInt(2, user.getLevel());
            AddAddressStmt.setString(3, user.getFirstName());
            AddAddressStmt.setString(4, user.getLastName());
            AddAddressStmt.setString(5, user.getPwd());
            AddAddressStmt.setString(6, user.getEmail());
            AddAddressStmt.setString(7, user.getPhone());
            AddAddressStmt.setString(8, user.getStreet());
            AddAddressStmt.setString(9, user.getCity());
            AddAddressStmt.setString(10, user.getState());
            AddAddressStmt.setString(11, user.getZipcode());
            AddAddressStmt.setString(12, user.getCountry());
            AddAddressStmt.setInt(13, user.getEventLevel());


            //Execute Statement
            AddAddressStmt.executeUpdate();
            complete = true;

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        } finally {
            return complete;
        }
    }

    /**
     * A method to look up a UID by email address.
     *
     * @param email String of the username to search for
     *
     * @return int of the UID if successful.
     * @throws DoesNotExistException if user does not exist.
     */
    @Override
    public int getUIDByEmail(String email) throws DoesNotExistException {
        int returnQuery = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE EMAIL=?");
            idQueryStmt.setString(1, email);
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
                returnQuery.append(rs.getString("FNAME"));
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("LNAME"));
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
                returnQuery.append(" , ");
                returnQuery.append(rs.getString("EVENTLEVEL"));
                returnQuery.append("\n");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }

        return returnQuery.toString();
    }

    /**
     * This function goes through the entire table and establishes the next
     * valid UID number and returns it.
     * @return An Integer corresponding to the next valid UID that may be inserted
     * correctly into the database. IF ZERO IS RETURNED SOMETHING WENT HORRIBLY WRONG.
     */
    @Override
    public int nextValidUID() {                
        int newUID = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS");
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
     * This function is used to remove a user from the database with the specified
     * UID
     * @param uid the UID of the user to be removed.
     * @return true, upon successful removal from the database.
     * @throws DoesNotExistException if the user you are trying to delete does not exist.
     */
    @Override
    public boolean removeUser(int uid) throws DoesNotExistException {        
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("DELETE FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);            
            idQueryStmt.executeUpdate();                        
            
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            throw new DoesNotExistException("User does not exist.");
        }
        return true;
    }
    
    

    ///////////////////// GETTERS ////////////////////////////
    /**
     * Returns the First Name of the user with the UID specified
     *
     * @param uid The UID of the user in question.
     * @return String of the username that was specified.
     *
     */
    @Override
    public String getFirstName(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("FNAME"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }
    
    /**
     * Returns the Last Name of the user with the specified UID.
     * @param uid
     * @return
     * @throws DoesNotExistException 
     */
    @Override
    public String getLastName(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("LNAME"); //Should not have two uids with the same name                            
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
     *
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

    /**
     * Returns the Street address of the user as a string.
     * @param uid the UID of the user.
     * @return
     * @throws DoesNotExistException 
     */
    @Override
    public String getStreet(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("STREET"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }        

    /**
     * Returns the specified city if with the specified UID.
     * @param uid the UID of the user
     * @return a String of the City of the user
     * @throws DoesNotExistException 
     */
    @Override
    public String getCity(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("CITY"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * Returns the which of the fifty united states the user lives in...
     * @param uid
     * @return
     * @throws DoesNotExistException 
     */
    @Override
    public String getState(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("STATE"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * I give you props for reading my javadoc this far.
     * @param uid
     * @return seriously? if you haven't figured out the pattern yet then....
     * @throws DoesNotExistException 
     */
    @Override
    public String getZipcode(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("ZIPCODE"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * Returns which country the user resides in. MURICA!
     * @param uid 
     * @return MURICA!
     * @throws DoesNotExistException if you don't live in MURICA!
     */
    @Override
    public String getCountry(int uid) throws DoesNotExistException {
        String returnQuery = "";
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getString("COUNTRY"); //Should not have two uids with the same name                            
            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }

    /**
     * Returns the privilege level of the user to access event info.
     * @param uid the UID of the user
     * @return
     * @throws DoesNotExistException 
     */
    @Override
    public int getEventCreationPrivilege(int uid) throws DoesNotExistException {
        int returnQuery = 0;
        try {

            PreparedStatement idQueryStmt = dbConnection.prepareStatement("SELECT * FROM USERS WHERE UID=?");
            idQueryStmt.setInt(1, uid);
            ResultSet rs = idQueryStmt.executeQuery();

            //Gets the row with uid specified
            while (rs.next()) {
                //UNAME = coulmn name.
                returnQuery = rs.getInt("EVENTLEVEL");

            }
            return returnQuery;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }
        throw new DoesNotExistException("UserData");
    }
    
    

    ////////////////////// SETTERS ///////////////////////////////
    /**
     * Resets the UID of the user specified by the first argument to the UID of
     * the second argument.
     * @param uid The original UID
     * @param suid The new UID
     * @throws DuplicateInsertionException if the new UID already exists.
     */
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
     * Updates the first name of the user specified by UID
     *
     * @param uid The UID of the user to have its name updated
     * @param fname Specifies the new username to replace the one specified by
     * uid
     * @throws DoesNotExistException if the UID specified does not exist.
     */
    @Override
    public void setFirstName(int uid, String fname) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET FNAME=? WHERE UID=?");
            idQueryStmt.setString(1, fname);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();            
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the Last Name of the user specified by the UID
     * @param uid The UID of the user to change
     * @param lname The new last name.
     * @throws DoesNotExistException if the user searched for does not exist.
     */
    @Override
    public void setLastName(int uid, String lname) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET LNAME=? WHERE UID=?");
            idQueryStmt.setString(1, lname);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }      

    /**
     * Sets the email or rather USERNAME of the UID in question
     * @param uid the user being searched for
     * @param email the new email as a string
     * @throws DoesNotExistException if the user UID is not found.
     */
    @Override
    public void setEmail(int uid, String email) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET EMAIL=? WHERE UID=?");
            idQueryStmt.setString(1, email);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the password of the user with the specified UID forgetting the old one.
     * @param uid the UID of the user
     * @param pwd the password as a string to be changed.
     * @throws DoesNotExistException if the user searched for does not exist
     */
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
    
    /**
     * Sets the overall user privilege level of the user specified by the UID
     * @param uid the user to have its level changed
     * @param level the new privilege level
     * @throws DoesNotExistException if the user searched for does not exist.
     */
    @Override
    public void setLevel(int uid, int level) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET LEVEL=? WHERE UID=?");
            idQueryStmt.setInt(1, level);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }
    
    /**
     * Sets the Phone number of the user specified by the UID.
     * @param uid The UID of the user to have the number changed.
     * @param phone The new number. Represented as a string.
     * @throws DoesNotExistException If the UID does not exist.
     */
    @Override
    public void setPhone(int uid, String phone) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET PHONE=? WHERE UID=?");
            idQueryStmt.setString(1, phone);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the Street name of the UID.
     * @param uid The user you are looking for
     * @param street the new street name as a String.
     * @throws DoesNotExistException if the user does not exist.
     */
    @Override
    public void setStreet(int uid, String street) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET STREET=? WHERE UID=?");
            idQueryStmt.setString(1, street);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the city of the UID in question.
     * @param uid the user being searched for.
     * @param city the new city as a String.
     * @throws DoesNotExistException if the UID does not exist.
     */
    @Override
    public void setCity(int uid, String city) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET CITY=? WHERE UID=?");
            idQueryStmt.setString(1, city);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the current state in the 50 united states that the user lives.
     * @param uid the UID of the user.
     * @param state the new state in which they live. NERDVANA!
     * @throws DoesNotExistException 
     */
    @Override
    public void setState(int uid, String state) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET STATE=? WHERE UID=?");
            idQueryStmt.setString(1, state);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the zipcode of the UID specified.
     * @param uid the UID of the user.
     * @param zipcode the new zipcode of the user. 90210....
     * @throws DoesNotExistException 
     */
    @Override
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET ZIPCODE=? WHERE UID=?");
            idQueryStmt.setString(1, zipcode);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the Country in which the UID lives. MURICA!
     * @param uid the UID of the user.
     * @param country the new country in which he lives.
     * @throws DoesNotExistException if you attempt to set a country besides MURICA!
     */
    @Override
    public void setCountry(int uid, String country) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET COUNTRY=? WHERE UID=?");
            idQueryStmt.setString(1, country);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }

    /**
     * Sets the users ability to create events.
     * @param uid which UID to set?????
     * @param level the privilege level to give them.
     * @throws DoesNotExistException at exactly 3:41 AM every day for the rest of your life.
     */
    @Override
    public void setEventCreationPrivilege(int uid, int level) throws DoesNotExistException {
        try {
            PreparedStatement idQueryStmt = dbConnection.prepareStatement("UPDATE USERS SET LEVEL=? WHERE UID=?");
            idQueryStmt.setInt(1, level);
            idQueryStmt.setInt(2, uid);
            idQueryStmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new DoesNotExistException("UserData");
        }
    }
    
    
}
