package com.example.areact.group;

public class GroupHamPerson {
    private String name;
    private String stdNum;
    private String memberRank;

    GroupHamPerson(String name, String stdNum, String memberRank) {
        this.name = name;
        this.stdNum = stdNum;
        this.memberRank = memberRank;
    }

    public String getName() {
        return name;
    }

    public String getMemberRank() {
        return memberRank;
    }

    public String getStdNum() {
        return stdNum;
    }
}
