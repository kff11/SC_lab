package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class SeatReq {

    @SerializedName("lab")
    private String lab;

    @SerializedName("number")
    private int number;

    @SerializedName("state")
    private int state;

    @SerializedName("name")
    private String name;

    @SerializedName("studentId")
    private String studentId;

    public SeatReq(String lab, int number, int state, String name, String studentId){
        this.lab = lab;
        this.number = number;
        this.state = state;
        this.name = name;
        this.studentId = studentId;
    }

    public SeatReq(String lab){
        this.lab = lab;
    }

    public int getNumber() {
        return number;
    }

    public int getState() {
        return state;
    }
}
