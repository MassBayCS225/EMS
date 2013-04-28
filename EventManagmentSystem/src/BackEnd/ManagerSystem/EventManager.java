package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.util.ArrayList;
import java.sql.Timestamp;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class EventManager {

    //private ArrayList<Event> eventList;
    private Event selectedEvent;

    public EventManager() {
        // not used in current version. may be utilized in future version
        //eventList = new ArrayList<Event>();
        // get information from database
    }

    // method may be finished in future version
    public ArrayList<Event> getEventList() {
        return null;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void createEvent(Event newEvent, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            //eventList.add(newEvent);
            selectedEvent = newEvent; // remove this line when adding multiple event functionality
            // write to database
        }
    }

    public void clearEvent(User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            //eventList.remove(selectedEvent);
            selectedEvent = null; // remove this line when adding multiple event functionality
        }
    }

    public void addOrganizer(User organizer, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().add(organizer);
            // write to database
        }
    }

    public void removeOrganizer(User organizer, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().remove(organizer);
            // write to database
            // remove all related database entries
        }
    }

    public void addSubEvent(SubEvent subEvent, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getSubEventList().add(subEvent);
            // write to database
        }
    }

    public void removeSubEvent(SubEvent subEvent, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getSubEventList().remove(subEvent);
            // write to database
            // remove all related database entries
        }
    }

    public void addCommittee(Committee committee, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getCommitteeList().add(committee);
            // write to database
        }
    }

    public void removeCommittee(Committee committee, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getCommitteeList().remove(committee);
            // write to database
            // remove all related database entries
        }
    }
       
    public void addParticipant(Participant participant){
        selectedEvent.getParticipantList().add(participant);
        // write to database
    }
    
    public void removeParticipant(Participant participant){
        selectedEvent.getParticipantList().remove(participant);
        // write to database
    }

    public void editDescription(String description, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setDescription(description);
            // write to database
        }
    }

    public void editLocation(Location location, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setLocation(location);
            // write to database
        }
    }

    public void editStartDateTime(Timestamp startDateTime, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setStartDateTime(startDateTime);
            // write to database
        }
    }

    public void editEndDateTime(Timestamp endDateTime, User loggedInUser) {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setEndDateTime(endDateTime);
            // write to database
        }
    }
}
