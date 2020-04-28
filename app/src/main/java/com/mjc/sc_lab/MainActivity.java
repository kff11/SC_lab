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

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_drawer_layout);

        setToolbar();
        setDrawerLayout();



    }
    private void setToolbar(){
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
    }

    private void setDrawerLayout(){
        // 네비게이션 드로어
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

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
}

