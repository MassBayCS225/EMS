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
        Event e = new Event(1,"test");
        ArrayList<Committee> list = new ArrayList<Committee>();
        Committee c = new Committee(1,"Test");
        c.getTaskList().add(new Task(1,"task1"));
        c.getTaskList().add(new Task(2,"task2"));
        Committee c2 = new Committee(2,"Test2");
        c2.getTaskList().add(new Task(1,"task12"));
        c2.getTaskList().add(new Task(2,"task22"));
        
        SubEvent se1 = new SubEvent(1,"SE Test 1");
        TimeSchedule ts1 = new TimeSchedule();
        ts1.setStartDateTime(2013,5,7,5,30);
        ts1.setStartDateTime(2013,5,7,8,00);
        se1.setTimeSchedule(ts1);
        SubEvent se2 = new SubEvent(2,"SE Test 2");
        TimeSchedule ts2 = new TimeSchedule();
        ts2.setStartDateTime(2013,5,8,4,30);
        ts2.setStartDateTime(2013,5,8,9,30);
        se2.setTimeSchedule(ts2);
        SubEvent se3 = new SubEvent(3,"SE Test 3");
        TimeSchedule ts3 = new TimeSchedule();
        ts3.setStartDateTime(2013,5,10,8,00);
        ts3.setStartDateTime(2013,5,10,11,00);
        se3.setTimeSchedule(ts3);
        SubEvent se4 = new SubEvent(4,"SE Test 4");
        TimeSchedule ts4 = new TimeSchedule();
        ts4.setStartDateTime(2013,5,10,4,30);
        ts4.setStartDateTime(2013,5,10,8,30);
        se4.setTimeSchedule(ts4);
        SubEvent se5 = new SubEvent(5,"SE Test 5");
        TimeSchedule ts5 = new TimeSchedule();
        ts5.setStartDateTime(2013,5,12,16,30);
        ts5.setStartDateTime(2013,5,12,18,30);
        se5.setTimeSchedule(ts5);
        SubEvent se6 = new SubEvent(6,"SE Test 6");
        TimeSchedule ts6 = new TimeSchedule();
        ts6.setStartDateTime(2013,5,21,8,00);
        ts6.setStartDateTime(2013,5,21,11,00);
        se6.setTimeSchedule(ts6);        
        e.getSubEventList().add(se1);
        e.getSubEventList().add(se2);
        e.getSubEventList().add(se3);     
        e.getSubEventList().add(se4);
        e.getSubEventList().add(se5);
        e.getSubEventList().add(se6);
        
        list.add(c);
        list.add(c2);
        e.setCommitteeList(list);
        MainManager manager = MainManager.getInstance();
        
        //manager.getEventManager().setSelectedEvent(e);            
        User u = new User(1,"A","B","AB@AB.com","ab","ab");
        Address addr = new Address("Street","City", "STATE","00000","COUNTRY");
        PhoneNumber num = new PhoneNumber("5555555555");
        u.setAddress(addr);
        u.setPhoneNumber(num);
        User u2 = new User(2,"B","C","BC@BC.com","bc","bc");
        User u3 = new User(3,"C","D","CD@CD.com","cd","cd");
        User u4 = new User(4,"D","E","DE@DE.com","de","de");
        u.setAdminPrivilege(true);
        try
        {
            manager.getEventManager().createEvent(e,u);
            //manager.getEventManager().setSelectedEvent(manager.getEventManager().getEventList().get(0));
            manager.getEventManager().setSelectedEvent(e);
            for(User tu : e.getOrganizerList())
            {
                manager.getEventManager().addOrganizer(tu, u);
            }
//            for(Committee tc : e.getCommitteeList())
//            {
//                manager.getEventManager().addCommittee(tc, u);
//            }
//            for(SubEvent ts : e.getSubEventList())
//            {
//                manager.getEventManager().addSubEvent(ts, u);
//            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        manager.getUserManager().setLoggedInUser(u);
        manager.getEventManager().getSelectedEvent().getOrganizerList().add(u);
        manager.getEventManager().getSelectedEvent().getOrganizerList().add(u2);
        manager.getEventManager().getSelectedEvent().getOrganizerList().add(u3);
        manager.getEventManager().getSelectedEvent().getOrganizerList().add(u4);
        manager.getUserManager().setSelectedUser(u);
        manager.getUserManager().addUser(u);
//        Home home = new Home();
//        home.setVisible(true);
        //JFrame frame = new JFrame();
        //frame.add(new Main());
        //frame.setVisible(true);
        Home home = new Home();
        home.setVisible(true);
    }
}
