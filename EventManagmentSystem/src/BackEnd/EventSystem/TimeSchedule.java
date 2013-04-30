package BackEnd.EventSystem;

import java.sql.Timestamp;

/**
 *
 * @author Ketty Lezama
 */

//Will completely edit.

public class TimeSchedule {
    private Timestamp startDateTime, endDateTime;
    
    TimeSchedule() {
        startDateTime = null;
        endDateTime = null;
    }
    
    public void setStartDateTime(Timestamp startDateTime){
        this.startDateTime = startDateTime;
    }
    
    public Timestamp getStartDateTime() {
        return startDateTime;
    }
    
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }
    
    public Timestamp getEndDateTime() {
        return endDateTime;
    }
    
    public boolean equals(TimeSchedule timeSchedule) {
        if (this.getStartDateTime().equals(timeSchedule.getStartDateTime()) 
                && this.getEndDateTime().equals(timeSchedule.getEndDateTime()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        return null;
    }
}
