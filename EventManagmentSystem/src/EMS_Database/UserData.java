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
    public String queryEntireTable() {
        return ud.queryEntireTable();
    }
    
    
}
