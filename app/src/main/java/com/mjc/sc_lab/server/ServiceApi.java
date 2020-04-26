package com.mjc.sc_lab.server;

import com.mjc.sc_lab.server.datas.LoginReq;
import com.mjc.sc_lab.server.datas.LoginRes;
import com.mjc.sc_lab.server.datas.SignUpReq;
import com.mjc.sc_lab.server.datas.SignUpRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginRes> userLogin(@Body LoginReq loginReq);

    @POST("/user/signUp")
    Call<SignUpRes> signUp(@Body SignUpReq signUpReq);
}
