package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.sql.Timestamp;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class BudgetItemManager {
    

    private BudgetItem selectedBudgetItem;

    public BudgetItemManager() {
    }

    public void setSelectedBudgetItem(BudgetItem selectedBudgetItem) {
        this.selectedBudgetItem = selectedBudgetItem;
    }

    public BudgetItem getSelectedBudgetItem() {
        return selectedBudgetItem;
    }

    public void editValue(int value, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setValue(value);
            // write to database
        }
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setDescription(description);
            // write to database
        }
    }

    public void editDate(Timestamp date, User loggedInUser, Event selectedEvent, Committee selectedCommittee) throws PrivilegeInsufficientException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setDate(date);
            // write to database
        }
    }
}
