package com.example.areact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class GroupMainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        replaceFragment(new GroupFragment());
        bottomNavigationView = findViewById(R.id.bottom_navi_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.group_btn:
                        replaceFragment(new GroupFragment());
                        break;
                    case R.id.sns_btn:
                        replaceFragment(new SocialFragment());
                        break;
                    case R.id.alert_btn:
                        replaceFragment(new AlertFragment());
                        break;
                    case R.id.setup_btn:
                        replaceFragment(new SetupFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.replace(R.id.main_frame, fragment);
        ft.commit();
    }
}
