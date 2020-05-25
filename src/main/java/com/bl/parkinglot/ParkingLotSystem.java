package com.bl.parkinglot;
import java.util.ArrayList;
import java.util.List;
public class ParkingLotSystem
{
    private int actualCapacity;
    private List<Object> vehicleList;
    private List<ParkinLotObserver> observerList;
    private AirportSecurity security;

    public ParkingLotSystem(int capacity) {
        this.observerList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.actualCapacity = capacity;
    }

    public void parked(Object vehicle) throws ParkinLotException {
        if (isVehicleParked( vehicle )) {
            throw new ParkinLotException( ParkinLotException.ExceptionType.ALREADY_PARKED,"already parked" );
        }
        if (this.vehicleList.size() == actualCapacity) {
            for (ParkinLotObserver observer : observerList) {
                observer.isCapacityFull();
            }
            throw new ParkinLotException( ParkinLotException.ExceptionType.PARKING_IS_FULL,"Parking is full");
        }
        this.vehicleList.add( vehicle );
    }

    public boolean isVehicleParked(Object vehicle){
        if(this.vehicleList.contains(vehicle))
            return true;
        return false;
    }

    public boolean UnPark(Object vehicle) {
        if (vehicle==null)
            return false;
        if (this.vehicleList.contains(vehicle)) {
            this.vehicleList.remove(vehicle);
            for(ParkinLotObserver observer:observerList)
                observer.isParkingEmpty();
            return true;
        }
        throw new ParkinLotException(ParkinLotException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle Is Not In Parking");
    }


    public void registerParkingLotObserver(ParkinLotObserver observer) {
        this.observerList.add(observer);
    }

    public void registerSecurity(AirportSecurity airportSecurity) {
        this.security=airportSecurity;
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }
}