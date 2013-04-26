package BackEnd.UserSystem;

import BackEnd.UserSystem.PhoneNumber;

/**
 * This class represents a Participant of an event.
 * @author Anderson Santana
 */
public class Participant{
  private String name;
  private String emailAddress;
  private PhoneNumber phoneNumber;
  private Address address;
  
 /**
  * Constructor.
 *  This constructor initializes the participant object with a name.
 *  @param name The participant's name
 */
  public Participant(String name){
    this.name = name;
  }
 
 /**
  *  Overloaded Constructor.
  *  This constructor initializes the Participant object with a name
  *  and an email address
  *  @param name The participant's name
  *  @param address The participant's email address
  */
  public Participant(String name, String emailAddress){
    this.name = name;
    this.emailAddress = emailAddress;
  }
  
 /**
  *  It sets the participant's name.
  *  @param name The participant's name
  */ 
  public void setName(String name){
    this.name = name; 
  }
  
 /**
  *  It returns the participant's name.
  *  @return The participant's name
  */ 
  public String getName(){
    return name;
  }
  
 /**
  *  It sets the participant's email address.
  *  @param emailAddress The participant's email address
  */
  public void setEmailAddress(String emailAddress){
    this.emailAddress = emailAddress; 
  }
  
 /**
  *  It verifies the participant's email address. By figuring out 
  *  if it exists in the system or any other reason not yet specified.
  *  
  *  @param emailAddress The participant's email address
  *  @return true if the object's email address matches the 
  *    current object, false otherwise
  */ 
  private boolean verifyEmailAddress(String emailAddress){
    // NOT COMPLETE //
    //if(this.emailAddress.equals(emailAddress))
      return true;
    //else
      //return false;
  }
  
 /**
  *  It returns the participant's email address.
  *  @return The participant's email address
  */ 
  public String getEmailAddress(){
    return emailAddress;
  }
  
 /**
  *  It sets the participant's phone number.
  *  @param phoneNumber The participant's phone number
  */ 
  public void setPhoneNumber(PhoneNumber phoneNumber){
    this.phoneNumber = phoneNumber;
  }
  
 /**
  *  It returns the participant's phone number.
  *  @return The participant's phone number
  */ 
  public PhoneNumber getPhoneNumber(){
    return phoneNumber;
  }
  
 /**
  *  It sets the participant's address.
  *  @param address The participant's address
  */ 
  public void setAddress(Address address){
    this.address = address;
  }
  
  /**
  *  It returns the participant's address.
  *  @returns The participant's address
  */ 
  public Address getAddress(){
    return address;
  }
  
  /** Determines whether the current object matches its argument.
    * @param obj The object to be compared to the current object
    * @return true if the object has the same details; otherwise,
    *   return false     
    */    
  @Override    
  public boolean equals(Object obj){
    if(obj == this) return true;
    if(obj == null) return false;
    if(this.getClass() == obj.getClass()){
      Participant other = (Participant) obj;
      return name.equals(other.name) && emailAddress.equals(other.emailAddress) &&
        phoneNumber.equals(other.phoneNumber) && address.equals(other.address);
        } else {
          return false;
        }
  }
  
  /**
   * Creates a string representation of the participant.
   * @return A string representation of the participant
   */
  @Override
  public String toString(){
    String info = "Name: " + name +
                  "\nEmail: " + emailAddress + 
                  "\nPhone: " + phoneNumber + 
                  "\nAddress: " + address;
    
    return info;
  }
}