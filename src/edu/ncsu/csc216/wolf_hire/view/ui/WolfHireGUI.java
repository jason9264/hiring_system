package edu.ncsu.csc216.wolf_hire.view.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.manager.WolfHire;

/**
 * Container for the WolfHire system that has the menu options for new position 
 * files, loading existing files, saving files and quitting.
 * Depending on user actions, other JPanels are loaded for the
 * different ways users interact with the UI.
 * 
 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
 */
public class WolfHireGUI extends JFrame implements ActionListener {
	
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "Wolf Hire";
	/** Text for the File Menu. */
	private static final String FILE_MENU_TITLE = "File";
	/** Text for the add position menu item. */
	private static final String ADD_TITLE = "Add Position";
	/** Text for the Load menu item. */
	private static final String LOAD_TITLE = "Load Positions File";
	/** Text for the Save menu item. */
	private static final String SAVE_TITLE = "Save Positions";
	/** Text for the Quit menu item. */
	private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
	private JMenuBar menuBar;
	/** Menu for the GUI. */
	private JMenu menu;
	/** Menu item for creating a new position. */
	private JMenuItem itemAddPosition;
	/** Menu item for loading a file containing one or more Positions and their Applicants. */
	private JMenuItem itemLoadPosition;
	/** Menu item for saving the positions and their applications. */
	private JMenuItem itemSavePosition;
	/** Menu item for quitting the program. */
	private JMenuItem itemQuit;
	/** Panel that will contain different views for the application. */
	private JPanel panel;
	/** Constant to identify PositionPanel for CardLayout. */
	private static final String POSITION_LIST_PANEL = "PositionPanel";
	/** Constant to identify SubmittedPanel for CardLayout. */
	private static final String SUBMITTED_PANEL = "SubmittedPanel";
	/** Constant to identify RejectedPanel for CardLayout. */
	private static final String REJECTED_PANEL = "RejectedPanel";
	/** Constant to identify ReviewingPanel for CardLayout. */
	private static final String REVIEWING_PANEL = "ReviewingPanel";
	/** Constant to identify InterviewingPanel for CardLayout. */
	private static final String INTERVIEWING_PANEL = "InterviewingPanel";
	/** Constant to identify ProcessingPanel for CardLayout. */
	private static final String PROCESSING_PANEL = "ProcessingPanel";
	/** Constant to identify HiredPanel for CardLayout. */
	private static final String HIRED_PANEL = "HiredPanel";
	/** Constant to identify InactivePanel for CardLayout. */
	private static final String INACTIVE_PANEL = "InactivePanel";
	/** Constant to identify AddApplicationPanel for CardLayout. */
	private static final String ADD_APPLICATION_PANEL = "AddApplication";
	/** Constant to identify AddPositionPanel for CardLayout. */
	private static final String POSITION_PANEL = "AddPosition";
	
	/** Position panel - we only need one instance, so it's final. */
	private final PositionListPanel pnlPositionList = new PositionListPanel();
	/** Submitted panel - we only need one instance, so it's final. */
	private final SubmittedPanel pnlSubmitted = new SubmittedPanel();
	/** Rejected panel - we only need one instance, so it's final. */
	private final RejectedPanel pnlRejected = new RejectedPanel();
	/** Reviewing panel - we only need one instance, so it's final. */
	private final ReviewingPanel pnlReviewingPanel = new ReviewingPanel();
	/** Interviewing panel - we only need one instance, so it's final. */
	private final InterviewingPanel pnlInterviewing = new InterviewingPanel();
	/** Processing panel - we only need one instance, so it's final. */
	private final ProcessingPanel pnlProcessing = new ProcessingPanel();
	/** Hired panel - we only need one instance, so it's final. */
	private final HiredPanel pnlHired = new HiredPanel();
	/** Inactive panel - we only need one instance, so it's final. */
	private final InactivePanel pnlInactive = new InactivePanel();
	/** Add Application panel - we only need one instance, so it's final. */
	private final AddApplicationPanel pnlAddApplication = new AddApplicationPanel();
	/** Add Position panel - we only need one instance, so it's final. */
	private final PositionPanel pnlPosition = new PositionPanel();
	/** Reference to CardLayout for panel.  Stacks all of the panels. */
	private CardLayout cardLayout;
	
	
	/**
	 * Constructs a WolfHireGUI object that will contain a JMenuBar and a
	 * JPanel that will hold different possible views of the data in
	 * the WolfHire system.
	 */
	public WolfHireGUI() {
		super();
		
		//Set up general GUI info
		setSize(500, 700);
		setLocation(50, 50);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUpMenuBar();
		
		//Create JPanel that will hold rest of GUI information.
		//The JPanel utilizes a CardLayout, which stack several different
		//JPanels.  User actions lead to switching which "Card" is visible.
		panel = new JPanel();
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		panel.add(pnlPositionList, POSITION_LIST_PANEL);
		panel.add(pnlSubmitted, SUBMITTED_PANEL);
		panel.add(pnlRejected, REJECTED_PANEL);
		panel.add(pnlReviewingPanel, REVIEWING_PANEL);
		panel.add(pnlInterviewing, INTERVIEWING_PANEL);
		panel.add(pnlProcessing, PROCESSING_PANEL);
		panel.add(pnlHired, HIRED_PANEL);
		panel.add(pnlInactive, INACTIVE_PANEL);
		panel.add(pnlAddApplication, ADD_APPLICATION_PANEL);
		panel.add(pnlPosition, POSITION_PANEL);
		cardLayout.show(panel, POSITION_LIST_PANEL);
		
		//Add panel to the container
		Container c = getContentPane();
		c.add(panel, BorderLayout.CENTER);
		
		//Add window listener to save when closing
		addWindowListener(new WindowAdapter() {

			/**
			 * Ask the user to save the positions file when closing GUI.
			 * @param e WindowEvent leading to GUI closing
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				WolfHire model = WolfHire.getInstance();
				try {
					model.savePositionsToFile(getFileName(false));
				} catch (IllegalArgumentException exp) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, exp.getMessage());
				} catch (IllegalStateException exp) {
					//Don't do anything - user canceled (or error)
				}
			}
			
		});
		
		//Set the GUI visible
		setVisible(true);
	}
	
	/**
	 * Makes the GUI Menu bar that contains options for loading/saving a position
	 * file containing applications or for quitting the application.
	 */
	private void setUpMenuBar() {
		//Construct Menu items
		menuBar = new JMenuBar();
		menu = new JMenu(FILE_MENU_TITLE);
		itemAddPosition = new JMenuItem(ADD_TITLE);
		itemLoadPosition = new JMenuItem(LOAD_TITLE);
		itemSavePosition = new JMenuItem(SAVE_TITLE);
		itemQuit = new JMenuItem(QUIT_TITLE);
		itemAddPosition.addActionListener(this);
		itemLoadPosition.addActionListener(this);
		itemSavePosition.addActionListener(this);
		itemQuit.addActionListener(this);
		
		//Start with save button disabled
		itemSavePosition.setEnabled(false);
		
		//Build Menu and add to GUI
		menu.add(itemAddPosition);
		menu.add(itemLoadPosition);
		menu.add(itemSavePosition);
		menu.add(itemQuit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Performs an action based on the given ActionEvent.
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		//Use singleton to create/get the sole instance.
		WolfHire model = WolfHire.getInstance();
		if (e.getSource() == itemAddPosition) {
			//Create a new position
			try {
				cardLayout.show(panel, POSITION_PANEL);
				validate();
				repaint();
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, exp.getMessage());
			}
		} else if (e.getSource() == itemLoadPosition) {
			//Load an existing position list
			try {
				model.loadPositionsFromFile(getFileName(true));
				itemSavePosition.setEnabled(true);
				pnlPositionList.updatePosition();
				cardLayout.show(panel, POSITION_LIST_PANEL);
				validate();
				repaint();
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, exp.getMessage());
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		} else if (e.getSource() == itemSavePosition) {
			//Save positions and applications
			try {
				model.savePositionsToFile(getFileName(false));
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, exp.getMessage());
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		} else if (e.getSource() == itemQuit) {
			//Quit the program
			try {
				model.savePositionsToFile(getFileName(false));
				System.exit(0);  //Ignore SpotBugs warning here - this is the only place to quit the program!
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, exp.getMessage());
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		}
	}
	
	/**
	 * Returns a file name generated through interactions with a JFileChooser
	 * object.
	 * @param load true if loading a file, false if saving
	 * @return the file name selected through JFileChooser
	 * @throws IllegalStateException if no file name provided
	 */
	private String getFileName(boolean load) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		int returnVal = Integer.MIN_VALUE;
		if (load) {
			returnVal = fc.showOpenDialog(this);
		} else {
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File gameFile = fc.getSelectedFile();
		return gameFile.getAbsolutePath();
	}

	/**
	 * Starts the GUI for the WolfHire application.
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		new WolfHireGUI();
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * shows the position and it's list of applications
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class PositionListPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		
		/** Label for selecting active position */
		private JLabel lblActivePosition;
		/** Combo box for Project list */
		private JComboBox<String> comboPositionList;
		/** Button to update to the select position */
		private JButton btnUpdatePosition;
		
		/** Button for creating a new application */
		private JButton btnAdd;
		/** Button for deleting the selected application in the list */
		private JButton btnDelete;
		/** Button for editing the selected application in the list */
		private JButton btnEdit;
		
		/** Label for filter by state */
		private JLabel lblFilterState;
		/** Combo box for states */
		private JComboBox<String> comboFilterState;
		/** Button to update to filter by state */
		private JButton btnFilterState;
		
		/** JTable for displaying the list of applications */
		private JTable tableApplications;
		/** TableModel for applications */
		private ApplicationTableModel tableModel;
		
		/**
		 * Creates the position panel with application list.
		 */
		public PositionListPanel() {
			super(new BorderLayout());
			
			//Set up JPanel for positions
			lblActivePosition = new JLabel("Active Position");
			comboPositionList = new JComboBox<String>();
			btnUpdatePosition = new JButton("Select Position");
			btnUpdatePosition.addActionListener(this);
						
			//Set up the JPanel that will hold action buttons		
			btnAdd = new JButton("Add Application");
			btnAdd.addActionListener(this);
			btnDelete = new JButton("Delete Application");
			btnDelete.addActionListener(this);
			btnEdit = new JButton("Edit Application");
			btnEdit.addActionListener(this);
			
			//Set up JPanel for filter
			lblFilterState = new JLabel("Filter by State");
			comboFilterState = new JComboBox<String>();
			btnFilterState = new JButton("Filter");
			btnFilterState.addActionListener(this);
			
			comboFilterState.addItem("All");
			comboFilterState.addItem(Application.SUBMITTED_NAME);
			comboFilterState.addItem(Application.REJECTED_NAME);
			comboFilterState.addItem(Application.REVIEWING_NAME);
			comboFilterState.addItem(Application.INTERVIEWING_NAME);
			comboFilterState.addItem(Application.PROCESSING_NAME);
			comboFilterState.addItem(Application.HIRED_NAME);
			comboFilterState.addItem(Application.INACTIVE_NAME);
			
			JPanel pnlActions = new JPanel();
			pnlActions.setLayout(new GridLayout(3, 3));
			pnlActions.add(lblActivePosition);
			pnlActions.add(comboPositionList);
			pnlActions.add(btnUpdatePosition);
			pnlActions.add(btnAdd);
			pnlActions.add(btnDelete);
			pnlActions.add(btnEdit);
			pnlActions.add(lblFilterState);
			pnlActions.add(comboFilterState);
			pnlActions.add(btnFilterState);
						
			//Set up table
			tableModel = new ApplicationTableModel();
			tableApplications = new JTable(tableModel);
			tableApplications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableApplications.setPreferredScrollableViewportSize(new Dimension(500, 500));
			tableApplications.setFillsViewportHeight(true);
			
			JScrollPane listScrollPane = new JScrollPane(tableApplications);
			
			add(pnlActions, BorderLayout.NORTH);
			add(listScrollPane, BorderLayout.CENTER);
			
			updatePosition();
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnUpdatePosition) {
				int idx = comboPositionList.getSelectedIndex();
				
				if (idx == -1) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No position selected");
				} else {				
					String positionName = comboPositionList.getItemAt(idx);
					WolfHire.getInstance().loadPosition(positionName);
				}
				updatePosition();
			} else if (e.getSource() == btnFilterState) {
				int idx = comboFilterState.getSelectedIndex();
				
				if (idx == -1) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No state selected");
				} else {				
					String stateName = comboFilterState.getItemAt(idx);
					tableModel.updateData(stateName);
				}
			} else if (e.getSource() == btnAdd) {
				//If the add button is clicked switch to the AddApplicationPanel
				cardLayout.show(panel,  ADD_APPLICATION_PANEL);
			} else if (e.getSource() == btnDelete) {
				//If the delete button is clicked, delete the application
				int row = tableApplications.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No application selected");
				} else {
					try {
						int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
						WolfHire.getInstance().deleteApplicationById(id);
					} catch (NumberFormatException nfe ) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid id");
					}
				}
				updatePosition();
			} else if (e.getSource() == btnEdit) {
				//If the edit button is clicked, switch panel based on state
				int row = tableApplications.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No application selected");
				} else {
					try {
						int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
						String stateName = WolfHire.getInstance().getApplicationById(id).getState();
						if (stateName.equals(Application.SUBMITTED_NAME)) {
							cardLayout.show(panel, SUBMITTED_PANEL);
							pnlSubmitted.setInfo(id);
						}
						if (stateName.equals(Application.REJECTED_NAME)) {
							cardLayout.show(panel, REJECTED_PANEL);
							pnlRejected.setInfo(id);
						}
						if (stateName.equals(Application.REVIEWING_NAME)) {
							cardLayout.show(panel, REVIEWING_PANEL);
							pnlReviewingPanel.setInfo(id);
						}
						if (stateName.equals(Application.INTERVIEWING_NAME)) {
							cardLayout.show(panel, INTERVIEWING_PANEL);
							pnlInterviewing.setInfo(id);
						} 
						if (stateName.equals(Application.PROCESSING_NAME)) {
							cardLayout.show(panel, PROCESSING_PANEL);
							pnlProcessing.setInfo(id);
						}  
						if (stateName.equals(Application.HIRED_NAME)) {
							cardLayout.show(panel, HIRED_PANEL);
							pnlHired.setInfo(id);
						} 
						if (stateName.equals(Application.INACTIVE_NAME)) {
							cardLayout.show(panel, INACTIVE_PANEL);
							pnlInactive.setInfo(id);
						}
						
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid id");
					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid id");
					}
				}
			} 
			WolfHireGUI.this.repaint();
			WolfHireGUI.this.validate();
		}
		
		public void updatePosition() {
			tableModel.updateData("All");
			
			String positionName = WolfHire.getInstance().getActivePositionName();
			
			if (positionName == null) {
				positionName = "Create a Position";
				btnAdd.setEnabled(false);
				btnDelete.setEnabled(false);
				btnEdit.setEnabled(false);
				btnUpdatePosition.setEnabled(false);
				btnFilterState.setEnabled(false);
			} else {
				btnAdd.setEnabled(true);
				btnDelete.setEnabled(true);
				btnEdit.setEnabled(true);
				btnUpdatePosition.setEnabled(true);
				btnFilterState.setEnabled(true);
			}
			
			comboPositionList.removeAllItems();
			String [] positionList = WolfHire.getInstance().getPositionList();
			for (int i = 0; i < positionList.length; i++) {
				comboPositionList.addItem(positionList[i]);
			}
			comboPositionList.setSelectedItem(WolfHire.getInstance().getActivePositionName());
			
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Position: " + positionName);
			setBorder(border);
			setToolTipText("Project: " + positionName);
		}
		
		/**
		 * ApplicationTableModel is the object underlying the JTable object that displays
		 * the list of applications to the user.
		 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
		 */
		private class ApplicationTableModel extends AbstractTableModel {
			
			/** ID number used for object serialization. */
			private static final long serialVersionUID = 1L;
			/** Column names for the table */
			private String [] columnNames = {"ID", "State", "Unity ID", "Reviewer"};
			/** Data stored in the table */
			private Object [][] data;
			
			/**
			 * Constructs the ApplicationTableModel by requesting the latest information
			 * from the ApplicationTableModel.
			 */
			public ApplicationTableModel() {
				updateData("All");
			}

			/**
			 * Returns the number of columns in the table.
			 * @return the number of columns in the table.
			 */
			public int getColumnCount() {
				return columnNames.length;
			}

			/**
			 * Returns the number of rows in the table.
			 * @return the number of rows in the table.
			 */
			public int getRowCount() {
				if (data == null) 
					return 0;
				return data.length;
			}
			
			/**
			 * Returns the column name at the given index.
			 * @param col the column index
			 * @return the column name at the given column.
			 */
			public String getColumnName(int col) {
				return columnNames[col];
			}

			/**
			 * Returns the data at the given {row, col} index.
			 * @param row the row index
			 * @param col the column index
			 * @return the data at the given location.
			 */
			public Object getValueAt(int row, int col) {
				if (data == null)
					return null;
				return data[row][col];
			}
			
			/**
			 * Sets the given value to the given {row, col} location.
			 * @param value Object to modify in the data.
			 * @param row the row index
			 * @param col the column index
			 */
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
			
			/**
			 * Updates the given model with application information from the WolfHire system.
			 * @param stateName name of state to filter on. 
			 */
			private void updateData(String stateName) {
				WolfHire m = WolfHire.getInstance();
				data = m.getApplicationsAsArray(stateName);
			}
		}
	}
	
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with a submitted application.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class SubmittedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** ApplicationPanel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Label - reviewer */
		private JLabel lblReviewer;
		/** Text Field - reviewer */
		private JTextField txtReviewer;
		/** Button - Assign w/ reviewer */
		private JButton btnAssign;
		
		/** Label - rejection reason */
		private JLabel lblRejectionReason;
		/** ComboBox - rejection reason */
		private JComboBox<String> comboRejectionReason;
		/** Button - Reject w/ rejection reason */
		private JButton btnReject;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the SubmittedState
		 */
		public SubmittedPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			lblReviewer = new JLabel("Reviewer Id:");
			txtReviewer = new JTextField(25);
			btnAssign = new JButton("Assign");
			
			lblRejectionReason = new JLabel("Rejection Reason:");
			comboRejectionReason = new JComboBox<String>();
			comboRejectionReason.addItem("Qualifications");
			comboRejectionReason.addItem("Incomplete");
			comboRejectionReason.addItem("Positions");
			comboRejectionReason.addItem("Duplicate");
			btnReject = new JButton("Reject");
			
			btnCancel = new JButton("Cancel");
			
			btnAssign.addActionListener(this);
			btnReject.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());
			
			JPanel pnlAssign = new JPanel();
			pnlAssign.setLayout(new GridLayout(1, 3));
			pnlAssign.add(lblReviewer);
			pnlAssign.add(txtReviewer);
			pnlAssign.add(btnAssign);
			
			JPanel pnlReject = new JPanel();
			pnlReject.setLayout(new GridLayout(1, 3));
			pnlReject.add(lblRejectionReason);
			pnlReject.add(comboRejectionReason);
			pnlReject.add(btnReject);

			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 1));
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlAssign, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlReject, c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
					
			add(pnlInfo, BorderLayout.NORTH);
					
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnAssign) {				
				String reviewer = txtReviewer.getText();
				//Try a command.  If problem, go back to application list.
				try {
					Command c = new Command(Command.CommandValue.ASSIGN, reviewer);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnReject) {
				int idx = comboRejectionReason.getSelectedIndex();
				
				if (idx == -1) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No rejection reason selected");
				} else {				
					String rejectionReason = comboRejectionReason.getItemAt(idx);
					//Try a command.  If problem, go back to application list.
					try {
						Command c = new Command(Command.CommandValue.REJECT, rejectionReason);
						WolfHire.getInstance().executeCommand(id, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
						reset = false;
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
						reset = false;
					}
				}
			}
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}
	
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with an application in the rejected state.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class RejectedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Button - Reopen*/
		private JButton btnResubmit;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the RejectedState
		 */
		public RejectedPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			btnResubmit = new JButton("Resubmit");
			btnCancel = new JButton("Cancel");
			
			btnResubmit.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());


			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 2));
			pnlBtnRow.add(btnResubmit);
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnResubmit) {
				try {
					Command c = new Command(Command.CommandValue.RESUBMIT, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} 
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}
	
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with an application in the ReviewingState.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class ReviewingPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Button - Schedule */
		private JButton btnSchedule;
		
		/** Button - Return */
		private JButton btnReturn;
		
		/** Label - rejection reason */
		private JLabel lblRejectionReason;
		/** ComboBox - rejection reason */
		private JComboBox<String> comboRejectionReason;
		/** Button - Reject w/ rejection reason */
		private JButton btnReject;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the Reviewing state
		 */
		public ReviewingPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			btnSchedule = new JButton("Schedule");
			
			btnReturn = new JButton("Return");
			
			lblRejectionReason = new JLabel("Rejection Reason:");
			comboRejectionReason = new JComboBox<String>();
			comboRejectionReason.addItem("Qualifications");
			comboRejectionReason.addItem("Incomplete");
			comboRejectionReason.addItem("Positions");
			comboRejectionReason.addItem("Duplicate");
			btnReject = new JButton("Reject");
			
			btnCancel = new JButton("Cancel");
			
			btnSchedule.addActionListener(this);
			btnReturn.addActionListener(this);
			btnReject.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());
			
			JPanel pnlReject = new JPanel();
			pnlReject.setLayout(new GridLayout(1, 3));
			pnlReject.add(lblRejectionReason);
			pnlReject.add(comboRejectionReason);
			pnlReject.add(btnReject);

			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnSchedule);
			pnlBtnRow.add(btnReturn);
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlReject, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnSchedule) {				
				//Try a command.  If problem, go back to application list.
				try {
					Command c = new Command(Command.CommandValue.SCHEDULE, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnReturn ) {
				//Try a command.  If problem, go back to application list.
				try {
					Command c = new Command(Command.CommandValue.RETURN, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnReject) {
				int idx = comboRejectionReason.getSelectedIndex();
				
				if (idx == -1) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No rejection reason selected");
				} else {				
					String rejectionReason = comboRejectionReason.getItemAt(idx);
					//Try a command.  If problem, go back to application list.
					try {
						Command c = new Command(Command.CommandValue.REJECT, rejectionReason);
						WolfHire.getInstance().executeCommand(id, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
						reset = false;
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
						reset = false;
					}
				}
			}
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with an application in the InterviewingState.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class InterviewingPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Label - reviewer */
		private JLabel lblReviewer;
		/** ComboBox - reviewer */
		private JTextField txtReviewer;
		/** Button - Assign w/ reviewer */
		private JButton btnAssign;
		
		/** Button - Schedule */
		private JButton btnSchedule;
		/** Button - Process */
		private JButton btnProcess;
		
		/** Label - rejection reason */
		private JLabel lblRejectionReason;
		/** ComboBox - rejection reason */
		private JComboBox<String> comboRejectionReason;
		/** Button - Reject w/ rejection reason */
		private JButton btnReject;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the Interviewing state
		 */
		public InterviewingPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			lblReviewer = new JLabel("Reviwer Id:");
			txtReviewer = new JTextField(25);
			btnAssign = new JButton("Assign");
			
			btnSchedule = new JButton("Schedule");
			btnProcess = new JButton("Process");
			
			lblRejectionReason = new JLabel("Rejection Reason:");
			comboRejectionReason = new JComboBox<String>();
			comboRejectionReason.addItem("Qualifications");
			comboRejectionReason.addItem("Incomplete");
			comboRejectionReason.addItem("Positions");
			comboRejectionReason.addItem("Duplicate");
			btnReject = new JButton("Reject");
			
			btnCancel = new JButton("Cancel");
			
			btnAssign.addActionListener(this);
			btnSchedule.addActionListener(this);
			btnProcess.addActionListener(this);
			btnReject.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());
			
			JPanel pnlAccept = new JPanel();
			pnlAccept.setLayout(new GridLayout(1, 3));
			pnlAccept.add(lblReviewer);
			pnlAccept.add(txtReviewer);
			pnlAccept.add(btnAssign);
			
			JPanel pnlReject = new JPanel();
			pnlReject.setLayout(new GridLayout(1, 3));
			pnlReject.add(lblRejectionReason);
			pnlReject.add(comboRejectionReason);
			pnlReject.add(btnReject);

			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnSchedule);
			pnlBtnRow.add(btnProcess);
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlAccept, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlReject, c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnAssign) {				
				String reviewer = txtReviewer.getText();
				//Try a command.  If problem, go back to application list.
				try {
					Command c = new Command(Command.CommandValue.ASSIGN, reviewer);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnReject) {
				int idx = comboRejectionReason.getSelectedIndex();
				
				if (idx == -1) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No rejection reason selected");
				} else {				
					String rejectionReason = comboRejectionReason.getItemAt(idx);
					//Try a command.  If problem, go back to application list.
					try {
						Command c = new Command(Command.CommandValue.REJECT, rejectionReason);
						WolfHire.getInstance().executeCommand(id, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
						reset = false;
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
						reset = false;
					}
				}
			} else if (e.getSource() == btnSchedule) {
				try {
					Command c = new Command(Command.CommandValue.SCHEDULE, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnProcess) {
				try {
					Command c = new Command(Command.CommandValue.PROCESS, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			}
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
				txtReviewer.setText("");
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with an application in the ProcessingState.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class ProcessingPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Button - Hire */
		private JButton btnHire;
		
		/** Label - rejection reason */
		private JLabel lblRejectionReason;
		/** ComboBox - rejection reason */
		private JComboBox<String> comboRejectionReason;
		/** Button - Reject w/ rejection reason */
		private JButton btnReject;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the Processing state
		 */
		public ProcessingPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			btnHire = new JButton("Reopen");
			
			lblRejectionReason = new JLabel("Rejection Reason:");
			comboRejectionReason = new JComboBox<String>();
			comboRejectionReason.addItem("Qualifications");
			comboRejectionReason.addItem("Incomplete");
			comboRejectionReason.addItem("Positions");
			comboRejectionReason.addItem("Duplicate");
			btnReject = new JButton("Reject");
			
			btnCancel = new JButton("Cancel");
			
			btnHire.addActionListener(this);
			btnReject.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());

			JPanel pnlReject = new JPanel();
			pnlReject.setLayout(new GridLayout(1, 3));
			pnlReject.add(lblRejectionReason);
			pnlReject.add(comboRejectionReason);
			pnlReject.add(btnReject);

			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 2));
			pnlBtnRow.add(btnHire);
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlReject, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnHire) {
				try {
					Command c = new Command(Command.CommandValue.HIRE, null);
					WolfHire.getInstance().executeCommand(id, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
					reset = false;
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
					reset = false;
				}
			} else if (e.getSource() == btnReject) {
				int idx = comboRejectionReason.getSelectedIndex();
				
				if (idx == -1) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No rejection reason selected");
				} else {				
					String rejectionReason = comboRejectionReason.getItemAt(idx);
					//Try a command.  If problem, go back to application list.
					try {
						Command c = new Command(Command.CommandValue.REJECT, rejectionReason);
						WolfHire.getInstance().executeCommand(id, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
						reset = false;
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
						reset = false;
					}
				}
			} 
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * interacts with an application in the HiredState.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class HiredPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current application's id */
		private int id;
		
		/** Label - termination reason */
		private JLabel lblTerminationReason;
		/** ComboBox - termination reason */
		private JComboBox<String> comboTerminationReason;
		/** Button - Terminate */
		private JButton btnTerminate;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the HiredState
		 */
		public HiredPanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			lblTerminationReason = new JLabel("Rejection Reason:");
			comboTerminationReason = new JComboBox<String>();
			comboTerminationReason.addItem("Completed");
			comboTerminationReason.addItem("Resigned");
			comboTerminationReason.addItem("Fired");
			btnTerminate = new JButton("Terminate");
			
			btnCancel = new JButton("Cancel");
			
			btnTerminate.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());

			JPanel pnlTerminate = new JPanel();
			pnlTerminate.setLayout(new GridLayout(1, 3));
			pnlTerminate.add(lblTerminationReason);
			pnlTerminate.add(comboTerminationReason);
			pnlTerminate.add(btnTerminate);
			
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 1));
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlTerminate, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnTerminate) {
				int idx = comboTerminationReason.getSelectedIndex();
				
				if (idx == -1) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "No termination reason selected");
				} else {				
					String terminationReason = comboTerminationReason.getItemAt(idx);
					//Try a command.  If problem, go back to application list.
					try {
						Command c = new Command(Command.CommandValue.TERMINATE, terminationReason);
						WolfHire.getInstance().executeCommand(id, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid command");
						reset = false;
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid state transition");
						reset = false;
					}
				}
			} 
			if (reset) {
				//All buttons lead back to position
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			}
			//Otherwise, do not refresh the GUI panel and wait for correct user input.
		}
		
	}

	
	private class InactivePanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Panel that presents the application's information to the user */
		private ApplicationPanel pnlInfo;
		/** Current Applications's id */
		private int id;
		
		/** Button - cancel edit */
		private JButton btnCancel;
		
		/**
		 * Constructs the JPanel for editing an application in the Inactive state
		 */
		public InactivePanel() {
			super(new BorderLayout());
			
			pnlInfo = new ApplicationPanel();		
			
			btnCancel = new JButton("Cancel");
			
			btnCancel.addActionListener(this);
			
			JPanel pnlCommands = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Commands");
			pnlCommands.setBorder(border);
			pnlCommands.setToolTipText("Commands");
			
			pnlCommands.setLayout(new GridBagLayout());
			
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 1));
			pnlBtnRow.add(btnCancel);
			
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlCommands.add(pnlBtnRow, c);
						
			add(pnlInfo, BorderLayout.NORTH);
			
			add(pnlCommands, BorderLayout.SOUTH);
			
		}
		
		/**
		 * Set the ApplicationInfoPanel with the given application data.
		 * @param id id of the application
		 */
		public void setInfo(int id) {
			this.id = id;
			pnlInfo.setApplicationInfo(this.id);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			//All buttons lead back to position
			cardLayout.show(panel, POSITION_LIST_PANEL);
			pnlPositionList.updatePosition();
			WolfHireGUI.this.repaint();
			WolfHireGUI.this.validate();
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * shows information about the application.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class ApplicationPanel extends JPanel {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
				
		/** Label - state */
		private JLabel lblState;
		/** JTextField - state */
		private JTextField txtState;
		
		/** Label - first name */
		private JLabel lblFirstName;
		/** JTextField - first name */
		private JTextField txtFirstName;
		
		/** Label - surname */
		private JLabel lblSurname;
		/** JTextField - surname */
		private JTextField txtSurname;
		
		/** Label - unity id */
		private JLabel lblUnityId;
		/** JTextField - unity id */
		private JTextField txtUnityId;
		
		/** Label - reviewer */
		private JLabel lblReviwer;
		/** JTextField - reviewer */
		private JTextField txtReviewer;
		
		/** Label - note */
		private JLabel lblNote;
		/** JTextField - note */
		private JTextField txtNote;
		
		/** 
		 * Construct the panel for the information.
		 */
		public ApplicationPanel() {
			super(new GridBagLayout());
			
			lblState = new JLabel("State: ");
			lblFirstName = new JLabel("First Name: ");
			lblSurname = new JLabel("Surname: ");
			lblUnityId = new JLabel("Unity Id: ");
			lblReviwer = new JLabel("Reviwer Id: ");
			lblNote = new JLabel("Note: ");

			txtState = new JTextField(15);
			txtFirstName = new JTextField(15);
			txtSurname = new JTextField(15);
			txtUnityId = new JTextField(15);
			txtReviewer = new JTextField(15);
			txtNote = new JTextField(15);
			
			txtState.setEditable(false);
			txtFirstName.setEditable(false);
			txtSurname.setEditable(false);
			txtUnityId.setEditable(false);
			txtReviewer.setEditable(false);
			txtNote.setEditable(false);	
			
			GridBagConstraints c = new GridBagConstraints();
						
					
			//Row 1 - state
			JPanel row2 = new JPanel();
			row2.setLayout(new GridLayout(1, 2));
			row2.add(lblState);
			row2.add(txtState);
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row2, c);
			
			//Row 2 - first name
			JPanel row3 = new JPanel();
			row3.setLayout(new GridLayout(1, 2));
			row3.add(lblFirstName);
			row3.add(txtFirstName);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row3, c);
			
			//Row 3 - surname
			JPanel row4 = new JPanel();
			row4.setLayout(new GridLayout(1, 2));
			row4.add(lblSurname);
			row4.add(txtSurname);
			
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row4, c);
			
			//Row 4 - unity id
			JPanel row5 = new JPanel();
			row5.setLayout(new GridLayout(1, 2));
			row5.add(lblUnityId);
			row5.add(txtUnityId);
			
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row5, c);
			
			//Row 5 - reviewer id
			JPanel row6 = new JPanel();
			row6.setLayout(new GridLayout(1, 2));
			row6.add(lblReviwer);
			row6.add(txtReviewer);
			
			c.gridx = 0;
			c.gridy = 5;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row6, c);
			
			//Row 6 - note
			JPanel row7 = new JPanel();
			row7.setLayout(new GridLayout(1, 2));
			row7.add(lblNote);
			row7.add(txtNote);
			
			c.gridx = 0;
			c.gridy = 7;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			add(row7, c);
		}
		
		/**
		 * Adds information about the application to the display.  
		 * @param id the id for the application to display information about.
		 */
		public void setApplicationInfo(int id) {
			//Get the application from the model
			Application application = WolfHire.getInstance().getApplicationById(id);
			if (application == null) {
				//If the application doesn't exist for the given id, show an error message
				JOptionPane.showMessageDialog(WolfHireGUI.this, "Invalid id");
				cardLayout.show(panel, POSITION_LIST_PANEL);
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
			} else {
				//Set the border information with the position name, application id, and current state
				String positionName = WolfHire.getInstance().getActivePositionName();
				
				Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
				TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, positionName + "-  Application #" + application.getId());
				setBorder(border);
				setToolTipText(positionName + " application " + application.getId() + " - " + application.getState());
				
				//Set all of the fields with the information
				txtState.setText(application.getState());
				txtFirstName.setText(application.getFirstName());
				txtSurname.setText(application.getSurname());
				txtUnityId.setText(application.getUnityId());
				txtReviewer.setText(application.getReviewer());
				txtNote.setText(application.getNote());
				
				if (application.getState().equals(Application.REJECTED_NAME)) {
					lblNote.setText("Rejection Reason: ");
				} else if (application.getState().equals(Application.INACTIVE_NAME)) {
					lblNote.setText("Termination Reason: ");
				}
			}
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the JPanel that 
	 * allows for creation of a new application.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class AddApplicationPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;

		/** Label - first name */
		private JLabel lblFirstName;
		/** JTextField - first name */
		private JTextField txtFirstName;
		
		/** Label - surname */
		private JLabel lblSurname;
		/** JTextField - surname */
		private JTextField txtSurname;
		
		/** Label - unity id */
		private JLabel lblUnityId;
		/** JTextField - unity id */
		private JTextField txtUnityId;
		
		/** Button to add a application */
		private JButton btnAdd;
		/** Button for canceling add action */
		private JButton btnCancel;
		
		/**
		 * Creates the JPanel for adding new applications to the 
		 * position.
		 */
		public AddApplicationPanel() {
			super(new BorderLayout());  
			
			//Construct widgets
			lblFirstName = new JLabel("First Name: ");
			lblSurname = new JLabel("Surname: ");
			lblUnityId = new JLabel("Unity Id: ");
			
			txtFirstName = new JTextField(15);
			txtSurname = new JTextField(15);
			txtUnityId = new JTextField(15);
			
			btnAdd = new JButton("Apply to Position");
			btnCancel = new JButton("Cancel");
			
			//Adds action listeners
			btnAdd.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlAdd = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Add Application");
			pnlAdd.setBorder(border);
			pnlAdd.setToolTipText("Add Application");
			
			pnlAdd.setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
						
			//Row 1 - Title
			JPanel row1 = new JPanel();
			row1.setLayout(new GridLayout(1, 2));
			row1.add(lblFirstName);
			row1.add(txtFirstName);
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 2;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row1, c);
			
			//Row 3 - User
			JPanel row2 = new JPanel();
			row2.setLayout(new GridLayout(1, 2));
			row2.add(lblSurname);
			row2.add(txtSurname);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 2;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row2, c);
			
			//Row 3 - Action
			JPanel row3 = new JPanel();
			row3.setLayout(new GridLayout(1, 2));
			row3.add(lblUnityId);
			row3.add(txtUnityId);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 2;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row3, c);
			
			//Row 4 - Buttons
			JPanel row5 = new JPanel();
			row5.setLayout(new GridLayout(1, 2));
			row5.add(btnAdd);
			row5.add(btnCancel);
			
			c.gridx = 0;
			c.gridy = 3;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row5, c);
			
			add(pnlAdd, BorderLayout.NORTH);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true; //Assume done unless error
			if (e.getSource() == btnAdd) {
				String firstName = txtFirstName.getText();
				String surname = txtSurname.getText();
				String unityId = txtUnityId.getText();
				
				//Get instance of model and add application
				try {
					WolfHire.getInstance().addApplicationToPosition(firstName, surname, unityId);
				} catch (IllegalArgumentException exp) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Application cannot be created.");
				}
			} 
			if (reset) {
				//All buttons lead to back application list
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
				//Reset fields
				txtFirstName.setText("");
				txtSurname.setText("");
				txtUnityId.setText("");
			}
		}
	}
	
	
	
	/**
	 * Inner class that creates the JPanel for creating a new position.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private class PositionPanel extends JPanel implements ActionListener {
		/** Serial Version UID */
		private static final long serialVersionUID = 1L;
		
		/** Label - position name */
		private JLabel lblPositionName;
		/** Text Field - position name */
		private JTextField txtPositionName;
		
		/** Label - hours per week */
		private JLabel lblHoursPerWeek;
		/** Text Field - hours per week */
		private JTextField txtHoursPerWeek;
		
		/** Label - pay rate */
		private JLabel lblPayRate;
		/** Text Field - pay rate */
		private JTextField txtPayRate;
		
		/** Button - add position */
		private JButton btnAdd;
		/** Button - cancel */
		private JButton btnCancel;
		
		/**
		 * Constructs the new position panel
		 */
		public PositionPanel() {
			super(new BorderLayout()); 
			
			lblPositionName = new JLabel("Position Name: ");
			lblHoursPerWeek = new JLabel("Hours per Week: ");
			lblPayRate = new JLabel("Pay Rate: ");
			
			txtPositionName = new JTextField(25);
			txtHoursPerWeek = new JTextField(5);
			txtPayRate = new JTextField(5);
			
			btnAdd = new JButton("Add Position");
			btnCancel = new JButton("Cancel");
			
			btnAdd.addActionListener(this);
			btnCancel.addActionListener(this);
			
			JPanel pnlAdd = new JPanel();
			Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Add Position");
			pnlAdd.setBorder(border);
			pnlAdd.setToolTipText("Add Position");
			
			pnlAdd.setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			
			//Row 1 - position name
			JPanel row1 = new JPanel();
			row1.setLayout(new GridLayout(1, 2));
			row1.add(lblPositionName);
			row1.add(txtPositionName);
			
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 0;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row1, c);
			
			//Row 2 - hours per week
			JPanel row2 = new JPanel();
			row2.setLayout(new GridLayout(1, 2));
			row2.add(lblHoursPerWeek);
			row2.add(txtHoursPerWeek);
			
			c.gridx = 0;
			c.gridy = 1;
			c.weightx = 1;
			c.weighty = 0;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row2, c);
			
			//Row 3 - pay rate 
			JPanel row3 = new JPanel();
			row3.setLayout(new GridLayout(1, 2));
			row3.add(lblPayRate);
			row3.add(txtPayRate);
			
			c.gridx = 0;
			c.gridy = 2;
			c.weightx = 1;
			c.weighty = 0;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row3, c);
			
			//Row 4 - buttons
			JPanel row4 = new JPanel();
			row4.setLayout(new GridLayout(1, 2));
			row4.add(btnAdd);
			row4.add(btnCancel);
			
			c.gridx = 0;
			c.gridy = 4;
			c.weightx = 1;
			c.weighty = 20;
			c.anchor = GridBagConstraints.LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			pnlAdd.add(row4, c);
			
			add(pnlAdd, BorderLayout.NORTH);
		}

		/**
		 * Performs an action based on the given ActionEvent.
		 * @param e user event that triggers an action.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean reset = true; //Assume done unless error
			if (e.getSource() == btnAdd) {
				try {
					String positionName = txtPositionName.getText();
					int hoursPerWeek = Integer.parseInt(txtHoursPerWeek.getText());
					int payRate = Integer.parseInt(txtPayRate.getText());
					
					//Get instance of model and add application
					WolfHire.getInstance().addNewPosition(positionName, hoursPerWeek, payRate);
				} catch (IllegalArgumentException exp) {
					reset = false;
					JOptionPane.showMessageDialog(WolfHireGUI.this, "Position cannot be created.");
				}
			} 
			if (reset) {
				//All buttons lead to back application list
				cardLayout.show(panel, POSITION_LIST_PANEL);
				pnlPositionList.updatePosition();
				WolfHireGUI.this.repaint();
				WolfHireGUI.this.validate();
				//Reset fields
				txtPositionName.setText("");
				txtHoursPerWeek.setText("");
				txtPayRate.setText("");
			}
		}
		
		
	}
}