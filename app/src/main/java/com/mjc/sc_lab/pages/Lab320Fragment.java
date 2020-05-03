package com.mjc.sc_lab.pages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mjc.sc_lab.R;
import com.mjc.sc_lab.SharedPreference;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.HashMap;


public class Lab320Fragment extends Fragment implements View.OnClickListener {
    private View view;

    private SharedPreference sharedPreference;
    private HashMap<String, View> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lab320, container, false);
        sharedPreference = new SharedPreference(getContext());

        layoutInit();
        buttonInit();

        return view;
    }

    private void layoutInit() {
        String name = sharedPreference.getSharedString("name");
        TextView textView = view.findViewById(R.id.lab320_name);
        textView.setText(name + "님 원하는 자리를 선택해 주세요!");
    }

    private void buttonInit() {
        map = new HashMap<String, View>();
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getContext().getPackageName());
            String btnVar = "btnVar" + Integer.toString(i);
            map.put(btnVar, view.findViewById(btnId));
            map.get(btnVar).setOnClickListener(this);
            map.get(btnVar).setBackgroundResource(R.color.seatNotUse);
        }
    }

    @Override
    public void onClick(View v) {
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getContext().getPackageName());
            final String btnVar = "btnVar" + Integer.toString(i);
            if (v.getId() == btnId) {
                new SweetAlertDialog(getContext())
                        .setTitleText("자리 예약")
                        .setContentText(Integer.toString(i) + "번 자리를 예약하시겠습니까?")
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
                                map.get(btnVar).setBackgroundResource(R.color.myeongjiBlue);
                                sweetAlertDialog.cancel();
                            }
                        })
                        .show();
                break;
            }
        }
    }
}
