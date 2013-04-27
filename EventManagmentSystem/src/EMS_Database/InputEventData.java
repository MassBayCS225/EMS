package EMS_Database;

import java.sql.Timestamp;
import java.util.ArrayList;

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
    private ArrayList<Integer> committee; // as a list of committees in charge of event
    private ArrayList<Integer> organizerList; // list of organizers
    private ArrayList<Integer> subEventList;
    private ArrayList<Integer> participantList;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public InputEventData(String description, String location, Timestamp startDate, Timestamp endDate, int complete, String street, 
            String city, String state, String zipcode, String country, ArrayList<Integer> organizerList, ArrayList<Integer> subEventList, ArrayList<Integer> participantList , ArrayList<Integer> committee) {
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

    public ArrayList<Integer> getCommittee() {
        return committee;
    }

    public ArrayList<Integer> getOrganizerList() {
        return organizerList;
    }

    public ArrayList<Integer> getSubEventList() {
        return subEventList;
    }

    public ArrayList<Integer> getParticipantList() {
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

    public void setCommittee(ArrayList<Integer> committee) {
        this.committee = committee;
    }

    public void setOrganizerList(ArrayList<Integer> organizerList) {
        this.organizerList = organizerList;
    }

    public void setSubEventList(ArrayList<Integer> subEventList) {
        this.subEventList = subEventList;
    }

    public void setParticipantList(ArrayList<Integer> participantList) {
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
