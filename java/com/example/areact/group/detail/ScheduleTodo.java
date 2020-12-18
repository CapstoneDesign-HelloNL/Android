package com.example.areact.group.detail;

public class ScheduleTodo {
    private String title;
    private String date;

    public ScheduleTodo(String str, String date) {
        this.title = str;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
