package EMS_Database;

/**
 * This class is designed to gather all the data needed for the creation of a
 * task to be added to the task table.
 *
 * @author mike
 */
public class InputTask {

    private int uid;
    private String details;
    private String location;
    private String startTime;
    private String endTime;
    private int complete;
    private String authority;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public InputTask(int uid, String details, String location, String startTime, String endTime, int complete, String authority, String street, String city, String state, String zipcode, String country) {
        this.uid = uid;
        this.details = details;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.complete = complete;
        this.authority = authority;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }

    //GETTERS
    public int getUid() {
        return uid;
    }

    public String getDetails() {
        return details;
    }

    public String getLocation() {
        return location;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getComplete() {
        return complete;
    }

    public String getAuthority() {
        return authority;
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
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
