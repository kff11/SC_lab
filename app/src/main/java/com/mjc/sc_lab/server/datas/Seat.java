package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("number")
    private int number;

    @SerializedName("state")
    private int state;

    public int getNumber() {
        return number;
    }

    public int getState() {
        return state;
    }
}
