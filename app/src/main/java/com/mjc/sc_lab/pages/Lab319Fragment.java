package com.mjc.sc_lab.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mjc.sc_lab.R;
import com.mjc.sc_lab.SharedPreference;
import com.mjc.sc_lab.server.RetrofitConnector;
import com.mjc.sc_lab.server.ServiceApi;
import com.mjc.sc_lab.server.datas.Seat;
import com.mjc.sc_lab.server.datas.SeatReq;
import com.mjc.sc_lab.server.datas.SeatRes;
import com.mjc.sc_lab.server.datas.StateRes;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Lab319Fragment extends Fragment implements View.OnClickListener {
    private View view;

    private SharedPreference sharedPreference;
    private HashMap<String, View> map;

    private String name, studentId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lab319, container, false);
        sharedPreference = new SharedPreference(getContext());

        layoutInit();
        buttonInit();

        return view;
    }

    private void layoutInit() {
        name = sharedPreference.getSharedString("name");
        studentId = sharedPreference.getSharedString("studentId");
        TextView textView = view.findViewById(R.id.lab319_name);
        textView.setText(name + "님 원하는 자리를 선택해 주세요!");
    }

    private void setButtonState(StateRes res) {
        map = new HashMap<String, View>();
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getContext().getPackageName());
            String btnVar = "btnVar" + Integer.toString(i);
            map.put(btnVar, view.findViewById(btnId));
            map.get(btnVar).setOnClickListener(this);
            ArrayList<SeatReq> state = res.getState();
            if (state.get(i - 1).getState() == 1) {
                map.get(btnVar).setBackgroundResource(R.color.myeongjiBlue);
            } else {
                map.get(btnVar).setBackgroundResource(R.color.seatNotUse);
            }
        }

    }

    private void buttonInit() {
        SeatReq req = new SeatReq("319");
        Retrofit retrofit = RetrofitConnector.createRetrofit(getContext());

        Call<StateRes> call = retrofit.create(ServiceApi.class).getSeatState(req);
        call.enqueue(new Callback<StateRes>() {
            @Override
            public void onResponse(Call<StateRes> call, Response<StateRes> response) {
                StateRes result = response.body();
                setButtonState(result);
            }

            @Override
            public void onFailure(Call<StateRes> call, Throwable t) {
                Log.d("Server", "onFailure: " + t.toString());
            }
        });
    }

  /*  private SweetAlertDialog reserveAlertDialog(Context context, final int number) {
        return new SweetAlertDialog(getContext())
                .setTitleText("자리 예약")
                .setContentText(Integer.toString(number) + "번 자리를 예약하시겠습니까?")
                .setConfirmText("예약")
                .setCancelText("취소")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        setSeatReserve(number, name, studentId);
                        sweetAlertDialog.cancel();
                    }
                });
    }*/

    private void setSeatReserve(final int number, String name, String studentId) {
        SeatReq req = new SeatReq("319", number, 1, name, studentId);
        Retrofit retrofit = RetrofitConnector.createRetrofit(getContext());

        Call<SeatRes> call = retrofit.create(ServiceApi.class).reserveSeat(req);
        call.enqueue(new Callback<SeatRes>() {
            @Override
            public void onResponse(Call<SeatRes> call, Response<SeatRes> response) {
                SeatRes result = response.body();

                if (result.isSuc()) {
                    String btnVar = "btnVar" + Integer.toString(number);
                    map.get(btnVar).setBackgroundResource(R.color.myeongjiBlue);
                    Toast.makeText(getContext(), Integer.toString(number) + "번 자리 예약되었습니다!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "이미 예약된 자리입니다!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<SeatRes> call, Throwable t) {
                Log.d("Server", "onFailure: " + t.toString());
            }
        });
    }

    private void reserveAlertDialog(Context context, final int number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("알림");
        builder.setMessage(number + "번 자리를 예약 하시겠습니까?");
        builder.setPositiveButton("예약", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setSeatReserve(number, name, studentId);
            }
        });
        builder.setNegativeButton("취소", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getContext().getPackageName());
            if (v.getId() == btnId) {
                final int number = i;
                reserveAlertDialog(getContext(), number);
                break;
            }
        }
    }
}
