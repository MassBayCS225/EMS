package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import java.sql.Timestamp;

import EMS_Database.*;
import EMS_Database.impl.*;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class BudgetItemManager {

    private Income_Table incomeTable;
    private Expense_Table expenseTable;
    private BudgetItem selectedBudgetItem;

    public BudgetItemManager() {
        incomeTable = new Income_Table();
        expenseTable = new Expense_Table();
    }

    public Income_Table getIncomeTable() {
        return incomeTable;
    }

    public Expense_Table getExpenseTable() {
        return expenseTable;
    }

    public void setSelectedBudgetItem(BudgetItem selectedBudgetItem) {
        this.selectedBudgetItem = selectedBudgetItem;
    }

    public BudgetItem getSelectedBudgetItem() {
        return selectedBudgetItem;
    }

    public void editValue(int value, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setValue(value);
            // write to database
            if (selectedBudgetItem instanceof Income) {
                incomeTable.setValue(selectedBudgetItem.getBUDGET_ITEM_ID(), value);
            } else {
                expenseTable.setValue(selectedBudgetItem.getBUDGET_ITEM_ID(), value);
            }
        }
    }

    public void editDescription(String description, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setDescription(description);
            // write to database

            if (selectedBudgetItem instanceof Income) {
                incomeTable.setDescription(selectedBudgetItem.getBUDGET_ITEM_ID(), description);
            } else {
                expenseTable.setDescription(selectedBudgetItem.getBUDGET_ITEM_ID(), description);
            }
        }
    }

    public void editDate(int year, int month, int day, int hour, int minute, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudgetItem.setDate(year, month, day, hour, minute);
            // write to database

            if (selectedBudgetItem instanceof Income) {
                incomeTable.setDate(selectedBudgetItem.getBUDGET_ITEM_ID(), selectedBudgetItem.getDate());
            } else {
                incomeTable.setDate(selectedBudgetItem.getBUDGET_ITEM_ID(), selectedBudgetItem.getDate());
            }
        }
    }
}
