package EMS_Database;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mike
 */
public class InputSubEventData {
    
    private String description;
    private String details;
    private int complete;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;        
    private Timestamp startTime;
    private Timestamp endTime;
    
    public InputSubEventData(){
	Timestamp time = new Timestamp(new Date().getTime()); //gets current time.
	this.description = "default description";
	this.details = "default details";	
	this.complete = 0;
	this.street = "default street";
	this.city = "default city";
	this.state = "default state";
	this.zipcode = "default zipcode";
	this.country = "default country";
	this.startTime = time;
	this.endTime = time;
    }

    public InputSubEventData(String description, String details, int complete, String street, String city, String state, String zipcode, String country, Timestamp startTime, Timestamp endTime) {
	this.description = description;
	this.details = details;
	this.complete = complete;
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
	this.country = country;
	this.startTime = startTime;
	this.endTime = endTime;
    }

    public String getDescription() {
	return description;
    }

    public String getDetails() {
	return details;
    }

    public int getComplete() {
	return complete;
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

    public void setDescription(String description) {
	this.description = description;
    }

    public void setDetails(String details) {
	this.details = details;
    }

    public void setComplete(int complete) {
	this.complete = complete;
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

   

    
}
