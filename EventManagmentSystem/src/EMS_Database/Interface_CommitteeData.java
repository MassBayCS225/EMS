package EMS_Database;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface Interface_CommitteeData {

    //SPECIAL FUNCTIONS
    public ArrayList<Integer> stringToList(String uidList) throws NumberFormatException;

    public String listToString(ArrayList<Integer> list);

    public ArrayList<Integer> currentUIDList();

    public int createCommittee(InputCommittee committee) throws DuplicateInsertionException;

    public int nextValidUID();

    public String queryEntireTable();

    // GETTERS
    public String getTitle(int uid) throws DoesNotExistException;

    public int getChairman(int uid) throws DoesNotExistException;

    public String getBudgetAccessList(int uid) throws DoesNotExistException;

    public String getCommitteeMembers(int uid) throws DoesNotExistException;

    public String getTaskList(int uid) throws DoesNotExistException;

    public double getBudget(int uid) throws DoesNotExistException;

    // SETTERS
    public void setTitle(int uid, String title) throws DoesNotExistException;

    public void setChairman(int uid, int nuid) throws DoesNotExistException;

    public void setBudgetAccessList(int uid, String accessList) throws DoesNotExistException;

    public void setCommitteeMembers(int uid, String memberList) throws DoesNotExistException;

    public void setTaskList(int uid, String taskList) throws DoesNotExistException;

    public void setBudget(int uid, double budget) throws DoesNotExistException;
}
