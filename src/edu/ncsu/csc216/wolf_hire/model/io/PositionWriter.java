package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * class to Writes the Positions and their Applications to the file name provided.
 * @author jason
 */
public class PositionWriter {

	/**
	 * Writes the position to the file
	 * @param fileName the file name to write to
	 * @param positions the list of positions
	 * @throws IllegalArgumentException  if the file cannot be created or written to
	 */
	public static void writePositionsToFile(String fileName, ArrayList<Position> positions) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));

			for (int i = 0; i < positions.size(); i++) {
				Position pos = positions.get(i);
				ArrayList<Application> appList = pos.getApplications();
				
				if(0 == appList.size() || appList == null) {
					continue;
				}
				
				fileWriter.println(pos.toString());
				for(int j = 0; j < pos.getApplications().size(); j++) {
					Application app = appList.get(j);
					fileWriter.println("* " + app.getId() + "," + app.getState() + "," + app.getFirstName() +
							"," + app.getSurname() + "," + app.getUnityId() + "," + app.getReviewer() + "," + app.getNote());
				}
 	
			}
			fileWriter.close();
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
}
