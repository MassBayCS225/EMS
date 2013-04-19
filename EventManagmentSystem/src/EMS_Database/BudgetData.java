/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS_Database;

/**
 *
 * @author mike
 */
public class BudgetData implements Interface_BudgetData{
    private Interface_BudgetData bd;
    public BudgetData(String budgetType){
        bd = BudgetDataFactory.create(budgetType);
        
    }

    @Override
    public void addExpense() {
        bd.addExpense();
    }

    @Override
    public void removeExpense() {
        bd.removeExpense();
    }
    
}
