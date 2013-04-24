package BackEnd;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */
public class Committee implements Reportable {
    private int EVENT_ID, COMMITTEE_ID;
    private String title;
    private ArrayList<User> memberList, treasurerList;
    private User headOfCommittee;
    private ArrayList<Task> taskList;
    private Budget budget;
    
    public Committee(int event_id, String title) {
        EVENT_ID = event_id;
        COMMITTEE_ID = 0; //Might change this.
        this.title = title;
        memberList = new ArrayList<User>();
        treasurerList = new ArrayList<User>();
        headOfCommittee = null;
        taskList = new ArrayList<Task>();
        budget = new Budget();
    }
    
    public boolean isFinished() {
        boolean completed = true;
        
        for (Task task : taskList)
            if (task.getCompleted() == false)
                completed = false;
        
        return completed;
    }
    
    private void generateCOMMITTEE_ID() {
        
    }
    
    public int getCOMMITTEE_ID() {
        return COMMITTEE_ID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }
    
    public ArrayList<User> getMemberList() {
        return memberList;
    }
    
    public void setTreasurerList(ArrayList<User> treasurerList) {
        this.treasurerList = treasurerList;
    }
    
    public ArrayList<User> getTreasurerList() {
        return treasurerList;
    }
    
    public void setHeadOfCommittee(User user) {
        headOfCommittee = user;
    }
    
    public User getHeadOfCommittee() {
        return headOfCommittee;
    }
    
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public void setBudget(Budget budget) {
        this.budget = budget;
    }
    
    public Budget getBudget() {
        return budget;
    }
    
    public boolean equals(Committee committee) {
        
    }
    
    public String toString() {
        
    }
    
    public ArrayList<String> getReport() {
        
    }
}
