package EMS_Database;

/**
 * An object class for use with implementing a new user into the database
 *
 * @author Mike Meding
 */
public class User {

    private int uid;
    private int level;
    private String uname;
    private String pwd;
    private String email;
    
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public User() {
    }

    /** 
     * User function constructor creates new user with parameters specified
     * 
     * @param uid The unique user identification number. Does not check
     * for duplicates upon creation.
     * @param level The created users privilege level as an Integer.
     * @param uname The String username of the user
     * @param pwd The protected password of the user (should be hashed.) as a
     * String
     * @param email The email of the user as a String.
     * @param phone The phone number represented as a String 
     * @param street Personal info as a String default is null
     * @param city Personal info as a String default is null
     * @param state Personal info as a String default is null
     * @param zipcode Personal info as a String default is null
     * @param country Personal info as a String default is null
     * 
     */
    public User(int uid, int level, String uname, String pwd, String email, String phone, String street, String city , String state, String zipcode, String country) {
        this.uid = uid;
        this.level = level;
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
        
        this.phone = phone;
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

    public int getLevel() {
        return level;
    }

    public String getName() {
        return uname;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
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

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
