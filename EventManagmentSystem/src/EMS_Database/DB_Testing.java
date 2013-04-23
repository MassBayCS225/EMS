package EMS_Database;

import java.util.ArrayList;

/**
 *
 * @author Mike Meding
 *
 */
public class DB_Testing extends UserData {

    public DB_Testing() {
        super("the dummy database class one :D");
    }

    public void run() {
        
        User user = new User(4, 2, "Rickey", "rickey's password", "retarded@email.com");
        
        //
        try {
            createUser(user);                        
        } catch (DuplicateInsertionException e) {
            System.out.println("Insertion Failed");
        }
        
        
        //sample output
        try {
            System.out.println(getUIDByName("Thad"));
            
            System.out.println(getLevelByUID(2));
            System.out.println(getNameByUID(2));                        
            System.out.println(getPwdByUID(2));
            System.out.println(getEmailByUID(2)); 
                        
        } catch (DoesNotExistException e) {
            System.out.println("That user does not exist.");
        }
        

    }

    public static void main(String[] args) {
        new DB_Testing().run();
    }
}
