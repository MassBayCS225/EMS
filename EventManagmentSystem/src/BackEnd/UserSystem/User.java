package BackEnd.UserSystem;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Tersoff
 */
public class User extends Participant
{
    private final int USER_ID = 1;
    private String userName;
    private String password;
    private boolean adminPrivilege;
    private boolean eventCreationPrivilege;
    final private char[] ILLEGAL_CHARACTERS = {'@', '/', '\\', ' '};
    /**
     * Constructor, creates a User object
     * @param uName         the desired username
     * @param pword         the desired password
     * @param pwordMatch    the password entered a second time to verify it
     */
    public User(int user_ID, String firstName, String lastName, String emailAddress, String uName, String pword, String pwordMatch) throws PasswordMismatchError, IllegalCharacterException
    {
        
        super(firstName, lastName, emailAddress)
        setUserName(uName);
        if(verifyPassword(pword, pwordMatch))
            setPassword(pword);
        else
            throw new PasswordMismatchError("The passwords do not match.");
            
    }
    /**
     * Sets the username of a User
     * 
     * @param uName The new username
     * @throws IllegalCharacterException throws exception if the username
     * contains illegal characters
     */
    public void setUserName(String uName) throws IllegalCharacterException
    {
        if(checkCharacters(uName))
            userName = uName;
        else
            throw new IllegalCharacterException("Username contains illegal characters");
    }
    /**
     * Sets the password of a User
     * @param pword The new password
     * @throws IllegalCharacterException throws exception if the password
     * contains illegal characters
     */
    public void setPassword(String pword)throws IllegalCharacterException
    {
        if(checkCharacters(pword))
            password = pword;
        else
            throw new IllegalCharacterException("Password contains illegal characters");
    }
    private boolean verifyPassword(String pword, String pwordMatch)
    {
        if(pword.equals(pwordMatch))
            return true;
        else
            return false;
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }
    /**
     * Checks a String object such as a username or password to see whether or
     * not it contains any illegal characters.
     * 
     * @param s The String to be checked
     * @return  Returns false if the string contains illegal characters,
     *          otherwise returns true
     */
    public boolean checkCharacters(String s)
    {
        boolean b = true;
        for(int ic : ILLEGAL_CHARACTERS)
        {
            for(int x = 0; x < s.length(); x++)
            {
                if(ILLEGAL_CHARACTERS[ic]==s.charAt(x))
                    b = false;
            }
        }
        return b;
    }
    public void setAdminPrivilege(boolean b)
    {
        adminPrivilege = b;
    }
    public boolean getAdminPrivilege()
    {
        return adminPrivilege;
    }
    public void setEventCreationPrivilege(boolean b)
    {
        eventCreationPrivilege = b;
    }
    public boolean getEventCreationPrivilege(boolean b)
    {
        return eventCreationPrivilege;
    }
}
