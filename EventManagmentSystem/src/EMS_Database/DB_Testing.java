package EMS_Database;

/**
 *
 * @author Mike Meding
 *
 */
public class DB_Testing extends UserData {

    public DB_Testing() {
        super("db");
    }

    public void run() {
        //Bogus user data.
        User user1 = new User(1, 2, "Name_1", "password", "retarded@email.com", "2143341905", "181 Bacon St.", "Natick", "MA", "01760", "USA");
        User user2 = new User(2, 2, "Name_2", "password", "retarded@email.com", null, null, null, null, null, null);
        User user3 = new User(3, 2, "Name_3", "password", "retarded@email.com", null, null, null, null, null, null);
        User user4 = new User(4, 2, "Name_4", "password", "retarded@email.com", null, null, null, null, null, null);
        User user5 = new User(5, 2, "Name_5", "password", "retarded@email.com", null, null, null, null, null, null);
        User user6 = new User(6, 2, "Name_6", "password", "retarded@email.com", null, null, null, null, null, null);

// can only be done once.
//        try {
//            createUser(user1);
//            createUser(user2);
//            createUser(user3);
//            createUser(user4);
//            createUser(user5);
//            createUser(user6);
//        } catch (DuplicateInsertionException e) {
//            System.out.println("Insertion Failed");
//        }

//        System.out.println(queryEntireTable()); //debug method
//        System.out.println("");
//        System.out.println("");

        //sample output
//        try {
//            //Testing get methods
//            //System.out.println(getUIDByName("Rickey"));          
//            
//            int uid = 4;
//            System.out.println("Level = " + getLevel(uid));
//            System.out.println("Name = " + getName(uid));
//            System.out.println("Password =  " + getPwd(uid));
//            System.out.println("Email = " + getEmail(uid));            
//
//        } catch (DoesNotExistException e) {
//            System.out.println("That user does not exist.");
//        }


//        try {
//            setPwd(4, "password");            
//        } catch (DoesNotExistException dnee) {
//            System.out.println("updating name failed.");
//        }

        System.out.println(queryEntireTable()); //debug method
        System.out.println("");
        
        try {
            System.out.println(getUIDByName("Name_1"));
            System.out.println(getPhone(1));
            
        } catch (DoesNotExistException e) {
            System.out.println("User does not exist in the database.");
            System.out.println(e.getMessage());
        }
//        
//        try {
//            System.out.println(getName(3));
//        } catch(DoesNotExistException f) {
//            System.out.println("getting name failed.");            
//        }


    }

    public static void main(String[] args) {
        new DB_Testing().run();
    }
}
