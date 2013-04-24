package BackEnd;
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
    
    private void generateEVENT_ID() {
        
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
        
    }
    
    public String toString() {
        
    }
    
    public ArrayList<String> getReport() {
        
    }
}
