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
        return "\t - User: Client\n\t - ID: " + id + "\n\t - Deadline: " + deadline + "\n" + "\t - Job Duration: " + jobDuration + "ms \n";
    }

}