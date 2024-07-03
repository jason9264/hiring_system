package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

class WolfHireTest {

	@Test
    public void testAddNewPosition() {
		WolfHire.getInstance().resetManager();
        WolfHire.getInstance().addNewPosition("CSC coach", 15, 15);
        assertEquals("CSC coach", WolfHire.getInstance().getActivePositionName());
        assertEquals(1, WolfHire.getInstance().getPositionList().length);

        Exception e = assertThrows(IllegalArgumentException.class, 
				() -> WolfHire.getInstance().addNewPosition("Breadwinner", 14, 50000));
		assertEquals("Position cannot be created.", e.getMessage());

		Exception e1 = assertThrows(IllegalArgumentException.class, 
					() -> WolfHire.getInstance().addNewPosition("Breadwinner2", 50000, 14));
		assertEquals("Position cannot be created.", e1.getMessage());

		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> WolfHire.getInstance().addNewPosition("", 50000, 14));
		assertEquals("Position cannot be created.", e2.getMessage());

		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> WolfHire.getInstance().addNewPosition(null, 50000, 14));
		assertEquals("Position cannot be created.", e3.getMessage());
    }

        @Test
        public void testLoadPositionsFromFile() throws Exception {
            WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.loadPositionsFromFile("test-files/positions2.txt");

            // Check that positions have been loaded
            assertNotNull(wolfHire.getPositionList());
            assertEquals(4, wolfHire.getPositionList().length);
        
        }
    
        @Test
        public void testSavePositionsToFile() throws IOException {
            WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            
            wolfHire.addNewPosition("Engineer", 15, 15);
            wolfHire.addNewPosition("Scientist", 15, 15);

            String fileName = "testPositions.txt";
            wolfHire.savePositionsToFile(fileName);

            File file = new File(fileName);
            assertTrue(file.exists()); 
        }
        
        @Test
    	public void addApplicationToPosition() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.addNewPosition("CSC coach", 15, 15);
    		wolfHire.addApplicationToPosition("firstName", "surname", "unityId");
    		assertNotNull(wolfHire.getPositionList());
            assertEquals(1, wolfHire.getPositionList().length);
        }
        
        @Test
    	public void deleteApplicationById() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.addNewPosition("CSC coach", 15, 15);
            Application app = new Application("firstName", "surname", "unityId");
    		wolfHire.getActivePosition().addApplication(app);
    		Application app2 = new Application("Ha", "Ha", "haha");
    		wolfHire.getActivePosition().addApplication(app2);
    		assertNotNull(wolfHire.getPositionList());
    		wolfHire.deleteApplicationById(1);
            assertEquals(1, wolfHire.getActivePosition().getApplications().size());
        }

        @Test
    	public void executeCommand() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.addNewPosition("CSC coach", 15, 15);
    		wolfHire.addApplicationToPosition("firstName", "surname", "unityId");
    		Command c = new Command(Command.CommandValue.ASSIGN, "applicationID");
    		wolfHire.executeCommand(0, c);
    		assertNotNull(wolfHire.getPositionList());
            assertEquals(1, wolfHire.getPositionList().length);
        }
        
        @Test
    	public void getActivePosition() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.addNewPosition("CSC coach", 15, 15);
            Position pos = wolfHire.getActivePosition();
            assertEquals(pos.getPositionName(), "CSC coach");
            
			wolfHire.resetManager();
            wolfHire = WolfHire.getInstance();
            assertNull(null, wolfHire.getActivePosition());
        }
        
        @Test
        public void testGetApplicationsAsArray() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire = WolfHire.getInstance();
            wolfHire.addNewPosition("Bread", 15, 15);
            wolfHire.addApplicationToPosition("Jason", "Wang", "jwang");
            wolfHire.addApplicationToPosition("Jason2", "Wang2", "jwang2");
            wolfHire.addApplicationToPosition("Jason3", "Wang3", "jwang3");
            
            String[][] demo = wolfHire.getApplicationsAsArray("All");
            assertEquals("1", demo[0][0]);
            assertEquals("Submitted", demo[0][1]);
            assertEquals("jwang", demo[0][2]);
            assertEquals("", demo[0][3]);
            assertEquals("2", demo[1][0]);
            assertEquals("Submitted", demo[1][1]);
            assertEquals("jwang2", demo[1][2]);
            assertEquals("", demo[1][3]);
            assertEquals("3", demo[2][0]);
            assertEquals("Submitted", demo[2][1]);
            assertEquals("jwang3", demo[2][2]);
            assertEquals("", demo[2][3]);
            
            String[][] demo2 = wolfHire.getApplicationsAsArray("Submitted");
            assertEquals("1", demo2[0][0]);
            assertEquals("Submitted", demo2[0][1]);
            assertEquals("jwang", demo2[0][2]);
            assertEquals("", demo2[0][3]);
            assertEquals("2", demo2[1][0]);
            assertEquals("Submitted", demo2[1][1]);
            assertEquals("jwang2", demo2[1][2]);
            assertEquals("", demo2[1][3]);
            assertEquals("3", demo2[2][0]);
            assertEquals("Submitted", demo2[2][1]);
            assertEquals("jwang3", demo2[2][2]);
            assertEquals("", demo2[2][3]);
            
            
       }
        
        /**
         * Test get id
         */
        @Test
        public void wolfHireTestGetId() {
        	WolfHire wolfHire = WolfHire.getInstance();
            wolfHire.resetManager();
            wolfHire.addNewPosition("CSC coach", 15, 15);
    		wolfHire.addApplicationToPosition("firstName", "surname", "unityId");
    		assertNotNull(wolfHire.getPositionList());
            assertEquals(1, wolfHire.getPositionList().length);
            assertEquals("firstName", wolfHire.getApplicationById(1).getFirstName());
        }
    
}
