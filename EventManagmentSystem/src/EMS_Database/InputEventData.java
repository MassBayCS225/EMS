package EMS_Database;

import java.sql.Timestamp;

/**
 *
 * @author mike
 */
public class InputEventData {

    private String description;
    private String location;
    private Timestamp startDate;
    private Timestamp endDate;
    private int complete;
    private String committee; // as a list of committees in charge of event

    public InputEventData(String description, String location, Timestamp startDate, Timestamp endDate, int complete, String committee) {
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.committee = committee;
    }

    //SETTERS    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void setCommittee(String committee) {
        this.committee = committee;
    }

    //GETTERS    
    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getComplete() {
        return complete;
    }

    public String getCommittee() {
        return committee;
    }
}
