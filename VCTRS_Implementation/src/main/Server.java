package main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

    public static void main(String[] args) throws UnknownHostException, IOException {
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
            System.out.println("This is the server");
            System.out.println("Waiting for request\n");
            
            socket = serverSocket.accept();
            System.out.println("User Connected");
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            socket2 = serverSocket2.accept();
            
            while (isRunning) {
                System.out.println("Awaiting message\n\n");
                
            	serverGUI.messageIn = inputStream.readUTF();
            	serverGUI.incomingRequest.setText(serverGUI.messageIn);
                //messageIn = inputStream.readUTF();
                System.out.println("Message received\n");
                
                inputStream2 = new DataInputStream(socket2.getInputStream());
        		outputStream2 = new DataOutputStream(socket2.getOutputStream());
        		
        		serverGUI.messageOut = inputStream2.readUTF();
        		
        		if(serverGUI.messageOut.equals("Accepted")) {
                	System.out.println("Submission accepted");
                    outputStream.writeUTF(serverGUI.messageOut);
                }
        		else if(serverGUI.messageOut.equals("Rejected")) {
                	System.out.println("Submission rejected");
                	outputStream.writeUTF(serverGUI.messageOut);
                }
        		/*
                System.out.println(messageIn.toString());
                System.out.println("Accept or reject?");
                input = new Scanner(System.in);
                response = input.nextLine();
                while(!response.equals("accept") || !response.equals("reject"))
                {
                	if (response.toLowerCase().equals("accept")) {
                        System.out.println("Submission accepted");
                        messageOut = "accept";
                        break;
                    }
                    else if(response.toLowerCase().equals("reject"))
                    {
                        System.out.println("Submission rejected");
                        messageOut = "reject";
                        break;
                    }
                    else {
                        System.out.println("That is not a valid response. Please try again.");
                        response = input.nextLine();
                    }
                }
                outputStream.writeUTF(messageOut);
                */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}