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
}
