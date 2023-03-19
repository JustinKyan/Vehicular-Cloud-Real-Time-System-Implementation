
/* Project:  VCRTS: Vehicular Cloud Real Time System
* Class: Client.java 
* Author:   Justin Kyan, Goden Liu, Andy Mathura, Tahmidul Haque, David Chang, Kevin Boukpessi 
* Date: 2/14/2023
* This program allows for the creation of a Client Object that stores it's 
* information including Id, Job Duration, and Job Deadline
*/
import java.util.*;

public class Client {
	private int ClientID;
	private String approximateTime;
	private String jobDeadline;

	public Client(int ClientID, String approximateTime, String jobDeadline) {
		this.ClientID = ClientID;
		this.approximateTime = approximateTime;
		this.jobDeadline = jobDeadline;
	}

	public int getClientID() {
		return ClientID;
	}

	public String getApproximateTime() {
		return approximateTime;
	}

	public String getJobDeadline() {
		return jobDeadline;
	}

	public String toString() {
		return "Client ID: " + ClientID + "\nDuration: " + approximateTime + "\nDeadline: " + jobDeadline + "\n";
	}

}