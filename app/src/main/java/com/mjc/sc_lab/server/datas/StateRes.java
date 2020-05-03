package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StateRes {


    @SerializedName("seat")
    private ArrayList<SeatReq> seat;

    public ArrayList<SeatReq> getState() {
        return seat;
    }

}


