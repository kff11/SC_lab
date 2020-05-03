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

import com.mjc.sc_lab.R;

import java.util.HashMap;


public class Lab320Fragment extends Fragment implements View.OnClickListener {
    private View view;

    private HashMap<String, View> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lab320, container, false);

        buttonInit();

        return view;
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
            String btnVar = "btnVar" + Integer.toString(i);
            if (v.getId() == btnId) {
                map.get(btnVar).setBackgroundResource(R.color.myeongjiBlue);
                break;
            }
        }
    }
}
