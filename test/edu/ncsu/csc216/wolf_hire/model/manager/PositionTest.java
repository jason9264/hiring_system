package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Tests for position class
 * @author jason
 *
 */
class PositionTest {

	/**
	 * Test creating invalid and valid contructors for position
	 */
	@Test
	public void testPosition() {
		
		Exception e = assertThrows(IllegalArgumentException.class, 
				() -> new Position(null, 5, 15));
		assertEquals("Position cannot be created.", e.getMessage());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("", 5, 15));
		assertEquals("Position cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Jerry's board", 60, 15));
		assertEquals("Position cannot be created.", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Barry's board", 5, 340));
		assertEquals("Position cannot be created.", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Dog Sitter", 5, 6));
		assertEquals("Position cannot be created.", e4.getMessage());
		
		Position p = new Position("Student", 5, 15);
		assertEquals("Student", p.getPositionName());
		assertEquals(5, p.getHoursPerWeek());
		assertEquals(15, p.getPayRate());
		assertEquals(0, p.appList.size());
	}
	
	/**
	 * Test adding application to position
	 */
	@Test
	public void testAddApplication() {
		Position demo = new Position("JustTest", 10, 10);
		Application app = new Application("Jason", "Wang", "jwang");
		int id = demo.addApplication(app);
		assertEquals(1, id);

		int id2 = demo.addApplication("Red", "Bed", "Head");
		assertEquals(2, id2);
		
		app = new Application("Led", "Dead", "Zed");
		int id3 = demo.addApplication(app);
		assertEquals(3, id3);
		
		demo.setApplicationId();
		//Test get applications
		demo.getApplications();
		app = demo.getApplicationById(3);
		assertEquals("Led", app.getFirstName());
		assertEquals("Dead", app.getSurname());
		assertEquals("Zed", app.getUnityId());
		
		//Test execute
		Command c = new Command(Command.CommandValue.ASSIGN, "app");
		demo.executeCommand(id, c);
		assertEquals(Application.REVIEWING_NAME, demo.getApplicationById(id).getState());
		}
	
	/**
	 * Test deleting application by ID
	 */
	@Test
	public void testDeleteApplicationById() {
		Position demo = new Position("JustTest", 10, 10);
	    demo.addApplication("Ja", "ja", "jaaa");
	    demo.addApplication("eee", "eee", "eeeee");
	    demo.addApplication("Baaaob", "aaaaa", "bobasmith");
	    int id = demo.addApplication("Alice", "Johnson", "alicejohnson");
	    assertEquals(4, demo.getApplications().size());
	    demo.deleteApplicationById(id);
	    assertEquals(3, demo.getApplications().size());
	    assertNull(demo.getApplicationById(id));
	}
	

	/**
	 * Test to string method for position.
	 */
    @Test
    public void testToString() {
        Position pos = new Position("Teaching Assistant", 10, 20);

        String expected = "# Teaching Assistant,10,20";
        assertEquals(expected, pos.toString());
    }
		
}
