package BackEnd.EventSystem;

import BackEnd.UserSystem.User;
import BackEnd.UserSystem.Participant;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */

public class Event extends ScheduleItem implements Reportable {
    private int EVENT_ID;
    private ArrayList<User> organizerList;
    private ArrayList<SubEvent> subEventList;
    private ArrayList<Committee> committeeList;
    private ArrayList<Participant> participantList;
    
    public Event(){
        super();
        organizerList = new ArrayList<User>();
        subEventList = new ArrayList<SubEvent>();
        committeeList = new ArrayList<Committee>();
        participantList = new ArrayList<Participant>();
    }
    
    public Event(int event_id, String description) {
        super(description);
        EVENT_ID = event_id;
        organizerList = new ArrayList<User>();
        subEventList = new ArrayList<SubEvent>();
        committeeList = new ArrayList<Committee>();
        participantList = new ArrayList<Participant>();
    }
    
    public Event(int eventID, Event event){
        super((ScheduleItem)event);
        EVENT_ID = eventID;
        organizerList = event.getOrganizerList();
        subEventList = event.getSubEventList();
        committeeList = event.getCommitteeList();
        participantList = event.getParticipantList();
    }
    
    public boolean isReady() {
        boolean eventReady = true;
        
        for (Committee committee : committeeList)
            if (committee.isFinished() == false)
                eventReady = false;
        
        return eventReady;
    }
    
    private void setEVENT_ID(int event_id) {
        EVENT_ID = event_id;
    }
    
    public int getEVENT_ID() {
        return EVENT_ID;
    }
    
    public void setOrganizerList(ArrayList<User> organizerList) {
        this.organizerList = organizerList;
    }
    
    public ArrayList<User> getOrganizerList() {
        return organizerList;
    }
    
    public void setSubEventList(ArrayList<SubEvent> subEventList) {
        this.subEventList = subEventList;
    }
    
    public ArrayList<SubEvent> getSubEventList() {
        return subEventList;
    }
    
    public void setCommitteeList(ArrayList<Committee> committeeList) {
        this.committeeList = committeeList;
    }
    
    public ArrayList<Committee> getCommitteeList() {
        return committeeList;
    }
    
    public void setParticipantList(ArrayList<Participant> participantList) {
        this.participantList = participantList;
    }
    
    public ArrayList<Participant> getParticipantList() {
        return participantList;
    }
    
    public boolean equals(Event event) {
        if (this.getEVENT_ID() == event.getEVENT_ID()
                && this.getOrganizerList().equals(event.getOrganizerList())
                && this.getSubEventList().equals(event.getSubEventList())
                && this.getCommitteeList().equals(event.getCommitteeList())
                && this.getParticipantList().equals(event.getParticipantList()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        return "Event Description: \n" + super.getDescription() + "\n\n" + super.getLocation().toString() + 
                "\n\n" + super.getTimeSchedule().toString();
    }
    
    public ArrayList<String> getReport() {
        // I had to change this sorry. It was driving me crazy -MM
        throw new UnsupportedOperationException("Not supported yet.");
        // Just remove these lines when you are ready to implement this function -MM
    }
}
