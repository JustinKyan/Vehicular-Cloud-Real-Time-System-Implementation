package main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class VCController {
    private Queue<Car> cars; 
    private Queue<Job> jobs;
    
    private ArrayList<Checkpoint> checkpoints;
    private ArrayList<Client> registeredClients;
    private ArrayList<Car> registeredCars;
    private ArrayList<CarOwner> registeredOwners;
    private ArrayList<Job> jobsInProgress, submittedJobs, completedJobs, checkpointedJobs;
    

    Queue<Job> getJobs(){ return this.jobs; }
    
    public VCController(){
        cars = new LinkedList<Car>(); 
        jobs = new LinkedList<Job>();
        checkpoints = new ArrayList<Checkpoint>();
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
    
    void addCar(Car v){ cars.add(v); } //recruit car
    
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
    
    
}