package BackEnd.UserSystem;

/**
 *  ZipCodeInvalidLengthException Exception
 *  @author Anderson Santana
 */
public class  ZipCodeInvalidLengthException extends RuntimeException{
  
  /**
   * Default Constructor.
   */
  public  ZipCodeInvalidLengthException(){}
  
  /**
   * Constructor.
   * @param reasoon The reason of the exception.
   */
  public  ZipCodeInvalidLengthException(String reason){
    super(reason);
  }
}