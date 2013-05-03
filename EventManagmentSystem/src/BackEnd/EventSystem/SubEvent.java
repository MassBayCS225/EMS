package BackEnd.EventSystem;

import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */

public class SubEvent extends ScheduleItem implements Reportable {
    private int SUB_EVENT_ID;
    
    public SubEvent(int sub_event_id, String description) {
        super(description);
        SUB_EVENT_ID = sub_event_id;
    }
    
    public SubEvent(int subEventID, SubEvent subEvent){
        super((ScheduleItem)subEvent);
        SUB_EVENT_ID = subEventID;
    }
    
    private void setSUB_EVENT_ID(int sub_event_id) {
        SUB_EVENT_ID = sub_event_id;
    }
    
    public int getSUB_EVENT_ID() {
        return SUB_EVENT_ID;
    }
    
    public boolean equals(SubEvent subEvent) {
        if (this.getSUB_EVENT_ID() == subEvent.getSUB_EVENT_ID())
            return true;
        else
            return false;
    }
    
    public String toString() {
        return "Sub-Event Description: \n" + super.getDescription();
    }
    
    public ArrayList<String> getReport() {
       return null;
    }
}