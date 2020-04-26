package com.mjc.sc_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.mjc.sc_lab.server.datas.LoginReq;
import com.mjc.sc_lab.server.datas.LoginRes;
import com.mjc.sc_lab.server.RetrofitConnector;
import com.mjc.sc_lab.server.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText editText_id, editText_pw;
    private Button loginbtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TypefaceProvider.registerDefaultIconSets();

        editText_id = findViewById(R.id.login_id);
        editText_pw = findViewById(R.id.login_pw);

    }

    private void searchUserData(String id, String pw) {
        LoginReq data = new LoginReq(id, pw);
        Retrofit retrofit = RetrofitConnector.createRetrofit(getApplicationContext());

        Call<LoginRes> call = retrofit.create(ServiceApi.class).userLogin(data);
        call.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                LoginRes result = response.body();

                if (result.isSuc()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 확인해 주세요.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Log.d("Server", "onFailure: " + t.toString());
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_loginBtn:
                String id = editText_id.getText().toString();
                String pw = editText_pw.getText().toString();

                searchUserData(id, pw);
                break;
            case R.id.login_signUpBtn:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }

    }
}
