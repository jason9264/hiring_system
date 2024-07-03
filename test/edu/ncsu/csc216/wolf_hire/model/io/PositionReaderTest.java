package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Unit test for PositionReader class
 * @author jason
 *
 */
class PositionReaderTest {

	/**
	 * Test reading position from a file
	 */
	@Test
	public void testReadPositionFile2() {
		ArrayList<Position> positions = null;
		try {
			positions = PositionReader.readPositionFile("test-files/positions2.txt");
		} catch (Exception e) {
			return;
		}
		assertEquals(4, positions.size());
	}
	
	/**
	 * Test reading position from a file2
	 */
	@Test
	public void testReadPositionFile1() {
		ArrayList<Position> positions = null;
		try {
			positions = PositionReader.readPositionFile("test-files/positions1.txt");
		} catch (Exception e) {
			return;
		}
		assertEquals(1, positions.size());
	}
	
	/**
	 * Test reading position from a file3
	 */
	@Test
	public void testReadPositionFile3() {
		ArrayList<Position> positions = null;
		try {
			positions = PositionReader.readPositionFile("test-files/positions3.txt");
		} catch (Exception e) {
			return;
		}
		assertEquals(0, positions.size());
	}
	
	/**
	 * Test reading position from a file12
	 */
	@Test
	public void testReadPositionFile12() {
		ArrayList<Position> positions = null;
		try {
			positions = PositionReader.readPositionFile("test-files/positions12.txt");
		} catch (Exception e) {
			return;
		}
		assertEquals(0, positions.size());
	}
	
	/**
	 * Test reading position from a file13
	 */
	@Test
	public void testReadPositionFile13() {
		ArrayList<Position> positions = null;
		try {
			positions = PositionReader.readPositionFile("test-files/positions13.txt");
		} catch (Exception e) {
			return;
		}
		assertEquals(0, positions.size());
	}

}
