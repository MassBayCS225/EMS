package BackEnd.EventSystem;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Ketty Lezama
 */

//Will completely edit.

public class TimeSchedule {
    private Calendar startDateTime, endDateTime;
    
    TimeSchedule() {
        startDateTime = new GregorianCalendar();
        endDateTime = new GregorianCalendar();
    }
    
    // Returns -1 if either year or month input outside of range.
    private int numDaysInMonth(int year, int month) {
        if (year >= 2013 && year <= 9999) {
            if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11) return 31;
            else if (month == 3 || month == 5 || month == 8 || month == 10) return 30;
            else if (month == 1 && year % 4 == 0) return 29;
            else if (month == 1 && year % 4 != 0) return 28;
            else return -1;
        }
        else return -1;
    }
    
    public void setStartDateTime(int year, int month, int day, int hour, int minute){
        if (year >= 2013 && year <= 9999)
            startDateTime.set(Calendar.YEAR, year);
        
        if (month >= 0 && month < 12)
            startDateTime.set(Calendar.MONTH, month);
        
        if (day > 0 && day <= numDaysInMonth(month, year))
            startDateTime.set(Calendar.DAY_OF_MONTH, day);
        
        if (hour >= 0 && hour <= 24)
            startDateTime.set(Calendar.HOUR_OF_DAY, hour);
        
        if (minute >= 0 && minute < 60)
            startDateTime.set(Calendar.MINUTE, minute);
    }
    
    public Calendar getStartDateTimeCalendar() {
        return startDateTime;
    }
    
    public Timestamp getStartDateTimeTimestamp() {
        return new Timestamp(startDateTime.getTimeInMillis());
    }
    
    public void setEndDateTime(int year, int month, int day, int hour, int minute) {
        if (year >= 2013 && year <= 9999)
            endDateTime.set(Calendar.YEAR, year);
        
        if (month >= 0 && month < 12)
            endDateTime.set(Calendar.MONTH, month);
        
        if (day > 0 && day <= numDaysInMonth(month, year))
            endDateTime.set(Calendar.DAY_OF_MONTH, day);
        
        if (hour >= 0 && hour <= 24)
            endDateTime.set(Calendar.HOUR_OF_DAY, hour);
        
        if (minute >= 0 && minute < 60)
            endDateTime.set(Calendar.MINUTE, minute);
    }
    
    public Calendar getEndDateTimeCalendar() {
        return endDateTime;
    }
    
    public Timestamp getEndDateTimeTimestamp() {
        return new Timestamp(endDateTime.getTimeInMillis());
    }
    
    public boolean equals(TimeSchedule timeSchedule) {
        if (this.getStartDateTimeCalendar().equals(timeSchedule.getStartDateTimeCalendar()) 
                && this.getEndDateTimeCalendar().equals(timeSchedule.getEndDateTimeCalendar()))
            return true;
        else
            return false;
    }
    
    // Edit.
    public String toString() {
        return "Start Date & Time: " + startDateTime.get(Calendar.MONTH) + "/" + startDateTime.get(Calendar.DAY_OF_MONTH) +
                "/" + startDateTime.get(Calendar.YEAR) + " " + String.format("%02d", startDateTime.get(Calendar.HOUR_OF_DAY)) + ":" +
                String.format("%02d", startDateTime.get(Calendar.MINUTE)) + "\nEnd Date & Time: " + endDateTime.get(Calendar.MONTH) +
                "/" + endDateTime.get(Calendar.DAY_OF_MONTH) + "/" + endDateTime.get(Calendar.YEAR) + " " + 
                String.format("%02d", endDateTime.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", endDateTime.get(Calendar.MINUTE));
    }
}
