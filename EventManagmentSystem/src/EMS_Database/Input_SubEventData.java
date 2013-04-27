package EMS_Database;

import java.sql.Timestamp;

/**
 *
 * @author mike
 */
public class Input_SubEventData {
    
    private String description;    
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private Timestamp startTime;
    private Timestamp endTime;
    private int complete;

    public Input_SubEventData(String description, String street, String city, String state, String zipcode, String country, Timestamp startTime, Timestamp endTime, int complete) {
        this.description = description;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.startTime = startTime;
        this.endTime = endTime;
        this.complete = complete;
    }

    //GETTERS
    public String getDescription() {
        return description;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public int getComplete() {
        return complete;
    }
    
    //SETTERS
    public void setDescription(String description) {
        this.description = description;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }
    

    
}
