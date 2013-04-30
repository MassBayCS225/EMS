/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.EventSystem;

import java.sql.Timestamp;

/**
 *
 * @author Shaunt
 */
public class BudgetItem {
    
    private double value;
    private String description;
    private Timestamp date;
    
    public BudgetItem(double value, String description) {
        this.value = value;
        this.description = description;
        
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BudgetItem other = (BudgetItem) obj;
        if (this.value != other.value) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "BudgetItem{" + "value=" + value + ", description=" + description + ", date=" + date + '}';
    }
    
    
    
    
    
}
