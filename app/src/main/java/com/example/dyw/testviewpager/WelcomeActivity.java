package com.example.dyw.testviewpager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_FIRSTACTIVITY = 1000;
    private static final int GO_MAINACTIVITY = 1001;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case GO_FIRSTACTIVITY:
                    goFirstactivity();
                    break;
                case GO_MAINACTIVITY:
                    goMainactivity();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init(){
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn",true);
        if (!isFirstIn)
        {
            mHandler.sendEmptyMessageDelayed(GO_FIRSTACTIVITY,TIME);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_MAINACTIVITY,TIME);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();

        }

    }

    private void goFirstactivity(){
        Intent intent = new Intent(WelcomeActivity.this,FirstActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMainactivity(){
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
