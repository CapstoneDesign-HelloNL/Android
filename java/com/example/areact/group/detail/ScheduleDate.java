package com.example.areact.group.detail;

public class ScheduleDate {
    private String year;
    private String month;
    private String day;

    ScheduleDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
