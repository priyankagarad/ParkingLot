package com.bl.parkinglot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class CalculateTime {
    public CalculateTime(Map<Integer, Object> vehicleTime) {
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }
}
