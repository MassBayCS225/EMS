package EMS_Database;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface Interface_FunctionWrapper {
    
    public String getDBString(String query, String table, int uid) throws DoesNotExistException;
    
    public double getDBDouble(String query, String table, int uid) throws DoesNotExistException;
    
    public ArrayList<Integer> getDBArrayList(String query, String table, int uid) throws DoesNotExistException;
    
    public int getDBInt(String query, String table, int uid) throws DoesNotExistException;
    
    public Timestamp getDBTimestamp(String query, String table, int uid) throws DoesNotExistException;

}
