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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.areact.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SocialFragment extends Fragment {
    private SocialFragment() {
    }

    public static class SingletonHolder {
        public static SocialFragment INSTANCE = new SocialFragment();
    }

    public static SocialFragment getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DrawerLayout drawerLayout;
    private View drawerView;
    private ImageButton ham_btn;
    private TextView feed_write, feed_msg;
    private RecyclerView recyclerView;
    private String feed_name;
    private String feed_body;
    String feed_date;
    private FragmentManager fm;
    private final ArrayList<FeedPost> post = new ArrayList<>();
    private Feed_adapter adapter;

    private static SharedPreferences sharedPref = null;
    private static SharedPreferences.Editor editor = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor();
        fm = getParentFragmentManager();
        adapter = new Feed_adapter(post, fm);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                feed_body = bundle.getString("body");

                feed_name = "박민호";
                feed_date = getDate();
                FeedPost feedPost = new FeedPost(feed_name, feed_date, feed_body);
                post.add(feedPost);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_social_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.feed_post_recyview);
        feed_write = rootView.findViewById(R.id.feed_ham_write);
        feed_msg = rootView.findViewById(R.id.feed_ham_msg);
        ham_btn = rootView.findViewById(R.id.feed_hamburger_main);
        drawerLayout = rootView.findViewById(R.id.feed_drawer_layout_main);
        drawerView = rootView.findViewById(R.id.feed_drawer);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //BottomNavigationView hide
        View bottomView = getLayoutInflater().inflate(R.layout.activity_group_main, null);
        BottomNavigationView navigationView = bottomView.findViewById(R.id.bottom_navi_bar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        //hamburger Button
        ham_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        feed_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new FeedPostWrite());
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.addToBackStack(null);
                drawerLayout.closeDrawer(drawerView);
                ft.commit();
            }
        });

        feed_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_frame, new FeedMessageFragment());
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.addToBackStack(null);
                drawerLayout.closeDrawer(drawerView);
                ft.commit();

            }
        });
    }

    private String getDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        return dateFormat.format(date);
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
