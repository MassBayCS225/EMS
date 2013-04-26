package BackEnd.EventSystem;

import BackEnd.User;
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
    
    public Event(int event_id, String description) {
        super(description);
        EVENT_ID = event_id;
        organizerList = new ArrayList<User>();
        subEventList = new ArrayList<SubEvent>();
        committeeList = new ArrayList<Committee>();
    }
    
    public boolean isReady() {
        boolean subEventsReady = true, committeesFinished = true;
        
        for (SubEvent subEvent : subEventList)
            if (subEvent.isReady() == false)
                subEventsReady = false;
        
        for (Committee committee : committeeList)
            if (committee.isFinished() == false)
                committeesFinished = false;
        
        return subEventsReady && committeesFinished;
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
    
    public boolean equals(Event event) {
        if (this.getEVENT_ID() == event.getEVENT_ID()
                && this.getOrganizerList().equals(event.getOrganizerList())
                && this.getSubEventList().equals(event.getSubEventList())
                && this.getCommitteeList().equals(event.getCommitteeList()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        String organizerNames = "", subEvents = "", committees = "";
        
        for(User user : organizerList)
            organizerNames += user.getName() + "\n"; // Change.
        
        return "Description: \n" + super.getDescription() + "Event ID: " + EVENT_ID +
                "\nOrganizer List:";
    }
    
    public ArrayList<String> getReport() {
        // I had to change this sorry. It was driving me crazy -MM
        throw new UnsupportedOperationException("Not supported yet.");
        // Just remove these lines when you are ready to implement this function -MM
    }
}
