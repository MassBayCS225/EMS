package EMS_Database;

import java.sql.Timestamp;

/**
 * This class is designed to gather all the data needed for the creation of a
 * task to be added to the task table.
 *
 * @author mike
 */
public class InputTask {

    private String descripton;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;    
    private Timestamp startDate;
    private Timestamp endDate;
    private int complete;
    private String manager;
    

    public InputTask(String descripton, String street, String city, String state, String zipcode, String country, Timestamp startDate, Timestamp endDate, int complete, String manager) {        
        this.descripton = descripton;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.manager = manager;
    }
    
    //GETTERS
    
    public String getDescripton() {
        return descripton;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public int getComplete() {
        return complete;
    }

    public String getManager() {
        return manager;
    }

    //SETTERS    

    public void setDescripton(String descripton) {
        this.descripton = descripton;
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

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }               
}
