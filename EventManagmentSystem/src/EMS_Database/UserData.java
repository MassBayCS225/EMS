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
    public boolean addUser(int uid, int level, String uname, String pwd, String email) {
        return ud.addUser(uid, level, uname, pwd, email);
    }

    @Override
    public ArrayList getUserByUID(int uid) {
        return ud.getUserByUID(uid);
    }

    @Override
    public int getUserUIDByName(String uname) {
        try {
            return ud.getUserUIDByName(uname);
        } catch (DoesNotExistException dnee) {
        }
        return 0;
    }
}
