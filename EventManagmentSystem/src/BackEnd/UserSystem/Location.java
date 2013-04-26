package BackEnd.UserSystem;

/**
 * This class represents a location.
 * @author Anderson Santana
 */
public class Location extends Address{
    private String details;
    
    /**
     * Constructor.
     * @param details The details of the location
     */
    public Location(String details){
        this.details = details;
    }
    
    /**
     * Overloaded Constructor.
     * @param details The details of the location
     * @param address The address location
     */
    public Location(String details, Address address){
        this.details = details;
    }
    
    /**
     * It sets the details of the location.
     * @param details The details of the location
     */
    public void setDetails(String details){
        this.details = details;
    }
    
    /**
     * It returns the details of the location.
     * @return The details of the location
     */
    public String getDetails(){
        return details;
    }
    
    /**
     * Determines whether the current object matches its argument.
     * @param obj The object to be compared to the current object
     * @return true if the object has the same details; otherwise,
     *          return false
     */
    @Override
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() == obj.getClass()){
            Location other = (Location) obj;
            return details.equals(other.details);
        } else {
            return false;
        }
    }
    
    /**
     * Creates a string representation of the location.
     * @return A string representation of the location
     */
    @Override
    public String toString(){
        return super.toString() + ": location details is " + details;
    }
}