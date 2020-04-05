package com.knewbie.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.knewbie.news.R;
import com.knewbie.news.widget.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    public static final String START_MAIN = "start_main";
    boolean has_skip = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean isStartMain = CacheUtils.getBoolean(this, START_MAIN);
        if (isStartMain) {  //进入过主界面
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Button buttonEnter = findViewById(R.id.buttonSplashEnter);
            buttonEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    has_skip = true;
                    finish();
                }
            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!has_skip) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 3000);
        }

    }
}
