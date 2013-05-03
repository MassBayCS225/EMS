package EMS_Database;

import EMS_Database.impl.Committees_Table;
import EMS_Database.impl.Events_Table;
import EMS_Database.impl.Expense_Table;
import EMS_Database.impl.Income_Table;
import EMS_Database.impl.SubEvent_Table;
import EMS_Database.impl.Tasks_Table;
import EMS_Database.impl.UserData_Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mike Meding
 *
 */
public class DB_Testing {

    public void run() {
	// THIS IS THE EFFECTIVE API FOR TABLES

	int uid;

	// Create table objects.
	Events_Table et = new Events_Table();
	SubEvent_Table set = new SubEvent_Table();
	Tasks_Table tt = new Tasks_Table();
	Committees_Table ct = new Committees_Table();
	UserData_Table udt = new UserData_Table();
	Expense_Table exTable = new Expense_Table();
	Income_Table inTable = new Income_Table();

	//BOGUS Events table data
	Timestamp startDate = new Timestamp(new Date().getTime());
	Timestamp endDate = new Timestamp(new Date().getTime());
	ArrayList<Integer> list = new ArrayList<Integer>();
	list.add(3);
	list.add(4);
	InputEventData ied = new InputEventData();
	//BOGUS SubEvents table data
	InputSubEventData ised = new InputSubEventData();
	//BOGUS Committees table data
	InputCommittee ic = new InputCommittee();
	//BOGUS Tasks table data
	InputTask it = new InputTask();
	//BOGUS UserData table data
	InputUser iu = new InputUser(); //can be declared with no data.
	//BOGUS Income data
	//InputIncome in = new InputIncome("some large sum of money", 0.01);
	InputIncome in = new InputIncome();
	//BOGUS Expense data
	//InputExpense ex = new InputExpense("something expensive", 0.02);
	InputExpense ex = new InputExpense();



	//ALL TABLE QUERY
//	System.out.println("EVENTS TABLE");
//	System.out.print(et.queryEntireTable());
//	System.out.println("SUBEVENTS TABLE");
//	System.out.print(set.queryEntireTable());
//	System.out.println("TASKS TABLE");
//	System.out.print(tt.queryEntireTable());
//	System.out.println("COMMITTEE TABLE");
//	System.out.print(ct.queryEntireTable());
//	System.out.println("USERS TABLE");
//	System.out.print(udt.queryEntireTable());


	//CREATE BUDGET INSERTION
//	for (int x = 0; x < 20; x++) {
//	    System.out.print(inTable.nextValidUID()+" ");
//	    inTable.insertBudgetItem(in);	    
//	}
	//System.out.print(inTable.queryEntireTable());
//	try {
//	    uid = 1;
//
//	    System.out.println(exTable.getDescription(uid) + "===" + exTable.getValue(uid));
//	    System.out.println(inTable.getDescription(uid) + "===" + inTable.getValue(uid));
//	    
//	    exTable.setDescription(uid, "new expense");
//	    inTable.setDescription(uid, "new income");
//	    exTable.setValue(uid, 100.55);
//	    inTable.setValue(uid, 564.8);
//	    
//	    System.out.println(exTable.getDescription(uid) + "===" + exTable.getValue(uid));
//	    System.out.println(inTable.getDescription(uid) + "===" + inTable.getValue(uid));
//	    
//	    
//
//	} catch (DoesNotExistException dnee) {
//	    System.err.println(dnee.getMessage());
//	}



	//CREATE USER INSERTION	
//	try{
//	    uid = udt.createUser(iu); 
//	    System.out.println(udt.getLevel(uid));
//	    System.out.println(udt.getFirstName(uid));
//	    System.out.println(udt.getLastName(uid));
//	    System.out.println(udt.getPwd(uid));
//	    System.out.println(udt.getEmail(uid));
//	    System.out.println(udt.getPhone(uid));
//	    System.out.println(udt.getState(uid));
//	    System.out.println(udt.getCity(uid));
//	    System.out.println(udt.getZipcode(uid));
//	    System.out.println(udt.getCountry(uid));
//	    System.out.println(udt.getEventCreationPrivilege(uid));
//	    System.out.println(udt.getParticipant(uid));
//	    
//	    udt.setLevel(uid, 5);
//	    udt.setFirstName(uid, "new firstname");
//	    udt.setLastName(uid, "new lastname");
//	    udt.setPwd(uid, "new pwd");
//	    udt.setEmail(uid, "new email");
//	    udt.setPhone(uid, "new phone");
//	    udt.setStreet(uid, "new street");
//	    udt.setCity(uid, "new city");
//	    udt.setState(uid, "new state");
//	    udt.setZipcode(uid, "new zipcode");
//	    udt.setCountry(uid, "new country");
//	    udt.setEventCreationPrivilege(uid, 12);
//	    udt.setParticipant(uid, true);
//	    
//	    System.out.println(udt.getLevel(uid));
//	    System.out.println(udt.getFirstName(uid));
//	    System.out.println(udt.getLastName(uid));
//	    System.out.println(udt.getPwd(uid));
//	    System.out.println(udt.getEmail(uid));
//	    System.out.println(udt.getPhone(uid));
//	    System.out.println(udt.getState(uid));
//	    System.out.println(udt.getCity(uid));
//	    System.out.println(udt.getZipcode(uid));
//	    System.out.println(udt.getCountry(uid));
//	    System.out.println(udt.getEventCreationPrivilege(uid));
//	    System.out.println(udt.getParticipant(uid));
//	    
//	    System.out.println(udt.getParticipantList());
//	    
//	}catch(DoesNotExistException dnee) {
//	    System.out.println(dnee.getMessage());
//	}


	//CREATE EVENT INSERTION
//        try {
//            uid = et.createEvent(ied);
//            System.out.println("The created uid = " + uid);
//        } catch (DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
	// System.out.println(et.queryEntireTable());        
	// System.out.println(); //blank line.
//        try{
//            uid = 3;
//	    list.add(46);
//            System.out.println(et.getDescription(uid));
//	    System.out.println(et.getDetails(uid));
//            System.out.println(et.getStartDate(uid));
//            System.out.println(et.getEndDate(uid));
//            System.out.println(et.getComplete(uid));
//            System.out.println(et.getStreet(uid));
//            System.out.println(et.getCity(uid));
//            System.out.println(et.getState(uid));
//            System.out.println(et.getZipcode(uid));
//            System.out.println(et.getCountry(uid));
//            System.out.println(et.getOrganizerList(uid));  
//            System.out.println(et.getSubEventList(uid));  
//            System.out.println(et.getParticipantList(uid));  
//            System.out.println(et.getCommittee(uid));   	    
//	   
//	    et.setDescription(uid, "new description");	   
//	    et.setDetails(uid, "new details");	   
//	    et.setStartDate(uid, startDate);	   
//	    et.setEndDate(uid, endDate);	   
//            et.setComplete(uid, 1);	   
//            et.setStreet(uid, "Mulholland Drive");	   
//            et.setCity(uid, "Tokyo");	   
//            et.setState(uid, "XYZ");	   
//            et.setZipcode(uid, "90210");	   
//            et.setCountry(uid, "JAPAN!");	   
//            et.setOrganizerList(uid, list);	   
//            et.setSubEventList(uid, list);	   
//            et.setParticipantList(uid, list);	   
//            et.setCommittee(uid, list);       	   
//	    
//	    System.out.println(et.getDescription(uid));
//	    System.out.println(et.getDetails(uid));
//            System.out.println(et.getStartDate(uid));
//            System.out.println(et.getEndDate(uid));
//            System.out.println(et.getComplete(uid));
//            System.out.println(et.getStreet(uid));
//            System.out.println(et.getCity(uid));
//            System.out.println(et.getState(uid));
//            System.out.println(et.getZipcode(uid));
//            System.out.println(et.getCountry(uid));
//            System.out.println(et.getOrganizerList(uid));  
//            System.out.println(et.getSubEventList(uid));  
//            System.out.println(et.getParticipantList(uid));  
//            System.out.println(et.getCommittee(uid));   
//	    
//	                
//        }catch(DoesNotExistException dnee){	    
//            System.err.println(dnee.getMessage());
//        }
//	System.out.println("\n\n");


	//CREATE SUBEVENT INSERTION
//        try {
//            uid = set.createSubEvent(ised);
//            System.out.println("The created uid = " + uid);
//        } catch (DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
	//System.out.println(set.queryEntireTable());

//        try{
//            uid = 2;
//            System.out.println(set.getDescription(uid));
//	    System.out.println(set.getDetails(uid));
//            System.out.println(set.getComplete(uid));
//            System.out.println(set.getStreet(uid));
//            System.out.println(set.getCity(uid));
//            System.out.println(set.getState(uid));
//            System.out.println(set.getZipcode(uid));
//            System.out.println(set.getStartDate(uid));
//            System.out.println(set.getEndDate(uid));
//	    	    
//            set.setDescription(uid, "Better Description!");	    
//	    set.setDetails(uid, "new details");	    
//            set.setStreet(uid,"28 Mullholand Drive");	    
//            set.setCity(uid,"Amherst");	    
//            set.setState(uid, "Massachusetts");	    
//            set.setZipcode(uid, "12345");	    
//            set.setCountry(uid, "USA");	    
//            set.setStartDate(uid, startDate);	    
//            set.setEndDate(uid, endDate);	    
//            set.setComplete(uid, 1);	    
//	    
//	    System.out.println(set.getDescription(uid));
//	    System.out.println(set.getDetails(uid));
//            System.out.println(set.getComplete(uid));
//            System.out.println(set.getStreet(uid));
//            System.out.println(set.getCity(uid));
//            System.out.println(set.getState(uid));
//            System.out.println(set.getZipcode(uid));
//            System.out.println(set.getStartDate(uid));
//            System.out.println(set.getEndDate(uid));
//            
//        } catch(DoesNotExistException dnee) {
//	    System.err.println("an error has occured.");
//            System.err.println(dnee.getMessage());
//        }   

	//System.out.println("\n\n");	    

	//CREATE COMMITTEE INSERTION
//        try{
//            uid = ct.createCommittee(ic);
//            System.out.println("The created uid = " + uid);
//        } catch(DuplicateInsertionException die) {
//            System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//        }
//        //System.out.println(ct.queryEntireTable());
//        try{
//            uid = 5;
//            System.out.println(ct.getTitle(uid));
//            System.out.println(ct.getChairman(uid));
//            System.out.println(ct.getBudgetAccessList(uid));
//            System.out.println(ct.getCommitteeMembers(uid));
//            System.out.println(ct.getTaskList(uid));
//            System.out.println(ct.getBudget(uid));
//	    
//	    ct.setTitle(uid, "Better Title");
//            ct.setChairman(uid, 2);
//            ct.setBudgetAccessList(uid, list);
//            ct.setCommitteeMembers(uid, list);
//            ct.setTaskList(uid, list);
//            ct.setBudget(uid, 59.52);
//	    
//	    System.out.println(ct.getTitle(uid));
//            System.out.println(ct.getChairman(uid));
//            System.out.println(ct.getBudgetAccessList(uid));
//            System.out.println(ct.getCommitteeMembers(uid));
//            System.out.println(ct.getTaskList(uid));
//            System.out.println(ct.getBudget(uid));	    	    
//            
//        } catch(DoesNotExistException dnee) {
//            System.err.println(dnee.getMessage());
//        }



	//CREATE TASKS INSERTION
//	try {
//	    uid = tt.createTask(it);
//	    System.out.println("The created uid = " + uid);
//	} catch (DuplicateInsertionException die) {
//	    System.err.println("Duplicate Insertion? that shouldn't be happening :/");
//	}

//        System.out.println(tt.queryEntireTable());
//	try {
//	    uid = 1;
//	    System.out.println(tt.getDescription(uid));
//	    System.out.println(tt.getDetails(uid));
//	    System.out.println(tt.getStreet(uid));
//	    System.out.println(tt.getCity(uid));
//	    System.out.println(tt.getState(uid));
//	    System.out.println(tt.getZipcode(uid));
//	    System.out.println(tt.getCountry(uid));
//	    System.out.println(tt.getStartDate(uid));
//	    System.out.println(tt.getEndDate(uid));
//	    System.out.println(tt.getComplete(uid));
//	    System.out.println(tt.getAuthority(uid));
//
//	    tt.setDescription(uid, "set description");
//	    tt.setDetails(uid, "set details");
//	    tt.setStreet(uid, "set street");
//	    tt.setCity(uid, "set city");
//	    tt.setState(uid, "set state");
//	    tt.setZipcode(uid, "set zipcode");
//	    tt.setCountry(uid, "se country");
//	    tt.setStartDate(uid, startDate);
//	    tt.setEndDate(uid, endDate);
//	    tt.setComplete(uid, 1);
//	    tt.setAuthority(uid, list);
//
//	    System.out.println(tt.getDescription(uid));
//	    System.out.println(tt.getDetails(uid));
//	    System.out.println(tt.getStreet(uid));
//	    System.out.println(tt.getCity(uid));
//	    System.out.println(tt.getState(uid));
//	    System.out.println(tt.getZipcode(uid));
//	    System.out.println(tt.getCountry(uid));
//	    System.out.println(tt.getStartDate(uid));
//	    System.out.println(tt.getEndDate(uid));
//	    System.out.println(tt.getComplete(uid));
//	    System.out.println(tt.getAuthority(uid));
//
//	} catch (DoesNotExistException dnee) {
//	    System.err.println(dnee.getMessage());
//	}
//	System.out.println("\n\n");
////        System.out.println(tt.queryEntireTable());

	// just to see whats there.
//	System.out.println("EVENTS TABLE");
//	System.out.print(et.queryEntireTable());
//	System.out.println("SUBEVENTS TABLE");
//	System.out.print(set.queryEntireTable());
//	System.out.println("TASKS TABLE");
//	System.out.print(tt.queryEntireTable());
	
	//CREATE BUDGET TESTS
	try{
	    uid = exTable.insertBudgetItem(ex);
	    
	    System.out.println(exTable.getDescription(uid));
	    System.out.println(exTable.getDate(uid));
	    System.out.println(exTable.getValue(uid));
	    
	    exTable.setDescription(uid, "new description");
	    exTable.setDate(uid, endDate);
	    exTable.setValue(uid, 654.8);	    
	    
	    System.out.println(exTable.getDescription(uid));
	    System.out.println(exTable.getDate(uid));
	    System.out.println(exTable.getValue(uid));	    	    
	    
	    System.out.println(exTable.total());
	    System.out.println(exTable.queryEntireTable());
	    
	    
	    uid = inTable.insertBudgetItem(in);
	    
	    System.out.println(inTable.getDescription(uid));
	    System.out.println(inTable.getDate(uid));
	    System.out.println(inTable.getValue(uid));
	    
	    inTable.setDescription(uid, "new description");
	    inTable.setDate(uid, endDate);
	    inTable.setValue(uid, 12335.58);
	    
	    System.out.println(inTable.getDescription(uid));
	    System.out.println(inTable.getDate(uid));
	    System.out.println(inTable.getValue(uid));
	    
	    System.out.println(inTable.total());
	    System.out.println(inTable.queryEntireTable());
	    
	} catch(DoesNotExistException dnee){
	    System.err.println(dnee.getMessage());
	}
    }

    public static void main(String[] args) {
	new DB_Testing().run();
    }
}
