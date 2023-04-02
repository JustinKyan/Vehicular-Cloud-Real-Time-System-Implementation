package main;

public class Car {
    private int id;
    private String CarInfo;
    private double residencyDuration;
    
    private CarOwner carOwner;

    public Car(int id, String VehicleInfo, double duration){
        this.id = id;
        this.CarInfo = VehicleInfo;
        this.residencyDuration = duration;
    }

    public String toString(){
        return "User: Car Owner\nID: " + id + "\n" + "Model: " + CarInfo + "\n" + "Duration: " + residencyDuration + "\n";
    }
    
    public String getCarInfo() { return this.CarInfo; }
    public CarOwner getOwner() { return this.carOwner; }
    
}