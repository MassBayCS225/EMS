package BackEnd.EventSystem;

import java.util.*;

/**
 *
 * @author Ketty Lezama
 */

public class TimeSchedule {
    private Calendar startDate, endDate, startTime, endTime;
    
    TimeSchedule() {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        startTime = new GregorianCalendar();
        endTime = new GregorianCalendar();
    }
    
    public void setStartDate(Calendar startDate){
        this.startDate = startDate;
    }
    
    public Calendar getStartDate() {
        return startDate;
    }
    
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    
    public Calendar getEndDate() {
        return endDate;
    }
    
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }
    
    public Calendar getStartTime() {
        return startTime;
    }
    
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
    
    public Calendar getEndTime() {
        return endTime;
    }
    
    public boolean equals(TimeSchedule timeSchedule) {
        if (this.getStartDate().equals(timeSchedule.getStartDate()) 
                && this.getEndDate().equals(timeSchedule.getEndDate())
                && this.getStartTime().equals(timeSchedule.getStartTime())
                && this.getEndTime().equals(timeSchedule.getEndTime()))
            return true;
        else
            return false;
    }
    
    public String toString() {
        
    }
}
