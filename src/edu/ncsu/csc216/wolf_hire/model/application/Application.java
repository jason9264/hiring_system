package edu.ncsu.csc216.wolf_hire.model.application;

import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Implementation for the application
 * @author Jason Wang
 *
 */
public class Application {

	/**
	 * The Applications ID
	 */
	private int applicationId = 0;
	
	/**
	 * The first name of the application
	 */
	private String firstName = null;
	
	/**
	 * The surname of the application
	 */
	private String surname = null;
	
	/**
	 * The unityId of the application
	 */
	private String unityId = null;
	
	/**
	 * The reviewer of the application
	 */
	private String reviewer = null;
	
	/**
	 * The note of the applications
	 */
	private String note = null;
	
	/**
	 * The counter of the applciations
	 */
	private static int counter = 0;
	
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
	
	/**
	 * Constructor application
	 * @param firstName the first name of the application
	 * @param surname the surname of the application
	 * @param unityId the unityId of the application
	 */
	public Application(String firstName, String surname, String unityId) {
		//Start conditional checking

		if(firstName == null || surname == null || unityId == null ||
				"".equals(firstName) || "".equals(surname) || "".equals(unityId)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		//End conditional checking
		else {
		setId(counter);
		incrementCounter();
		currentState = submittedState;
		reviewer = null;
		note = null;
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		}
	}
	
	/**
	 * Constructor application with additional variables
	 * @param id the ID of the application
	 * @param state the state of the application
	 * @param firstName the firstname of the application
	 * @param surname the surname of the applications
	 * @param unityId the unityID of the application
	 * @param reviewer the reviewer of the application
	 * @param note the note of the applications
	 */
	public Application(int id, String state, String firstName, String surname, 
			String unityId, String reviewer, String note) {
		if(id <= 0) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(state == null) {
			throw new IllegalArgumentException("Application cannot be created.");

		}
		//End conditional checking
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		
		if(reviewer != null && SUBMITTED_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(reviewer != null && REJECTED_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((reviewer == null || "".equals(reviewer)) && REVIEWING_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((reviewer == null || "".equals(reviewer)) && INTERVIEWING_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((reviewer == null || "".equals(reviewer)) && PROCESSING_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((reviewer == null || "".equals(reviewer)) && HIRED_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if((reviewer == null || "".equals(reviewer)) && INACTIVE_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		setReviewer(reviewer);
		setState(state);
		
		if(note != null && SUBMITTED_NAME.equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(REJECTED_NAME.equals(state) && note == null || REJECTED_NAME.equals(state) && !("Qualifications".equals(note) || "Incomplete".equals(note) ||
				 "Positions".equals(note) || "Duplicate".equals(note))){
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(REVIEWING_NAME.equals(state) && ("Qualifications".equals(note) || "Incomplete".equals(note) ||
				 "Positions".equals(note) || "Duplicate".equals(note) || "Fired".equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(INTERVIEWING_NAME.equals(state) && ("Qualifications".equals(note) || "Incomplete".equals(note) ||
				 "Positions".equals(note) || "Duplicate".equals(note) || "Fired".equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(PROCESSING_NAME.equals(state) && ("Qualifications".equals(note) || "Incomplete".equals(note) ||
				 "Positions".equals(note) || "Duplicate".equals(note) || "Fired".equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(HIRED_NAME.equals(state) && ("Qualifications".equals(note) || "Incomplete".equals(note) ||
				 "Positions".equals(note) || "Duplicate".equals(note) || "Fired".equals(note))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if(INACTIVE_NAME.equals(state) && note == null || INACTIVE_NAME.equals(state) && !("Completed".equals(note) || "Resigned".equals(note) ||
				 "Fired".equals(note))){
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		setNote(note);
		
		if(id > counter) {
			setId(id);
			setCounter(id + 1);
		}
		else {
			setId(id);
		}
	}
	
	
	/**
	 * Sets the ID
	 * @param id applicationId the applicationId to set
	 */
	private void setId(int id) {
		applicationId = id;
	}
	
	/**
	 * Sets the State
	 * @param state applicationId the applicationId to set
	 */
	private void setState(String state) {
		if(state == null || "".equals(state)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		if (SUBMITTED_NAME.equals(state)) {
			currentState = submittedState;
		}
		if (state.equals(REJECTED_NAME)) {
			currentState = rejectedState;
		}
		
		if (state.equals(REVIEWING_NAME)) {
			currentState = reviewingState;
		}
		
		if (state.equals(INTERVIEWING_NAME)) {
			currentState = interviewingState;
		}
			
		if (state.equals(PROCESSING_NAME)) {
			currentState = processingState;
		}
			
		if (state.equals(HIRED_NAME)) {
			currentState = hiredState;
		}
			
		if (state.equals(INACTIVE_NAME)) {
			currentState = inactiveState;
		}
	}

	/**
	 * Sets the first name
	 * @param firstName the firstName to set
	 */
	private void setFirstName(String firstName) {
		
		if(firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		
		this.firstName = firstName;
	}
	
	/**
	 * Sets the surname
	 * @param surname the surname to set
	 */
	private void setSurname(String surname) {
		if(surname == null || "".equals(surname)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.surname = surname;
	}
	
	/**
	 * Sets the UnityID
	 * @param unityId the unityId to set
	 */
	private void setUnityId(String unityId) {
		if(unityId == null || "".equals(unityId)) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.unityId = unityId;
	}
	
	/**
	 * Sets the reviewer
	 * @param reviewer the reviewer to set
	 */
	private void setReviewer(String reviewer) {
		if (reviewer == null) {
			this.reviewer = "";
		}
		this.reviewer = reviewer;
	}
	
	/**
	 * Sets the note
	 * @param note the note to set
	 */
	private void setNote(String note) {
		if (note == null) {
			this.note = "";
		}
		this.note = note;
	}
	
	/**
	 * sets the counter
	 * @param newCount the new counter to set to
	 */
	public static void setCounter(int newCount) {
		counter = newCount;
	}
	
	/**
	 * Gets the ID
	 * @return the ID
	 */
	public int getId() {
		return applicationId;
		
	}
	
	/**
	 * Gets the first Name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * Gets the surname
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Gets the unityID
	 * @return the unityId
	 */
	public String getUnityId() {
		return unityId;
	}

	/**
	 * Gets the reviewer
	 * @return the reviewer
	 */
	public String getReviewer() {
		if(reviewer == null){
			return "";
		}
		return reviewer;
	}

	/**
	 * Get the note
	 * @return the note
	 */
	public String getNote() {
		if(note == null){
			return "";
		}
		return note;
	}
	
	/**
	 * returns the state
	 * @return the state
	 */
	public String getState() {
		return currentState.getStateName();
	}

	/**
	 * Incrememnts the counter
	 */
	public static void incrementCounter() {
		Application.counter = Application.counter + 1;
		
	}
	
	/**
	 * turns the application to a string
	 * @return a string of the application
	 */
	@Override
	public String toString() {
		String app = null;
		
		app = "*" + getId() + "," + getFirstName() + "," + getSurname() + "," + getUnityId() + "," +
				getReviewer() + "," + getNote();
		
		return app;
	}
	
	/**
	 * updates the command
	 * @param c the command to be updated
	 * @throws UnsupportedOperationException e if the update state is invalid
	 */
	public void update(Command c) {
		try {
			currentState.updateState(c);
		} catch (UnsupportedOperationException e) {
			throw new UnsupportedOperationException("Invalid command.");
		}
	}
	
	//States
	/** 
	 * The current state
	 */
	private ApplicationState currentState;
	
	/**
	 * The submitted state
	 */
	private final ApplicationState submittedState = new SubmittedState();
	
	/**
	 * The rejected state
	 */
	private final ApplicationState rejectedState = new RejectedState();
	
	/**
	 * The reviewing state
	 */
	private final ApplicationState reviewingState = new ReviewingState();
	
	/**
	 * The interviewing state
	 */
	private final ApplicationState interviewingState = new InterviewingState();
	
	/**
	 * The processing state
	 */
	private final ApplicationState processingState = new ProcessingState();
	
	/**
	 * The hired state
	 */
	private ApplicationState hiredState = new HiredState();
	
	/**
	 * The submitted state
	 */
	private ApplicationState inactiveState = new InactiveState();
	//End States
	
	/**
	 * Interface for states in the Application State Pattern.  All 
	 * concrete Application states must implement the ApplicationState interface.
	 * The ApplicationState interface should be a private interface of the 
	 * Application class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface ApplicationState {
		
		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	}
	
	/**
	 * Submitted State for the interface
	 * @author jason
	 *
	 */
	private class SubmittedState implements ApplicationState {

		@Override
		public void updateState(Command command) {
		
			if(command.getCommand() == Command.CommandValue.ASSIGN) {
				reviewer = command.getCommandInformation();
                currentState = reviewingState;
				}
			else if(command.getCommand() == Command.CommandValue.REJECT) {
				if(!("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) ||
						 "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation()))) {
					throw new UnsupportedOperationException("Invalid command.");
				}
            	note = command.getCommandInformation();
            	currentState = rejectedState; 
            	}
			else{
				throw new UnsupportedOperationException("Invalid command.");
				} 
			
			}
			
		@Override
		public String getStateName() {
			return SUBMITTED_NAME;
			}
		
		}
	
	/**
	 * RejectState state for the interface
	 * @author jason
	 *
	 */
	private class RejectedState implements ApplicationState {

		@Override
		public void updateState(Command command) {
		
			if(command.getCommand() == Command.CommandValue.RESUBMIT) {
				currentState = submittedState;
				reviewer = command.getCommandInformation();
				note = "";
				}
			else {
				throw new UnsupportedOperationException("Invalid command.");

			}
		
		}
	
		
		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}

	}
	
	/**
	 * Reviewing State of the interface
	 * @author jason
	 *
	 */
	private class ReviewingState implements ApplicationState {

		@Override
		public void updateState(Command command) {
			if(command.getCommand() == Command.CommandValue.SCHEDULE) {
                currentState = interviewingState;
			}
			else if(command.getCommand() == Command.CommandValue.RETURN) {
                reviewer = command.getCommandInformation();
                currentState = submittedState;
			}
			else if(command.getCommand() == Command.CommandValue.REJECT) {
				if(!("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) ||
						 "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation()))) {
					throw new UnsupportedOperationException("Invalid command.");
				}
            	note = command.getCommandInformation();
            	reviewer = "";
            	currentState = rejectedState;
			}
			else {
				throw new UnsupportedOperationException("Invalid command.");

			}
		}

		@Override
		public String getStateName() {
			return REVIEWING_NAME;
		}

	}
	
	/**
	 * Interviewing State of the interface
	 * @author jason
	 *
	 */
	private class InterviewingState implements ApplicationState {

		@Override
		public void updateState(Command command) {
			
			if(command.getCommand() == Command.CommandValue.PROCESS){
                currentState = processingState;
			}
			else if(command.getCommand() == Command.CommandValue.ASSIGN){
				reviewer = command.getCommandInformation();
                currentState = reviewingState;
            }
			else if(command.getCommand() == Command.CommandValue.SCHEDULE){
				currentState = interviewingState;
            }
			else if(command.getCommand() == Command.CommandValue.REJECT) {
				if(!("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) ||
						 "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation()))) {
					throw new UnsupportedOperationException("Invalid command.");
				}
				note = command.getCommandInformation();
				reviewer = "";
            	currentState = rejectedState;
			}		
			else {
				throw new UnsupportedOperationException("Invalid command.");

			}
		}

		@Override
		public String getStateName() {
			return INTERVIEWING_NAME;
		}

	}
	
	/**
	 * Processing State of the interface
	 * @author jason
	 *
	 */
	private class ProcessingState implements ApplicationState {

		@Override
		public void updateState(Command command) {
			
			if(command.getCommand() == Command.CommandValue.HIRE) {
                currentState = hiredState;
			}
			else if(command.getCommand() == Command.CommandValue.REJECT) {
				if(!("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) ||
						 "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation()))) {
					throw new UnsupportedOperationException("Invalid command.");
				}
            	note = command.getCommandInformation();
            	reviewer = "";
            	currentState = rejectedState;
			}
			else {
				throw new UnsupportedOperationException("Invalid command.");
			}
						
		}

		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}

	}
	
	/**
	 * Hired state of the interface
	 * @author jason
	 *
	 */
	private class HiredState implements ApplicationState {

		@Override
		public void updateState(Command command) {
			if(command.getCommand() == Command.CommandValue.TERMINATE) {
                if (!("Completed".equals(command.getCommandInformation()) ||
				 "Resigned".equals(command.getCommandInformation()) || "Fired".equals(command.getCommandInformation()))) {
    				throw new UnsupportedOperationException("Invalid command.");
                }
				note = command.getCommandInformation();
                currentState = inactiveState;	
			}			
			else {
				throw new UnsupportedOperationException("Invalid command.");
			}
		}
		@Override
		public String getStateName() {
			return HIRED_NAME;
		}

	}
	
	/**
	 * Inactive State for the interface
	 * @author jason
	 *
	 */
	private class InactiveState implements ApplicationState {

		@Override
		public void updateState(Command command) {
			throw new UnsupportedOperationException("Invalid command.");
		}

		@Override
		public String getStateName() {
			return INACTIVE_NAME;
		}

	
	}
}
