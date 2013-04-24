package BackEnd;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */
public class SubEvent extends ScheduleItem implements Reportable {
    private int EVENT_ID, SUB_EVENT_ID;
    private ArrayList<Participant> participantList;
    
    public SubEvent(int event_id, String description) {
        super(description);
        EVENT_ID = event_id;
        SUB_EVENT_ID = 0; // Might change this.
        participantList = new ArrayList<Participant>();
    }
    
    public boolean isReady() {
        return 
    }
    
    public void generateSUB_EVENT_ID() {
        
    }
    
    public int getSUB_EVENT_ID() {
        return SUB_EVENT_ID;
    }
    
    public void setParticipantList(ArrayList<Participant> participantList) {
        this.participantList = participantList;
    }
    
    public ArrayList<Participant> getParticipantList() {
        return participantList;
    }
    
    public boolean equals(SubEvent subEvent) {
        return
    }
    
    public String toString() {
        return
    }
    
    public ArrayList<String> getReport() {
        return
    }
}
