package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;


/**
 * Imlements the wolfhire class for the manager packages
 * @author jason
 *
 */
public class WolfHire {

	/**
	 * final Wolfhire singelton
	 */
	private static WolfHire singleton;
	
	/**
	 * Array list of positions
	 */
	ArrayList<Position> positions = new ArrayList<>();
	
	/**
	 * Current Active position
	 */
	private Position activePosition;
	
	/**
	 * Constructor for wolfhire
	 */
	private WolfHire() {
		
	}
	
	/**
	 * Returns the WolfHire manager
	 * @return returns the WolfHire manager
	 */
	public final static WolfHire getInstance() {
		 if (singleton == null) {
		        singleton = new WolfHire();
		    }
		    return singleton;
		
	}
	
	/**
	 * Loads the position from the file
	 * @param fileName the files name
	 */
	public void loadPositionsFromFile(String fileName) {
		try {
			positions = PositionReader.readPositionFile(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(positions.size() != 0) {
		loadPosition(positions.get(0).getPositionName());
		}
	}
	
	/**
	 * Saves the position to the file
	 * @param fileName the files name
	 * @throws IllegalArgumentException  if file cannot be written to
	 */
	public void savePositionsToFile(String fileName) {
		if(activePosition != null) {
		
			try {
				PositionWriter.writePositionsToFile(fileName, positions);
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to save file");
			}
		}
		else {
			throw new IllegalArgumentException("Unable to save file");
		}
	}
	
	/**
	 * Adds a new position
	 * @param positionName the name of the position
	 * @param hoursPerWeek the hours per week worked
	 * @param payRate the pay rate for the position
	 */
	public void addNewPosition(String positionName, int hoursPerWeek, int payRate) {
		if(positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		
		for(int i = 0; i < positions.size(); i++) {
			if(positions.get(i).getPositionName() == positionName) {
				throw new IllegalArgumentException ("Position cannot be created.");
			}
		}
		
		Position newPosition = new Position(positionName, hoursPerWeek, payRate);
		positions.add(newPosition);
		loadPosition(positionName);
		
	}
	
	/**
	 * loads the position
	 * @param positionName the positions name
	 */
	public void loadPosition(String positionName) {
		if(positions == null || positions.size() == 0) {
			throw new IllegalArgumentException("Position not available.");
		}
		
			int size = positions.size();
			for(int i = 0; i < size; i++) {
				if (positionName.equals(positions.get(i).getPositionName())) {
					activePosition = positions.get(i);
					activePosition.setApplicationId();
					break;
				}
				else if(i == size - 1 && activePosition.getPositionName() != positionName) {
					throw new IllegalArgumentException("Position not available.");
				}
			}
	}
	
	/**
	 * gets the active position
	 * @return the active position
	 */
	public String getActivePositionName() {
		if (activePosition == null) {
			return null;
		}
		return activePosition.getPositionName();
	}
	
	/**
	 * gets an array of the position as a list
	 * @return array of position list
	 */
	public String[] getPositionList() {
		String[] positionStringArray = new String[positions.size()];
		
		for(int i = 0; i < positions.size(); i++) {
			
			positionStringArray[i] = positions.get(i).getPositionName();
		}
		
		return positionStringArray;
	}
	
	/**
	 * Adds application to the position
	 * @param firstName the first name of the application
	 * @param surname the surname of the application
	 * @param unityId the unityId of the application
	 */
	public void addApplicationToPosition(String firstName, String surname, String unityId) {
		if(activePosition != null)	{
		activePosition.addApplication(firstName, surname, unityId);
		}
		
	}
	
	/**
	 * Executes the command 
	 * @param id the id of the position to execute on
	 * @param c the command to execute
	 */
	public void executeCommand(int id, Command c) {
		if (activePosition != null) {
		activePosition.executeCommand(id, c);
		}
	}
	
	/**
	 * deletes the application by id
	 * @param id the id to delete
	 */
	public void deleteApplicationById(int id) {
		if (activePosition != null) {
		activePosition.deleteApplicationById(id);
		}
	}
	
	/**
	 * Gets the application as an array
	 * @param stateName the states name
	 * @return the applications as array
	 */
	public String[][] getApplicationsAsArray(String stateName){
		if(activePosition != null) {
			if ("All".equals(stateName)) {
				int size = activePosition.getApplications().size();
				String appArray[][] = new String[size][4];
				for (int i = 0; i < size; i++) {
					appArray[i][0] = activePosition.getApplications().get(i).getId() + "";
					appArray[i][1] = activePosition.getApplications().get(i).getState();
					appArray[i][2] = activePosition.getApplications().get(i).getUnityId();
					appArray[i][3] = activePosition.getApplications().get(i).getReviewer() + "";
				}
				return appArray;
			}
			else {
				ArrayList<Application> apps = new ArrayList<Application>();
				for (int i = 0; i < activePosition.getApplications().size(); i++) {
					apps.add(activePosition.getApplications().get(i));
				}
				for (int i = 0; i < apps.size(); i++) {
					if(!apps.get(i).getState().equals(stateName)) {
						apps.remove(i);
					}
				}
				String appArray[][] = new String[apps.size()][4];
				for (int i = 0; i < apps.size(); i++) {
					appArray[i][0] = apps.get(i).getId() + "";
					appArray[i][1] = apps.get(i).getState();
					appArray[i][2] = apps.get(i).getUnityId();
					appArray[i][3] = apps.get(i).getReviewer() + "";
				}
				return appArray;
			}
		}
		String appArray[][] = null;
		return appArray;
		
	}
	
	/**
	 * returns the application by the id
	 * @param id to be returned
	 * @return the application
	 */
	public Application getApplicationById(int id) {
		if (activePosition != null) {
		return activePosition.getApplicationById(id);
		}
		Application blank = null;
		
		return blank;
	}
	
	/**
	 * Resets the manager
	 */
	protected void resetManager() {
		activePosition = null;
		singleton = new WolfHire();
	}
	
	/**
	 * Gets the active Position
	 * @return the position
	 */
	public Position getActivePosition() {
		if (activePosition == null) {
			return null;
		}
		return activePosition;
	}

	
}
