package main;
import java.util.*;

public class CarOwner extends User {
	
	private ArrayList<Car> ownedCars = new ArrayList<Car>();
	
	public CarOwner(int ownerID, ArrayList<Car> owned){
		super(ownerID);
		this.ownedCars = owned;
		this.privilege = false;
	}
	
	public int getOwnerID(){
		return this.id;
	}
	
	public ArrayList<Car> getCarsOwned() {
		return this.ownedCars;
	}
	
	public void regCar(Car c) {
		this.ownedCars.add(c);
	}
	
	public void unregCar(Car c) {
		for(int i = 0; i < ownedCars.size(); i++) {
			if(this.ownedCars.get(i) == c)
				this.ownedCars.remove(i);
		}
	}
}