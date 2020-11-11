package com.example.areact;

import android.graphics.drawable.Drawable;

public class GroupSearchItem {
    public GroupSearchItem() {}

    private Drawable iconDrawable;
    private String group_name;
    private String society_name;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }

    public void setGroup_name(String group) {
        group_name = group;
    }

    public void setSociety_name(String society) {
        society_name = society;
    }

    public Drawable getIcon() {
        return this.iconDrawable;
    }

    public String getGroup_name() {
        return this.group_name;
    }

    public String getSociety_name() {
        return this.society_name;
    }
}
