package com.todo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTest {

    public static void main(String[] args) throws ParseException {

        String start = "2017-11-30T12:05";
        String end = "2017-11-30T12:15";

        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startDateObj = curFormater.parse(start);
        Date endDateObj = curFormater.parse(end);

        long diff =  endDateObj.getTime() - startDateObj.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print(diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds.");
       
    }
}
