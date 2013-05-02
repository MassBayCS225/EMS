/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BackEnd.EventSystem.Event;
import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Task;
import BackEnd.ManagerSystem.EventManager;
import BackEnd.UserSystem.IllegalCharacterException;
import BackEnd.UserSystem.PasswordMismatchError;
import java.util.ArrayList;

/**
 *
 * @author Karina
 */
public class TestGuiDriver 
{
    public static void main(String[] args) throws PasswordMismatchError, IllegalCharacterException
    {
        Event e = new Event(1,"test");
        ArrayList<Committee> list = new ArrayList<Committee>();
        Committee c = new Committee(1,1,"Test");
        c.getTaskList().add(new Task(1,1,"task1"));
        c.getTaskList().add(new Task(1,2,"task2"));
        Committee c2 = new Committee(1,2,"Test2");
        c2.getTaskList().add(new Task(2,1,"task12"));
        c2.getTaskList().add(new Task(2,2,"task22"));
        //c.setChair(new User(1,"A","B","AB@AB.com","ab","ab"));
        list.add(c);
        list.add(c2);
        e.setCommitteeList(list);
        EventManager manager = new EventManager();
        manager.setSelectedEvent(e);
        Home home = new Home(manager);
        home.setVisible(true);
    }
}
