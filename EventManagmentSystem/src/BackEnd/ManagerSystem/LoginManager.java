package BackEnd.ManagerSystem;

import BackEnd.UserSystem.Participant;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author David Tersoff
 */
public class LoginManager {

    private User loggedInUser;
    private ArrayList<Participant> userList;
    private final String INCORRECT_LOG_IN = "Incorrect log in information.";
    private PasswordEncryptor encryptor;

    public LoginManager(ArrayList<Participant> userList)throws 
            NoSuchAlgorithmException, InvalidKeySpecException,
            DoesNotExistException, NoSuchPaddingException {
	this.userList = userList;
        encryptor = new PasswordEncryptor();
    }

    public void setLoggedInUser(String emailAddress, String password)
	    throws LogInIncorrectException, InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, IOException {

	User user = checkEmailAddress(emailAddress);
	checkPassword(user, password);
	loggedInUser = user;
    }

    private User checkEmailAddress(String emailAddress) throws LogInIncorrectException {
	
	for (int i = 0; i < userList.size(); i++) {
	    if (userList.get(i).getEmailAddress().equals(emailAddress)) {
		return (User) userList.get(i);
	    }
	}
	throw new LogInIncorrectException(INCORRECT_LOG_IN);
    }

    private boolean checkPassword(User user, String password) throws
            LogInIncorrectException, InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, IOException {

	if (user.getPassword().equals(encryptor.decrypt(password))) {
	    return true;
	} else {
	    throw new LogInIncorrectException(INCORRECT_LOG_IN);
	}

    }

    public User getLoggedInUser() {
	return loggedInUser;
    }

    public void logOut() {
	loggedInUser = null;
    }
}