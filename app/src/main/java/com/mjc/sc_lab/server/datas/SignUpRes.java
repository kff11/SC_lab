package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class SignUpRes {
    @SerializedName("isSign")
    public boolean isSign;

    public boolean isSign() {
        return isSign;
    }
}
