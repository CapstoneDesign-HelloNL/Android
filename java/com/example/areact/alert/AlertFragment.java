package com.example.areact.alert;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.R;

public class AlertFragment extends Fragment {
    private RelativeLayout layout;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;


    private AlertFragment() {
    }

    public static class SingletonHolder {
        public static AlertFragment INSTANCE = new AlertFragment();
    }

    public static AlertFragment getInstance() {
        return AlertFragment.SingletonHolder.INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_alert_fragment, container, false);
        layout = rootView.findViewById(R.id.alert_layout);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundResource(R.drawable.alert_old_bg);
            }
        });
    }

    private String getAccessToken() {
        String token;
        sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    public String getRefreshToken() {
        String token;
        sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_refresh_token", "");
        return token;
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#FCFCFC"));
        }
    }
}
