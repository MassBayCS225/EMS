package BackEnd;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */
public class SubEvent extends ScheduleItem {
    private int EVENT_ID, SUB_EVENT_ID;
    private ArrayList<Participant> participantList;
    
    public SubEvent(int event_id, String description) {
        super(description);
        EVENT_ID = event_id;
        SUB_EVENT_ID = 0; // Might change this.
        participantList = new ArrayList<Participant>();
    }
    
}
