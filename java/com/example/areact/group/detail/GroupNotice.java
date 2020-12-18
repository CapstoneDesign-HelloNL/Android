package com.example.areact.group.detail;

import android.graphics.drawable.Drawable;

public class GroupNotice {
    private Drawable imageView;
    private String keyword;
    private String title;
    private String date;
    private Integer id;

    public GroupNotice(Drawable imageView, String keyword, String title, String date, Integer id) {
        this.imageView = imageView;
        this.keyword = keyword;
        this.title = title;
        this.date = date;
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Drawable getImageView() {
        return imageView;
    }

    public Integer getId() {
        return id;
    }
}
