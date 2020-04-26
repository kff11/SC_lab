package com.mjc.sc_lab.server.datas;

import com.google.gson.annotations.SerializedName;

public class SignUpReq {
    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("studentId")
    private String studentId;

    @SerializedName("name")
    private String name;

    @SerializedName("admin")
    private String admin;

    public SignUpReq(String id, String password, String studentId, String name, String admin) {
        this.id = id;
        this.password = password;
        this.studentId = studentId;
        this.name = name;
        this.admin = admin;

    }
}
