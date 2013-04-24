import java.util.ArrayList;

/**
 * This class represents a phone number.
 * @author Anderson Santana
 */
public class PhoneNumber{
  
  private char[] areaCode;
  private char[] exchangeNumber;
  private char[] localNumber;
  private ArrayList<Character> extensionNumber;
  private final int AREA_CODE_LENGTH = 3;
  private final int EXCHANGE_NUMBER_LENGTH = 3;
  private final int LOCAL_NUMBER_LENGTH = 4;
  private final char[] VALID_SYMBOLS = {'-', '.', '(', ')'};
  
  public PhoneNumber(String digits)
  {
    // Throws exceptions
    int digitAmount = 0;
    for(int i = digitAmount; i < AREA_CODE_LENGTH; digitAmount++)
      areaCode = digits.charAt(digitAmount);
    
    //Strip symbols inside loop?
    for(int i = digitAmount; i < EXCHANGE_NUMBER_LENGTH; digitAmount++)
      exchangeNumber = digits.charAt(digitAmount);
  }
}