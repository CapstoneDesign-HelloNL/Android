package com.example.areact.group.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.example.areact.group.detail.server.GroupNoticeGet;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupNotice_detail extends FragmentActivity {
    //Group_Notice_write 에서 받아온 데이터
    String mTitle, mRadioState;
    String mDate;
    String mKeyword;
    String mIsChecked;
    String groupName;
    String isCurrentView;
    Integer id;

    RecyclerView recyclerView;
    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    private final ArrayList<GroupNotice> list = new ArrayList<>();
    private GroupNoticeAdapter adapter = new GroupNoticeAdapter(list, getGroupName(), getId());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_detail);
        changeStatusBarColor();

        ImageButton back_btn = findViewById(R.id.group_notice_back_btn);
        ImageButton write_btn = findViewById(R.id.group_notice_write_btn);
        recyclerView = findViewById(R.id.group_detail_notice_recycle);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //Group 이름 데이터 받기
        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        isCurrentView = intent.getStringExtra("fromDelToDetail");

        sharedPref = getSharedPreferences("groupName", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        System.out.println(isCurrentView);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new GroupNotice_write());
            }
        });

        System.out.println("onCreate");

        if (isCurrentView != null && isCurrentView.equals("access")) {
            connectToServer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        connectToServer();
        System.out.println("onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Intent intent = getIntent();
        setGroupName(intent.getStringExtra("groupName"));
        connectToServer();
        System.out.println("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
        System.out.println("onDestroy");
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getNoticeData(groupName).enqueue(new Callback<GroupNoticeGet>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NotNull Call<GroupNoticeGet> call, @NotNull Response<GroupNoticeGet> response) {
                if (response.isSuccessful()) {
                    GroupNoticeGet body = response.body();
                    Log.d("GroupNotice Get?", body.getMsg());
                    Log.d("GroupNotice Get?", body.getStatus());

                    for (int i = 0; i < body.getData().size(); i++) {
                        mTitle = body.getData().get(i).getTitle();
                        mDate = body.getData().get(i).getCreatedAt();
                        setId(body.getData().get(i).getId());
                        formattingDate(mDate);
                        GroupNotice groupNotice = new GroupNotice(getDrawable(R.drawable.ic_flash_on_black_24dp), "회의", mTitle, mDate, getId());
                        list.add(groupNotice);
                    }
                    adapter.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<GroupNoticeGet> call, Throwable t) {

            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("groupName", groupName);
        fragment.setArguments(bundle);
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        ft.replace(R.id.group_notice_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void formattingDate(String sDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(sDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
            mDate = simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#4B9AC8"));
        }
    }

    private String getAccessToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_access_token", "");
        return token;
    }

    public String getRefreshToken() {
        String token;
        sharedPref = getSharedPreferences("tokenData", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        token = sharedPref.getString("s_refresh_token", "");
        return token;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public Integer getId() {
        return id;
    }
}
