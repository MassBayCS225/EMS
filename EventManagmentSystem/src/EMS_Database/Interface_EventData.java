package EMS_Database;

/**
 *
 * @author mike
 */
public interface Interface_EventData {
    
    // SPECIAL CASE FUNCTIONS
    public int createTask(InputTask task) throws DuplicateInsertionException;
    public String queryAllTasks();
    public int nextValidUID();
    public String queryCompleteTasks();
    
    
    
}
