package main;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;

    public static void main(String[] args) {
        String messageIn = "";
        String messageOut = "";
        String response = "";
        Scanner input;
        boolean isRunning = false;

        try {
            isRunning = true;
            System.out.println("This is the server");
            System.out.println("Waiting for request\n");
            serverSocket = new ServerSocket(3000);
            socket = serverSocket.accept();
            System.out.println("User Connected");

            while (isRunning) {
                System.out.println("Awaiting message\n\n");

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                
                messageIn = inputStream.readUTF();
                System.out.println("Message received\n");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}