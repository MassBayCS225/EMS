/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Event;
import BackEnd.EventSystem.SubEvent;
import BackEnd.EventSystem.Task;
import BackEnd.EventSystem.TimeSchedule;
import BackEnd.ManagerSystem.MainManager;
import BackEnd.UserSystem.IllegalCharacterException;
import BackEnd.UserSystem.PasswordMismatchError;
import BackEnd.ManagerSystem.PrivilegeInsufficientException;
import BackEnd.UserSystem.Address;
import BackEnd.UserSystem.PhoneNumber;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import java.util.ArrayList;

/**
 *
 * @author Karina
 */
public class TestGuiDriver 
{
    public static void main(String[] args) throws PasswordMismatchError, IllegalCharacterException, PrivilegeInsufficientException, DoesNotExistException
    {

        MainManager manager = MainManager.getInstance();
        Home home = new Home();
        home.setVisible(true);
    }
}
