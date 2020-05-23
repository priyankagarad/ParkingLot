package com.bl.parkinglot;
public class ParkingLotSystem
{

    private int actualCapacity;
    private int currentCapacity;
    Object vehicle;
    private ParkingLotOwer owner;

    public ParkingLotSystem(int capacity){
        this.currentCapacity=0;
        this.actualCapacity=capacity;
    }

    public void  parked(Object vehicle) throws Exception {
        if (this.currentCapacity == this.actualCapacity){
            owner.capacityFull();
        throw new Exception("parking  Lot is full");
    }
        this.currentCapacity++;
        this.vehicle=vehicle;
    }

    public boolean isVehicleParked(Object vehicle){
        if(this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean UnPark(Object vehicle) {
        if (vehicle==null)
            return false;
        if (this.vehicle .equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    public void registerOwner(ParkingLotOwer owner) {
        this.owner=owner;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }
}
