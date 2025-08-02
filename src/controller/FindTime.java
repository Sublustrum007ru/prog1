package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FindTime {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public String findTime(){
        String currentTime = "[" + LocalTime.now().format(TIME_FORMATTER) + "] : ";
        return currentTime;
    }

}
