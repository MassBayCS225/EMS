package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.sql.Timestamp;

import EMS_Database.*;
import EMS_Database.impl.*;
import java.util.ArrayList;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class TaskManager {

    private Tasks_Table tasksTable;
    private Task selectedTask;

    public TaskManager() {
        tasksTable = new Tasks_Table();        
    }
    
    public Tasks_Table getTasksTable(){
        return tasksTable;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void addResponsible(User responsible, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getResponsibleList().add(responsible);
            // write to database
            
            ArrayList<Integer> newResponsibleList = tasksTable.getAuthority(selectedTask.getTASK_ID());
            newResponsibleList.add(responsible.getUserId());
            tasksTable.setAuthority(selectedTask.getTASK_ID(), newResponsibleList);
        }
    }

    public void removeResponsible(User responsible, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getResponsibleList().remove(responsible);
            // write to database
            
            ArrayList<Integer> newResponsibleList = tasksTable.getAuthority(selectedTask.getTASK_ID());
            newResponsibleList.remove(responsible.getUserId());
            tasksTable.setAuthority(selectedTask.getTASK_ID(), newResponsibleList);
            // remove all related database entries
        }
    }

    public void editCompleted(boolean completed, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setCompleted(completed);
            // write to database
            
            if (completed){
            tasksTable.setComplete(selectedTask.getTASK_ID(), 1);
            }
            else{
              tasksTable.setComplete(selectedTask.getTASK_ID(), 0);  
            }
        }
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setDescription(description);
            // write to database
            
            tasksTable.setDescription(selectedTask.getTASK_ID(), description);
        }
    }

    public void editLocation(Location location, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setLocation(location);
            // write to database
            
            // tasksTable.setDetails(selectedTask.getTASK_ID(), location.getDetails());
            tasksTable.setStreet(selectedTask.getTASK_ID(), location.getStreet());
            tasksTable.setCity(selectedTask.getTASK_ID(), location.getCity());
            tasksTable.setState(selectedTask.getTASK_ID(), location.getState());
            tasksTable.setZipcode(selectedTask.getTASK_ID(), location.getZipCode());
            tasksTable.setCountry(selectedTask.getTASK_ID(), location.getCountry());
        }
    }

    public void editStartDateTime(int year, int month, int day, int hour, int minute, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getTimeSchedule().setStartDateTime(year, month, day, hour, minute);
            // write to database
            
            tasksTable.setStartDate(selectedTask.getTASK_ID(), selectedTask.getTimeSchedule().getStartDateTimeTimestamp());
        }
    }

    public void editEndDateTime(int year, int month, int day, int hour, int minute, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getTimeSchedule().setEndDateTime(year, month, day, hour, minute);
            // write to database
            
            tasksTable.setEndDate(selectedTask.getTASK_ID(), selectedTask.getTimeSchedule().getEndDateTimeTimestamp());
        }
    }
}