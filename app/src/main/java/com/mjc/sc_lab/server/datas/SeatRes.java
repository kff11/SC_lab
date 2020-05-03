package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeatRes {

    @SerializedName("number")
    private int number;

    @SerializedName("name")
    private String name;

    @SerializedName("studentId")
    private String studentId;

    @SerializedName("state")
    private List<SeatReq> state;

    @SerializedName("suc")
    private boolean suc;

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<SeatReq> getState() {
        return state;
    }

    public boolean isSuc() {
        return suc;
    }


}

