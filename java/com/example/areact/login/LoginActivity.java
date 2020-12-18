package com.example.areact.login;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.areact.R;


public class LoginActivity extends FragmentActivity implements SignUpFragment.buttonClick {
    private ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn1 = findViewById(R.id.sign_in_btn);
        final Button btn2 = findViewById(R.id.sign_up_btn);

        View view = getLayoutInflater().inflate(R.layout.sign_up_fragment, null, false);
        Button sign_up_btn = view.findViewById(R.id.sign_up_complete_btn);

        pager = findViewById(R.id.pager);
        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0);

        View.OnTouchListener movePageLisener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int tag =(int) view.getTag();
                pager.setCurrentItem(tag);

                return false;
            }
        };

        btn1.setOnTouchListener(movePageLisener);
        btn1.setTag(0);
        btn2.setOnTouchListener(movePageLisener);
        btn2.setTag(1);
        sign_up_btn.setOnTouchListener(movePageLisener);
        sign_up_btn.setTag(0);
    }

    public static class pagerAdapter extends FragmentStatePagerAdapter {
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

    @Override
    public void buttonClicked(View v) {
        pager.setCurrentItem(0);
    }
}
