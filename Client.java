package main;
import java.util.*;

public class Client extends User {
	private String approximateTime, jobDeadline;
	private ArrayList<Job> jobsSubmitted;
	
	public Client(int ClientID, String approximateTime, String jobDeadline, ArrayList<Job> jobsSubmitted){
		super(ClientID);
		this.approximateTime = approximateTime;
		this.jobDeadline = jobDeadline;
		this.jobsSubmitted = jobsSubmitted;
	}
	
	public int getClientID(){
		return this.id;
	}
	public String getApproximateTime(){
		return approximateTime;
	}
	public String getJobDeadline(){
		return jobDeadline;
	}
	public void submitJob(Job job) {
		this.jobsSubmitted.add(job);
	}
	
	public void requestJobCancel(Job job, int id) {
		// In VC controller, remove job for the corresponding job ID.
	}
}