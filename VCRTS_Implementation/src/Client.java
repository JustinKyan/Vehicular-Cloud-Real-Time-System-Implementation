package main;
import java.util.*;

public class Client {
	private int ClientID, approximateTime;
	private String jobDeadline;
	
	public Client(int ClientID, int approximateTime, String jobDeadline){
		this.ClientID = ClientID;
		this.approximateTime = approximateTime;
		this.jobDeadline = jobDeadline;
	}
	public int getClientID(){
		return ClientID;
	}
	public int getApproximateTime(){
		return approximateTime;
	}
	public String getJobDeadline(){
		return jobDeadline;
	}
	public String toString(){
		return "Client ID: " + ClientID + "\nDuration: " + approximateTime + "\nDeadline:" + jobDeadline;
	}
	
}