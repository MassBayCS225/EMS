package EMS_Database;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface Interface_BudgetData {

    // SPECIAL CASE FUNCTIONS
    public ArrayList<Integer> currentUIDList();
    
    public int nextValidUID();
    
    public String queryEntireTable();
    
    public void removeBudgetItem(int uid) throws DoesNotExistException; 
    
    public double total();
    
    // GETTERS
    public String getDescription(int uid) throws DoesNotExistException;  
    
    public double getValue(int uid) throws DoesNotExistException;    
    
    // SETTERS
    public void setDescription(int uid, String description) throws DoesNotExistException;
    
    public void setValue(int uid, double value) throws DoesNotExistException;
    
}
