package bsu.edu.cs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogTime {

    private int month;
    private int day;
    private int year;
    private int hour;
    private int minutes;
    private int seconds;

    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public void parseTimeStringIntoVariables(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedTime = LocalDateTime.parse(timeString, formatter);

        this.year = parsedTime.getYear();
        this.month = parsedTime.getMonthValue();
        this.day = parsedTime.getDayOfMonth();
        this.hour = parsedTime.getHour();
        this.minutes = parsedTime.getMinute();
        this.seconds = parsedTime.getSecond();
    }

    public String makeTimeReadable() {
        return String.format("%02d/%02d/%04d %02d:%02d:%02d", month, day, year, hour, minutes, seconds);
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}