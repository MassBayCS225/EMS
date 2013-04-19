package EMS_Database.impl;

import EMS_Database.InitDB;
import EMS_Database.Interface_UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mike Meding
 */
public class DB_UserData extends InitDB implements Interface_UserData  {
    
    public static Connection dbConnection;
    
    public boolean addUser(int uid, int level , String uname , String pwd , String email) {
        /* 
         * null is an acceptable type to enter into addUser()
         * returns true if insertion completed succesfully
         */        
        
        
        InitalizeDB("jdbc:derby://localhost:1527/EMS_DB");
        dbConnection = getConnection();
                
        boolean complete = false;

        try {
            //Creating Statement
            PreparedStatement AddAddressStmt = dbConnection.prepareStatement("INSERT INTO APP.USERS VALUES(?,?,?,?,?)");
            AddAddressStmt.setInt(1, uid);
            AddAddressStmt.setInt(2, level);
            AddAddressStmt.setString(3, uname);
            AddAddressStmt.setString(4, pwd);
            AddAddressStmt.setString(5, email);

            //Execute Statement
            AddAddressStmt.executeUpdate();
            complete = true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        } finally {
            return complete;
        }
    }// end of addRow

    @Override
    public ArrayList getUserByUID(int uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
