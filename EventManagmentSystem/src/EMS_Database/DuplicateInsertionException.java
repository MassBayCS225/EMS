/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EMS_Database;

/**
 *
 * @author mike
 */
public class DuplicateInsertionException extends Exception {

    public DuplicateInsertionException() {
        System.out.println("data aleady exists");
    }

    public DuplicateInsertionException(String type) {
        System.out.println(type + " data already exists.");
    }
}
