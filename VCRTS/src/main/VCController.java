package main;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.sql.*;

public class VCController extends Thread {
    private Queue<Car> cars; 
    private Queue<Job> jobs;
    
    private ArrayList<Checkpoint> checkpoints;
    private ArrayList<Client> registeredClients;
    private ArrayList<Car> registeredCars;
    private ArrayList<CarOwner> registeredOwners;
    private ArrayList<Job> jobsInProgress, submittedJobs, completedJobs, checkpointedJobs;
    
    // Server implementation variables
    static ServerSocket serverSocket;
    static ServerSocket serverSocket2;
    static Socket socket;
    static Socket socket2;
    static DataInputStream inputStream;
    static DataOutputStream outputStream;
    static DataInputStream inputStream2;
    static DataOutputStream outputStream2;

    
    Queue<Job> getJobs(){ return this.jobs; }
    
    public VCController(){
        cars = new LinkedList<Car>(); 
        jobs = new LinkedList<Job>();
        checkpoints = new ArrayList<Checkpoint>();
    }
    
    void writeToDB() {
    }
    
    void assignJob(Job j){ jobs.add(j); }
    
    void markComplete(Job completed) { completedJobs.add(completed); }
    
    void copyImage(Job j) { checkpointedJobs.add(j); }
    
    void addCheckpoint(Checkpoint c){ checkpoints.add(c); }
    
    void getCompletionTime(){
        int total = 0;
        for (Job current : jobs) {
            total += current.jobDuration;
            current.completionTime = total;
        }
    }
   
    
    /* boolean isJobComplete() {
    	// return true if current job @ lookup's completion time is 0. else return false.
    }
    */
    
    void printCurrentJobList() {
    	for(int i = 0; i < this.jobs.size(); i++) {
    		this.jobsInProgress.get(i).toString();
    	}
    }
    
    /* boolean deadlinePassed() {
    	// return true if the current time is past the allocated deadline in "Job.java" [String at the moment, Date later]
    	// else return false.
    }
    */
    
    void addCar(Car v){ cars.add(v); }
    
    void removeCar(ArrayList<Job> job, Car v) {
    	// Removes a vehicle from the over-all arraylist (across all registeredCars) if it exists.
    	// Probably preferable to use something like a HashMap.
    }
    
    void printCarList() {
    	for(int i = 0; i < registeredCars.size(); i++) {
    		this.registeredCars.get(i).toString();
    	}
    }
    
    void restartComp() {
    	// In the event of a memory leak or any other issue, re-start.
    }
    
    public static void main(String[] args) {
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
    }
}