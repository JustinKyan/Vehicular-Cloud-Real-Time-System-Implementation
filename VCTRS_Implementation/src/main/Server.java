package main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Server {
    static ServerSocket serverSocket;
    static ServerSocket serverSocket2;
    static Socket socket;
    static Socket socket2;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    static DataInputStream inputStream2;
    static DataOutputStream outputStream2;

    public static void main(String[] args) throws UnknownHostException, IOException, SocketTimeoutException{
        String messageIn = "";
        String messageOut = "";
        String response = "";
        //Scanner input;
        boolean isRunning = false;
        serverSocket = new ServerSocket(3000);
    	serverSocket2 = new ServerSocket(3001);

        try {
            isRunning = true;
            ServerGUI serverGUI = new ServerGUI();
            
            serverGUI.acceptButton.setVisible(false);
        	serverGUI.rejectButton.setVisible(false);
        	
            serverGUI.incomingRequest.setText("This is the Server\n");
            serverGUI.incomingRequest.append("Waiting for User\n");
            
            
            socket = serverSocket.accept();
            serverGUI.incomingRequest.append("User Connected");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            socket2 = serverSocket2.accept();
            
            while (isRunning) {
            	serverGUI.incomingRequest.append("\nAwaiting Message\n");
                 	
            	serverGUI.messageIn = inputStream.readUTF();
            	
            	serverGUI.incomingRequest.setText("Message Received\n");
            	
            	serverGUI.acceptButton.setVisible(true);
            	serverGUI.rejectButton.setVisible(true);
            	
            	serverGUI.incomingRequest.append(serverGUI.messageIn);
            	
            	serverGUI.incomingRequest.append("\nDo You Accept or Reject this Submission?\n");
            	
                
                inputStream2 = new DataInputStream(socket2.getInputStream());
        		outputStream2 = new DataOutputStream(socket2.getOutputStream());
        		
        		serverGUI.messageOut = inputStream2.readUTF();
        		
        		serverGUI.acceptButton.setVisible(false);
            	serverGUI.rejectButton.setVisible(false);
        		
        		if(serverGUI.messageOut.equals("Accept")) {
        			serverGUI.incomingRequest.append("\nSubmission Accepted\n");
                    outputStream.writeUTF(serverGUI.messageOut);
                }
        		else if(serverGUI.messageOut.equals("Reject")) {
        			serverGUI.incomingRequest.append("\nSubmission Rejected\n");
                	outputStream.writeUTF(serverGUI.messageOut);
                }
        		
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }{
    	
    }
}