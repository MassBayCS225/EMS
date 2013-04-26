package EMS_Database;

/**
 *
 * @author mike
 */
public class InputCommittee {

    
    private int uid;
    private String title;
    private int chairman;
    private String budgetAcess;
    private String committeeMembers;
    private String taskList;
    private double budget;

    public InputCommittee(int uid, String title, int chairman, String budgetAcess, String committeeMembers, String taskList, double budget) {
        this.uid = uid;
        this.title = title;
        this.chairman = chairman;
        this.budgetAcess = budgetAcess;
        this.committeeMembers = committeeMembers;
        this.taskList = taskList;
        this.budget = budget;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public int getChairman() {
        return chairman;
    }

    public String getBudgetAcess() {
        return budgetAcess;
    }

    public String getCommitteeMembers() {
        return committeeMembers;
    }

    public String getTaskList() {
        return taskList;
    }

    public double getBudget() {
        return budget;
    }
    
    
    //SETTERS
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChairman(int chairman) {
        this.chairman = chairman;
    }

    public void setBudgetAcess(String budgetAcess) {
        this.budgetAcess = budgetAcess;
    }

    public void setCommitteeMembers(String committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setTaskList(String taskList) {
        this.taskList = taskList;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    
}
