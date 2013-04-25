/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS_Database;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface Interface_UserData {

    public boolean createUser(User user) throws DuplicateInsertionException;
    
    public String queryEntireTable();

    // GETTERS
    public int getUIDByName(String uname) throws DoesNotExistException;

    public String getName(int uid) throws DoesNotExistException;

    public String getEmail(int uid) throws DoesNotExistException;

    public String getPwd(int uid) throws DoesNotExistException;

    public int getLevel(int uid) throws DoesNotExistException;
    
    public String getPhone(int uid) throws DoesNotExistException;
    
    public String getStreet(int uid) throws DoesNotExistException;
    
    public String getCity(int uid) throws DoesNotExistException;
    
    public String getState(int uid) throws DoesNotExistException;
    
    public String getZipcode(int uid) throws DoesNotExistException;
    
    public String getCountry(int uid) throws DoesNotExistException;
    
    //SETTERS
    public void setUID(int uid, int nuid) throws DuplicateInsertionException;

    public void setName(int uid, String uname) throws DoesNotExistException;

    public void setEmail(int uid, String email) throws DoesNotExistException;

    public void setPwd(int uid, String pwd) throws DoesNotExistException;

    public void setLevel(int uid, int level) throws DoesNotExistException;
    
    public void setPhone(int uid, String phone) throws DoesNotExistException;
    
    public void setStreet(int uid, String street) throws DoesNotExistException;
    
    public void setCity(int uid, String city) throws DoesNotExistException;
    
    public void setState(int uid, String state) throws DoesNotExistException;
    
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException;
    
    public void setCountry(int uid, String country) throws DoesNotExistException;
}
