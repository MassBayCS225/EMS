package BackEnd.ManagerSystem;

import BackEnd.UserSystem.Participant;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import EMS_Database.impl.UserData_Table;
import java.util.ArrayList;

/**
 *
 * @author David Tersoff
 */
public class LoginManager {

    private User loggedInUser;
    private ArrayList<Participant> userList;
    private final String INCORRECT_LOG_IN = "Incorrect log in information.";

    public LoginManager(ArrayList<Participant> userList) {
	this.userList = userList;
    }

    public void setLoggedInUser(String emailAddress, String password)
	    throws LogInIncorrectException {

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

    private boolean checkPassword(User user, String password) throws LogInIncorrectException {

	if (user.getPassword().equals("" + password.hashCode())) {
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