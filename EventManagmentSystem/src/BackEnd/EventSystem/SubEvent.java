package BackEnd.EventSystem;

import BackEnd.Participant;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */

public class SubEvent extends ScheduleItem implements Reportable {
    private int EVENT_ID, SUB_EVENT_ID;
    private ArrayList<Participant> participantList;
    
    public SubEvent(int event_id, int sub_event_id, String description) {
        super(description);
        EVENT_ID = event_id;
        SUB_EVENT_ID = sub_event_id;
        participantList = new ArrayList<Participant>();
    }
    
    public boolean isReady() {
         
    }
    
    public void setSUB_EVENT_ID(int sub_event_id) {
        SUB_EVENT_ID = sub_event_id;
    }
    
    public int getSUB_EVENT_ID() {
        return SUB_EVENT_ID;
    }
    
    // Not on UML; Added it.
    public int getEVENT_ID() {
        return EVENT_ID;
    }
    
    public void setParticipantList(ArrayList<Participant> participantList) {
        this.participantList = participantList;
    }
    
    public ArrayList<Participant> getParticipantList() {
        return participantList;
    }
    
    public boolean equals(SubEvent subEvent) {
        if (this.getEVENT_ID() == subEvent.getEVENT_ID()
                && this.getSUB_EVENT_ID() == subEvent.getSUB_EVENT_ID()
                && this.getParticipantList().equals(subEvent.getParticipantList()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        String participantListNames = "";
        
        for(Participant participant : participantList)
            participantListNames += participant.getFirstName() + " " + participant.getLastName() + "\n";
        
        return "Description: \n" + super.getDescription() + "\nEvent ID: " + EVENT_ID +
                "\nSub-Event ID: " + SUB_EVENT_ID + "\nParticipant List: \n" + participantListNames;
    }
    
    public ArrayList<String> getReport() {
       
    }
}
