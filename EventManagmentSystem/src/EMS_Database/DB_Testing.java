package EMS_Database;

import EMS_Database.impl.Events_Table;
import java.sql.Timestamp;

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
        Events_Table et = new Events_Table();

        Timestamp startDate = new Timestamp(1992, 03, 06, 12, 00, 00, 00);
        Timestamp endDate = new Timestamp(2013, 03, 06, 12, 00, 00, 00);
        //InputEventData ied = new InputEventData("first event", "The Woods", startDate, endDate, 0, "2,1,");

        try {
            int uid = et.createSubEvent(ied);
            System.out.println("The created uid = " + uid);
        } catch (DuplicateInsertionException die) {
            System.err.println("Duplicate Insertion?");
        }
        System.out.println(et.queryEntireTable());






        //ONLY DO ONCE
//        try {
//            createUser(user1);
//            createUser(user2);
//            createUser(user3);
//
//        } catch (DuplicateInsertionException e) {
//            System.out.println("Insertion Failed");
//        }       


//        System.out.println(queryEntireTable()); //debug method
//        System.out.println("");
//        
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
