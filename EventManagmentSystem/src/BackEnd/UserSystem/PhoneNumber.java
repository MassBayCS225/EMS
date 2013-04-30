package BackEnd.UserSystem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a phone number.
 * @author Anderson Santana
 */
public class PhoneNumber{
    private final int AREA_CODE_LENGTH = 3;
    private final int EXCHANGE_NUMBER_LENGTH = 3;
    private final int LOCAL_NUMBER_LENGTH = 4;
    private final int TOTAL_NUMBER_LENGTH = 10;
    private final char[] VALID_SYMBOLS = {'-', '.', '(', ')', ' '};
    private char[] areaCode = new char[AREA_CODE_LENGTH];
    private char[] exchangeNumber = new char[EXCHANGE_NUMBER_LENGTH];
    private char[] localNumber = new char[LOCAL_NUMBER_LENGTH];
    private ArrayList<Character> extensionNumber;
  
    /**
     * This constructor initializes the Phone Number object with
     * a string of digits.
     * @param digits The phone number
     */
    public PhoneNumber(String digits){
	//() and  -
        final int maxNumberOfSymbolsPerPhoneNumber = 3; 
        //two periods or two space characters
        final int minNumberOfSymbolsPerPhoneNumber = 2; 
        
        if(digits.length() > TOTAL_NUMBER_LENGTH + 
                maxNumberOfSymbolsPerPhoneNumber)
            throw new PhoneNumberInvalidLengthException(
                "Invalid phone number length");
        else if(digits.length() == TOTAL_NUMBER_LENGTH + 
                maxNumberOfSymbolsPerPhoneNumber  || digits.length() ==
                TOTAL_NUMBER_LENGTH + minNumberOfSymbolsPerPhoneNumber)
           setDigits(stripSymbols(digits));
        else if(digits.length() > TOTAL_NUMBER_LENGTH)
            throw new PhoneNumberInvalidFormatException(
                "Invalid phone number format");
        else{
           verifyNumeric(digits);
           setDigits(digits);
        }
    }
    
    /**
     *  This constructor initializes the phone number object with an
     *  area code, an exchange number,and a local number.   
     *  @param areaCode The area code
     *  @param exchangeNumber The exchange number
     *  @param localNumber The local number
     */
    public PhoneNumber(char[] areaCode, char[] exchangeNumber,
                         char[] localNumber){
        verifyNumeric(areaCode);
        verifyNumeric(exchangeNumber);
        verifyNumeric(localNumber);
        verifyAreaCodeLength(areaCode);
        verifyExchangeNumberLength(exchangeNumber);
        verifyLocalNumberLength(localNumber);
        this.areaCode = areaCode; 
        this.exchangeNumber = exchangeNumber;
        this.localNumber = localNumber;
    }
  
    /**
     * It removes all symbols from the phone number.
     * @param digits
     * @return All digits from the phone number without
     *           symbols
     */
    private String stripSymbols(String digits){
        String actualNumber = "";
        if(digits.charAt(0) == VALID_SYMBOLS[2] && 
           digits.charAt(4) == VALID_SYMBOLS[3] &&
           digits.charAt(8) == VALID_SYMBOLS[0]){
            for(int i = 0; i < digits.length(); i++){
                if(Character.isDigit(digits.charAt(i)))
                    actualNumber+=digits.charAt(i); // else do nothing
            }
        }
        else if((digits.charAt(3) == VALID_SYMBOLS[1] ||
                digits.charAt(3) == VALID_SYMBOLS[4]) && 
                digits.charAt(3) == digits.charAt(7)){
            for(int i = 0; i < digits.length(); i++){
                if(Character.isDigit(digits.charAt(i)))
                    actualNumber+=digits.charAt(i); // else do nothing
            }
        }
        else throw new PhoneNumberInvalidLengthException(
                "Invalid phone number length");
        verifyNumeric(actualNumber);
        return actualNumber;
    }
    
    /**
     * It sets all the digits of the phone number.
     * @param areaCode
     * @param exchangeNumber
     * @param localNumber 
     */
    public void setDigits(char[] areaCode, char[] exchangeNumber,
                          char[] localNumber){
        verifyNumeric(areaCode);
        verifyNumeric(exchangeNumber);
        verifyNumeric(localNumber);
        this.areaCode = areaCode; 
        this.exchangeNumber = exchangeNumber;
        this.localNumber = localNumber;
    }
    
    /**
     * It sets all the digits of the phone number.
     * @param digits 
     */
    public void setDigits(String digits){
        for(int i = 0; i < digits.length(); i++){
            if(i < AREA_CODE_LENGTH)
                areaCode[i] = digits.charAt(i);
            else if(i < AREA_CODE_LENGTH + EXCHANGE_NUMBER_LENGTH)
                exchangeNumber[i - AREA_CODE_LENGTH] = digits.charAt(i);
            else
                localNumber[i - (AREA_CODE_LENGTH + EXCHANGE_NUMBER_LENGTH)] = 
                        digits.charAt(i);
        }
    }
    
    /**
     * It verifies if the phone number is numeric.
     * @param digits
     * @return true if the phone number is numeric; otherwise 
     * throws an exception
     */
    private boolean verifyNumeric(char[] digits){
        for(int i = 0; i < digits.length; i++)
            if(!Character.isDigit(digits[i]))
                throw new PhoneNumberNonNumericException(
                    "Digits entered are not numeric");
        return true;
    }
  
    /**
     * It verifies if the phone number is numeric.
     * @param digits
     * @return true if the phone number is numeric; otherwise 
     * throws an exception
     */
    private boolean verifyNumeric(String digits){
        if(digits.length() < TOTAL_NUMBER_LENGTH)
            throw new PhoneNumberNonNumericException(
                    "Digits entered are not numeric");
        for(int i = 0; i < digits.length(); i++)
            if(!Character.isDigit(digits.charAt(i)))
                 throw new PhoneNumberNonNumericException(
                    "Digits entered are not numeric");
            return true;
    }
  
    /**
     * It sets the area code.
     * @param areaCode The area code.
     */
    public void setAreaCode(char[] areaCode){
          this.areaCode = areaCode; 
    }
  
    /**
     * It verifies if the area code length is correct.
     * @param areaCode
     * @return true if the area code length is correct; otherwise 
     *   return false
     */
    private boolean verifyAreaCodeLength(char[] areaCode){
          if(areaCode.length == AREA_CODE_LENGTH)
            return true;
          else throw new PhoneNumberInvalidLengthException(
                    "Invalid area code length");
    }
  
    /**
     * It returns the area code.
     * @return The area code
     */
    public char[] getAreaCode(){
        return areaCode;
    }
 
    /**
     * It sets the exchange number.
     * @param exchangeNumber The exchange number
     */
    public void setExchangeNumber(char[] exchangeNumber){
        this.exchangeNumber = exchangeNumber;
    }
  
    /**
     * It verifies if the exchange number length is correct.
     * @param exchangeNumber
     * @return true if the exchange number length is correct; otherwise 
     *   return false
     */
    private boolean verifyExchangeNumberLength(char[] exchangeNumber){
          if(exchangeNumber.length == EXCHANGE_NUMBER_LENGTH)
              return true;
          else throw new PhoneNumberInvalidLengthException(
                    "Invalid exchange number length");
    }
  
    /**
     * It returns the exchange number.
     * @return The exchange number
     */
    public char[] getExchangeNumber(){
         return exchangeNumber;
    }
  
    /**
     * It sets the local number.
     * @param localNumber The local number
     */
    public void setLocalNumber(char[] localNumber){
          this.localNumber = localNumber;
    }
    
    /**
     * It verifies if the local number length is correct.
     * @param localNumber
     * @return true if the local number length is correct; otherwise 
     *   return false
     */
    private boolean verifyLocalNumberLength(char[] localNumber){
          if(localNumber.length == LOCAL_NUMBER_LENGTH)
           return true;
          else throw new PhoneNumberInvalidLengthException(
                    "Invalid local number length");
    }
  
    /**
     * It returns the local number.
     * @return The local number
     */
    public char[] getLocalNumber(){
          return localNumber;
    }
  
    /**
     * It sets the extension number.
     * @param extensionNumber The extension number
     */
    public void setExtensionNumber(ArrayList<Character> extensionNumber){
        this.extensionNumber = extensionNumber;
    }
  
    /**
     * It returns the extension number.
     * @return The extension number
     */
    public ArrayList<Character> getExtensionNumber(){
         return extensionNumber;
    }
  
    /** 
     * Determines whether the current object matches its argument.
     * @param obj The object to be compared to the current object
     * @return true if the object has the same details; otherwise,
     *   return false     
     */    
    @Override    
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() == obj.getClass()){
            PhoneNumber other = (PhoneNumber)obj;
            return Arrays.equals(areaCode, other.areaCode) && 
               Arrays.equals(exchangeNumber, other.exchangeNumber) &&
               Arrays.equals(localNumber, other.localNumber) && 
               extensionNumber.equals(other.extensionNumber);
        }else{
            return false;
        }
    }
  
    /** 
     * It generates a hash code for the phone number object.
     * @return The hash code
     */
    @Override
    public int hashCode(){
        int hash = 3;
        hash = 83 * hash + Arrays.hashCode(this.areaCode);
        hash = 83 * hash + Arrays.hashCode(this.exchangeNumber);
        hash = 83 * hash + Arrays.hashCode(this.localNumber);
        hash = 83 * hash + (this.extensionNumber != null ? 
              this.extensionNumber.hashCode() : 0);
        return hash;
    }
  
    /**
     * Creates a string representation of the phone number.
     * @return A string representation of the phone number
     */
    @Override
    public String toString(){
        String info = "Area code: " + new String(areaCode) +
                    "\nExchange number: " + new String(exchangeNumber) +
                    "\nLocal number: " + new String(localNumber) + 
                    "\nExtension number: " + extensionNumber;
        return info;
    }
}
