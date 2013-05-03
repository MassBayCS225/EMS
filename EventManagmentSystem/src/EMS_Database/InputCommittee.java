package EMS_Database;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public class InputCommittee {
        
    private String title;
    private int chairman;
    private ArrayList<Integer> budgetAcess;
    private ArrayList<Integer> committeeMembers;
    private ArrayList<Integer> taskList;
    private double budget;
    
    public InputCommittee(){
	ArrayList<Integer> list = new ArrayList<Integer>();
	this.title = "default title";
        this.chairman = 0;
        this.budgetAcess = list;
        this.committeeMembers = list;
        this.taskList = list;
        this.budget = 0.0;
    }

    public InputCommittee(String title, int chairman, ArrayList<Integer> budgetAcess, ArrayList<Integer> committeeMembers, ArrayList<Integer> taskList, double budget) {
	this.title = title;
	this.chairman = chairman;
	this.budgetAcess = budgetAcess;
	this.committeeMembers = committeeMembers;
	this.taskList = taskList;
	this.budget = budget;
    }

    public String getTitle() {
	return title;
    }

    public int getChairman() {
	return chairman;
    }

    public ArrayList<Integer> getBudgetAcess() {
	return budgetAcess;
    }

    public ArrayList<Integer> getCommitteeMembers() {
	return committeeMembers;
    }

    public ArrayList<Integer> getTaskList() {
	return taskList;
    }

    public double getBudget() {
	return budget;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public void setChairman(int chairman) {
	this.chairman = chairman;
    }

    public void setBudgetAcess(ArrayList<Integer> budgetAcess) {
	this.budgetAcess = budgetAcess;
    }

    public void setCommitteeMembers(ArrayList<Integer> committeeMembers) {
	this.committeeMembers = committeeMembers;
    }

    public void setTaskList(ArrayList<Integer> taskList) {
	this.taskList = taskList;
    }

    public void setBudget(double budget) {
	this.budget = budget;
    }

    
    
}
