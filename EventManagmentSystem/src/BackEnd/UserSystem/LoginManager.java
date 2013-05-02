/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.UserSystem;

import EMS_Database.DoesNotExistException;
import EMS_Database.InputUser;
import EMS_Database.impl.UserData_Table;
/**
 *
 * @author David Tersoff
 */
public class LoginManager 
{
    private User loggedInUser;
    private InputUser input;
    private UserData_Table table;
    private int UID;
    
    public LoginManager()
    {
    }
    //public void setLoggedInUser(String email, String password)throws DoesNotExistException, PasswordMismatchError, IllegalCharacterException
    //{
    //    int uid = table.getUIDByEmail(email);
    //    if (password.equals(table.getPwd(uid)))
    //            loggedInUser =
    //}
    public boolean checkPassword(String email, String password) throws DoesNotExistException
    {
        int uid = table.getUIDByEmail(email);
        if(password.equals(table.getPwd(uid)))
        {
            UID = uid;
            return true;
        }
        else
            return false;
    }
}
