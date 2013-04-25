/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS_Database;

/**
 *
 * @author mike
 */
public class UserData implements Interface_UserData {

    private Interface_UserData ud;

    public UserData(String userType) {
        ud = UserDataFactory.create(userType);
    }

    //GETTERS
    @Override
    public String getName(int uid) throws DoesNotExistException {
        return ud.getName(uid);
    }

    @Override
    public int getUIDByName(String uname) throws DoesNotExistException {
        return ud.getUIDByName(uname);
    }

    @Override
    public boolean createUser(User user) throws DuplicateInsertionException {
        return ud.createUser(user);
    }

    @Override
    public String getEmail(int uid) throws DoesNotExistException {
        return ud.getEmail(uid);
    }

    @Override
    public String getPwd(int uid) throws DoesNotExistException {
        return ud.getPwd(uid);
    }

    @Override
    public int getLevel(int uid) throws DoesNotExistException {
        return ud.getLevel(uid);
    }

    @Override
    public String getPhone(int uid) throws DoesNotExistException {
        return ud.getPhone(uid);        
    }

    @Override
    public String getStreet(int uid) throws DoesNotExistException {
        return ud.getStreet(uid);
    }

    @Override
    public String getCity(int uid) throws DoesNotExistException {
        return ud.getCity(uid);
    }

    @Override
    public String getState(int uid) throws DoesNotExistException {
        return ud.getState(uid);
    }

    @Override
    public String getZipcode(int uid) throws DoesNotExistException {
        return ud.getZipcode(uid);
    }

    @Override
    public String getCountry(int uid) throws DoesNotExistException {
        return ud.getCountry(uid);
    }
    
    
    
    //SETTERS
    @Override
    public void setUID(int uid, int suid) throws DuplicateInsertionException {
        ud.setUID(uid, suid);
    }

    @Override
    public void setName(int uid, String uname) throws DoesNotExistException {
        ud.setName(uid, uname);
    }

    @Override
    public void setEmail(int uid, String email) throws DoesNotExistException {
        ud.setEmail(uid, email);
    }

    @Override
    public void setPwd(int uid, String pwd) throws DoesNotExistException {
        ud.setPwd(uid, pwd);
    }

    @Override
    public void setLevel(int uid, int level) throws DoesNotExistException {
        ud.setLevel(uid, level);
    }

    @Override
    public void setPhone(int uid, String phone) throws DoesNotExistException {
        ud.setPhone(uid, phone);
    }

    @Override
    public void setStreet(int uid, String street) throws DoesNotExistException {
        ud.setStreet(uid, street);
    }

    @Override
    public void setCity(int uid, String city) throws DoesNotExistException {
        ud.setCity(uid, city);
    }

    @Override
    public void setState(int uid, String state) throws DoesNotExistException {
        ud.setState(uid, state);
    }

    @Override
    public void setZipcode(int uid, String zipcode) throws DoesNotExistException {
        ud.setZipcode(uid, zipcode);
    }

    @Override
    public void setCountry(int uid, String country) throws DoesNotExistException {
        ud.setCountry(uid, country);
    }

        
    //Debug Methods
    @Override
    public String queryEntireTable() {
        return ud.queryEntireTable();
    }
    
    
    
    
}
