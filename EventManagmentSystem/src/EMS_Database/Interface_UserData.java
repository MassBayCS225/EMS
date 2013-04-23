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
    public int getUIDByName(String uname) throws DoesNotExistException;
    public String getNameByUID(int uid) throws DoesNotExistException;
    public String getEmailByUID(int uid) throws DoesNotExistException;
    public String getPwdByUID(int uid) throws DoesNotExistException;
    public int getLevelByUID(int uid) throws DoesNotExistException;
}
