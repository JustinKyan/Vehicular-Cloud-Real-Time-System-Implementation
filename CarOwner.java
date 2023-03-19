
/* Project:  VCRTS: Vehicular Cloud Real Time System
* Class: CarOwner.java 
* Author:   Justin Kyan, Goden Liu, Andy Mathura, Tahmidul Haque, David Chang, Kevin Boukpessi 
* Date: 2/14/2023
* This program allows for the creation of a CarOwner Object that stores it's 
* information including Id, Vehicle Info, and Residency Time  
*/
import java.util.*;

public class CarOwner extends User{
	private int ownerID;
	private String vehicleInfo, time;

	
	
	public CarOwner(int ownerID, String name, String vehicleInfo, String time) {
		super(ownerID, name, false);
		
		this.ownerID = ownerID;
		this.vehicleInfo = vehicleInfo;
		this.time = time;
	}

	public int getownerID() {
		return ownerID;
	}

	public String getvehicleInfo() {
		return vehicleInfo;
	}

	public String getTime() {
		return time;
	}

	public String toString() {
		return "ownerID: " + ownerID + "\nVehicle Info: " + vehicleInfo + "\nResidency Time: " + time + "\n";
	}
}