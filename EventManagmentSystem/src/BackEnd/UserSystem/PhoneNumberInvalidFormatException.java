package BackEnd.UserSystem;

/**
 * PhoneNumberInvalidFormatException Exception
 * @author Anderson Santana
 */
public class PhoneNumberInvalidFormatException extends RuntimeException{
  
  /**
   * Default Constructor.
   */
  public PhoneNumberInvalidFormatException(){}
  
  /**
   * Constructor.
   * @param reasoon The reason of the exception.
   */
  public PhoneNumberInvalidFormatException(String reason){
    super(reason);
  }
}