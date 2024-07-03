package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Processes a file containing position and application information and 
 * creates a List of Positions and their associated Applications.
 * @author jason
 *
 */
public class PositionReader {

	/**
	 * reads the position in the file
	 * @param fileName the files name
	 * @return an array list of positions
	 * @throws IllegalArgumentException if the file cannot be found or does not e
	 */
	public static ArrayList<Position> readPositionFile(String fileName) {
		Scanner fileReader = null;
		try{
			fileReader = new Scanner(new FileInputStream(fileName));
		}
		catch (Exception e){
			throw new IllegalArgumentException("Unable to load file.");
		}
		
	    if (!fileReader.hasNext() || fileReader.next().charAt(0) != '#') {
	        fileReader.close();
	        return new ArrayList<Position>();
	    }
	    
	    fileReader.useDelimiter("\\r?\\n?[#]"); // Delimiter for position tokens

	    ArrayList<Position> positions = new ArrayList<Position>();
	    ArrayList<Position> empty = new ArrayList<Position>();

	    while (fileReader.hasNext()) {
	        String positionText = fileReader.next();
	        try {
	            Position position = processPosition(positionText);
	            positions.add(position);
	        }
	        catch (Exception e){
	        	try {
	    		String[] lines = positionText.split("\\r?\\n");
	    	    Position position = processPositionLine(lines[0]);
	    	    positions.add(position);
	        	}
	        	catch(Exception e1) {
	        		// do nothing
	        	}

	        }
	    }
	  
	    if(positions.size() <= 1 || positions == null) {
	    	try {
	    		int a = positions.get(0).getApplications().size();
	    		if (a == 0) {
	    			return empty;
	    		}
	    	}
	    	catch (Exception e){
		    	return empty;
	    	}
	    }
	    
	    fileReader.close();
	    return positions;
	}
	
	/**
	 * processes the current position
	 * @param positionText the text for the position
	 * @return the position
	 */
	private static Position processPosition(String positionText) {
		String[] lines = positionText.split("\\r?\\n");

	    Position position = null;
		try {
	    position = processPositionLine(lines[0]);
		}
		catch (Exception e){
			throw new IllegalArgumentException();
		}
	    for (int i = 1; i < lines.length; i++) {
	        String applicationLine = lines[i].trim();
	        if (applicationLine.startsWith("*")) {
	        	Application newApplication = null;
        		try {
            		newApplication = processApplication(applicationLine.substring(1));
	                position.addApplication(newApplication);
	                }
	                catch (Exception e){
	                	//do nothing
	                }
	        }
	    }
	    return position;
	}
	
	
	/**
	 * proceses the entire line of the position
	 * @param positionLine as a line in the file
	 * @return the a position processes as whole line 
	 */
	private static Position processPositionLine(String positionLine) {
		String[] positionInformation = positionLine.split(",");
        String positionName = positionInformation[0].trim();
        int hoursPerWeek = Integer.parseInt(positionInformation[1].trim());
        int payRate = Integer.parseInt(positionInformation[2].trim());
        Position pos = null;
        try {
        	pos = new Position(positionName, hoursPerWeek, payRate);
        }
        catch (Exception e) {
        	throw new IllegalArgumentException();
        }
        return pos;
	}
	
	/**
	 * processes an application
	 * @param applicationLine the applicatio as a line in the file
	 * @return the application
	 */
	private static Application processApplication(String applicationLine) {
		String[] applicationDetails = applicationLine.split(",");
		if(applicationDetails.length > 7) {
			throw new IllegalArgumentException();
		}
		String textId = applicationDetails[0].trim();
		int id = Integer.parseInt(textId);
		String state = applicationDetails[1].trim();
        String firstName = applicationDetails[2].trim();
        String surname = applicationDetails[3].trim();
        String unityId = applicationDetails[4].trim();
        String reviewer;
        try {
        	reviewer = applicationDetails[5].trim();
        	if ("".equals(reviewer)) {
        		reviewer = null;
        	}
        }
        catch (Exception e){
        	reviewer = null;
        }
        String note;
        try {
        	note = applicationDetails[6].trim();
        	if ("".equals(note)) {
        		note = null;
        	}
        }
        catch (Exception e) {
        	note = null;
        }
        Application app =  new Application(id, state, firstName, surname, unityId, reviewer, note);
	        
        return app;
        
	}
	
}
