package com.example.areact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    private static int TIMEOUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        }, TIMEOUT);
    }
}