package EMS_Database;

import EMS_Database.impl.Committees_Table;
import EMS_Database.impl.Events_Table;
import EMS_Database.impl.SubEvent_Table;
import EMS_Database.impl.Tasks_Table;
import EMS_Database.impl.UserData_Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mike Meding
 *
 */
public class DB_Testing {

    public void run() {
        //Bogus user data.
//        InputUser user1 = new InputUser(1, 2, "FirstName_1", "LastName_1 ", "password", "retarded@email.com", "2143341905", "181 Bacon St.", "Natick", "MA", "01760", "USA", 0);
//        InputUser user2 = new InputUser(2, 2, "FirstName_2", "LastName_2 ", "password", "retarded@email.com", "2143341905", "181 Bacon St.", "Natick", "MA", "01760", "USA", 0);
//        InputUser user3 = new InputUser(3, 2, "FirstName_3", "LastName_3 ", "password", "retarded@email.com", "2143341905", "181 Bacon St.", "Natick", "MA", "01760", "USA", 0);

        // THIS IS THE EFFECTIVE API FOR TABLES

        int uid;

        // Create table objects.
        Events_Table et = new Events_Table();
        SubEvent_Table set = new SubEvent_Table();
        Tasks_Table tt = new Tasks_Table();
        Committees_Table ct = new Committees_Table();
        UserData_Table udt = new UserData_Table();

        //BOGUS Events table data
        Timestamp startDate = new Timestamp(new Date().getTime());
        Timestamp endDate = new Timestamp(new Date().getTime());
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(4);
        InputEventData ied = new InputEventData("first event", "The Woods", startDate, endDate, 0, "181 Bacon St.", "Natick", "MA", "01760", "MURICA!", list , list , list , list);
        //BOGUS SubEvents table data
        InputSubEventData ised = new InputSubEventData("awesome event", 1, "181 Bacon St.", "Natick", "MA", "01760", "MURICA!", startDate, endDate);
        //BOGUS Committees table data
        InputCommittee ic = new InputCommittee("Knights Of The Round Table", 1, "1,2", "1,2", "1,2", 100000.1);
        //BOGUS Tasks table data
        InputTask it = new InputTask("Stop Programming So Much", "181 Bacon St.", "Natick", "MA", "01760", "MURICA!", startDate, endDate, 0, "2,1,");
        //BOGUS UserData table data


        //CREATE EVENT INSERTION
//        try {
//            uid = et.createEvent(ied);
//            System.out.println("The created uid = " + uid);
//        } catch (DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
//        System.out.println(et.queryEntireTable());        
//        System.out.println(); //blank line.
//        try{
//            uid = 4;
//            System.out.println(et.getDescription(uid));
//            System.out.println(et.getStartDate(uid));
//            System.out.println(et.getEndDate(uid));
//            System.out.println(et.getComplete(uid));
//            System.out.println(et.getStreet(uid));
//            System.out.println(et.getCity(uid));
//            System.out.println(et.getState(uid));
//            System.out.println(et.getZipcode(uid));
//            System.out.println(et.getCountry(uid));
//            System.out.println(et.getOrganizerList(uid));  
//            System.out.println(et.getSubEventList(uid));  
//            System.out.println(et.getParticipantList(uid));  
//            System.out.println(et.getCommittee(uid));              
//            
//        }catch(DoesNotExistException dnee){
//            System.err.println("No user here.... :/");
//            System.err.println(dnee.getMessage());
//        }
//        
//        try{
//            uid = 4;
//            list.add(999);
//            et.setDescription(uid, "3 AM Coding. Wooo!");            
//            et.setStartDate(uid, startDate);
//            et.setEndDate(uid, endDate);
//            et.setComplete(uid, 1);
//            et.setStreet(uid, "Mulholland Drive");
//            et.setCity(uid, "Tokyo");
//            et.setState(uid, "XYZ");
//            et.setZipcode(uid, "90210");
//            et.setCountry(uid, "JAPAN!");
//            et.setOrganizerList(uid, list);
//            et.setSubEventList(uid, list);
//            et.setParticipantList(uid, list);
//            et.setCommittee(uid, list);                        
//            
//        }catch(DoesNotExistException dnee){            
//            System.err.println(dnee.getMessage());
//        }                              

        //CREATE SUBEVENT INSERTION
//        try {
//            uid = set.createSubEvent(ised);
//            System.out.println("The created uid = " + uid);
//        } catch (DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
        System.out.println(set.queryEntireTable());
        
        try{
            uid = 2;
            System.out.println(set.getDescription(uid));
            System.out.println(set.getComplete(uid));
            System.out.println(set.getStreet(uid));
            System.out.println(set.getCity(uid));
            System.out.println(set.getState(uid));
            System.out.println(set.getZipcode(uid));
            System.out.println(set.getStartDate(uid));
            System.out.println(set.getEndDate(uid));
            set.setDescription(uid, "Better Description!");
            set.setStreet(uid,"28 Mullholand Drive");
            set.setCity(uid,"Amherst");
            set.setState(uid, "Massachusetts");
            set.setZipcode(uid, "12345");
            set.setCountry(uid, "USA");
            set.setStartDate(uid, startDate);
            set.setEndDate(uid, endDate);
            set.setComplete(uid, 1);
            
        } catch(DoesNotExistException dnee) {
            System.err.println(dnee.getMessage());
        }        
        System.out.println(set.queryEntireTable());

        //CREATE COMMITTEE INSERTION
//        try{
//            uid = ct.createCommittee(ic);
//            System.out.println("The created uid = " + uid);
//        } catch(DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
//        System.out.println(ct.queryEntireTable());

        //CREATE TASKS INSERTION
//        try {
//            uid = tt.createTask(it);
//            System.out.println("The created uid = " + uid);
//        } catch (DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
//        System.out.println(tt.queryEntireTable());


//        //Testing GETTERS
//        try {
//            int uid = 1;
//            System.out.println(getLevel(uid));
//            System.out.println(getFirstName(uid));
//            System.out.println(getLastName(uid));
//            System.out.println(getEmail(uid));
//            System.out.println(getPwd(uid));
//            System.out.println(getPhone(uid));
//            System.out.println(getStreet(uid));
//            System.out.println(getCity(uid));
//            System.out.println(getState(uid));
//            System.out.println(getZipcode(uid));
//            System.out.println(getCountry(uid));
//            System.out.println(nextValidUID());
//            
//        } catch (DoesNotExistException e) {            
//            System.err.println(e.getMessage());
//        }

//        //Testing SETTERS
//        try {
//            int uid = 2;
//            setFirstName(uid,"NewFirstName2");
//            setLastName(uid,"NewLastName2");            
//            setPwd(uid, "bestPasswordEVER!");
//            setEmail(uid,"muffins@muffintop.hotmail.com");
//            setPhone(uid, "867-5309");
//            setStreet(uid, "Grove St.");
//            setCity(uid, "Maui");
//            setState(uid, "Calm.");
//            setZipcode(uid, "12345");
//            setCountry(uid,"MURICA!");
//            setEventCreationPrivilege(uid, 1);
//        } catch(DoesNotExistException blah) {
//            System.err.println(blah.getMessage());
//        }
//        
//        System.out.println(queryEntireTable()); //debug method
//        System.out.println("");



    }

    public static void main(String[] args) {
        new DB_Testing().run();
    }
}
