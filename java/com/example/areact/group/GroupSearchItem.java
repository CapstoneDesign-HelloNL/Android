package com.example.areact.group;

public class GroupSearchItem {
    private String groupName;
    private String univName;

    GroupSearchItem(String group_name, String school_name) {
        this.groupName = group_name;
        this.univName = school_name;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getUnivName() {
        return this.univName;
    }
}
