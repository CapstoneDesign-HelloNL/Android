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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.areact.R;
import com.example.areact.group.detail.server.GroupNoticeOneGet;
import com.example.areact.server.RetrofitAPI;
import com.example.areact.server.ServiceGenerator;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupNoticePostsActivity extends AppCompatActivity {
    private TextView title, content, keyword, time;

    String mDate;
    String groupName;
    Integer id;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_notice_posts);
        changeStatusBarColor();

        title = findViewById(R.id.group_notice_posts_title);
        content = findViewById(R.id.group_notice_posts_content);
        keyword = findViewById(R.id.group_notice_posts_keyword);
        time = findViewById(R.id.group_notice_posts_time);
        ImageButton back_btn = findViewById(R.id.group_notice_posts_back_btn);
        ImageButton del_btn = findViewById(R.id.group_notice_posts_delete_btn);

        Intent intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        //id = intent.getIntExtra("id",1)+42;

        connectToServer();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteDialogNotice dialogNotice = new DeleteDialogNotice(GroupNoticePostsActivity.this, groupName, id, getAccessToken(), getRefreshToken());
                String titleStr = "정말 게시물을 삭제하시겠습니까?";
                dialogNotice.callFunction(titleStr);
            }
        });
    }

    private void connectToServer() {
        RetrofitAPI retrofitAPI = ServiceGenerator.createService(RetrofitAPI.class, getAccessToken());
        retrofitAPI.getNoticeDataOne("NL", 45).enqueue(new Callback<GroupNoticeOneGet>() {
            @Override
            public void onResponse(Call<GroupNoticeOneGet> call, Response<GroupNoticeOneGet> response) {
                if (response.isSuccessful()) {
                    GroupNoticeOneGet body = response.body();

                    Log.d("Server GroupNoticePosts", body.getMsg());
                    Log.d("Server GroupNoticePosts", body.getStatus());
                    title.setText(body.getData().getTitle());
                    content.setText(body.getData().getContent());
                    keyword.setText("회의");
                    mDate = body.getData().getCreatedAt();
                    formattingDate(mDate);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Log.d("Server GroupNoticePosts", responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupNoticeOneGet> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void formattingDate(String sDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(sDate);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            mDate = simpleDateFormat.format(date);
            time.setText(mDate);
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
}
