/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.EventSystem;

/**
 *
 * @author Shaunt
 */
public class Expense extends BudgetItem {
    
    public Expense(double value, String description) {
        super(value, description);
    }
    
    public Expense(int expenseID, BudgetItem budgetItem){
        super(expenseID, budgetItem);
    }
}
