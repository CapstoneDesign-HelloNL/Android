package com.example.areact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FeedPostMsg extends AppCompatActivity {
    private FragmentManager fm;
    private FragmentTransaction ft;
    SocialFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_post_msg);
    }
}
