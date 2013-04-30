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
import EMS_Database.InputUser;
import EMS_Database.impl.UserData_Table;

/**
 *
 * @author David Tersoff
 */
public class UserManager {
    
    private User selectedUser;
    private User loggedInUser;
    private UserData_Table table;
    
    public UserManager()
    {
        
    }
    public void addUser(User u)
    {
        InputUser input = new InputUser(u);
        table.createUser(input);
    }
    public void removeUser(User u)
    {
        
    }
    public void setSelectedUser(User u) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser = u;
        
    }
    public User getSelectedUser() throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            return selectedUser;
    }
    public void editFirstName(String firstName) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setFirstName(firstName);
        //Write to database
    }
    public void editLastName(String lastName) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setLastName(lastName);
        //Write to database
    }
    public void editEmailAddress(String emailAddress) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setEmailAddress(emailAddress);
        //Write to database
    }
    public void editAddress(Address address) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setAddress(address);
    }
    public void editPhoneNumber(PhoneNumber phoneNumber) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setPhoneNumber(phoneNumber);
        //Write to database
    }
    public void editPassword(String password, String passwordMatch) throws IllegalCharacterException, PasswordMismatchError, PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setPassword(password, passwordMatch);
        //Write to database
    }
    public void editAdminPrivilege(boolean adminPrivilege) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setAdminPrivilege(adminPrivilege);
        //Write to database
    }
    public void editEventCreationPrivilege(boolean eventCreationPrivilege) throws PrivilegeInsufficientException
    {
        if(PrivilegeManager.hasAdminPrivilege(loggedInUser))
            selectedUser.setEventCreationPrivilege(eventCreationPrivilege);
        //Write to database
    }
    
}
