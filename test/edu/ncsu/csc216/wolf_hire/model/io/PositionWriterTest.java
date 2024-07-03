package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Test the writing to a file position class
 * @author jason
 *
 */
class PositionWriterTest {
	
	/**
	 * Tests writer method
	 */
	@Test
	public void testWritePositionsToFile() {
        ArrayList<Position> positions = new ArrayList<>();
        Position p1 = new Position("Jared's dog", 12, 20);
        Position p2 = new Position("My cleaner", 15, 15);
        positions.add(p1);
        positions.add(p2);

        Application a1 = new Application(2, "Submitted", "Carol", "Schmidt", "cschmid", null, null);
        Application a2 = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
        
        p1.addApplication(a1);
        p2.addApplication(a2);
        
        String fileName = "test-positions.txt";
        try {
            PositionWriter.writePositionsToFile(fileName, positions);
        } catch (Exception e) {
            fail("Unexpected exception writing to file: " + e.getMessage());
        }
        
        File file = new File(fileName);
        assertTrue(file.exists());

        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            return;
        }

        String line1 = fileReader.nextLine();
        assertEquals("# Jared's dog,12,20", line1);
        String line2 = fileReader.nextLine();
        assertEquals("* 2,Submitted,Carol,Schmidt,cschmid,,", line2);
        

        fileReader.close();
        file.delete();

	}
	
	/**
	 * Test case for write position application for hired state.
	 */
	@Test
	public void testWritePositionApplicationHired() {
        ArrayList<Position> positions = new ArrayList<>();
        Position p1 = new Position("Position", 18, 20);
        Application a1 = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
        positions.add(p1);
        p1.addApplication(a1);

        String fileName = "actual_hired.txt";
        try {
            PositionWriter.writePositionsToFile(fileName, positions);
        } catch (Exception e) {
            fail("Unexpected exception writing to file: " + e.getMessage());
        }
        
        File file = new File(fileName);
        assertTrue(file.exists());
	}
	
	/**
	 * Test case for write position application for hired state.
	 */
	@Test
	public void testWriteMultiplePosition() {
        ArrayList<Position> positions = new ArrayList<>();
        Position p1 = new Position("A", 18, 20);
        Position p2 = new Position("B", 10, 12);
        Position p3 = new Position("C", 18, 20);
        Position p4 = new Position("D", 11, 11);
        Application a1 = new Application(2, "Submitted", "Carol", "Schmidt", "cschmid", null, null);
        Application a2 = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
        Application a3 = new Application(7, "Inactive", "Deanna", "Sanders", "dsander", "tmbarnes", "Completed");
        Application a4 = new Application(8, "Interviewing", "Benjamin", "Nieves", "bmnieves", "sesmith5", null);
        Application a5 = new Application(11, "Processing", "Quemby", "Mullen", "qmullen", "sesmith5", null);
        Application a6 = new Application(3, "Rejected", "Kathleen", "Gillespie", "kgilles", null, "Incomplete");

        positions.add(p1);
        positions.add(p2);
        positions.add(p3);
        positions.add(p4);

        p1.addApplication(a1);
        p1.addApplication(a2);
        p2.addApplication(a3);
        p2.addApplication(a4);
        p2.addApplication(a5);
        p4.addApplication(a6);

        String fileName = "expected_positions.txt";
        try {
            PositionWriter.writePositionsToFile(fileName, positions);
        } catch (Exception e) {
            fail("Unexpected exception writing to file: " + e.getMessage());
        }
        
        File file = new File(fileName);
        assertTrue(file.exists());
	}
	
	
}
