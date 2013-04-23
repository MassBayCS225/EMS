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
     * 
     */
    public User(int uid, int level, String uname, String pwd, String email) {
        this.uid = uid;
        this.level = level;
        this.uname = uname;
        this.pwd = pwd;
        this.email = email;
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

    //SETTERS
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
