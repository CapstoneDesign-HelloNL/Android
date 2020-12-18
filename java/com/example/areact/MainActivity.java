package com.example.areact;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.areact.alert.AlertFragment;
import com.example.areact.feed.SocialFragment;
import com.example.areact.group.GroupFragment;
import com.example.areact.setup.SetupFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView bottomNavigationView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);
        changeStatusBarColor("#FCFCFC");

        System.out.println("MainActivity : onCreate");
        bottomNavigationView = findViewById(R.id.bottom_navi_bar);
        bottomNavigationView.setVisibility(View.VISIBLE);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.group_btn:
                        replaceFragment(new GroupFragment());
                        break;
                    case R.id.sns_btn:
                        replaceFragment(SocialFragment.getInstance());
                        break;
                    case R.id.alert_btn:
                        replaceFragment(AlertFragment.getInstance());
                        break;
                    case R.id.setup_btn:
                        replaceFragment(SetupFragment.getInstance());
                        break;
                }
                return true;
            }
        });
        replaceFragment(new GroupFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}
