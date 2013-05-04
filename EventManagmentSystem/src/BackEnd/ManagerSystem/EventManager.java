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

    private ArrayList<Event> eventList;
    private Event selectedEvent;
    private Events_Table eventsTable;
    private UserData_Table usersTable;
    private SubEvent_Table subEventsTable;
    private Committees_Table committeesTable;
    private Tasks_Table tasksTable;
    private Income_Table incomeTable;
    private Expense_Table expenseTable;

    public EventManager(ArrayList<Participant> userList, UserData_Table usersTable, Tasks_Table tasksTable,
            SubEvent_Table subEventsTable, Committees_Table committeesTable,
            Income_Table incomeTable, Expense_Table expenseTable) // FIGURE OUT HOW TO HANDLE EXCEPTION
            throws DoesNotExistException {
        eventList = new ArrayList<Event>();

        eventsTable = new Events_Table();
        //System.out.println("events : " + eventsTable.queryEntireTable());
        this.usersTable = usersTable;
        this.subEventsTable = subEventsTable;
        this.tasksTable = tasksTable;
        this.committeesTable = committeesTable;
        this.incomeTable = incomeTable;
        this.expenseTable = expenseTable;
        //System.out.println("userList:" + userList);
        rebuildEventList(userList);
    }

    private void rebuildEventList(ArrayList<Participant> userList) // FIGURE OUT HOW TO HANDLE EXCEPTION
            throws DoesNotExistException {
        ArrayList<Integer> eventIDList = eventsTable.currentUIDList("EVENTS");

        //System.out.println("eventIDList:" + eventIDList);
        for (Integer eventID : eventIDList) {
            eventList.add(rebuildEvent(eventID, userList));
        }
    }

    private Event rebuildEvent(int eventID, ArrayList<Participant> userList) // FIGURE OUT HOW TO HANDLE EXCEPTION
            throws DoesNotExistException {
        Event event = new Event(eventID, eventsTable.getDescription(eventID));

        event.setSubEventList(rebuildSubEventList(eventID));
        event.setOrganizerList(rebuildOrganizerList(eventID, userList));
        event.setParticipantList(rebuildParticipantList(eventID, userList));
        event.setCommitteeList(rebuildCommitteeList(eventID, userList));

        //System.out.println("subEventList:" + event.getSubEventList());
        //System.out.println("organizerList:" + event.getOrganizerList());
        //System.out.println("participantList:" + event.getParticipantList());
        //System.out.println("committeeList:" + event.getCommitteeList());

        //System.out.println(eventID);
        //System.out.println(eventsTable.currentUIDList("EVENTS"));
        //System.out.println(eventsTable.getDetails(eventID));
        event.setLocation(new Location(eventsTable.getStreet(eventID), eventsTable.getCity(eventID),
                eventsTable.getState(eventID), eventsTable.getZipcode(eventID), eventsTable.getCountry(eventID),
                eventsTable.getDetails(eventID)));
        event.getTimeSchedule().setStartDateTime(eventsTable.getStartDate(eventID));
        event.getTimeSchedule().setEndDateTime(eventsTable.getEndDate(eventID));

        return event;
    }

    private ArrayList<SubEvent> rebuildSubEventList(int eventID)
            throws DoesNotExistException {
        ArrayList<SubEvent> subEventList = new ArrayList<SubEvent>();
        for (Integer subEventID : eventsTable.getSubEventList(eventID)) {
            subEventList.add(rebuildSubEvent(subEventID));
        }
        return subEventList;
    }

    private SubEvent rebuildSubEvent(int subEventID)
            throws DoesNotExistException {
        SubEvent subEvent = new SubEvent(subEventID, subEventsTable.getDescription(subEventID));
        subEvent.setLocation(new Location(subEventsTable.getStreet(subEventID), subEventsTable.getCity(subEventID),
                subEventsTable.getState(subEventID), subEventsTable.getZipcode(subEventID), subEventsTable.getCountry(subEventID),
                subEventsTable.getDetails(subEventID)));
        subEvent.getTimeSchedule().setStartDateTime(subEventsTable.getStartDate(subEventID));
        subEvent.getTimeSchedule().setEndDateTime(subEventsTable.getEndDate(subEventID));

        return subEvent;
    }

    private ArrayList<User> rebuildOrganizerList(int eventID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<User> organizerList = new ArrayList<User>();
        ArrayList<Integer> organizerIDList = eventsTable.getOrganizerList(eventID);

        for (Participant user : userList) {
            if (organizerIDList.contains(user.getUserId())) {
                organizerList.add((User) user);
            }
        }
        return organizerList;
    }

    private ArrayList<Participant> rebuildParticipantList(int eventID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<Participant> participantList = new ArrayList<Participant>();
        ArrayList<Integer> participantIDList = eventsTable.getParticipantList(eventID);
        for (Participant participant : userList) {
            if (participantIDList.contains(participant.getUserId())) {
                participantList.add(participant);
            }
        }
        return participantList;
    }

    private ArrayList<Committee> rebuildCommitteeList(int eventID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<Committee> committeeList = new ArrayList<Committee>();
        for (Integer committeeID : eventsTable.getCommittee(eventID)) {
            committeeList.add(rebuildCommittee(committeeID, userList));
        }
        return committeeList;
    }

    private Committee rebuildCommittee(int committeeID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        Committee committee = new Committee(committeeID, committeesTable.getTitle(committeeID));

        try {
            committee.setMemberList(rebuildCommitteeMemberList(committeeID, userList));
            committee.setBudgetAccessList(rebuildBudgetAccessList(committeeID, userList));
            //System.out.println(usersTable.currentUIDList("USERS"));
            //System.out.println(committeesTable.getChairman(committeeID));
            //committee.setChair(usersTable.getUser(committeesTable.getChairman(committeeID)));
            committee.setTaskList(rebuildTaskList(committeeID, userList));
        } catch (DoesNotExistException e) {
            System.out.println("CATCHING:");
            e.printStackTrace();
        }

        Budget newBudget = new Budget();
        newBudget.setIncomeList(rebuildIncomeList(committeeID));
        newBudget.setExpenseList(rebuildExpenseList(committeeID));
        committee.setBudget(newBudget);
        return committee;
    }

    private ArrayList<User> rebuildCommitteeMemberList(int committeeID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<User> memberList = new ArrayList<User>();
        ArrayList<Integer> memberIDList = committeesTable.getCommitteeMembers(committeeID);

        for (Participant user : userList) {
            if (memberIDList.contains(user.getUserId())) {
                memberList.add((User) user);
            }
        }
        return memberList;
    }

    private ArrayList<User> rebuildBudgetAccessList(int committeeID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<User> budgetAccessList = new ArrayList<User>();
        ArrayList<Integer> budgetAccessIDList = committeesTable.getBudgetAccessList(committeeID);

        for (Participant user : userList) {
            if (budgetAccessIDList.contains(user.getUserId())) {
                budgetAccessList.add((User) user);
            }
        }
        return budgetAccessList;
    }

    private ArrayList<Task> rebuildTaskList(int committeeID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        for (Integer taskID : committeesTable.getTaskList(committeeID)) {
            taskList.add(rebuildTask(taskID, userList));
        }
        return taskList;

    }

    private Task rebuildTask(int taskID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        Task task = new Task(taskID, tasksTable.getDescription(taskID));
        task.setLocation(new Location(tasksTable.getStreet(taskID), tasksTable.getCity(taskID),
                tasksTable.getState(taskID), tasksTable.getZipcode(taskID), tasksTable.getCountry(taskID),
                tasksTable.getDetails(taskID)));
        task.getTimeSchedule().setStartDateTime(tasksTable.getStartDate(taskID));
        task.getTimeSchedule().setEndDateTime(tasksTable.getEndDate(taskID));

        task.setResponsibleList(rebuildResponsibleList(taskID, userList));
        task.setCompleted(tasksTable.getComplete(taskID) == 1 ? true : false);

        return task;
    }

    private ArrayList<User> rebuildResponsibleList(int taskID, ArrayList<Participant> userList)
            throws DoesNotExistException {
        ArrayList<User> responsibleList = new ArrayList<User>();
        ArrayList<Integer> responsibleIDList = tasksTable.getAuthority(taskID);

        for (Participant user : userList) {
            if (responsibleIDList.contains(user.getUserId())) {
                responsibleList.add((User) user);
            }
        }
        return responsibleList;
    }

    private ArrayList<Income> rebuildIncomeList(int committeeID) throws DoesNotExistException {
        ArrayList<Income> incomeList = new ArrayList<Income>();
        ArrayList<Integer> incomeIDList = committeesTable.getIncome(committeeID);

        for (Integer incomeID : incomeIDList) {
            incomeList.add(rebuildIncome(incomeID));
        }
        return incomeList;
    }

    private Income rebuildIncome(int incomeID) throws DoesNotExistException {
        Income income = new Income(incomeID, incomeTable.getValue(incomeID), incomeTable.getDescription(incomeID));
        return income;
    }

    private ArrayList<Expense> rebuildExpenseList(int committeeID) throws DoesNotExistException {

        ArrayList<Expense> expenseList = new ArrayList<Expense>();
        ArrayList<Integer> expenseIDList = committeesTable.getExpense(committeeID);

        for (Integer expenseID : expenseIDList) {
            expenseList.add(rebuildExpense(expenseID));
        }
        return expenseList;

    }

    private Expense rebuildExpense(int expenseID) throws DoesNotExistException {
        Expense expense = new Expense(expenseID, expenseTable.getValue(expenseID), expenseTable.getDescription(expenseID));
        return expense;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setSelectedEvent(Event selectedEvent) {
        //System.out.println("checking event selection");
        this.selectedEvent = selectedEvent;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void createEvent(Event newEvent, User loggedInUser)
            throws PrivilegeInsufficientException, DuplicateInsertionException {
        if (PrivilegeManager.hasEventCreationPrivilege(loggedInUser)) {
            // write to database

            // FIX THIS STUFF

            ArrayList<Integer> organizerIDList, subEventIDList, participantIDList, committeeIDList;
            organizerIDList = new ArrayList<Integer>();
            organizerIDList.add(loggedInUser.getUserId());
            subEventIDList = new ArrayList<Integer>();
            participantIDList = new ArrayList<Integer>();
            committeeIDList = new ArrayList<Integer>();

            Event event = new Event(eventsTable.createEvent(new InputEventData(newEvent.getDescription(), newEvent.getLocation().getDetails(),
                    "IGNORE", newEvent.getTimeSchedule().getStartDateTimeTimestamp(), newEvent.getTimeSchedule().getEndDateTimeTimestamp(),
                    0, committeeIDList, organizerIDList, subEventIDList, participantIDList,
                    newEvent.getLocation().getStreet(), newEvent.getLocation().getCity(),
                    newEvent.getLocation().getState(), newEvent.getLocation().getZipCode(),
                    newEvent.getLocation().getCountry())), newEvent);

            eventList.add(event);
            selectedEvent = event;
        }
    }

    public void deleteEvent(User loggedInUser)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {

            // remove the event from the database
            for (int i = 0; i < selectedEvent.getCommitteeList().size(); i++) {
                deleteCommittee(selectedEvent.getCommitteeList().get(i), loggedInUser);
            }
            for (int m = 0; m < selectedEvent.getSubEventList().size(); m++) {
                deleteSubEvent(selectedEvent.getSubEventList().get(m), loggedInUser);
            }
            for (int n = 0; n < selectedEvent.getParticipantList().size(); n++) {
                if (!(selectedEvent.getParticipantList().get(n) instanceof User)) {
                    usersTable.removeUser(selectedEvent.getParticipantList().get(n).getUserId());
                }
                selectedEvent.getParticipantList().remove(n);
            }
            eventsTable.removeEvent(selectedEvent.getEVENT_ID());
            eventList.remove(selectedEvent.getEVENT_ID());
            selectedEvent = null;
        }
    }

    public void addOrganizer(User organizer, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().add(organizer);
            // write to database

            Integer organizerUserID = new Integer(usersTable.getUIDByEmail(organizer.getEmailAddress()));
            ArrayList<Integer> newOrganizerList = eventsTable.getOrganizerList(selectedEvent.getEVENT_ID());
            newOrganizerList.add(organizerUserID);
            eventsTable.setOrganizerList(selectedEvent.getEVENT_ID(), newOrganizerList);
        }
    }

    public void removeOrganizer(User organizer, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getOrganizerList().remove(organizer);
            // write to database

            Integer organizerUserID = new Integer(usersTable.getUIDByEmail(organizer.getEmailAddress()));
            ArrayList<Integer> newOrganizerList =
                    eventsTable.getOrganizerList(selectedEvent.getEVENT_ID());
            newOrganizerList.remove(organizerUserID);
            eventsTable.setOrganizerList(selectedEvent.getEVENT_ID(),
                    newOrganizerList);

        }
    }

    public void createSubEvent(SubEvent subEvent, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getSubEventList().add(subEvent);

            Integer subEventID = new Integer(subEventsTable.createSubEvent(new InputSubEventData(subEvent.getDescription(),
                    subEvent.getLocation().getDetails(), 0, subEvent.getLocation().getStreet(),
                    subEvent.getLocation().getCity(), subEvent.getLocation().getState(),
                    subEvent.getLocation().getZipCode(),
                    subEvent.getLocation().getCountry(), subEvent.getTimeSchedule().getStartDateTimeTimestamp(),
                    subEvent.getTimeSchedule().getEndDateTimeTimestamp())));
            ArrayList<Integer> newSubEventList = eventsTable.getSubEventList(selectedEvent.getEVENT_ID());
            newSubEventList.add(subEventID);
            eventsTable.setSubEventList(selectedEvent.getEVENT_ID(), newSubEventList);
        }
    }

    public void deleteSubEvent(SubEvent subEvent, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {

            Integer subEventID = new Integer(subEvent.getSUB_EVENT_ID());
            ArrayList<Integer> newSubEventList = eventsTable.getSubEventList(selectedEvent.getEVENT_ID());
            newSubEventList.remove(subEventID);
            eventsTable.setSubEventList(selectedEvent.getEVENT_ID(), newSubEventList);
            subEventsTable.removeSubEvent(subEventID);
            selectedEvent.getSubEventList().remove(subEvent);
        }
    }

    public void createCommittee(Committee committee, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getCommitteeList().add(committee);
            committee.setChair(loggedInUser);

            //System.out.println("committee should be successfully added");
            // write to database

            ArrayList<Integer> budgetAccessIDList = new ArrayList<Integer>();
            for (int i = 0; i < committee.getBudgetAccessList().size(); i++) {
                budgetAccessIDList.add(committee.getBudgetAccessList().get(i).getUserId());
            }
            ArrayList<Integer> memberIDList = new ArrayList<Integer>();
            for (int i = 0; i < committee.getMemberList().size(); i++) {
                memberIDList.add(committee.getMemberList().get(i).getUserId());
            }
            ArrayList<Integer> incomeIDList = new ArrayList<Integer>();
            for (int i = 0; i < committee.getBudget().getIncomeList().size(); i++) {
                incomeIDList.add(committee.getBudget().getIncomeList().get(i).getBUDGET_ITEM_ID());
            }
            ArrayList<Integer> expenseIDList = new ArrayList<Integer>();
            for (int i = 0; i < committee.getBudget().getExpenseList().size(); i++) {
                expenseIDList.add(committee.getBudget().getExpenseList().get(i).getBUDGET_ITEM_ID());
            }
            ArrayList<Integer> taskIDList = new ArrayList<Integer>();
            for (int i = 0; i < committee.getTaskList().size(); i++) {
                taskIDList.add(committee.getTaskList().get(i).getTASK_ID());
            }

            Integer committeeID = new Integer(committeesTable.createCommittee(new InputCommittee(committee.getTitle(),
                    committee.getChair().getUserId(), budgetAccessIDList, memberIDList, taskIDList,
                    incomeIDList, expenseIDList, committee.getCOMMITTEE_ID())));

            ArrayList<Integer> newCommitteeList = eventsTable.getCommittee(selectedEvent.getEVENT_ID());
            newCommitteeList.add(committeeID);
            eventsTable.setCommittee(selectedEvent.getEVENT_ID(), newCommitteeList);

            //System.out.println("leaving add committee method");
        }
    }

    public void deleteCommittee(Committee committee, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {

            int committeeID = committee.getCOMMITTEE_ID();

            ArrayList<Integer> tempIDList = committeesTable.getTaskList(committeeID);
            for (Integer tempID : tempIDList) {
                tasksTable.removeTask(tempID);
            }

            tempIDList = committeesTable.getIncome(committeeID);
            for (Integer tempID : tempIDList) {
                incomeTable.removeBudgetItem(tempID);
            }

            tempIDList = committeesTable.getExpense(committeeID);
            for (Integer tempID : tempIDList) {
                expenseTable.removeBudgetItem(tempID);
            }

            tempIDList = eventsTable.getCommittee(selectedEvent.getEVENT_ID());
            tempIDList.remove(committeeID);
            eventsTable.setCommittee(selectedEvent.getEVENT_ID(), tempIDList);

            committeesTable.removeCommittee(committeeID);
            selectedEvent.getCommitteeList().remove(committee);
            // remove all related database entries
        }
    }

    public void addParticipant(Participant participant) throws DoesNotExistException {
        selectedEvent.getParticipantList().add(participant);
        // write to database


        Integer participantID = new Integer(participant.getUserId());
        ArrayList<Integer> newParticipantList = eventsTable.getParticipantList(selectedEvent.getEVENT_ID());
        newParticipantList.add(participantID);
        eventsTable.setParticipantList(selectedEvent.getEVENT_ID(), newParticipantList);

    }

    public void removeParticipant(Participant participant) throws DoesNotExistException {
        selectedEvent.getParticipantList().remove(participant);
        // write to database


        Integer participantID = new Integer(participant.getUserId());
        ArrayList<Integer> newParticipantList = eventsTable.getParticipantList(selectedEvent.getEVENT_ID());
        newParticipantList.remove(participantID);
        eventsTable.setParticipantList(selectedEvent.getEVENT_ID(), newParticipantList);

    }

    public void editDescription(String description, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setDescription(description);
            // write to database


            eventsTable.setDescription(selectedEvent.getEVENT_ID(), description);

        }
    }

    public void editLocation(Location location, User loggedInUser) throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.setLocation(location);
            // write to database


            eventsTable.setDetails(selectedEvent.getEVENT_ID(), location.getDetails());
            eventsTable.setStreet(selectedEvent.getEVENT_ID(), location.getStreet());
            eventsTable.setCity(selectedEvent.getEVENT_ID(), location.getCity());
            eventsTable.setState(selectedEvent.getEVENT_ID(), location.getState());
            eventsTable.setZipcode(selectedEvent.getEVENT_ID(), location.getZipCode());
            eventsTable.setCountry(selectedEvent.getEVENT_ID(), location.getCountry());

        }
    }

    public void editStartDateTime(int year, int month, int day, int hour, int minute, User loggedInUser)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getTimeSchedule().setStartDateTime(year, month, day, hour, minute);
            // write to database


            eventsTable.setStartDate(selectedEvent.getEVENT_ID(), selectedEvent.getTimeSchedule().getStartDateTimeTimestamp());

        }
    }

    public void editEndDateTime(int year, int month, int day, int hour, int minute, User loggedInUser)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasEventPrivilege(loggedInUser, selectedEvent)) {
            selectedEvent.getTimeSchedule().setEndDateTime(year, month, day, hour, minute);
            // write to database


            eventsTable.setEndDate(selectedEvent.getEVENT_ID(), selectedEvent.getTimeSchedule().getEndDateTimeTimestamp());

        }
    }
}