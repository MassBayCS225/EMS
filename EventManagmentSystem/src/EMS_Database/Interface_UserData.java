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
    public boolean addUser(int uid, int level , String uname , String pwd , String email);
    public ArrayList getUserByUID(int uid);
    public int getUserUIDByName(String uname) throws DoesNotExistException;
}
