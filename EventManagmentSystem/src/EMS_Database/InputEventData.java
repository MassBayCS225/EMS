package EMS_Database;

import java.sql.Timestamp;

/**
 * Designed to be a input structure into Event table
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
    private String organizerList; // list of organizers
    private String subEventList;
    private String participantList;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public InputEventData(String description, String location, Timestamp startDate, Timestamp endDate, int complete, String committee, String organizerList, String subEventList, String participantList, String street, String city, String state, String zipcode, String country) {
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.committee = committee;
        this.organizerList = organizerList;
        this.subEventList = subEventList;
        this.participantList = participantList;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
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

    public String getOrganizerList() {
        return organizerList;
    }

    public String getSubEventList() {
        return subEventList;
    }

    public String getParticipantList() {
        return participantList;
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

    public void setOrganizerList(String organizerList) {
        this.organizerList = organizerList;
    }

    public void setSubEventList(String subEventList) {
        this.subEventList = subEventList;
    }

    public void setParticipantList(String participantList) {
        this.participantList = participantList;
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
    
    
}
