package BackEnd.EventSystem;

import BackEnd.UserSystem.User;
import java.util.ArrayList;

/**
 * 
 * @author Ketty Lezama
 */

public class Committee implements Reportable {
    private int COMMITTEE_ID;
    private String title;
    private ArrayList<User> memberList, budgetAccessList;
    private User chair;
    private ArrayList<Task> taskList;
    private Budget budget;
    
    public Committee(){
        title = new String();
        memberList = new ArrayList<User>();
        budgetAccessList = new ArrayList<User>();
        chair = new User();
        taskList = new ArrayList<Task>();
        budget = new Budget();
    }
    
    public Committee(String title){
        this.title = title;
        memberList = new ArrayList<User>();
        budgetAccessList = new ArrayList<User>();
        chair = new User();
        taskList = new ArrayList<Task>();
        budget = new Budget();
    }
    
    public Committee(int committee_id, String title) {
        COMMITTEE_ID = committee_id;
        this.title = title;
        memberList = new ArrayList<User>();
        budgetAccessList = new ArrayList<User>();
        chair = new User();
        taskList = new ArrayList<Task>();
        budget = new Budget();
    }
    
    public Committee(int committee_id, Committee committee){
        this.COMMITTEE_ID = committee_id;
        title = committee.getTitle();
        memberList = committee.getMemberList();
        budgetAccessList = committee.getBudgetAccessList();
        chair = committee.getChair();
        taskList = committee.getTaskList();
        budget = committee.getBudget();
    }
    
    /**
     * Confirms if the committee has completed all of the tasks in taskList or not.
     * @return True if the committee has finished all tasks, and false if it has not.
     */
    public boolean isFinished() {
        boolean completed = true;
        
        for (Task task : taskList)
            if (task.getCompleted() == false)
                completed = false;
        
        return completed;
    }
    
    /**
     * Sets the committee ID to the given committee ID value.
     * @param committee_id The committee ID.
     */
    private void setCOMMITTEE_ID(int committee_id) {
        COMMITTEE_ID = committee_id;
    }
    
    /**
     * Returns the committee ID.
     * @return COMMITTEE_ID The committee ID.
     */
    public int getCOMMITTEE_ID() {
        return COMMITTEE_ID;
    }
    
    /**
     * Sets the committee title to the given title.
     * @param title The committee title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns the committee title.
     * @return title The committee's title.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Stores a list of committee members.
     * @param memberList
     */
    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }
    
    /**
     * Returns the committee member list.
     * @return memberList
     */
    public ArrayList<User> getMemberList() {
        return memberList;
    }
    
    public ArrayList<User> getMemberListWithChair() throws NullPointerException {
        for (User member : memberList)
            if (member.equals(chair))
                return memberList;
        
        if (chair != null)
            memberList.add(chair);
        else
            throw new NullPointerException("Object chair is currently null. Unable to add to member list.");
        
        return memberList;
    }
    
    public void setBudgetAccessList(ArrayList<User> budgetAccessList) {
        this.budgetAccessList = budgetAccessList;
    }
    
    public ArrayList<User> getBudgetAccessList() {
        return budgetAccessList;
    }
    
    public void setChair(User user) {
        chair = user;
    }
    
    public User getChair() {
        return chair;
    }
    
    public int getCompletePercent()
    {
        int pct = 0;
        float complete = 0.0f;
        float total = taskList.size();
        for(Task t : taskList)
        {
            if(t.getCompleted())
            {
                complete += 1;
            }
        }
        pct = (int)(complete/total *100);
        return pct;
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
        if (this.getCOMMITTEE_ID() == committee.getCOMMITTEE_ID()
               && this.getTitle().equalsIgnoreCase(committee.getTitle())
               && this.getMemberList().equals(committee.getMemberList())
               && this.getBudgetAccessList().equals(committee.getBudgetAccessList())
               && this.getChair().equals(committee.getChair())
               && this.getTaskList().equals(committee.getTaskList())
               && this.getBudget().equals(committee.getBudget()))
            return true;
        else
            return false;    
    }
    
    public String toString() {
        String taskDescriptions = "";
        
        for (Task task : taskList)
            taskDescriptions += task.toString() + "\n";
            
        return "Committee Title: " + title + "\nTotal Budget: $" + budget.getTotalBudget() + "\nTask List: \n" + taskDescriptions;
    }
    
    public ArrayList<String> getReport() {
        //TODO
        return null;
    }
}