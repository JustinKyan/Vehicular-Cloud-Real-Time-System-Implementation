package main;
import java.util.*;

public class CarOwner {
	private int OwnerID;
	private String VehicleInfo, time;
	
	public CarOwner(int OwnerID, String VehicleInfo, String time){
		this.OwnerID = OwnerID;
		this.VehicleInfo = VehicleInfo;
		this.time = time;
	}
	public int getOwnerID(){ return OwnerID; }
	public String getVehicleInfo(){	return VehicleInfo;	}
	public String getTime(){ return time;	}
	
	public String toString(){
		return "OwnerID:" + OwnerID + "\nVehicle Info:" + VehicleInfo + "\nResidency Time:" + time;

	}
}