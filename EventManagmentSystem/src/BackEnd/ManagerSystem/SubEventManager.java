package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.sql.Timestamp;

import EMS_Database.*;
import EMS_Database.impl.*;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class SubEventManager {

    private SubEvent_Table subEventsTable;
    private SubEvent selectedSubEvent;

    public SubEventManager() {
        subEventsTable = new SubEvent_Table();
    }
    
    public SubEvent_Table getSubEventsTable(){
        return subEventsTable;
    }

    public void setSelectedSubEvent(SubEvent selectedSubEvent) {
        this.selectedSubEvent = selectedSubEvent;
    }

    public SubEvent getSelectedSubEvent() {
        return selectedSubEvent;
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setDescription(description);
            // write to database
            
            subEventsTable.setDescription(selectedSubEvent.getSUB_EVENT_ID(), description); 
        }
    }

    public void editLocation(Location location, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.setLocation(location);
            // write to database
            
            // subEventsTable.setDetails(selectedSubEvent.getSUB_EVENT_ID(), location.getDetails());
            subEventsTable.setStreet(selectedSubEvent.getSUB_EVENT_ID(), location.getStreet());
            subEventsTable.setCity(selectedSubEvent.getSUB_EVENT_ID(), location.getCity());
            subEventsTable.setState(selectedSubEvent.getSUB_EVENT_ID(), location.getState());
            subEventsTable.setZipcode(selectedSubEvent.getSUB_EVENT_ID(), location.getZipCode());
            subEventsTable.setCountry(selectedSubEvent.getSUB_EVENT_ID(), location.getCountry());
        }
    }

    public void editStartDateTime(int year, int month, int day, int hour, int minute, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.getTimeSchedule().setStartDateTime(year, month, day, hour, minute);
            // write to database
            subEventsTable.setStartDate(selectedSubEvent.getSUB_EVENT_ID(), selectedSubEvent.getTimeSchedule().getStartDateTimeTimestamp()); 
        }
    }

    public void editEndDateTime(int year, int month, int day, int hour, int minute, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasSubEventPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedSubEvent.getTimeSchedule().setEndDateTime(year, month, day, hour, minute);
            // write to database
            subEventsTable.setEndDate(selectedSubEvent.getSUB_EVENT_ID(), selectedSubEvent.getTimeSchedule().getEndDateTimeTimestamp()); 
        }
    }
}
