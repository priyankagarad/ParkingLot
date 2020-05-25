/*********************************************************************************************************************
 * @purpose:ParkingLot System Class Define the the function of park,unpark the Vehicle and set The capacity of vehicle
 * @Author:priyanka garad
 * Date:24/5/2020
 ********************************************************************************************************************/
package com.bl.parkinglot.service;
import com.bl.parkinglot.exception.ParkinLotException;
import com.bl.parkinglot.model.AirportSecurity;
import com.bl.parkinglot.model.ParkinLotObserver;
import java.util.ArrayList;
import java.util.List;
public class ParkingLotSystem
{
    /**+
     * @param:vehicleList: vehicleList to store The Number of vehicle
     * @observerList:observerList Store the List of observer
     */
    private int actualCapacity;
    private List<Object> vehicleList;
    private List<ParkinLotObserver> observerList;
    private AirportSecurity security;
    ParkinLotObserver observer;

    /**+
     * @purpose:set the capacity
     * @param :capacity
     */
    public ParkingLotSystem(int capacity) {
        this.observerList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.actualCapacity = capacity;
    }

    /** To Park The Vehicle */
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

    /** to check vehicle is parked */
    public boolean isVehicleParked(Object vehicle){
        if(this.vehicleList.contains(vehicle))
            return true;
        return false;
    }

    /** To unPark the vehicle*/
    public boolean UnPark(Object vehicle) {
        if (vehicle==null)
            return false;
        if (this.vehicleList.contains(vehicle)) {
            this.vehicleList.remove(vehicle);
           // observer.parkingAvailable();

            for(ParkinLotObserver observer:observerList)
                observer.isParkingEmpty();
            return true;
        }
        throw new ParkinLotException(ParkinLotException.ExceptionType.VEHICLE_NOT_FOUND, "Vehicle Is Not In Parking");
    }

    /** To add observer to list */
    public void registerParkingLotObserver(ParkinLotObserver observer) {
        this.observerList.add(observer);
    }

    /** to register for parking */
    public void registerSecurity(AirportSecurity airportSecurity) {
        this.security=airportSecurity;
    }

    /** set the capacity of Vehicle in parking lot*/
    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }
}