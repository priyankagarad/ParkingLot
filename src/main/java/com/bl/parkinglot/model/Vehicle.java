/***********************************************************************************************************************
 * @purpose: Define vehicle Information
 * @Author:priyanka Garad
 * @Date:24/5/2020
 ***********************************************************************************************************************/
package com.bl.parkinglot.model;
import com.bl.parkinglot.enums.Driver;
public class Vehicle {
    private String vehicleName;
    private String vehicleNumber;
    private String vehicleType;
    public Driver driver;
    private String vehicleColor;

    public Vehicle( String vehicleName, String vehicleNumber, String vehicleType,Driver driver,String vehicleColor) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName=vehicleName;
        this.vehicleType=vehicleType;
        this.driver = driver;
        this.vehicleColor=vehicleColor;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleName='" + vehicleName + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", driver=" + driver +
                ", vehicleColore=" + vehicleColor +
                '}';
    }
}
