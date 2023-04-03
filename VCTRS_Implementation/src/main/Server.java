package main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Server extends Thread{
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
        boolean isRunning = false;
        try {
			serverSocket = new ServerSocket(3000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			serverSocket2 = new ServerSocket(3001);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
        try {
            isRunning = true;
            ServerGUI serverGUI = new ServerGUI();
            
            serverGUI.acceptButton.setVisible(false);
        	serverGUI.rejectButton.setVisible(false);
        	
        	serverGUI.incomingRequest.setText("[WELCOME]: This is Carboard's server for handling job requests.\n");
            serverGUI.incomingRequest.append("Waiting for a request...\n");
            
            
            socket = serverSocket.accept();
            serverGUI.incomingRequest.append("[NOTICE] User connection detected.");
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
        			serverGUI.incomingRequest.append("\n[NOTICE] Submission accepted.\n");
                    outputStream.writeUTF(serverGUI.messageOut);
                }
        		else if(serverGUI.messageOut.equals("Reject")) {
        			serverGUI.incomingRequest.append("\n[NOTICE] Submission rejected.\n");
                	outputStream.writeUTF(serverGUI.messageOut);
                }
        		
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }{
    	
    }
}