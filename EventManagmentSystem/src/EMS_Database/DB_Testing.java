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



        ArrayList<String> user = new ArrayList();

        //users are not permanantly stored. in temporary array
        addUser(1, 1, "Gilmore", "password", "stupid@email.com");
        addUser(2, 1, "Idiot", "password", "anotherStupid@email.com");

        user = getUserByUID(2);

        int userID = getUserUIDByName("Gilmore");
        System.out.println("UID = " + userID);

        if (user != null) {
            System.out.println(user.get(0) + " " + user.get(1) + " " + user.get(2) + " " + user.get(3) + " " + user.get(4));
        } else {
            System.out.println("UID does not exist.");
        }

    }

    public static void main(String[] args) {
        new DB_Testing().run();
    }
}
