package com.example.coe.homeactivity;


import static com.example.coe.LoginActivity.app;
import static com.example.coe.LoginActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.coe.R;
import com.example.coe.complaints.ComplaintsActivity;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        drawerLayout = findViewById(R.id.drawerview);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.topBar);

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        View headerView = navigationView.getHeaderView(0);
        user_name = (TextView) headerView.findViewById(R.id.username_home);
        user_name.setText(app.currentUser().getProfile().getEmail());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                // Add your navigational page code
                break;
            case R.id.navAnnouncements:
                // Add your navigational page code
                break;
            case R.id.navTimeTable:
                // Add your navigational page code
                break;
            case R.id.navResult:
                // Add your navigational page code
                break;
            case R.id.navComplaints:
                startActivity(new Intent(HomeActivity.this, ComplaintsActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
