package BackEnd.ManagerSystem;

import BackEnd.EventSystem.*;
import BackEnd.UserSystem.*;

import EMS_Database.*;
import EMS_Database.impl.*;
import java.sql.Timestamp;
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
     * Default, no arg constructor. Builds the budget list.
     */
    public BudgetManager(Committees_Table committeesTable, Income_Table incomeTable, Expense_Table expenseTable) {
        this.committeesTable = committeesTable;
        this.incomeTable = incomeTable;
        this.expenseTable = expenseTable;
    }

    /**
     * Store the budget selected by the user.
     *
     * @param budget the budget that is selected by the user
     */
    public void setSelectedBudget(Budget selectedBudget) {
        this.selectedBudget = selectedBudget;
    }

    /**
     * Retrieve the budget selected by the user
     *
     * @return the budget selected by the user
     */
    public Budget getSelectedBudget() {
        return selectedBudget;
    }

    /**
     * Add an income item to the selected budget. Verifies the user's privilege
     * level first, then edits the database if privileged.
     *
     * @param income
     * @param loggedInUser
     * @param selectedEvent
     * @param selectedCommittee
     */
    
    public void addIncome(Income income, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException, DuplicateInsertionException {
        
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {          
            Income newIncome = new Income(incomeTable.insertBudgetItem(new InputIncome(income.getDescription(), income.getDate(), income.getValue()
                    )), income);
            selectedBudget.getIncomeList().add(newIncome);
            
            Integer incomeID = new Integer(newIncome.getBUDGET_ITEM_ID());
            ArrayList<Integer> newIncomeIDList = committeesTable.getIncome(selectedCommittee.getCOMMITTEE_ID());
            newIncomeIDList.add(incomeID);
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newIncomeIDList);
            // remove all related database entries
            
        }
    }

    /**
     * Remove an income item from the selected budget. Verifies the user's
     * privilege level first, then edits the database if privileged.
     *
     * @param income
     * @param loggedInUser
     * @param selectedEvent
     * @param selectedCommittee
     */
    public void removeIncome(Income income, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {

            // write to database

            Integer incomeID = new Integer(income.getBUDGET_ITEM_ID());
            ArrayList<Integer> newIncomeIDList = committeesTable.getIncome(selectedCommittee.getCOMMITTEE_ID());
            newIncomeIDList.remove(incomeID);
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newIncomeIDList);
            // remove all related database entries
            incomeTable.removeBudgetItem(incomeID);
            selectedBudget.getIncomeList().remove(income);
        }
    }

    /**
     * Add an expense to the selected budget. Verifies the user's privilege
     * level first, then edits the database if privileged.
     *
     * @param expense the expense item to add
     * @param loggedInUser the currently logged in user
     * @param selectedEvent the selected event
     * @param selectedCommittee the selected committee
     */
    public void addExpense(Expense expense, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException, DuplicateInsertionException {
        
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {

            // write to database
            
            Expense newExpense = new Expense(expenseTable.insertBudgetItem(new InputExpense(expense.getDescription(), expense.getDate(), expense.getValue()
                    )), expense);
            selectedBudget.getExpenseList().add(newExpense);
            
            Integer expenseID = new Integer(newExpense.getBUDGET_ITEM_ID());
            ArrayList<Integer> newExpenseIDList = committeesTable.getExpense(selectedCommittee.getCOMMITTEE_ID());
            newExpenseIDList.add(expenseID);
            committeesTable.setIncome(selectedCommittee.getCOMMITTEE_ID(), newExpenseIDList);
        }
    }

    /**
     * Remove an expense item from the selected budget. Verifies the user's
     * privilege level first, then edits the database if privileged.
     *
     * @param expense
     * @param loggedInUser
     * @param selectedEvent
     * @param selectedCommittee
     */
    public void removeExpense(Expense expense, User loggedInUser, Event selectedEvent, Committee selectedCommittee)
            throws PrivilegeInsufficientException, DoesNotExistException {
        
        if (PrivilegeManager.hasBudgetPrivilege(loggedInUser, selectedEvent, selectedCommittee)) {
            // write to database
            // remove all related database entries
            
                        Integer expenseID = new Integer(expense.getBUDGET_ITEM_ID());
            ArrayList<Integer> newExpenseIDList = committeesTable.getExpense(selectedCommittee.getCOMMITTEE_ID());
            newExpenseIDList.remove(expenseID);
            committeesTable.setExpense(selectedCommittee.getCOMMITTEE_ID(), newExpenseIDList);
            // remove all related database entries
            expenseTable.removeBudgetItem(expenseID);
            selectedBudget.getExpenseList().remove(expense);
        }
    }
}