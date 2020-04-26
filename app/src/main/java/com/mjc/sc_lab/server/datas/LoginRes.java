package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class LoginRes {

    @SerializedName("suc")
    private boolean suc;


    public boolean isSuc() {
        return suc;
    }
}