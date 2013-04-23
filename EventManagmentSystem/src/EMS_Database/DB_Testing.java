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
        
        User user = new User(4, 2, "Rickey", "rickey's password", "retarded@email.com");
                
        
//        //
//        try {
//            createUser(user);                        
//        } catch (DuplicateInsertionException e) {
//            System.out.println("Insertion Failed");
//        }
        
        
        //sample output
        try {
            //System.out.println(getUIDByName("Rickey"));
            
            //System.out.println(getLevelByUID(4));
            System.out.println(getNameByUID(4));                        
            //System.out.println(getPwdByUID(4));
            //System.out.println(getEmailByUID(4)); 
                        
        } catch (DoesNotExistException e) {
            System.out.println("That user does not exist.");
        }
        

    }

    public static void main(String[] args) {
        new DB_Testing().run();
    }
}
