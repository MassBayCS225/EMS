/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.UserSystem.PasswordMismatchError;
import BackEnd.UserSystem.IllegalCharacterException;
import BackEnd.UserSystem.Address;
import BackEnd.UserSystem.PhoneNumber;
import BackEnd.UserSystem.User;
import BackEnd.UserSystem.Participant;
import EMS_Database.DoesNotExistException;
import EMS_Database.InputUser;
import EMS_Database.impl.UserData_Table;
import java.util.ArrayList;

/**
 *
 * @author David Tersoff
 */
public class UserManager {

    private ArrayList<Participant> userList;
    private User selectedUser;
    private UserData_Table usersTable;

    public UserManager()
            throws DoesNotExistException {
        usersTable = new UserData_Table();
        userList = new ArrayList<Participant>();
        rebuildUserList();
        //System.out.println(userList);
        // System.out.println("users : " + usersTable.queryEntireTable());
    }
    
    public UserData_Table getUsersTable(){
        return usersTable;
    }

    private void rebuildUserList()
            throws DoesNotExistException {
        //System.out.println(usersTable.currentUIDList("USERS"));
        for (Integer userID : usersTable.currentUIDList("USERS")) {
            if (usersTable.getParticipant(userID)) {
                userList.add(rebuildParticipant(userID));
            } else {
                userList.add((Participant) rebuildUser(userID));
            }
        }
    }

    private Participant rebuildParticipant(int userID)
            throws DoesNotExistException {
        Participant participant = new Participant(userID, usersTable.getFirstName(userID), usersTable.getLastName(userID),
                usersTable.getEmail(userID));
        participant.setPhoneNumber(new PhoneNumber(usersTable.getPhone(userID)));
        participant.setAddress(new Address(usersTable.getStreet(userID), usersTable.getCity(userID),
                usersTable.getState(userID), usersTable.getZipcode(userID), usersTable.getCountry(userID)));
        return participant;
    }

    private User rebuildUser(int userID)
            throws DoesNotExistException {
        User user = new User(userID, usersTable.getFirstName(userID), usersTable.getLastName(userID),
                usersTable.getEmail(userID), usersTable.getPwd(userID));

        user.setAdminPrivilege(usersTable.getLevel(userID) == 1 ? true : false);
        user.setEventCreationPrivilege(usersTable.getEventCreationPrivilege(userID) == 1 ? true : false);
        user.setPhoneNumber(new PhoneNumber(usersTable.getPhone(userID)));
        user.setAddress(new Address(usersTable.getStreet(userID), usersTable.getCity(userID),
                usersTable.getState(userID), usersTable.getZipcode(userID), usersTable.getCountry(userID)));

        return user;
    }

    public ArrayList<Participant> getUserList() {
        return userList;
    }

    public User createUser(User user) {
        User newUser = new User(usersTable.createUser(new InputUser(user)), user);
        userList.add(newUser);
        return newUser;
    }

    public void removeUser(User user) throws DoesNotExistException {
            usersTable.removeUser(selectedUser.getUserId());
            userList.remove(selectedUser);
    }

    public void setSelectedUser(User selectedUser){
        this.selectedUser = selectedUser;
    }

    public User getSelectedUser(){
            return selectedUser;
    }

    public void editFirstName(String firstName, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setFirstName(firstName);
            usersTable.setFirstName(selectedUser.getUserId(), firstName);
        }
        //Write to database
    }

    public void editLastName(String lastName, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setLastName(lastName);
            usersTable.setLastName(selectedUser.getUserId(), lastName);
        }
        //Write to database
    }

    public void editEmailAddress(String emailAddress, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setEmailAddress(emailAddress);
            usersTable.setEmail(selectedUser.getUserId(), emailAddress);
        }
        //Write to database
    }

    public void editAddress(Address address, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setAddress(address);
            usersTable.setAddress(selectedUser.getUserId(), address);
        }
    }

    public void editPhoneNumber(PhoneNumber phoneNumber, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setPhoneNumber(phoneNumber);
            usersTable.setPhone(selectedUser.getUserId(), phoneNumber.toString());
        }
        //Write to database
    }

    public void editPassword(String password, String passwordMatch, User loggedInUser) throws IllegalCharacterException, PasswordMismatchError, PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasUserPrivilege(loggedInUser, selectedUser)) {
            selectedUser.setPassword(password, passwordMatch);
            usersTable.setPwd(selectedUser.getUserId(), password);
        }
    }

    public void editAdminPrivilege(boolean adminPrivilege, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasAdminPrivilege(loggedInUser)) {
            selectedUser.setAdminPrivilege(adminPrivilege);
            usersTable.setLevel(selectedUser.getUserId(), adminPrivilege == true? 1 : 0);
        }

        //Write to database
    }

    public void editEventCreationPrivilege(boolean eventCreationPrivilege, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasAdminPrivilege(loggedInUser)) {
            selectedUser.setEventCreationPrivilege(eventCreationPrivilege);
            usersTable.setLevel(selectedUser.getUserId(), eventCreationPrivilege == true? 1 : 0);
        }

        //Write to database
    }
}