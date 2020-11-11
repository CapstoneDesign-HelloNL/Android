package com.example.areact;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class LoginActivity extends FragmentActivity {
    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);
        Button btn1 = findViewById(R.id.sign_in_btn);
        Button btn2 = findViewById(R.id.sign_up_btn);

        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0);

        View.OnClickListener movePageLisener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tag =(int) view.getTag();
                pager.setCurrentItem(tag);
            }
        };

        btn1.setOnClickListener(movePageLisener);
        btn1.setTag(0);
        btn2.setOnClickListener(movePageLisener);
        btn2.setTag(1);
    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SignInFragment();
                case 1:
                    return new SignUpFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
