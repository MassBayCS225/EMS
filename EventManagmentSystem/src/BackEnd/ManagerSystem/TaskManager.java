package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.sql.Timestamp;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class TaskManager {

    private Task selectedTask;

    public TaskManager() {
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public Task getSelectedTask() {
        return selectedTask;
    }

    public void addResponsible(User responsible, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getResponsibleList().add(responsible);
            // write to database
        }
    }

    public void removeResponsible(User responsible, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getResponsibleList().remove(responsible);
            // write to database
            // remove all related database entries
        }
    }

    public void editCompleted(boolean completed, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setCompleted(completed);
            // write to database
        }
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setDescription(description);
            // write to database
        }
    }

    public void editLocation(Location location, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.setLocation(location);
            // write to database
        }
    }

    public void editStartDateTime(Timestamp startDateTime, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getTimeSchedule().setStartDateTime(startDateTime);
            // write to database
        }
    }

    public void editEndDateTime(Timestamp endDateTime, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasTaskPrivilege(loggedInUser, selectedEvent, selectedCommittee, selectedTask)) {
            selectedTask.getTimeSchedule().setEndDateTime(endDateTime);
            // write to database
        }
    }
}