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
    public User(String firstName, String lastName, String emailAddress, String uName, String pword, String pwordMatch) throws PasswordMismatchError, IllegalCharacterException
    {
        super(firstName, lastName, emailAddress);
        setUserName(uName);
        setPassword(pword, pwordMatch);
            
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
     * 
     * @param pword         The new password
     * @param pwordMatch    repeated password, for verification
     * @throws IllegalCharacterException    throws exception if the password contains illegal characters
     * @throws PasswordMismatchError        throws exception if the passwords don't match.
     */
    public void setPassword(String pword, String pwordMatch)throws IllegalCharacterException, PasswordMismatchError
    {
        if(checkCharacters(pword))
        {
            if(verifyPassword(pword, pwordMatch))
            {
                password = pword;
            }
            else
                throw new PasswordMismatchError();
        }
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
    /**
     * 
     * @return username
     */
    public String getUserName()
    {
        return userName;
    }
    /**
     * 
     * @return password
     */
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
    /**
     * 
     * @param b boolean value determining if the user has admin privileges
     */
    public void setAdminPrivilege(boolean b)
    {
        adminPrivilege = b;
    }
    /**
     * 
     * @return the user's admin privileges.
     */
    public boolean getAdminPrivilege()
    {
        return adminPrivilege;
    }
    /**
     * 
     * @param b boolean value determining if the user has event creation privileges
     */
    public void setEventCreationPrivilege(boolean b)
    {
        eventCreationPrivilege = b;
    }
    /**
     * 
     * @return the user's event creation privileges
     */
    public boolean getEventCreationPrivilege()
    {
        return eventCreationPrivilege;
    }
    public boolean equals(User user)
    {
        
    }
    public String toString()
    {
        
    }
}
