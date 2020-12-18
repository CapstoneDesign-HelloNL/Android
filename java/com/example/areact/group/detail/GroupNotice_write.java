package com.example.areact.group.detail;

import android.content.Context;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.areact.R;
import com.example.areact.group.detail.server.GroupNoticePost;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupNotice_write extends Fragment {
    private ImageButton back_btn, write_btn;
    private RadioGroup radioGroup;
    private Switch mSwitch;
    EditText title_et, body_et;
    private String checked_str;
    private String radio_state_str;
    public String groupName;
    private Context mContext;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        groupName = bundle.getString("groupName");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_group_notice_write, container, false);
        back_btn = rootView.findViewById(R.id.group_notice_detail_back);
        write_btn = rootView.findViewById(R.id.group_notice_detail_write);
        mSwitch = rootView.findViewById(R.id.group_notice_detail_toggle);
        title_et = rootView.findViewById(R.id.group_notice_detail_title_et);
        body_et = rootView.findViewById(R.id.group_notice_detail_body_et);
        radioGroup = rootView.findViewById(R.id.group_radio_group);

        changeStatusBarColor("#ffffff");
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //라디오 그룹
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.meeting_radio_btn:
                        radio_state_str = "meeting";
                        break;
                    case R.id.normal_radio_btn:
                        radio_state_str = "normal";
                        break;
                    case R.id.contest_radio_btn:
                        radio_state_str = "contest";
                        break;
                    case R.id.etc_radio_btn:
                        radio_state_str = "etc";
                        break;
                }
            }
        });
        //default 값으로 스위치 버튼 off 설정
        mSwitch.setChecked(false);
        isChecked("off");

        //중요 스위치 on/off 시 동작
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isChecked("on");
                } else {
                    isChecked("off");
                }
            }
        });

        //뒤로 가기 버튼
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatusBarColor("#4B9AC8");
                getActivity().onBackPressed();
            }
        });

        //작성 완료 버튼
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatusBarColor("#4B9AC8");
                connectToServer();

                getActivity().getSupportFragmentManager().beginTransaction().remove(GroupNotice_write.this).commit();
                getActivity().setVisible(false);
                Intent intent = new Intent(getActivity(), GroupNotice_detail.class);
                intent.putExtra("groupName", groupName);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void isChecked(String s) {
        this.checked_str = s;
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        String title = title_et.getText().toString();
        String content = body_et.getText().toString();

        HashMap<String, Object> input = new HashMap<>();
        input.put("title", title);
        input.put("content", content);
        input.put("author", "박민호");
        input.put("photo", "https://www.kakao.com");
        retrofitAPI.postNoticeData(groupName, input).enqueue(new Callback<GroupNoticePost>() {
            @Override
            public void onResponse(Call<GroupNoticePost> call, Response<GroupNoticePost> response) {
                if (response.isSuccessful()) {
                    GroupNoticePost body = response.body();
                    Log.d("GroupNotice write?", body.getMsg());
                    Log.d("GroupNotice write?", body.getStatus());
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
            public void onFailure(Call<GroupNoticePost> call, Throwable t) {
                t.printStackTrace();
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