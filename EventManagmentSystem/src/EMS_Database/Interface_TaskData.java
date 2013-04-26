package EMS_Database;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface Interface_TaskData {
    
    // SPECIAL CASE FUNCTIONS
    public ArrayList<Integer> stringToList(String uidList) throws NumberFormatException;

    public String listToString(ArrayList<Integer> list);

    public int createSubEvent(InputEventData event) throws DuplicateInsertionException;

    public int nextValidUID();

    public String queryEntireTable();

    // GETTERS
    public String getDescription() throws DoesNotExistException;

    public String getLocation() throws DoesNotExistException;

    public Timestamp getStartDate() throws DoesNotExistException;

    public Timestamp getEndDate() throws DoesNotExistException;

    public int getComplete() throws DoesNotExistException;

    public ArrayList<Integer> getAuthority() throws DoesNotExistException;

    // SETTERS      
    public void setDescription(int uid, String description) throws DoesNotExistException;

    public void setLocation(int uid, String location) throws DoesNotExistException;

    public void setStartDate(int uid, Timestamp time) throws DoesNotExistException;

    public void setEndDate(int uid, Timestamp time) throws DoesNotExistException;

    public void setComplete(int uid, int complete) throws DoesNotExistException;

    public void setAuthority(int uid, ArrayList<Integer> committeeList) throws DoesNotExistException;
}
