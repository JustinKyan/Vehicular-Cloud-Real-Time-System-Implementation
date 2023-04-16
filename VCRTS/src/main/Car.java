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
        return "\t - User: Car Owner\n\t - ID: " + id + "\n" + "\t - Model: " + CarInfo + "\n" + "\t - Residency Duration: " + residencyDuration + "ms\n";
    }
    
    public String getCarInfo() { return this.CarInfo; }
    public CarOwner getOwner() { return this.carOwner; }
    
}