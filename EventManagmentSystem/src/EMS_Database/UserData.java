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
public class UserData implements Interface_UserData {

    private Interface_UserData ud;

    public UserData(String userType) {
        ud = UserDataFactory.create(userType);
    }

    @Override
    public String getNameByUID(int uid) throws DoesNotExistException {
        return ud.getNameByUID(uid);
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
    public String getEmailByUID(int uid) throws DoesNotExistException {
        return ud.getEmailByUID(uid);
    }

    @Override
    public String getPwdByUID(int uid) throws DoesNotExistException {
        return ud.getPwdByUID(uid);
    }

    @Override
    public int getLevelByUID(int uid) throws DoesNotExistException {
        return ud.getLevelByUID(uid);
    }
    
}
