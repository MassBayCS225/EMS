package BackEnd.EventSystem;

import java.util.ArrayList;

/**
 *
 * @author Shaunt
 */

public class Budget {
    
    private int BUDGET_ID;
    ArrayList<Income> incomeList;
    ArrayList<Expense> expenseList;
    
    public Budget(int budget_id) {
        this.BUDGET_ID = budget_id;
        this.incomeList = new ArrayList<Income>();
        this.expenseList = new ArrayList<Expense>();
    }
    
    public Budget(int budgetID, Budget budget){
        this.BUDGET_ID = budgetID;
        incomeList = budget.getIncomeList();
        expenseList = budget.getExpenseList();
    }

    public void setBUDGET_ID(int BUDGET_ID) {
        this.BUDGET_ID = BUDGET_ID;
    }

    public void setIncomeList(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public void setExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public int getBUDGET_ID() {
        return BUDGET_ID;
    }

    public ArrayList<Income> getIncomeList() {
        return incomeList;
    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }
    
    
    public double getTotalBudget() {
        double total = 0;
        for(int i = 0; i < incomeList.size(); i++) {
            total += incomeList.get(i).getValue();
        }
        for(int i = 0; i < expenseList.size(); i++) {
            total -= expenseList.get(i).getValue();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "Budget{" + "BUDGET_ID=" + 
                BUDGET_ID + ", incomeList=" + incomeList + ", expenseList=" + 
                expenseList + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Budget other = (Budget) obj;
        if (this.BUDGET_ID != other.BUDGET_ID) {
            return false;
        }
        if (this.incomeList != other.incomeList && (this.incomeList == null || !this.incomeList.equals(other.incomeList))) {
            return false;
        }
        if (this.expenseList != other.expenseList && (this.expenseList == null || !this.expenseList.equals(other.expenseList))) {
            return false;
        }
        return true;
    }
    
    public String getReport() {
        //TODO
        return null;
    }
    
    
}
