package BackEnd.UserSystem;

/**
 * EmailAddressNonExistentException Exception
 * @author Anderson Santana
 */
public class EmailAddressNonExistentException extends RuntimeException{
  
  /**
   * Default Constructor.
   */
  public EmailAddressNonExistentException(){}
  
  /**
   * Constructor.
   * @param reasoon The reason of the exception.
   */
  public EmailAddressNonExistentException(String reason){
    super(reason);
  }
}