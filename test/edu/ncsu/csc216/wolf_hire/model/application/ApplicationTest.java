package edu.ncsu.csc216.wolf_hire.model.application;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Unit test file for Application class
 * @author jason
 *
 */
class ApplicationTest {

	/**
	 * Constant string submitted name
	 */
	public static final String SUBMITTED_NAME = "Submitted";
	
	/**
	 * Constant string rejected name
	 */
	public static final String REJECTED_NAME = "Rejected";
	
	/**
	 * Constant string reviewing name
	 */
	public static final String REVIEWING_NAME = "Reviewing";
	
	/**
	 * Constant string interviewing name
	 */
	public static final String INTERVIEWING_NAME = "Interviewing";
	
	/**
	 * Constant string processing name
	 */
	public static final String PROCESSING_NAME = "Processing";
	
	/**
	 * Constant string hired name
	 */
	public static final String HIRED_NAME = "Hired";
	
	/**
	 * Constant string inactive name
	 */
	public static final String INACTIVE_NAME = "Inactive";
	
	/**
	 * Constant string qualification rejection
	 */
	public static final String QUALIFICATIONS_REJECTION = "Qualifications";
	
	/**
	 * Constant string incomplete rejection
	 */
	public static final String INCOMPLETE_REJECTION = "Incomplete";
	
	/**
	 * Constant string positions_rejection
	 */
	public static final String POSITIONS_REJECTION = "Positions";
	
	/**
	 * Constant string Duplicate rejection
	 */
	public static final String DUPLICATE_REJECTION = "Duplicate";
	
	/**
	 * Constant string completed rejection
	 */
	public static final String COMPLETED_TERMINATION = "Completed";
	
	/**
	 * Constant string resigned rejection
	 */
	public static final String RESIGNED_TERMINATION = "Resigned";
	
	/**
	 * Constant string fired termination
	 */
	public static final String FIRED_TERMINATION = "Fired";


	@BeforeEach
	void setUp() {
		Application.setCounter(0);
	}
	
	/**
	 * Test null constructor
	 */
	@Test
	public void testConstructorWithNullValues1() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(null, null, null));
		assertEquals("Application cannot be created.", e1.getMessage());
		Application.setCounter(0);
	}
	
	/** 
	 * Test blank constructor
	 */
	@Test
	public void testConstructorWithEmptyValues1() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application("", "", ""));	
		assertEquals("Application cannot be created.", e1.getMessage());
		Application.setCounter(0);
	}
	
	/**
	 * Test valid constructor
	 */
	@Test
	public void testConstructorWithValidValues1() {
		
		Application application = new Application("Jason", "Wang", "jwang");
		
		assertEquals("Jason", application.getFirstName());
		assertEquals("Wang", application.getSurname());
		assertEquals("jwang", application.getUnityId());
		assertEquals(0, application.getId());
		assertEquals(Application.SUBMITTED_NAME, application.getState());
		assertEquals("", application.getReviewer());
		assertEquals("", application.getNote());
	}
		

	
	/**
	 * Test Null constructor
	 */
	@Test
	public void testConstructorWithNullValues2() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(0, null, null, null, null, null, null));	
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(1, "Submitted", "Carol", "Schmidt", "cschmid", "sesmith5", null));	
				assertEquals("Application cannot be created.", e2.getMessage());
				
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(3, "Rejected", "Kathleen", "Gillespie", "kgilles", null, "Complete"));
		assertEquals("Application cannot be created.", e3.getMessage());
	

	}
	
	/**
	 * Test empty constructor
	 */
	@Test
	public void testConstructorWithEmptyValues2() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(0, "", "", "", "", "", ""));
		assertEquals("Application cannot be created.", e1.getMessage());
	}
	
	/**
	 * test negative Id
	 */
	@Test
	public void testConstructorWithNegativeId2() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(-1, "submitted", "Jason", "Wang", "jwang", "reviewer", "note"));
		assertEquals("Application cannot be created.", e1.getMessage());
	}
	
	/**
	 * Test valid constructor
	 */
	@Test
	public void testConstructorWithValidValues2() {
		
		Application application = new Application(3, SUBMITTED_NAME, "Jason", "Wang", "jwang", null, null);
		
		assertEquals(3, application.getId());
		assertEquals(SUBMITTED_NAME, application.getState());
		assertEquals("Jason", application.getFirstName());
		assertEquals("Wang", application.getSurname());
		assertEquals("jwang", application.getUnityId());
		assertEquals("", application.getReviewer());
		assertEquals("", application.getNote());
	}
	
	/**
	 * Test to string
	 */
	@Test
	public void testToString() {
		int id = 1;
		String firstName = "Jason";
		String surname = "Wang";
		String unityId = "jwang";
		
		Application application = new Application(id, SUBMITTED_NAME, firstName, surname, unityId, null, null);
		
		String expectedString = "*" + id + "," + firstName + "," + surname + "," + unityId + "," + "" + "," + "";
		
		assertEquals(expectedString, application.toString());
	}
	
	/**
	 * Test is submitted at initial state
	 */
	 @Test
	 public void testInitialState() {
	     Application app = new Application("Joe", "Seph", "Joeseph");
	     assertEquals(Application.SUBMITTED_NAME, app.getState());
	 }
	 
	 /**
	  * Test submitted state transitions
	  */
	 @Test
	 public void testSubmitStateTrans() {
	     Application app = new Application("Joe", "Seph", "Joeseph");
	     app.getState();
		 Command command = new Command(Command.CommandValue.ASSIGN, "reviewer");
	     app.update(command);
	     assertEquals(Application.REVIEWING_NAME, app.getState());
	     assertEquals("reviewer", app.getReviewer());
	
    	 app = new Application("Joe", "Seph", "Joeseph");
         command = new Command(Command.CommandValue.REJECT, "Qualifications");
         app.update(command);
         assertEquals(Application.REJECTED_NAME, app.getState());
         assertEquals("Qualifications", app.getNote());
    }
	 
	 /**
	  * Test submitted State reject invalid transition
	  */
	 @Test
	 public void testSubmitStateTransException() {
		 Application app = new Application("Joe", "Seph", "Joeseph");
	     app.getState();
         Command command = new Command(Command.CommandValue.REJECT, "Bready");
         Exception e = assertThrows(UnsupportedOperationException.class, 
 				() -> app.update(command));
 		 assertEquals("Invalid command.", e.getMessage());
	 }
	 
	 /**
	  * Test rejected state transitions
	  */
	@Test
	public void testRejectedStateTrans() {
	     Application app = new Application("Joe", "Seph", "Joeseph"); 
		 Command rejectCmd = new Command(Command.CommandValue.REJECT, "Qualifications");
	     app.update(rejectCmd);
	     assertEquals(Application.REJECTED_NAME, app.getState());
	     assertEquals("Qualifications", app.getNote());
	     
	     app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         Command reject = new Command(Command.CommandValue.REJECT, "Qualifications");
         app.update(reject);
         
         Command resubmit = new Command(Command.CommandValue.RESUBMIT, null);
         app.update(resubmit);
         assertEquals(Application.SUBMITTED_NAME, app.getState());
         assertEquals("", app.getReviewer());
         
         Application application = new Application("Joe", "Seph", "Joeseph"); 
         application.update(rejectCmd);
         
         Exception e = assertThrows(UnsupportedOperationException.class, 
 				() -> application.update(reject));
 		 assertEquals("Invalid command.", e.getMessage());
 		
         
    }
	
	/**
	 * Test invalid commands on a application
	 */
	@Test
	public void testInvalidCommand() {
        Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.SCHEDULE, "interviewer1"));
		assertEquals("Invalid information.", e1.getMessage());
    }
	
	/**
	 * Test reviewing state transitions
	 */
	@Test
	public void testReviewingStateTrans() {
	     Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);
         assertEquals(Application.INTERVIEWING_NAME, app.getState());
         assertEquals("reviewer1", app.getReviewer());
         
         app = new Application("Joe", "Seph", "Joeseph"); 
         app.update(assign);
         
         Command ret = new Command(Command.CommandValue.RETURN, null);
         app.update(ret);
         assertEquals(Application.SUBMITTED_NAME, app.getState());
         assertEquals("", app.getReviewer());

         
         Command reject = new Command(Command.CommandValue.REJECT, "Qualifications");
         app.update(reject);
         assertEquals(Application.REJECTED_NAME, app.getState());
         assertEquals("Qualifications", app.getNote());
         
	}
	
	/**
	 * Test interviewingState exception transition
	 */
	@Test
	public void testInterviewingStateRejectTransException() {
		Application app = new Application("Joe", "Seph", "Joeseph"); 
        Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
        app.update(assign);
        
        Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
        app.update(schedule);
        assertEquals(Application.INTERVIEWING_NAME, app.getState());
        assertEquals("", app.getNote());
        
        Command reject = new Command(Command.CommandValue.REJECT, "Bready");

        Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> app.update(reject));
        assertEquals("Invalid command.", e1.getMessage());
	}
	
	/**
	 * Test interviewing state valid transitions
	 */
	@Test
	public void testInterviewingStateTrans() {
	     Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);
         assertEquals(Application.INTERVIEWING_NAME, app.getState());
         assertEquals("", app.getNote());
                  
         Command process = new Command(Command.CommandValue.PROCESS, null);
         app.update(process);
         assertEquals(Application.PROCESSING_NAME, app.getState());
         assertEquals("reviewer1", app.getReviewer());
         
         app = new Application("Joe", "Seph", "Joeseph"); 
         app.update(assign);
         app.update(schedule);

         Command reject = new Command(Command.CommandValue.REJECT, "Incomplete");
         app.update(reject);
         assertEquals(Application.REJECTED_NAME, app.getState());
         assertEquals("Incomplete", app.getNote());
         
         app = new Application("Joe", "Seph", "Joeseph"); 
         app.update(assign);
         app.update(schedule);
         
         app.update(assign);
         assertEquals(Application.REVIEWING_NAME, app.getState());
         
	}
	
	/**
	 * Test processing state transitions
	 */
	@Test
	public void testProcessingStateTrans() {
	     Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);   
         Command process = new Command(Command.CommandValue.PROCESS, null);
         app.update(process);

         Command reject = new Command(Command.CommandValue.REJECT, "Incomplete");
         app.update(reject);
         assertEquals(Application.REJECTED_NAME, app.getState());
         assertEquals("Incomplete", app.getNote());
         
         app = new Application("Joe", "Seph", "Joeseph"); 
         app.update(assign);
         app.update(schedule);   
         app.update(process);
         
         Command hire = new Command(Command.CommandValue.HIRE, null);
         app.update(hire);
         assertEquals(Application.HIRED_NAME, app.getState());
         assertEquals("", app.getNote());
         
	}
	
	/**
	 * Test Processing state transition exception
	 */
	@Test
	public void testProcessingStateTransException() {
		 Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);   
         Command process = new Command(Command.CommandValue.PROCESS, null);
         app.update(process);
         Command reject = new Command(Command.CommandValue.REJECT, "Bready");
         Exception e = assertThrows(UnsupportedOperationException.class, 
  				() -> app.update(reject));
  		 assertEquals("Invalid command.", e.getMessage());
	}
	
	
	/**
	 * Test Hired State Transitions
	 */
	@Test
	public void testHiredState() {
		 Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);   
         Command process = new Command(Command.CommandValue.PROCESS, null);
         app.update(process);
         Command hire = new Command(Command.CommandValue.HIRE, null);
         app.update(hire);
         Command term = new Command(Command.CommandValue.TERMINATE, "Completed");
         app.update(term);
         assertEquals(Application.INACTIVE_NAME, app.getState());
         
		 Application application = new Application("Joe", "Seph", "Joeseph"); 
		 application.update(assign);
         application.update(schedule);   
         application.update(process);
         application.update(hire);
         Exception e = assertThrows(UnsupportedOperationException.class, 
  				() -> application.update(hire));
  		 assertEquals("Invalid command.", e.getMessage());
	}
	
	/**
	 * Test inactive state transitions
	 */
	@Test
	public void testInactiveState() {
		 Application app = new Application("Joe", "Seph", "Joeseph"); 
         Command assign = new Command(Command.CommandValue.ASSIGN, "reviewer1");
         app.update(assign);
         Command schedule = new Command(Command.CommandValue.SCHEDULE, null);
         app.update(schedule);   
         Command process = new Command(Command.CommandValue.PROCESS, null);
         app.update(process);
         Command hire = new Command(Command.CommandValue.HIRE, null);
         app.update(hire);
         Command term = new Command(Command.CommandValue.TERMINATE, "Completed");
         app.update(term);
         assertEquals(Application.INACTIVE_NAME, app.getState());
         
         Exception e = assertThrows(UnsupportedOperationException.class, 
    				() -> app.update(hire));
    		 assertEquals("Invalid command.", e.getMessage());
	}
	
}

