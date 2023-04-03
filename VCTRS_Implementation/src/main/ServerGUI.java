package main;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGUI extends JFrame implements ActionListener {
	static Socket socket2;
	static DataInputStream inputStream2;
    static DataOutputStream outputStream2;
    public JTextArea incomingRequest;
    public JButton acceptButton,rejectButton, exitButton;
    public JPanel responsePanel, buttonPanel, testPanel;
    String messageIn = "";
    String messageOut = "";
    
    public ServerGUI() throws IOException
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Carboard: Server");
        this.setSize(600,800);
        
        responsePanel = new JPanel();
        responsePanel.setLayout(new BorderLayout());
        responsePanel.setBackground(new Color(122, 122, 120));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(122, 122, 120));
        
        incomingRequest = new JTextArea(20,40);
        incomingRequest.setBackground(new Color(237, 217, 164));
        incomingRequest.setFont(new Font("Calibri", Font.BOLD, 14));
        incomingRequest.setEditable(false);
        incomingRequest.setMargin( new Insets(10,10,10,10) );
        incomingRequest.setLineWrap(true);
        incomingRequest.setWrapStyleWord(true);
        
        acceptButton = new JButton("Accept");
        acceptButton.setFont(new Font("Verdana", Font.BOLD, 12));
        acceptButton.addActionListener(this);
        
        rejectButton= new JButton("Reject");
        rejectButton.setFont(new Font("Verdana", Font.BOLD, 12));
        rejectButton.addActionListener(this);
        
        exitButton= new JButton("Exit Program");
        exitButton.setFont(new Font("Verdana", Font.BOLD, 12));
        exitButton.addActionListener(this);
        
        responsePanel.add(incomingRequest);
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(exitButton);
        
        this.add(responsePanel);
        this.add(buttonPanel);
        
        this.pack();
        this.setVisible(true);
    	
        socket2 = new Socket("localhost", 3001);
		inputStream2 = new DataInputStream(socket2.getInputStream());
		outputStream2 = new DataOutputStream(socket2.getOutputStream());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == acceptButton) {
			try 
			{
				messageOut = "Accept";
				outputStream2.writeUTF(messageOut);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		}
        else if(e.getSource() == rejectButton) 
        {    
            try 
            {
            	messageOut = "Reject";
				outputStream2.writeUTF(messageOut);
			} 
            catch (IOException e1) 
            {
				e1.printStackTrace();
			}
        }
        else if(e.getSource() == exitButton) {
        	System.exit(0);
        }
    }

}