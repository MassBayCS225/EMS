/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.ManagerSystem.ManagerExceptions;

/**
 * A custom exception class for a 
 *
 * @author Julian
 */
public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(){
    }
    
    public DuplicateEmailException(String message){
        super(message);
    }    
}
