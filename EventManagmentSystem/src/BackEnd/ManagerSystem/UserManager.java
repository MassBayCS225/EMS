/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.UserSystem;

/**
 *
 * @author David Tersoff
 */
public class UserManager {
    
    private User selectedUser;
    
    public UserManager()
    {
        
    }
    public void addUser(User u)
    {
       
    }
    public void removeUser(User u)
    {
        
    }
    public void setSelectedUser(User u)
    {
        selectedUser = u;
    }
    public User getSelectedUser()
    {
        return selectedUser;
    }
    public void editFirstName(String firstName)
    {
        selectedUser.setFirstName(firstName);
    }
    public void editLastName(String lastName)
    {
        selectedUser.setLastName(lastName)
    }
    public void editEmailAddress(String emailAddress)
    {
        selectedUser.setEmailAddress(emailAddress);
    }
    public void editAddress(Address address)
    {
        selectedUser.setAddress(address);
    }
    public void editPhoneNumber(PhoneNumber phoneNumber)
    {
        selectedUser.setPhoneNumber(phoneNumber);
    }
    public void editPassword(String password, String passwordMatch) throws IllegalCharacterException, PasswordMismatchError
    {
        selectedUser.setPassword(password, passwordMatch);
    }
    public void editAdminPrivilege(boolean adminPrivilege)
    {
        selectedUser.setAdminPrivilege(adminPrivilege);
    }
    public void editEventCreationPrivilege(boolean eventCreationPrivilege)
    {
        selectedUser.setEventCreationPrivilege(eventCreationPrivilege);
    }
    
}
