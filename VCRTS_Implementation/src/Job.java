package main;

public class Job {
    int id;
    String deadline;
    double jobDuration, completionTime;
    
    public Job(int id, String deadline, double duration){
        this.id = id;
        this.deadline = deadline;
        this.jobDuration = duration;
    }
    
    public Job getJobStatus() {
    	return (new Job(this.id, this.deadline, this.jobDuration));
    }
    
    public String toString(){
        return "ID: " + id + "\nDeadline: " + deadline + "\n" + "Duration: " + jobDuration + "ms \n";
    }

}