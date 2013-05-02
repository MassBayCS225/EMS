package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;
import EMS_Database.*;
import EMS_Database.impl.*;

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
    private Events_Table eventsTable;
    private UserData_Table userDataTable;

    public EventManager() {
        // not used in current version. may be utilized in future version
        //eventList = new ArrayList<Event>();
        // get information from database
        eventsTable = new Events_Table();
        userDataTable = new UserData_Table();
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

    public void createEvent(Event newEvent, User loggedInUser) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            //eventList.add(newEvent);
            selectedEvent = newEvent; // remove this line when adding multiple event functionality
            // write to database

            /*
            eventsTable.createEvent(new InputEventData(newEvent.getDescription(), newEvent.getLocation().getDetails(),
                    newEvent.getTimeSchedule().getStartDateTimeTimestamp(), newEvent.getTimeSchedule().getEndDateTimeTimestamp(),
                    0,
                    newEvent.getLocation().getStreet(), newEvent.getLocation().getCity(),
                    newEvent.getLocation().getState(), newEvent.getLocation().getZipCode(),
                    newEvent.getLocation().getCountry(), newEvent.getOrganizerList(),
                    newEvent.getSubEventList(), newEvent.getParticipantList(),
                    newEvent.getCommitteeList()));
            // convert lists into ID lists
            * */
        }
    }

    public void clearEvent(User loggedInUser) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            //eventList.remove(selectedEvent);
            selectedEvent = null; // remove this line when adding multiple event functionality
            // remove the event from the database
        }
    }

    public void addOrganizer(User organizer, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().add(organizer);
            // write to database

            /*
            Integer organizerUserID = new Integer(userDataTable.getUIDByEmail(organizer.getEmailAddress()));
            ArrayList<Integer> newOrganizerList = eventsTable.getOrganizerList(selectedEvent.getEVENT_ID());
            newOrganizerList.add(organizerUserID);
            eventsTable.setOrganizerList(selectedEvent.getEVENT_ID(), newOrganizerList);
            * */

        }
    }

    public void removeOrganizer(User organizer, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().remove(organizer);
            // write to database

            /*
            Integer organizerUserID = new Integer(userDataTable.getUIDByEmail(organizer.getEmailAddress()));
            ArrayList<Integer> newOrganizerList = eventsTable.getOrganizerList(selectedEvent.getEVENT_ID());
            newOrganizerList.remove(organizerUserID);
            eventsTable.setOrganizerList(selectedEvent.getEVENT_ID(), newOrganizerList);

            // remove all related database entries
            * */

        }
    }

    public void addSubEvent(SubEvent subEvent, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getSubEventList().add(subEvent);
            // write to database

            /*
            Integer subEventID = new Integer(subEvent.getSUB_EVENT_ID());
            ArrayList<Integer> newSubEventList = eventsTable.getSubEventList(selectedEvent.getEVENT_ID());
            newSubEventList.add(subEventID);
            eventsTable.setSubEventList(selectedEvent.getEVENT_ID(), newSubEventList);
            * */
        }
    }

    public void removeSubEvent(SubEvent subEvent, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getSubEventList().remove(subEvent);
            // write to database

            /*
            Integer subEventID = new Integer(subEvent.getSUB_EVENT_ID());
            ArrayList<Integer> newSubEventList = eventsTable.getSubEventList(selectedEvent.getEVENT_ID());
            newSubEventList.remove(subEventID);
            eventsTable.setSubEventList(selectedEvent.getEVENT_ID(), newSubEventList);

            // remove all related database entries
            * */
        }
    }

    public void addCommittee(Committee committee, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getCommitteeList().add(committee);

            // write to database

            /*
            Integer committeeID = new Integer(committee.getCOMMITTEE_ID());
            ArrayList<Integer> newCommitteeList = eventsTable.getCommittee(selectedEvent.getEVENT_ID());
            newCommitteeList.add(committeeID);
            eventsTable.setCommittee(selectedEvent.getEVENT_ID(), newCommitteeList);
            * */
        }
    }

    public void removeCommittee(Committee committee, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getCommitteeList().remove(committee);
            // write to database

            /*
            Integer committeeID = new Integer(committee.getCOMMITTEE_ID());
            ArrayList<Integer> newCommitteeList = eventsTable.getCommittee(selectedEvent.getEVENT_ID());
            newCommitteeList.remove(committeeID);
            eventsTable.setCommittee(selectedEvent.getEVENT_ID(), newCommitteeList);

            // remove all related database entries

* */
        }
    }

    public void addParticipant(Participant participant) throws DoesNotExistException {
        selectedEvent.getParticipantList().add(participant);
        // write to database

        /*
        Integer participantID = new Integer(participant.getPARTICIPANT_ID());
        ArrayList<Integer> newParticipantList = eventsTable.getParticipantList(selectedEvent.getEVENT_ID());
        newParticipantList.add(participantID);
        eventsTable.setParticipantList(selectedEvent.getEVENT_ID(), newParticipantList);
        * */
    }

    public void removeParticipant(Participant participant) throws DoesNotExistException {
        selectedEvent.getParticipantList().remove(participant);
        // write to database

        /*
        Integer participantID = new Integer(participant.getPARTICIPANT_ID());
        ArrayList<Integer> newParticipantList = eventsTable.getParticipantList(selectedEvent.getEVENT_ID());
        newParticipantList.remove(participantID);
        eventsTable.setParticipantList(selectedEvent.getEVENT_ID(), newParticipantList);
        * */
    }

    public void editDescription(String description, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setDescription(description);
            // write to database

            /*
            eventsTable.setDescription(selectedEvent.getEVENT_ID(), description);
            * */
        }
    }

    public void editLocation(Location location, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setLocation(location);
            // write to database

            /*
            // eventsTable.setDetails(selectedEvent.getEVENT_ID(), location.getDetails());
            eventsTable.setStreet(selectedEvent.getEVENT_ID(), location.getStreet());
            eventsTable.setCity(selectedEvent.getEVENT_ID(), location.getCity());
            eventsTable.setState(selectedEvent.getEVENT_ID(), location.getState());
            eventsTable.setZipcode(selectedEvent.getEVENT_ID(), location.getZipCode());
            eventsTable.setCountry(selectedEvent.getEVENT_ID(), location.getCountry());
            * */
        }
    }

    public void editStartDateTime(int year, int month, int day, int hour, int minute, User loggedInUser)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getTimeSchedule().setStartDateTime(year, month, day, hour, minute);
            // write to database

            /*
            eventsTable.setStartDate(selectedEvent.getEVENT_ID(), selectedEvent.getTimeSchedule().getStartDateTimeTimestamp());
            * */
        }
    }

    public void editEndDateTime(int year, int month, int day, int hour, int minute, User loggedInUser)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getTimeSchedule().setEndDateTime(year, month, day, hour, minute);
            // write to database
            
            /*
            eventsTable.setEndDate(selectedEvent.getEVENT_ID(), selectedEvent.getTimeSchedule().getEndDateTimeTimestamp());
            * */
        }
    }
    
}
