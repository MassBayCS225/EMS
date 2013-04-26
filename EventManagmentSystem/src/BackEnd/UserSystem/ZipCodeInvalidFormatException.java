package BackEnd.UserSystem;

/**
 *  ZipCodeInvalidFormatException Exception
 *  @author Anderson Santana
 */
public class  ZipCodeInvalidFormatException extends RuntimeException{
  
  /**
   * Default Constructor.
   */
  public  ZipCodeInvalidFormatException(){}
  
  /**
   * Constructor.
   * @param reasoon The reason of the exception.
   */
  public  ZipCodeInvalidFormatException(String reason){
    super(reason);
  }
}