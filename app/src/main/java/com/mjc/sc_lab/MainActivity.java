package com.mjc.sc_lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Button 변수들이 들어갈 배열
    private HashMap<String, View> map;

    // 레이아웃
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_drawer_layout);

        layoutInit();
        buttonInit();

    }

    private void layoutInit() {

        // 툴바
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);

        // 네비게이션 드로어
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.main_menu_profile:
                        intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        break;
                    case R.id.main_menu_posts:
                        intent = new Intent(getApplicationContext(), PostsActivity.class);
                        break;
                    case R.id.main_menu_setting:
                        intent = new Intent(getApplicationContext(), SettingActivity.class);
                        break;
                }
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                return false;
            }
        });

    }

    private void buttonInit() {
        map = new HashMap<String, View>();
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getPackageName());
            String btnVar = "btnVar" + Integer.toString(i);
            map.put(btnVar, findViewById(btnId));
            map.get(btnVar).setBackgroundResource(R.color.seatNotUse);
        }
    }


    public void onClick(View view) {
        for (int i = 1; i <= 36; i++) {
            int btnId = getResources().getIdentifier("lab_" + i, "id", getPackageName());
            String btnVar = "btnVar" + Integer.toString(i);

            if (view.getId() == btnId) {
                map.get(btnVar).setBackgroundResource(R.color.myeongjiBlue);
                break;
            }
        }
    }
}

