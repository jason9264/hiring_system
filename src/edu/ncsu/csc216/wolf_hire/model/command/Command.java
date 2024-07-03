package edu.ncsu.csc216.wolf_hire.model.command;

/**
 * This is the class implementation for the command
 * @author jason
 *
 */
public class Command {

	/**
	 * Include an enumeration from possible command values that can cause transitions in the FSM model.
	 */
	public enum CommandValue {
		/**
		 * Assign command
		 */
		ASSIGN, 
		
		/**
		 * Reject command
		 */
		REJECT, 
		
		/** 
		 * Resubmit Command
		 */
		RESUBMIT, 
		
		/** 
		 * Return Command
		 */
		RETURN, 
		
		/** 
		 * Schedule Command
		 */
		SCHEDULE, 
		
		/** 
		 * Process Command
		 */
		PROCESS, 
		
		/** 
		 * Hire Command
		 */
		HIRE, 
		
		/** 
		 * Terminate Command
		 */
		TERMINATE }

	/**
	 * Stores the command value of enum CommandValue
	 */
	private CommandValue command;
	
	/**
	 * String to store the commandInformation
	 */
	private String commandInformation;
	
	/**
	 * Constructor for Command
	 * @param command the command value chosen
	 * @param commandInformation the command information to be used 
	 */
	public Command(CommandValue command, String commandInformation ) {
		//Start conditional checking
		if (command == null) {
			throw new IllegalArgumentException("Invalid information.");
		}

		if ((command == Command.CommandValue.ASSIGN || command == Command.CommandValue.REJECT 
				|| command == Command.CommandValue.TERMINATE) && (commandInformation == null || "".equals(commandInformation))){
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if(command == Command.CommandValue.SCHEDULE && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if(command == Command.CommandValue.HIRE && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if(command == Command.CommandValue.RESUBMIT && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if(command == Command.CommandValue.PROCESS && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if(command == Command.CommandValue.RETURN && commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		//Ends conditional checking
		
		// set variables
		this.command = command;
		this.commandInformation = commandInformation;
	}

	
	/**
	 * Gets the command
	 * @return command the command
	 */
	public CommandValue getCommand() {
		return command;
		
	}
	
	/**
	 * Gets the command information
	 * @return comamndInformation the commands informations
	 */
	public String getCommandInformation() {
		return commandInformation;
	}
	
	
}
