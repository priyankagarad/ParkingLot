/***********************************************************************************************************************
 * @purpose: Providing Different Type Of Driver As Per Requirement
 * @Author: Priyanka Garad
 * Date: 24/5/2020
 ***********************************************************************************************************************/
package com.bl.parkinglot.enums;
public class Driver {
    public enum DriverType {
        HANDICAP,NORMAL
    }

    private DriverType driverType;
    public Driver(DriverType driverType){
        this.driverType=driverType;
    }

   public DriverType getDriverType() {
        return driverType;
    }
}

