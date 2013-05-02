package EMS_Database;

/**
 *
 * @author mike
 */
public class InputIncome {

    private String description;
    private double value;

    public InputIncome(String description, double value) {
	this.description = description;
	this.value = value;
    }

    //GETTERS
    public String getDescription() {
	return description;
    }

    public double getValue() {
	return value;
    }

    //SETTERS
    public void setDescription(String description) {
	this.description = description;
    }

    public void setValue(double value) {
	this.value = value;
    }
    
    
}
