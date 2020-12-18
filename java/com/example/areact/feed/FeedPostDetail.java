package com.example.areact.feed;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FeedPostDetail extends Fragment {
    private TextView comment_ok_tv, comment_count_tv;
    private TextView name_tv, date_tv, body_tv;
    private ImageButton back_btn;
    private EditText comment_et;
    private String comment_name, comment_date, commentBody;
    private String feed_name, feed_date, feed_body;
    private Long saveTimeCurrent;
    private RecyclerView recyclerView;
    private BottomNavigationView navigationView;
    private ScrollView scrollView;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    private final ArrayList<FeedPostComment> list = new ArrayList<>();
    private FeedPostCommentAdapter adapter = new FeedPostCommentAdapter(list);

    FeedPostDetail(String name, String date, String body) {
        this.feed_name = name;
        this.feed_date = date;
        this.feed_body = body;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        navigationView = getActivity().findViewById(R.id.bottom_navi_bar);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_feed_sub_detail, container, false);
        back_btn = rootView.findViewById(R.id.feed_sub_back_btn);
        comment_ok_tv = rootView.findViewById(R.id.feed_post_detail_comment_tv_btn);
        comment_et = rootView.findViewById(R.id.feed_post_detail_comment_et);
        comment_count_tv = rootView.findViewById(R.id.feed_post_detail_comment);
        name_tv = rootView.findViewById(R.id.feed_post_detail_name);
        date_tv = rootView.findViewById(R.id.feed_post_detail_date);
        body_tv = rootView.findViewById(R.id.feed_post_detail_body);
        scrollView = rootView.findViewById(R.id.sub_detail_scroll);

        recyclerView = rootView.findViewById(R.id.feed_post_detail_comment_recyclerview);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        saveTimeCurrent = System.currentTimeMillis();

        //게시글 detail 설정
        name_tv.setText(feed_name);
        date_tv.setText(feed_date);
        body_tv.setText(feed_body);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().
                        replace(R.id.main_frame, SocialFragment.getInstance()).commit();
            }
        });

        comment_ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment_name = "양정훈";
                comment_date = formatTimeString(saveTimeCurrent);
                commentBody = comment_et.getText().toString();
                FeedPostComment comment = new FeedPostComment(comment_name, commentBody, comment_date);
                list.add(comment);
                adapter.notifyDataSetChanged();
                comment_count_tv.setText(String.valueOf(list.size()));
                comment_et.setText("");


                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        navigationView.setVisibility(View.VISIBLE);
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

    private String getComment_date() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#4B9AC8"));
        }
    }

    private static class TIME_MAXIMUM {
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }

    public static String formatTimeString(long regTime) {
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }
}
