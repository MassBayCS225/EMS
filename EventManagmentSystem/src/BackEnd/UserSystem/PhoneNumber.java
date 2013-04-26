package BackEnd.UserSystem;

import java.util.ArrayList;

/**
 * This class represents a phone number.
 * @author Anderson Santana
 */
public class PhoneNumber{
  
  private final int AREA_CODE_LENGTH = 3;
  private final int EXCHANGE_NUMBER_LENGTH = 3;
  private final int LOCAL_NUMBER_LENGTH = 4;
  private final char[] VALID_SYMBOLS = {'-', '.', '(', ')'};
  private char[] areaCode = new char[AREA_CODE_LENGTH];
  private char[] exchangeNumber = new char[EXCHANGE_NUMBER_LENGTH];
  private char[] localNumber = new char[LOCAL_NUMBER_LENGTH];
  private ArrayList<Character> extensionNumber;
  
  
  public PhoneNumber(String digits){
    // Throws exceptions
    int digitAmount = 0;
    //try{
        for(int i = digitAmount; i < areaCode.length; digitAmount++){
        if(Character.isDigit(digits.charAt(digitAmount)))
          areaCode[digitAmount] = digits.charAt(digitAmount);
        
    }
    
    //Strip symbols inside or outside loop?
    //for(int i = digitAmount; i < EXCHANGE_NUMBER_LENGTH; digitAmount++)
      //exchangeNumber = digits.charAt(digitAmount);
    
    //catch()
  }
  
  
  public PhoneNumber(char[] areaCode, char[] exchangeNumber,
             char[] localNumber){
      this.areaCode = areaCode; 
  }
}