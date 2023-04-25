package main;
import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
import java.time.*;
import java.time.format.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

 
 
public class MainGUI extends JFrame implements ActionListener {
    // Instance variables
    private JPanel dropdownMenu, textInput, buttonInput, textOutput, logoPanel;
    private JTextField textB1, textB2, textB3;
    private JTextArea intro, output;
    private JLabel label1, label2, label3, logo;
    private JComboBox dropdownChoices;
    private JButton button1, button2, button3;
    
    LocalDateTime time = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
    String formattedTime = time.format(format);
    
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    static Connection connection = null;
    
    private File ownerFile = new File("Owner Log.txt");
    private File clientFile = new File("Client-Jobs.txt");
    
    Image image = ImageIO.read(new File("Carboard.png"));
    // create an ImageIcon from the image
    ImageIcon icon = new ImageIcon(image);
    
    private VCController controller = new VCController();
    
    // SQL Implementation variables
    static java.sql.Connection sqlConnection = null;
    static String url = "jdbc:mysql://localhost:3306/vc3?useTimezone=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "asdasd123";
    
    
    public MainGUI(String title) throws UnknownHostException, IOException {
        socket = new Socket("localhost", 3000);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        logoPanel = new JPanel();
        logoPanel.setBackground(new Color(122, 122, 120));
        logo = new JLabel(icon);
        logo.setOpaque(true);
      //  logo.setAlignmentX(CENTER_ALIGNMENT);
       // logo.setAlignmentY(CENTER_ALIGNMENT);
        logo.setBackground(new Color(122, 122, 120));
        logoPanel.add(logo);
        
        intro = new JTextArea();
        intro.setEditable(false);
        
        intro.setFont(new Font("Verdana", Font.BOLD, 12));
        intro.setBackground(new Color(122, 122, 120));
        intro.append("Welcome to Carboard!\nA simple vehicular run-time simulation system.\nTo start, choose a user from the drop-down menu.");
        logoPanel.add(intro);
        this.add(logoPanel);
        
        dropdownMenu = new JPanel();
        dropdownMenu.setBackground(new Color(122, 122, 120));
 
        textInput = new JPanel();
        textInput.setBackground(new Color(122, 122, 120));
        textInput.setLayout(new GridLayout(3, 2));
 
        buttonInput = new JPanel();
        buttonInput.setBackground(new Color(122, 122, 120));
        buttonInput.setLayout(new FlowLayout());
 
        textOutput = new JPanel();
        textOutput.setBackground(new Color(122, 122, 120));
        textOutput.setLayout(new BorderLayout(4, 10));
 
        
        //dropdownChoices box instantiation, goes into the dropdown Panel
        String[] list = {"", "Client", "Owner"};
        dropdownChoices = new JComboBox(list);
        dropdownChoices.setBackground(Color.WHITE);
        dropdownChoices.addActionListener(this);
 
        //All the text boxes and their labels, goes into the text input panel
        textB1 = new JTextField();
        textB2 = new JTextField();
        textB3 = new JTextField();
        textB1.setPreferredSize(new Dimension(120, 1));
        textB2.setPreferredSize(new Dimension(120, 1));
        textB3.setPreferredSize(new Dimension(120, 1));
        
        label1 = new JLabel("", SwingConstants.CENTER);
        label2 = new JLabel("", SwingConstants.CENTER);
        label3 = new JLabel("", SwingConstants.CENTER);
        
        label1.setFont(new Font("Calibri", Font.BOLD, 16));
        label1.setForeground(Color.BLACK);
        
        label2.setFont(new Font("Calibri", Font.BOLD, 14));
        label2.setForeground(Color.BLACK);
        
        label3.setFont(new Font("Calibri", Font.BOLD, 12));
        label3.setForeground(Color.BLACK);
 
        // Text area at the lowest panel of the JFrame; not editable, plan to add feature that outputs what will be input by the user
        output = new JTextArea();
        output.setFont(new Font("Calibri", Font.BOLD, 14));
        output.setBackground(new Color(237, 217, 164));
        output.setEditable(false);
        output.setMargin(new Insets(10, 4, 25, 4));
 
        button1 = new JButton(""); button2 = new JButton(""); button3 = new JButton("");
        button1.setFont(new Font("Verdana", Font.BOLD, 12));
        button1.setForeground(Color.BLACK);
        button2.setFont(new Font("Verdana", Font.BOLD, 12));
        button2.setForeground(Color.BLACK);
        button3.setFont(new Font("Verdana", Font.BOLD, 12));
        button3.setForeground(Color.BLACK);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
 
        dropdownMenu.add(dropdownChoices);
        textInput.add(label1);  textInput.add(textB1);
        textInput.add(label2);  textInput.add(textB2);
        textInput.add(label3);  textInput.add(textB3);
        buttonInput.add(button1); buttonInput.add(button2); buttonInput.add(button3);
        
        textOutput.add(output);
        
        //Accounts for if the program was just launched, no buttons to show.
        if(list[0].equals("")) {
            textB1.setVisible(false);
            textB2.setVisible(false);
            textB3.setVisible(false);
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(true);
            button3.setText("Exit Program");
        }
        
        this.add(dropdownMenu);
        this.add(textInput);
        this.add(buttonInput);
        this.add(textOutput);
 
        this.pack();
        this.setSize(800, 375);
        this.setVisible(true);
        
    }
 
    
    public void actionPerformed(ActionEvent event){
        //dropbox labelling for textboxes 
        if (event.getSource() == dropdownChoices) {
            if(dropdownChoices.getSelectedItem().equals("")) {
            	removeTexts();
            	logo.setIcon(new ImageIcon("funny logo.png"));
            	logoPanel.setVisible(true);
                textB1.setVisible(false);
                textB2.setVisible(false);
                textB3.setVisible(false);
    			label1.setText("");
    			label2.setText("");
    			label3.setText("");
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(true);
                button3.setText("Exit Program");
            }
            if (dropdownChoices.getSelectedItem().equals("Client")) {
            	addTexts();
                textB1.setVisible(true); textB2.setVisible(true);   textB3.setVisible(true);              
                logoPanel.setVisible(false);
                label1.setText("Job ID");
                label2.setText("Job Deadline");
                label3.setText("Approximate Job Duration (enter in ms)");
                button1.setVisible(true); button2.setVisible(true); button3.setVisible(true);
                button1.setText("Submit");
                button2.setText("Client Job Log");
                button3.setText("Completion Time");
            } else if (dropdownChoices.getSelectedItem().equals("Owner")) {
            	addTexts();
                textB1.setVisible(true); textB2.setVisible(true);   textB3.setVisible(true);
                
                logoPanel.setVisible(false);
                label1.setText("Car ID#");
                label2.setText("Car Info (Make, Model, Year)");
                label3.setText("Residency Time (in ms)");
                button1.setVisible(true);
                button2.setVisible(true);
                button3.setVisible(false);
                button1.setText("Submit");
                button2.setText("Car Owner Log");
            } 
        }
        
        if (event.getSource() == button3 && dropdownChoices.getSelectedItem().equals("")) {
        		System.exit(0);
        }
        
        if (event.getSource() == button1) {
            try {
                informationsubmitted(event);
                fileProcess(event);
            } 
            catch (IOException e1) 
            {
                e1.printStackTrace();
            }
        }
        
        if(event.getSource() == button2) {
            try {
                if (dropdownChoices.getSelectedItem().equals("Owner")){
                    if(ownerFile.exists()){
                        Desktop.getDesktop().open(ownerFile);   
                    }
                } else if (dropdownChoices.getSelectedItem().equals("Client")){
                    if(clientFile.exists()){
                        Desktop.getDesktop().open(clientFile);
                    }
                }
                
            } 
            catch (IOException e1) {
                e1.printStackTrace();
            } 
        }
 
        // CALCULATION OF COMPLETION TIME
        if (dropdownChoices.getSelectedItem().equals("Client") && event.getSource() == button3) {
            String notice = "Going down the list, we will see the total completion time across all jobs.\n";
            output.setText(notice);
            controller.getCompletionTime();
            for (Job job : controller.getJobs()) {
                String toPrint = "\tCompletion time as of job ID# " + job.id + "\n";
                toPrint += "\tJob ID: " + job.id + "\n"
                        + "\tJob Completion Time: " + job.jobDuration + "ms \n"
                        + "\tCumulative Job Completion Time: " + job.completionTime + "ms \n\n";
                output.append(toPrint);
            }
        }
    // ---------------------------------------------------------------------------------------------------------
 
    }
    
    public void informationsubmitted(ActionEvent event) {
        if (dropdownChoices.getSelectedItem().equals("Owner") && event.getSource() == button1) 
        {
        	refreshTime();
            
            output.setText("Information submitted on: " + formattedTime + "\n" +
        "\tOwner ID: " + textB1.getText() + "\n" + 
        "\tVehicle Info (Make, Model, Year): " + textB2.getText() + "\n" + 
        "\tResidency Duration: " + textB3.getText() + "ms");
        }
        else if (dropdownChoices.getSelectedItem().equals("Client") && event.getSource() == button1) {
        	refreshTime();
        	
            output.setText("Information submitted on: " + formattedTime + "\n" + 
        "\tJob ID: " + textB1.getText() + "\n" + 
        "\tJob Deadline: " + textB2.getText() + "\n" + 
        "\tApproximate Job Duration: " + textB3.getText() + " ms");
        }
    }

    
    public void fileProcess(ActionEvent event) throws UnknownHostException, IOException{
        //String userType = (String) dropdownChoices.getSelectedItem();
    	int id = Integer.parseInt(textB1.getText());
        String info = textB2.getText();
        double duration = Double.parseDouble(textB3.getText());
        String messageIn = "";
        emptyboxes();
        try {
            if(dropdownChoices.getSelectedItem().equals("Owner")) {
                
                Car newVehicle = new Car(id, info, duration);
                outputStream.writeUTF(newVehicle.toString());
                messageIn = inputStream.readUTF();
                if(messageIn.equals("Accept")) {
                	controller.addCar(newVehicle);
                    output.append("\n\t[Vehicle ACCEPTED; stored into the database.]");
                    writeToFile(newVehicle.toString(), ownerFile);
                    addtoDB(id, info, duration);
                }
                if(messageIn.equals("Reject")) {
                    output.append("\n\t[Vehicle REJECTED; NOT stored into the database.]");
                }
                
            }
            else if(dropdownChoices.getSelectedItem().equals("Client")) {
                Job newJob = new Job(id, info, duration);
                outputStream.writeUTF(newJob.toString());
                messageIn = inputStream.readUTF();
                if(messageIn.equals("Accept")) {   
                	controller.assignJob(newJob);
                    output.append("\n\t[JOB ACCEPTED; job added to database.]");
                    writeToFile(newJob.toString(), clientFile);
                    addtoDB(id, info, duration);
                }
                if(messageIn.equals("Reject")) {
                    output.append("\n\t[JOB REJECTED; job NOT stored into the database.]");
                }
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    void writeToFile(String toFile, File file) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
        fileWriter.write("Timestamp: " + formattedTime);
        fileWriter.newLine();
        fileWriter.write(toFile);
        fileWriter.newLine();
        fileWriter.close();
    }
    
    
    void addtoDB(int ID, String info, double time) {
    	if (dropdownChoices.getSelectedItem().equals("Owner")){
        try {
        	refreshTime();
            
            sqlConnection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO car_owner " + "VALUES" + " (" + 
            		ID + ", '"
            		+ info + "', " 
            		+ time + ", '" +
            		formattedTime + "');"; 
            // INSERT INTO car_owner VALUES (id, 'info', duration, 'formattedTime');
            Statement statement = sqlConnection.createStatement();
            int row = statement.executeUpdate(sql);
        } catch (SQLException e){
            	e.getMessage();
        	}
    	} else if (dropdownChoices.getSelectedItem().equals("Client")){
    		try {
        	refreshTime();
            
            sqlConnection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO client_jobs " + "VALUES" + " (" + 
            		ID + ", '"
            		+ info + "', " 
            		+ time + ", '" +
            		formattedTime + "');"; 
            // INSERT INTO client_jobs VALUES (id, 'info', duration, 'formattedTime');
            Statement statement = sqlConnection.createStatement();
            int row = statement.executeUpdate(sql);
        } catch (SQLException e){
            	e.getMessage();
        	}
    }
    
}
    
    void refreshTime() {
        time = LocalDateTime.now();
        format = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a");
        formattedTime = time.format(format);
    }
    
    void emptyboxes() {
        textB1.setText("");
        textB2.setText("");
        textB3.setText("");
    }
    
    void removeTexts() {
    	label1.setFont(new Font("Calibri", Font.BOLD, 1));
        label2.setFont(new Font("Calibri", Font.BOLD, 1));
        label3.setFont(new Font("Calibri", Font.BOLD, 1));
        
    	textB1.setPreferredSize(new Dimension(120, 1));
        textB2.setPreferredSize(new Dimension(120, 1));
        textB3.setPreferredSize(new Dimension(120, 1));
    }
    
    void addTexts() {
    	label1.setFont(new Font("Calibri", Font.BOLD, 16));
        label2.setFont(new Font("Calibri", Font.BOLD, 14));
        label3.setFont(new Font("Calibri", Font.BOLD, 12));
        
    	textB1.setPreferredSize(new Dimension(120, 35));
        textB2.setPreferredSize(new Dimension(120, 35));
        textB3.setPreferredSize(new Dimension(120, 35));
    }
}