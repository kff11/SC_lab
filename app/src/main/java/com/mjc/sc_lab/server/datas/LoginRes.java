package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class LoginRes {

    @SerializedName("suc")
    private boolean suc;

    @SerializedName("name")
    private String name;

    @SerializedName("studentId")
    private String studentId;

    public boolean isSuc() {
        return suc;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}