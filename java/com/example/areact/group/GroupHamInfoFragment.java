package com.example.areact.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.areact.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GroupHamInfoFragment extends BottomSheetDialogFragment {
    ImageButton back_btn;
    View layoutInflater, view;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    //싱글톤 구현
    private GroupHamInfoFragment() {
    }

    public static class SingletonHolder {
        public static final GroupHamInfoFragment INSTANCE = new GroupHamInfoFragment();
    }

    public static GroupHamInfoFragment getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_group_ham_info, container, false);
        back_btn = rootView.findViewById(R.id.group_ham_info_back_btn);
        layoutInflater = getLayoutInflater().inflate(R.layout.activity_group_detail, null, false);
        view = layoutInflater.findViewById(R.id.frame_layout_viewID);
        //상위 뷰 눌림 방지

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
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
}
