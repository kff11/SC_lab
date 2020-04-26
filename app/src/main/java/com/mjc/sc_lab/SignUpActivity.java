package com.mjc.sc_lab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.klinker.android.sliding.MultiShrinkScroller;
import com.klinker.android.sliding.SlidingActivity;
import com.mjc.sc_lab.server.RetrofitConnector;
import com.mjc.sc_lab.server.ServiceApi;
import com.mjc.sc_lab.server.datas.LoginReq;
import com.mjc.sc_lab.server.datas.SignUpReq;
import com.mjc.sc_lab.server.datas.SignUpRes;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends SlidingActivity {

    EditText editText_id, editText_pw, editText_name, editText_studentId;


    @Override
    public void init(Bundle savedInstanceState) {
        setTitle("회원가입");

        setPrimaryColors(
                getColor(R.color.myeongjiBlue),
                getColor(R.color.myeongjiDarkBlue)
        );
        setContent(R.layout.activity_sign_up);

        editText_id = findViewById(R.id.signUp_id);
        editText_pw = findViewById(R.id.signUp_pw);
        editText_name = findViewById(R.id.signUp_name);
        editText_studentId = findViewById(R.id.signUp_studentId);
    }

    @Override
    protected void configureScroller(MultiShrinkScroller scroller) {
        super.configureScroller(scroller);
        scroller.setIntermediateHeaderHeightRatio(0);
    }

    private void pushUserData(String id, String password, String studentId, final String name) {
        SignUpReq data = new SignUpReq(id, password, studentId, name, "N");
        Retrofit retrofit = RetrofitConnector.createRetrofit(getApplicationContext());

        Call<SignUpRes> call = retrofit.create(ServiceApi.class).signUp(data);
        call.enqueue(new Callback<SignUpRes>() {
            @Override
            public void onResponse(Call<SignUpRes> call, Response<SignUpRes> response) {
                SignUpRes result = response.body();
                if (result.isSign()) {
                    Toast.makeText(getApplicationContext(), name + "학생 회원가입 되었습니다!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "중복된 아이디가 존재합니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpRes> call, Throwable t) {
                Log.d("Server", "onFailure: " + t.toString());
            }
        });

    }

    public void onClick(View view) {
        String id = editText_id.getText().toString();
        String password = editText_pw.getText().toString();
        String name = editText_name.getText().toString();
        String studentId = editText_studentId.getText().toString();

        if (!Pattern.matches("^[a-z0-9]{6,15}$", id)) {
            Toast.makeText(this, "아이디는 영문자 및 숫자 6~20자여야만 합니다.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Pattern.matches("^[a-zA-Z0-9]{6,20}$", password)) {
            Toast.makeText(this, "비밀번호는 영문자 및 숫자 6~20자여야만 합니다.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Pattern.matches("^[0-9]{10}$", studentId)) {
            Toast.makeText(this, "학번은 숫자 10자여야만 합니다.", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Pattern.matches("^[가-힣]{2,10}$", name)) {
            Toast.makeText(this, "이름은 국문자 2~10자여야만 합니다.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            pushUserData(id, password, studentId, name);
        }

    }
}
