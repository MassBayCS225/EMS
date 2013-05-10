package BackEnd.ManagerSystem;

import BackEnd.EventSystem.Budget;
import BackEnd.EventSystem.Committee;
import BackEnd.EventSystem.Event;
import BackEnd.EventSystem.Expense;
import BackEnd.EventSystem.Income;
import BackEnd.UserSystem.User;
import EMS_Database.DoesNotExistException;
import EMS_Database.DuplicateInsertionException;
import EMS_Database.InputExpense;
import EMS_Database.InputIncome;
import EMS_Database.impl.Committees_Table;
import EMS_Database.impl.Expense_Table;
import EMS_Database.impl.Income_Table;
import java.util.ArrayList;

/**
 * This class serves as a liaison between the GUI and the back end and the data.
 * It checks to see whether a user has the proper privileges to change
 * something, and if the user does, then edits the database accordingly. It also
 * provides ready-to-use methods for the GUI to call.
 *
 * @author Julian Kuk
 */
public class BudgetManager {

    private Committees_Table committeesTable;
    private Income_Table incomeTable;
    private Expense_Table expenseTable;
    private Budget selectedBudget;

    /**
     * initializes the budget manager
     *
     * @param committeesTable the committees table
     * @param incomeTable the income table
     * @param expenseTable the expense table
     */
    public BudgetManager(Committees_Table committeesTable, Income_Table incomeTable, Expense_Table expenseTable) {
        this.committeesTable = committeesTable;
        this.incomeTable = incomeTable;
        this.expenseTable = expenseTable;
    }

    /**
     * store the budget selected by the user
     *
     * @param selectedBudget the selected budget
     */
    public void setSelectedBudget(Budget selectedBudget) {
        this.selectedBudget = selectedBudget;
    }

    /**
     * return the selected budget
     *
     * @return the selected budget
     */
    public Budget getSelectedBudget() {
        return selectedBudget;
    }

    /**
     * create an income entry in the database, if the user has a sufficient
     * privilege level
     *
     * @param income the income object to create
     * @param loggedInUser the currently logged in user
     * @param selectedEvent the currently selected event
     * @param selectedCommittee the currently selected committee
     * @return the income object created in the database with the proper ID
     * @throws PrivilegeInsufficientException
     * @throws DoesNotExistException
     * @throws DuplicateInsertionException
     */
    public Income createIncome(Income income, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException, DuplicateInsertionException {

        Income newIncome = null;
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            newIncome = new Income(incomeTable.insertBudgetItem(new InputIncome(
                    income.getDescription(), income.getDate(), income.getValue())), income);
            selectedBudget.getIncomeList().add(newIncome);
            ArrayList<Integer> newIncomeIDList = committeesTable.getIncome(selectedCommittee.getCOMMITTEE_ID());
            newIncomeIDList.add(newIncome.getBUDGET_ITEM_ID());
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newIncomeIDList);
        }
        return newIncome;
    }

    /**
     * delete an income item from the database, if the user has a sufficient
     * privilege level
     *
     * @param income the income item to delete
     * @param loggedInUser the currently logged in user
     * @param selectedEvent the currently selected event
     * @param selectedCommittee the currently selected committee
     * @throws PrivilegeInsufficientException
     * @throws DoesNotExistException
     */
    public void deleteIncome(Income income, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {

        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudget.getIncomeList().remove(income);
            ArrayList<Integer> newIncomeIDList = committeesTable.getIncome(selectedCommittee.getCOMMITTEE_ID());
            newIncomeIDList.remove(new Integer(income.getBUDGET_ITEM_ID()));
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newIncomeIDList);
            incomeTable.removeBudgetItem(income.getBUDGET_ITEM_ID());
        }
    }

    /**
     * create an expense item in the database, if the user has a sufficient
     * privilege
     *
     * @param expense the expense item to add
     * @param loggedInUser the currently logged in user
     * @param selectedEvent the selected event
     * @param selectedCommittee the selected committee
     * @return the expense object created in the database with proper ID
     * @throws PrivilegeInsufficientException
     * @throws DuplicateInsertionException
     * @throws DoesNotExistException
     */
    public Expense createExpense(Expense expense, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException, DuplicateInsertionException {

        Expense newExpense = null;
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            newExpense = new Expense(expenseTable.insertBudgetItem(new InputExpense(
                    expense.getDescription(), expense.getDate(), expense.getValue())), expense);
            selectedBudget.getExpenseList().add(newExpense);
            ArrayList<Integer> newExpenseIDList = committeesTable.getExpense(selectedCommittee.getCOMMITTEE_ID());
            newExpenseIDList.add(newExpense.getBUDGET_ITEM_ID());
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newExpenseIDList);
        }
        return newExpense;
    }

    /**
     * delete an expense item from the database, if the user has a sufficient
     * privilege
     *
     * @param expense the expense item to delete
     * @param loggedInUser the currently logged in user
     * @param selectedEvent the currently selected event
     * @param selectedCommittee the currently selected committee
     * @throws PrivilegeInsufficientException
     * @throws DoesNotExistException
     */
    public void deleteExpense(Expense expense, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {

        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            selectedBudget.getExpenseList().remove(expense);
            ArrayList<Integer> newExpenseIDList = committeesTable.getExpense(selectedCommittee.getCOMMITTEE_ID());
            newExpenseIDList.remove(new Integer(expense.getBUDGET_ITEM_ID()));
            committeesTable.setExpense(selectedCommittee.getCOMMITTEE_ID(), newExpenseIDList);
            expenseTable.removeBudgetItem(expense.getBUDGET_ITEM_ID());
        }
    }
}