package com.example.areact.group;

public class GroupEntity {
    private String group_name, society_name, group_in_count;

    public GroupEntity(String g_name, String s_name, String g_count) {
        this.group_name = g_name;
        this.society_name = s_name;
        this.group_in_count = g_count;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public void setSociety_name(String society_name) {
        this.society_name = society_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getGroup_in_count() {
        return group_in_count;
    }

    public String getSociety_name() {
        return society_name;
    }
}
