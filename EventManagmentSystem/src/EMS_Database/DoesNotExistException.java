/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS_Database;

/**
 *
 * @author mike
 */
public class DoesNotExistException extends Exception {

    public DoesNotExistException() {
        System.out.println("search error!");
    }

    public DoesNotExistException(String type) {
        System.out.println(type + " data does not exist.");
    }
}
