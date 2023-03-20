package main;

import java.util.ArrayList;

public class Checkpoint{
	ArrayList<Job> status = new ArrayList<Job>();
	
	public Job getImage(int jobID) {
		for(int i = 0; i < status.size(); i++) {
			if(status.get(i).id == jobID)
				return status.get(i);
		}
		return null;
	}
	
}