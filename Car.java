import java.util.*;
public class Car {
	private String model;
	private String make;
	private Date yearMade;
	private String vehicleIndexNumber; //might use this as arraylist tracker; char limit = 17;  
	private Date registrationTime;
	private CarOwner carOwner;
	private int carID; //for now this is what I am thinking about storing to tell the difference between cars

	public Car(String model, String make,Date yearMade, String vehicleIndexNumber, Date registrationTime, CarOwner carOwner, int carID) {
		setModel(model);
		setMake(make);
		setYearMade(yearMade);
		setVehicleIndexNumber(vehicleIndexNumber);
		setRegistrationTime(registrationTime);
		setCarOwner(carOwner);
		setCarID(carID);
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public Date getYearMade() {
		return yearMade;
	}
	public void setYearMade(Date yearMade) {
		this.yearMade = yearMade;
	}
	
	public String getVehicleIndexNumber() {
		return vehicleIndexNumber;
	}
	public void setVehicleIndexNumber(String vehicleIndexNumber) {
		this.vehicleIndexNumber = vehicleIndexNumber;
	}
	
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	
	public CarOwner getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}

	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	
	
}
