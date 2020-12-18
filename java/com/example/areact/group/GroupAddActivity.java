package com.example.areact.group;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.areact.R;
import com.example.areact.group.server.GroupAdd;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupAddActivity extends Fragment {

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    private EditText group_name, society_name, group_code;
    private GroupFragment fragment = new GroupFragment();
    private BottomNavigationView navigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //상태바 색상 변경
        changeStatusBarColor("#FFFFFF");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_add, container, false);

        //네비게이션 바 일시적으로 감추기
        navigationView = getActivity().findViewById(R.id.bottom_navi_bar);
        navigationView.setVisibility(View.GONE);

        //xml 연동
        ImageButton back_btn = rootView.findViewById(R.id.group_add_back_btn);
        Button complete_btn = rootView.findViewById(R.id.group_add_complete_btn);
        group_name = rootView.findViewById(R.id.group_add_group);
        society_name = rootView.findViewById(R.id.group_add_univ);
        group_code = rootView.findViewById(R.id.group_add_code);

        //뒤로가기 버튼
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("back_btn_click", "back");
                fragment.setArguments(bundle);
                changeStatusBarColor("#FCFCFC");
                getActivity().onBackPressed();
                navigationView.setVisibility(View.VISIBLE);
            }
        });

        //그룹 추가 완료 버튼
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText 초기화
                changeStatusBarColor("#FCFCFC");
                fragment_add();
                connectionServer();
                group_name.setText("");
                group_code.setText("");
                society_name.setText("");
            }
        });
        return rootView;
    }

    private void fragment_add() {
        FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        navigationView.setVisibility(View.VISIBLE);
        ft.commit();
    }

    private void connectionServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        String group_str = group_name.getText().toString();
        String society_str = society_name.getText().toString();
        String code_str = group_code.getText().toString();

        HashMap<String, Object> input = new HashMap<>();
        input.put("name", group_str);
        input.put("univName", society_str);
        input.put("joinCode", code_str);

        retrofitAPI.postGroupAdd(input).enqueue(new Callback<GroupAdd>() {
            @Override
            public void onResponse(Call<GroupAdd> call, Response<GroupAdd> response) {
                if (response.isSuccessful()) {
                    GroupAdd groupAdd = response.body();
                    Log.d("Group add", groupAdd.getMsg());
                    Log.d("Group add", groupAdd.getStatus());
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        System.out.println(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupAdd> call, Throwable t) {
                t.printStackTrace();
                Log.d("Group add", "실패");
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

    private String getRefreshToken() {
        String token;
        sharedPref = getActivity().getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}
