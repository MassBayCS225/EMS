package BackEnd.EventSystem;

import java.util.ArrayList;

/**
 *
 * @author Shaunt
 */

public class Budget {

    ArrayList<Income> incomeList;
    ArrayList<Expense> expenseList;
    
    public Budget() {
        this.incomeList = new ArrayList<Income>();
        this.expenseList = new ArrayList<Expense>();
    }
    
    public Budget(Budget budget){
        incomeList = budget.getIncomeList();
        expenseList = budget.getExpenseList();
    }

    public void setIncomeList(ArrayList<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public void setExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
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
        return "Budget{" + ", incomeList=" + incomeList + ", expenseList=" + 
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
