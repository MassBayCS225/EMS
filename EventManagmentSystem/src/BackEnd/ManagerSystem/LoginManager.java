/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.UserSystem.Participant;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import EMS_Database.InputUser;
import EMS_Database.impl.UserData_Table;

import java.util.ArrayList;

/**
 *
 * @author David Tersoff
 */
public class LoginManager 
{
    private User loggedInUser;
    private ArrayList<Participant> userList;
    
    public LoginManager(ArrayList<Participant> userList)
    {
        this.userList = userList;
    }
    
    public void setLoggedInUser(String email, String password)throws LogInIncorrectException
    {
        try{
            User user = checkEmailAddress(email);
            checkPassword(user, password);
            loggedInUser = user;
        }
        catch (LogInIncorrectException e){
            throw new LogInIncorrectException("Incorrect log in information.");
        }
        
    }
    
    private User checkEmailAddress(String email) throws LogInIncorrectException{
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getEmailAddress().equals(email)){
                return (User)userList.get(i);
            }
        }
        throw new LogInIncorrectException("Incorrect log in information.");
    }
    
    private boolean checkPassword(User user, String password) throws LogInIncorrectException{
        if (user.getPassword().equals(password)){
            return true;
        }
        else{
            throw new LogInIncorrectException("Incorrect log in information.");
        }
    }
    
    public User getLoggedInUser(){
        return loggedInUser;
    }
    
    public void logOut(){
        loggedInUser = null;
    }
}
