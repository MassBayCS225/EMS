/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EMS_Database.impl.Committees_Table;
import EMS_Database.impl.Events_Table;
import EMS_Database.impl.SubEvent_Table;
import EMS_Database.impl.UserData_Table;

/**
 *
 * @author Sid
 */
public class PrintInfo {
    
    public static void main(String[] args)
    {
        Events_Table edt = new Events_Table();
        //edt.removeAll("EVENTS");
        System.out.println("EVENTS");
        System.out.println(edt.queryEntireTable());
        
        System.out.println("USERS");
        UserData_Table udt = new UserData_Table();
        //udt.removeAll("USERS");
        System.out.println(udt.queryEntireTable());
        
        System.out.println("COMMITTEES");
        Committees_Table ct = new Committees_Table();
        //ct.removeAll("COMMITTEE");
        System.out.println(ct.queryEntireTable());
        
        System.out.println("SUBEVENTS");
        SubEvent_Table set = new SubEvent_Table();
        //set.removeAll("SUBEVENTS");
        System.out.println(set.queryEntireTable());
    }
    
}
