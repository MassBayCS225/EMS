package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;
import EMS_Database.*;
import EMS_Database.impl.*;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class CommitteeManager {

    private Committees_Table committeesTable;
    private Committee selectedCommittee;
    
    private Tasks_Table tasksTable;

    public CommitteeManager(Tasks_Table tasksTable) {
        committeesTable = new Committees_Table();
        this.tasksTable = tasksTable;
        
        System.out.println("committees : " + committeesTable.queryEntireTable());
    }
    
    public Committees_Table getCommitteesTable(){
        return committeesTable;
    }

    public void setSelectedCommittee(Committee selectedCommittee) {
        this.selectedCommittee = selectedCommittee;
    }

    public Committee getSelectedCommittee() {
        return selectedCommittee;
    }

    public void editTitle(String title, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.setTitle(title);
            // write to database

            committeesTable.setTitle(selectedCommittee.getCOMMITTEE_ID(), title);
        }
    }

    public void editChair(User chair, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.setChair(chair);
            // write to database

            committeesTable.setChairman(selectedCommittee.getCOMMITTEE_ID(), chair.getUserId());
        }
    }

    public void addBudgetAccess(User budgetAccess, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.getBudgetAccessList().add(budgetAccess);

            // write to database
            ArrayList<Integer> newBudgetAccessList = committeesTable.getBudgetAccessList(selectedCommittee.getCOMMITTEE_ID());
            newBudgetAccessList.add(budgetAccess.getUserId());
            committeesTable.setBudgetAccessList(selectedCommittee.getCOMMITTEE_ID(), newBudgetAccessList);
        }
    }

    public void removeBudgetAccess(User budgetAccess, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.getBudgetAccessList().remove(budgetAccess);
            // write to database

            ArrayList<Integer> newBudgetAccessList = committeesTable.getBudgetAccessList(selectedCommittee.getCOMMITTEE_ID());
            newBudgetAccessList.remove(budgetAccess.getUserId());
            committeesTable.setBudgetAccessList(selectedCommittee.getCOMMITTEE_ID(), newBudgetAccessList);
            // remove all related database entries
        }
    }

    public void addMember(User member, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.getMemberList().add(member);
            System.out.println("passed privilege");
            // write to database

            ArrayList<Integer> newMemberList = committeesTable.getCommitteeMembers(selectedCommittee.getCOMMITTEE_ID());
            System.out.println(newMemberList);
            newMemberList.add(member.getUserId());
            committeesTable.setCommitteeMembers(selectedCommittee.getCOMMITTEE_ID(), newMemberList);
        }
    }

    public void removeMember(User member, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.getMemberList().remove(member);
            // write to database

            ArrayList<Integer> newMemberList = committeesTable.getCommitteeMembers(selectedCommittee.getCOMMITTEE_ID());
            newMemberList.remove(member.getUserId());
            committeesTable.setCommitteeMembers(selectedCommittee.getCOMMITTEE_ID(), newMemberList);
            // remove all related database entries
        }
    }

    public Task createTask(Task task, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException, DuplicateInsertionException {
        
        Task newTask = null;
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            
            ArrayList<Integer> responsibleIDList = new ArrayList<Integer>();
            for (User responsible : task.getResponsibleList()){
                responsibleIDList.add(responsible.getUserId());
            }
            
            newTask = new Task(tasksTable.createTask(new InputTask(
                    task.getDescription(), task.getLocation().getDetails(), task.getLocation().getStreet(), task.getLocation().getCity(),
                    task.getLocation().getState(), task.getLocation().getZipCode(), task.getLocation().getCountry(),
                    task.getTimeSchedule().getStartDateTimeTimestamp(), task.getTimeSchedule().getEndDateTimeTimestamp(),
                    (task.getCompleted() == true? 1 : 0), responsibleIDList))
                    , task);
            
            ArrayList<Integer> newTaskList = committeesTable.getTaskList(selectedCommittee.getCOMMITTEE_ID());
            newTaskList.add(newTask.getTASK_ID());
            
            committeesTable.setTaskList(selectedCommittee.getCOMMITTEE_ID(), newTaskList);
            selectedCommittee.getTaskList().add(newTask);
        }
        return newTask;
    }
    
    public void deleteTask(Task task, User loggedInUser, Event selectedEvent)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedCommittee.getTaskList().remove(task);
            // write to database

            ArrayList<Integer> newTaskList = committeesTable.getTaskList(selectedCommittee.getCOMMITTEE_ID());
            newTaskList.remove(task.getTASK_ID());
            committeesTable.setTaskList(selectedCommittee.getCOMMITTEE_ID(), newTaskList);
            // remove all related database entries
            
            tasksTable.removeTask(task.getTASK_ID());
        }
    }
}