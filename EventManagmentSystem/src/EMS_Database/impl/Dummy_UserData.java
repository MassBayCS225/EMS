package EMS_Database.impl;

import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.Interface_UserData;
import EMS_Database.User;
import java.util.ArrayList;

/** A dummy temporary database for managing user data.
 * <p>
 *      This class functions the same way that the actual database will function
 * but without the need for a localhost database which would require complex
 * setup.
 *
 * @author Mike Meding
 */
public class Dummy_UserData implements Interface_UserData {

    public ArrayList<User> userList = new ArrayList();

    public Dummy_UserData() {
        // temp data to be inserted

    }

    @Override
    public String getName(int uid) throws DoesNotExistException {
        /**
         * Returns the a User based on a given UID.
         * <p>         
         * @param uid Is the unique id of the user in question.
         */
        
        for (User user : userList) {
            if (user.getUid() == uid) {
                return user.getName();
            }
        }
        throw new DoesNotExistException("UserData");
    }    

    @Override
    public int getUIDByName(String uname) throws DoesNotExistException {
        /**
         * Returns the UID of the specified user if it exists.
         * <p>         
         * @param uname The username of the user being searched for.
         */
        
        for (User user : userList) {
            if (user.getName().equals(uname)) {
                return user.getUid();
            }
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public boolean createUser(User user) throws DuplicateInsertionException {
        /**
         * Returns true upon successful creation and addition of a user to dummy
         * database.
         * <p>
         * @param user is of type User which holds all the parameters needed for
         * creation         
         */
        
        // Checks if user already exists in userList
        for(User currUser : userList){
            if(currUser.getUid() == user.getUid())
                throw new DuplicateInsertionException("UserData");
        }
        
        userList.add(user);
        return true;
    }

    @Override
    public String getEmail(int uid) throws DoesNotExistException {
        /**
         * Returns a String of the email of the user with the UID specified
         * <p>         
         * @param uid Is the unique id of the user in question.
         */
        
        for (User user : userList) {
            if (user.getUid() == uid) {
                return user.getEmail();
            }
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public String getPwd(int uid) throws DoesNotExistException {
        /**
         * Returns a String of the password of the user with the UID specified
         * <p>         
         * @param uid Is the unique id of the user in question.
         */
        
        for (User user : userList) {
            if (user.getUid() == uid) {
                return user.getPwd();
            }
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public int getLevel(int uid) throws DoesNotExistException {
        /**
         * Returns a String of the user privilege level of the user with the UID specified
         * <p>         
         * @param uid Is the unique id of the user in question.
         */
        
        for (User user : userList) {
            if (user.getUid() == uid) {
                return user.getLevel();
            }
        }
        throw new DoesNotExistException("UserData");
    }

    @Override
    public void setUID(int uid, int nuid) throws DuplicateInsertionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(int uid, String uname) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(int uid, String email) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPwd(int uid, String pwd) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLevel(int uid, int level) throws DoesNotExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String queryEntireTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhone(int uid) throws DoesNotExistException {
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
