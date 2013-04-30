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
        startDateTime = new Timestamp(System.currentTimeMillis());
        endDateTime = new Timestamp(System.currentTimeMillis());
    }
    
    public void setStartDateTime(Timestamp startDateTime){
        this.startDateTime = startDateTime;
    }
    
    // String dateTime must be formatted "YYYY-MM-DD HH:MM:SS"
    public void setStartDateTime(String dateTime) {
        startDateTime = Timestamp.valueOf(dateTime);
    }
    
    public Timestamp getStartDateTime() {
        return startDateTime;
    }
    
    public String startDateTimeString() {
        return startDateTime.toString();
    }
    
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }
    
    // String dateTime must be formatted "YYYY-MM-DD HH:MM:SS"
    public void setEndDateTime(String dateTime) {
        endDateTime = Timestamp.valueOf(dateTime);
    }
    
    public Timestamp getEndDateTime() {
        return endDateTime;
    }
    
    public String endDateTimeString() {
        return endDateTime.toString();
    }
    
    public boolean equals(TimeSchedule timeSchedule) {
        if (this.getStartDateTime().equals(timeSchedule.getStartDateTime()) 
                && this.getEndDateTime().equals(timeSchedule.getEndDateTime()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        return "Start Date & Time: " + startDateTime.toString() + "\nEnd Date & Time: " + endDateTime.toString();
    }
}
