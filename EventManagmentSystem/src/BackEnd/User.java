package BackEnd;

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
    private boolean adminPrivelege;
    final private char[] ILLEGAL_CHARACTERS = {'@', '/', '\\', ' '};
    
    public User(String uName, String pword, String pwordMatch)
    {
        
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
}
