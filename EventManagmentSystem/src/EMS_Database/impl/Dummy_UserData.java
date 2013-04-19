package EMS_Database.impl;

import EMS_Database.Interface_UserData;
import java.util.ArrayList;

/**
 *
 * @author Mike Meding
 */

public class Dummy_UserData implements Interface_UserData {
    public static ArrayList<ArrayList> userList = new ArrayList<ArrayList>();
   
    @Override
    public boolean addUser(int uid, int level , String uname , String pwd , String email){
        // adds an array list of a single users data an array list
        ArrayList<String> userData = new ArrayList<>();
        String sUid = "" + uid;
        userData.add(sUid);
        String sLevel = "" + level; 
        userData.add(sLevel);
        userData.add(uname);
        userData.add(pwd);
        userData.add(email);
        
        userList.add(userData);
        return true;
    }
    public ArrayList getUserByUID(int uid) {       
        ArrayList<String> user = new ArrayList<String>();
        String Suid = "" + uid;
        
        int x = 0;
        for(x=0;x<userList.size();x++){
            user = userList.get(x);
            if(user.get(0).equals(Suid) == true){                
                return user;
            }
        }
        return null;
    }    

    
    
    
}
