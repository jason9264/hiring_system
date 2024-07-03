package edu.ncsu.csc216.wolf_hire.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit test for Command class
 * @author jason
 *
 */
class CommandTest {

	/**
	 * Tests valid and invalid command constructors
	 */
	@Test
	public void testCommand() {
		// Test valid inputs
		Command c1 = new Command(Command.CommandValue.ASSIGN, "applicationID");
		assertEquals(Command.CommandValue.ASSIGN, c1.getCommand());
		assertEquals("applicationID", c1.getCommandInformation());

		Command c2 = new Command(Command.CommandValue.REJECT, "reason");
		assertEquals(Command.CommandValue.REJECT, c2.getCommand());
		assertEquals("reason", c2.getCommandInformation());

		Command c3 = new Command(Command.CommandValue.RESUBMIT, null);
		assertEquals(Command.CommandValue.RESUBMIT, c3.getCommand());
		assertEquals(null, c3.getCommandInformation());

		//Exception 1
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> new Command(null, "commandInformation"));
		assertEquals("Invalid information.", e.getMessage());
		
		//Exception 2
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.ASSIGN, null));
			assertEquals("Invalid information.", e1.getMessage());

		//Exception 3
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.RESUBMIT, "commandInformation"));
			assertEquals("Invalid information.", e2.getMessage());	
			
		//Exception 4
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.SCHEDULE, "commandInformation"));
			assertEquals("Invalid information.", e4.getMessage());	
			
		//Exception 4	
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(Command.CommandValue.HIRE, "commandInformation"));
			assertEquals("Invalid information.", e5.getMessage());	
	}
		
	/**
	 * Test getting command method
	 */
	@Test
	public void testGetCommand() {
		// Test getCommand
		Command c1 = new Command(Command.CommandValue.ASSIGN, "applicationID");
		assertEquals(Command.CommandValue.ASSIGN, c1.getCommand());

		Command c2 = new Command(Command.CommandValue.REJECT, "Qualifications");
		assertEquals(Command.CommandValue.REJECT, c2.getCommand());

		Command c3 = new Command(Command.CommandValue.RESUBMIT, null);
		assertEquals(Command.CommandValue.RESUBMIT, c3.getCommand());
	}
	
	/**
	 * Test get command information method
	 */
	@Test
	public void testGetCommandInformation() {
		// Test getCommandInformation
		Command c1 = new Command(Command.CommandValue.ASSIGN, "applicationID");
		assertEquals("applicationID", c1.getCommandInformation());

		Command c2 = new Command(Command.CommandValue.REJECT, "reason");
		assertEquals("reason", c2.getCommandInformation());

		Command c3 = new Command(Command.CommandValue.RESUBMIT, null);
		assertEquals(null, c3.getCommandInformation());
	}


	
}
