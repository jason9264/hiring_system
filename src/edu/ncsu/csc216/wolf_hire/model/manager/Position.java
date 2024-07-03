package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Implements the position class for the project
 * @author jason
 *
 */
public class Position {


	/**
	 * the positions name as a string
	 */
	private String positionName;
	
	/**
	 * the hours per week as an integer
	 */
	private int hoursPerWeek;
	
	/**
	 * the payrate as a integer
	 */
	private int payRate;
	
	/**
	 * List of application arrayList
	 */
	public ArrayList<Application> appList;

	
	/**
	 * Constructor for a position
	 * @param positionName the name of the position
	 * @param hoursPerWeek the hours worked week
	 * @param payRate the pay rate
	 */
	public Position(String positionName, int hoursPerWeek, int payRate) {
		setPositionName(positionName);
		setHoursPerWeek(hoursPerWeek);
		setPayRate(payRate);
		setApplicationId();
		appList = new ArrayList<Application>();
	}
	
	/**
	 * Sets the application ID
	 */
	public void setApplicationId() {
		if(appList == null || appList.size() == 0) {
			Application.setCounter(1);
		}
		else {
		int maxId = 0;
		if (appList != null) {
		for(int i = 0; i < appList.size(); i++) {
			if (maxId < appList.get(i).getId()) {
				maxId = appList.get(i).getId() + 1;
			}
		}
		}
	    Application.setCounter(maxId + 1);
		}
	}
	
	/**
	 * Sets the position name 
	 * @param positionName the position name as a string
	 */
	private void setPositionName(String positionName) {
		if(positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.positionName = positionName;
	}
	
	/**
	 * gets the position name
	 * @return the positions name
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * sets the hours per week
	 * @param hoursPerWeek the hours per week param
	 */
	private void setHoursPerWeek(int hoursPerWeek) {
		if(hoursPerWeek < 5 || hoursPerWeek > 20) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.hoursPerWeek = hoursPerWeek;
	}
	
	/**
	 * Gets the hours per week
	 * @return the hours perk week
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
		
	}
	
	/**
	 * Sets the pay rate for the position
	 * @param payRate the pay rate
	 */
	private void setPayRate(int payRate) {
		if(payRate < 7 || payRate > 35) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.payRate = payRate;
	}
	
	/**
	 * gets the pay rate
	 * @return the pay rate
	 */
	public int getPayRate() {
		return payRate;
		
	}
	
	/**
	 * adds an application using params
	 * @param firstName the first name 
	 * @param surname the surname
	 * @param unityId the unity id
	 * @return and integer value
	 */
	public int addApplication(String firstName, String surname, String unityId) {
		Application newApp = new Application(firstName, surname, unityId);
		appList.add(newApp);

		int id = newApp.getId();
		for(int i = 0; i < appList.size(); i++) {
			if (i == 0 && id < appList.get(0).getId()) {
				appList.add(i, newApp);
			}
			else if (id > appList.get(i).getId() && id < appList.get(i + 1).getId()) {
					appList.add(i, newApp);
				
			}
			if(i == appList.size() - 1 && id > appList.get(i).getId()) {
					appList.add(newApp);
			}
		}
		return newApp.getId();
		
	}
	
	/**
	 * adds an application by using an application
	 * @param application application to be added
	 * @return a integer value
	 */
	public int addApplication(Application application) {
		int id = application.getId();
		
		if(appList.size() == 0) {
			appList.add(application);
			return application.getId();
		}
		
		for (int i = 0; i < appList.size(); i++) {
			int current = appList.get(i).getId();
			int after = 0;
			
			if(i == appList.size() - 1) {
				after = appList.get(i).getId();
			}
			else {
				after = appList.get(i + 1).getId();
			}
			if (id < current && i == 0) {
				appList.add(i, application);
			}
			else if (current < id && id < after) {
				appList.add(i + 1, application);
				return id;
			}
			else if(i == appList.size() - 1 && current < id) {
				appList.add(application);
				return id;
			}
			else if(current == id || id == after) {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		}
		return application.getId();
		
	}
	
	/**
	 * Gets the list of applications
	 * @return the list of application
	 */
	public ArrayList<Application> getApplications(){
		return appList;
	}
	
	/**
	 * gets the application by application ID
	 * @param id of the application to retrieve
	 * @return the application retrieved
	 */
	public Application getApplicationById(int id) {
		
		for(int i = 0; i < appList.size(); i++) {
			if (id == appList.get(i).getId()) {
				return appList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Deletes an application by the Id
	 * @param id of the application
	 */
	public void deleteApplicationById(int id) {
		for(int i = 0; i < appList.size(); i++) {
			if (id == appList.get(i).getId()) {
				appList.remove(i);
			}
		}
	}
	
	/**
	 * executes the command
	 * @param id the id number
	 * @param c the command chosen
	 */
	public void executeCommand(int id, Command c) {
		
		for(int i = 0; i < appList.size(); i++) {
			if (id == appList.get(i).getId()) {
				appList.get(i).update(c);
			}
		}
	}
	
	/**
	 * Returns a full string description
	 * @return positionName Returns the position as a string
	 */
	public String toString() {
		String posText = "# " + getPositionName() + "," + getHoursPerWeek() + "," + getPayRate();
		return posText;
	}
	
	
	
}
