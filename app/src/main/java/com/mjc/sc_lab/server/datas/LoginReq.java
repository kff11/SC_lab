package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class LoginReq {
    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    public LoginReq(String id, String password) {
        this.id = id;
        this.password = password;
    }
}