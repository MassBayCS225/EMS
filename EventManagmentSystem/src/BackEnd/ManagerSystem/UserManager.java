/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.UserSystem.Address;
import BackEnd.UserSystem.IllegalCharacterException;
import BackEnd.UserSystem.PasswordMismatchError;
import BackEnd.UserSystem.PhoneNumber;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import EMS_Database.InputUser;
import EMS_Database.impl.UserData_Table;

/**
 *
 * @author David Tersoff
 */
public class UserManager
{
    
    private User selectedUser;
    private int selectedUID;
    private User loggedInUser;
    private UserData_Table table;
    
    public UserManager()
    {
        
    }
    public void addUser() throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
        {
            InputUser input = new InputUser(selectedUser);
            table.createUser(input);
        }
    }
    public void removeUser(User u) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.removeUser(selectedUID);
    }
    public void setSelectedUser(User u) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser = u;
        selectedUID = table.getUIDByEmail(selectedUser.getEmailAddress());
        
    }
    public User getSelectedUser() throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            return selectedUser;
        else
            throw new PrivilegeInsufficientException("Insufficient Privileges");
    }
    public void editFirstName(String firstName) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.setFirstName(selectedUID, firstName);
        //Write to database
    }
    public void editLastName(String lastName) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.setLastName(selectedUID, lastName);
        //Write to database
    }
    public void editEmailAddress(String emailAddress) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.setEmail(selectedUID, emailAddress);
        //Write to database
    }
    public void editAddress(Address address) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.setAddress(selectedUID, address)
    }
    public void editPhoneNumber(PhoneNumber phoneNumber) throws PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            table.setPhone(selectedUID, phoneNumber.toString());
        //Write to database
    }
    public void editPassword(String password, String passwordMatch) throws IllegalCharacterException, PasswordMismatchError, PrivilegeInsufficientException, DoesNotExistException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
        {
            selectedUser.setPassword(password, passwordMatch);
            table.setPwd(selectedUID, selectedUser.getPassword());
        //Write to database
    }   }
    public void editAdminPrivilege(boolean adminPrivilege) throws PrivilegeInsufficientException, DoesNotExistException
    {
        int level;
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
        {
            if(selectedUser.getAdminPrivilege())
                level = 1;
            else
                level = 0;
            table.setLevel(selectedUID, level);
        }
        
        //Write to database
    }
    public void editEventCreationPrivilege(boolean eventCreationPrivilege) throws PrivilegeInsufficientException, DoesNotExistException
    {
        int level;
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
        {
            if(selectedUser.getEventCreationPrivilege())
                level = 1;
            else
                level = 0;
            table.setEventCreationPrivilege(selectedUID, level);
        }
            
        //Write to database
    }
    
}
