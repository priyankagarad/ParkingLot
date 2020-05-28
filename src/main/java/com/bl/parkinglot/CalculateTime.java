package com.bl.parkinglot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class CalculateTime {
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }
}
