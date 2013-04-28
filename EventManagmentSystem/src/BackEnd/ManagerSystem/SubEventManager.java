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
public class SubEventManager {

    private SubEvent selectedSubEvent;

    public SubEventManager() {
    }

    public void setSelectedSubEvent(SubEvent selectedSubEvent) {
        this.selectedSubEvent = selectedSubEvent;
    }

    public SubEvent getSelectedSubEvent() {
        return selectedSubEvent;
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setDescription(description);
            // write to database
        }
    }

    public void editLocation(Location location, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setLocation(location);
            // write to database
        }
    }

    public void editStartDateTime(Timestamp startDateTime, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setStartDateTime(startDateTime);
            // write to database
        }
    }

    public void editEndDateTime(Timestamp endDateTime, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setEndDateTime(endDateTime);
            // write to database
        }
    }
}
