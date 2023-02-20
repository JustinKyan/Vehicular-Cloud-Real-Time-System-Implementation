
/* Project:  VCRTS: Vehicular Cloud Real Time System
* Class: MainGUI.java 
* Author:   Justin Kyan, Goden Liu, Andy Mathura, Tahmidul Haque, David Chang, Kevin Boukpessi 
* Date: 2/14/2023
* This program is the main GUI that creates a window that allows the user to choose either Car owner or Client through a drop box menu.
* It allows you to enter in a user's identification value, submit a owner's vehicule information and residency time if you selected owner 
* in the drop down menu and job duration and deadline if you selected Client.
* Information entered is stored within their respective file name logs that can be opened and view when clicking a button that calls and opens the txt file.
* Information of multiple users is abled to be entered and stored within such files.   
*/
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList.*;

public class MainGUI extends JFrame implements ActionListener {

	/*
	 * Time formatter. Notice how adding "a" at the end of the .ofPattern method
	 * accounts for AM/PM.
	 */
	public LocalDateTime time = LocalDateTime.now();
	public DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");

	// Instance variables. Declared in the order that the GUI is supposed to present
	// itself.
	private JPanel dropDownMenu, buttonInput, textInput, textOutput;
	private JComboBox dropDownChoices;
	private JLabel label1, label2, label3, intro;
	private JTextField textBox1, textBox2, textBox3;
	private JButton button1, button2;
	private JTextArea outputArea = new JTextArea();

	// Try to optimize with arraylists, WIP.
	private ArrayList<JPanel> panels = new ArrayList<JPanel>();
	private ArrayList<JTextField> textBoxes = new ArrayList<JTextField>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JButton> buttons = new ArrayList<JButton>();

	// File instantiation
	private File ownerLog = new File("ownerLog.txt");
	private File clientLog = new File("clientLog.txt");
	private File adminLog = new File("adminLog.txt");

	public MainGUI(String title) {
		// GUI window layout instantiation
		this.setTitle(title);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * All necessary panels in the GUI. Some dimensions may or may not matter; all
		 * preference. REMEMBER: MASSIVE overhaul due if we decide to use a different
		 * data structure for these inputs/outputs and the dropdown menu choices.
		 */

		this.dropDownMenu = new JPanel();

		this.textInput = new JPanel();
		this.textInput.setLayout(new GridLayout(7, 2));

		this.buttonInput = new JPanel();
		this.buttonInput.setLayout(new GridLayout(1, 5));

		this.textOutput = new JPanel();
		this.textOutput.setLayout(new BorderLayout(7, 2));

		// Filling our dropdown menu.
		String[] list = { "", "Owner", "Client" };
		dropDownChoices = new JComboBox(list);
		dropDownChoices.addActionListener(this);

		// Prompts for user-type selection on launch.
		intro = new JLabel("Select a user from the dropdown menu.", SwingConstants.CENTER);
		label1 = new JLabel("", SwingConstants.LEFT);
		label2 = new JLabel("", SwingConstants.LEFT);
		label3 = new JLabel("", SwingConstants.LEFT);

		// Experimenting with ArrayList management.
		labels.add(intro);
		labels.add(label1);
		labels.add(label2);
		labels.add(label3);

		// Instantiating all text boxes.
		textBox1 = new JTextField();
		textBox2 = new JTextField();
		textBox3 = new JTextField();

		textBox1.setPreferredSize(new Dimension(160, 30));
		textBox1.setHorizontalAlignment(JTextField.CENTER);

		textBox2.setPreferredSize(new Dimension(160, 30));
		textBox2.setHorizontalAlignment(JTextField.CENTER);

		textBox3.setPreferredSize(new Dimension(160, 30));
		textBox3.setHorizontalAlignment(JTextField.CENTER);

		// Initial button declaration, at launch since user type is not selected,
		// buttons do nothing. addActionListener to see what choice user chooses.
		button1 = new JButton("-");
		button2 = new JButton("-");

		button1.addActionListener(this);
		button2.addActionListener(this);

		// Text output area (BOTTOM)
		outputArea.setMargin(new Insets(10, 4, 4, 10));

		// Adds all elements to their respective panels
		dropDownMenu.add(dropDownChoices);
		textInput.add(label1);	textInput.add(textBox1);
		textInput.add(label2);	textInput.add(textBox2);
		textInput.add(label3);	textInput.add(textBox3);
		buttonInput.add(button1);	buttonInput.add(button2);
		textOutput.add(outputArea);

		// At this point, implements each element into our GUI in order.
		this.add(intro);
		this.add(dropDownMenu);
		this.add(textInput);
		this.add(buttonInput);
		this.add(textOutput);

		this.pack();
		this.setVisible(true);
	}

	// Action detection method. **NOTE: Refer to Oracle documentation.
	public void actionPerformed(ActionEvent event) {
		// Experimenting with ArrayList for the labels.
		// Note that we can easily just set each individual label's value and it would
		// still work.
		if (event.getSource() == dropDownChoices) {
			if (dropDownChoices.getSelectedItem().equals("")) {
				labels.get(0).setText("Select a user from the dropdown menu.");
				labels.get(1).setText("");
				labels.get(2).setText("");
				labels.get(3).setText("");
				button1.setText("-");
				button2.setText("-");
			} else if (dropDownChoices.getSelectedItem().equals("Owner")) {
				labels.get(0).setText("");
				labels.get(1).setText("   Owner ID#");
				labels.get(2).setText("   Vehicle Info (Make, Model, Year, VIN#)");
				labels.get(3).setText("   Job Deadline ");
				button1.setText("Confirm");
				button2.setText("Owner Log");
			} else if (dropDownChoices.getSelectedItem().equals("Client")) {
				labels.get(0).setText("");
				labels.get(1).setText("   Client ID#");
				labels.get(2).setText("   Job Duration");
				labels.get(3).setText("   Job Deadline");
				button1.setText("Confirm");
				button2.setText("Client Log");
			}
		}

		// OUTPUT AREA
		if (event.getSource() == button1) {
			try {
				if (dropDownChoices.getSelectedItem().equals("Client")) {
					FileWriter clientWrite = new FileWriter(clientLog, true);
					// Just like the CarOwner class, time is kept separate from Client class to deal
					// with time formatting.
					clientWrite.write("Time: " + time.format(format) + "\n");
					Client client = new Client(Integer.parseInt(textBox1.getText()), textBox2.getText(),
							textBox3.getText());
					clientWrite.write(client.toString() + "\n");
					clientWrite.close();
				}
				if (dropDownChoices.getSelectedItem().equals("Owner")) {
					FileWriter ownerWrite = new FileWriter(ownerLog, true);
					// Time is kept separate from the CarOwner class.
					ownerWrite.write("Time: " + time.format(format) + "\n");
					CarOwner owner = new CarOwner(Integer.parseInt(textBox1.getText()), textBox2.getText(),
							textBox3.getText());
					ownerWrite.write(owner.toString() + "\n");
					ownerWrite.close();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (event.getSource() == button2) {
			try {
				if (dropDownChoices.getSelectedItem().equals("Owner")) {
					if (ownerLog.exists()) {
						Desktop.getDesktop().open(ownerLog);
					}
				} else if (dropDownChoices.getSelectedItem().equals("Client")) {
					if (clientLog.exists()) {
						Desktop.getDesktop().open(clientLog);
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		if (dropDownChoices.getSelectedItem().equals("Owner") && event.getSource() == button1) {
			outputArea.setText("Wrote information to log: \n          Current time: " + time.format(format) + "\n"
					+ "          Owner ID: " + textBox1.getText() + "\n"
					+ "          Vehicle Info (Make, Model, Year, VIN#):\n          " + textBox2.getText() + "\n"
					+ "          Approximate Residency Duration: " + textBox3.getText());
		} else if (dropDownChoices.getSelectedItem().equals("Client") && event.getSource() == button1) {
			outputArea.setText("Wrote information to log: \n          Current time: " + time.format(format)
					+ "\n          Client ID: " + textBox1.getText() + "\n" + "          Approximate Job Duration:"
					+ textBox2.getText() + "\n" + "          Job Deadline: " + textBox3.getText());
		}
	}

}