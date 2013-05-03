/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

/**
 * This class serves as a collection of static methods that help manage whether
 * a given user has the correct privilege level to edit certain parts of an
 * event, or another user, etc.
 *
 * @author Julian Kuk
 */
public class PrivilegeManager {

    private static final String PRIVILEGE_INSUFFICIENT = "Privilege insufficient.";

    private PrivilegeManager() {
    }

    public static boolean hasAdminPrivilege(User loggedInUser) throws PrivilegeInsufficientException {
        if (loggedInUser.getAdminPrivilege()) {
            return true;
        } else {
            throw new PrivilegeInsufficientException(PRIVILEGE_INSUFFICIENT);
        }
    }

    public static boolean hasUserPrivilege(User loggedInUser, User selectedUser) throws PrivilegeInsufficientException {
        if (loggedInUser.equals(selectedUser)) {
            return true;
        } else {
            return hasAdminPrivilege(loggedInUser);
        }
    }

    public static boolean hasEventCreationPrivilege(User loggedInUser) throws PrivilegeInsufficientException {
        if (loggedInUser.getEventCreationPrivilege()) {
            return true;
        } else {
            return hasAdminPrivilege(loggedInUser);
        }
    }

    public static boolean hasEventPrivilege(User loggedInUser, Event selectedEvent) throws PrivilegeInsufficientException {
        if (selectedEvent.getOrganizerList().contains(loggedInUser)) {
            return true;
        } else {
            return hasAdminPrivilege(loggedInUser);
        }
    }

    public static boolean hasSubEventPrivilege(User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (selectedCommittee.getChair().equals(loggedInUser)) {
            return true;
        } else {
            return hasEventPrivilege(loggedInUser, selectedEvent);
        }
    }

    public static boolean hasCommitteePrivilege(User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (selectedCommittee.getChair().equals(loggedInUser)) {
            return true;
        } else {
            return hasEventPrivilege(loggedInUser, selectedEvent);
        }
    }

    public static boolean hasTaskPrivilege(User loggedInUser, Event selectedEvent, Committee selectedCommittee, Task selectedTask) throws PrivilegeInsufficientException {
        if (selectedTask.getResponsibleList().contains(loggedInUser)) {
            return true;
        } else {
            return hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee);
        }
    }

    public static boolean hasBudgetPrivilege(User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (selectedCommittee.getBudgetAccessList().contains(loggedInUser)) {
            return true;
        } else {
            return hasCommitteePrivilege(loggedInUser, selectedEvent, selectedCommittee);
        }
    }
}
