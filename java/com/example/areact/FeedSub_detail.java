package com.example.areact;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FeedSub_detail extends Activity {
    private DrawerLayout drawerLayout;
    private View drawerView;
    private TextView feed_write;
    private TextView feed_msg;
    private ImageButton back_btn;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_sub_detail);

        drawerLayout = findViewById(R.id.feed_drawer_layout);
        drawerView = findViewById(R.id.feed_drawer);
        feed_write =findViewById(R.id.feed_ham_write);
        feed_msg = findViewById(R.id.feed_ham_msg);
        back_btn = findViewById(R.id.feed_sub_back_btn);

        ImageButton ham_btn = findViewById(R.id.feed_hamburger);

        //뒤로 가기
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedSub_detail.this, SocialFragment.class);
                startActivity(intent);
            }
        });

        ham_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

    }

}
