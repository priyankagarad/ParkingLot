package com.bl.parkinglot;
import java.util.ArrayList;
import java.util.List;
public class ParkingLotSystem
{
    Object vehicle;
    private int actualCapacity;
    private int currentCapacity;
    private ParkingLotOwer owner;
    private AirportSecurity security;
    private List<ParkinLotObserver> observerList;

    public ParkingLotSystem(int capacity){
        this.observerList=new ArrayList<>();
        this.currentCapacity=0;
        this.actualCapacity=capacity;
    }

    public void  parked(Object vehicle) throws Exception {
        if (this.currentCapacity == this.actualCapacity){
            for (ParkinLotObserver observer:observerList){
                observer.capacityFull();
            }
            owner.capacityFull();
            security.capacityFull();
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

    public void registerParkinLotObserver(ParkinLotObserver observer) {
        this.observerList.add(observer);
    }

    public void registerSecurity(AirportSecurity airportSecurity) {
        this.security=airportSecurity;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }


}