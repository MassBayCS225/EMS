/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem;

/**
 *
 * @author Julian Kuk
 */
public class BudgetManager {

    private Budget selectedBudget;

    public BudgetManager() {
    }

    public void setSelectedBudget(Budget budget) {
        this.selectedBudget = budget;
    }

    public Budget getSelectedBudget() {
        return selectedBudget;
    }

    public void addIncome(Income income) {
        if (PrivilegeManager.hasBudgetPrivilege() {
            selectedBudget.getIncomeList().add(income);
            // write to database
        }
    }

    public void removeIncome(Income income) {
        if (PrivilegeManager.hasBudgetPrivilege() {
            selectedBudget.getIncomeList().remove(income);
            // write to database
        }
    }

    public void addExpense(Expense expense) {
        if (PrivilegeManager.hasBudgetPrivilege() {
            selectedBudget.getExpenseList().add(expense);
            // write to database
        }
    }

    public void removeExpense(Expense expense) {
        if (PrivilegeManager.hasBudgetPrivilege() {
            selectedBudget.getExpenseList().remove(expense);
            // write to database
        }
    }
}