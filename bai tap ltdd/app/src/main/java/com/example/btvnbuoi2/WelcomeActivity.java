package com.example.btvnbuoi2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("account",MODE_PRIVATE);
                if(sharedPreferences.getInt("tt",0) == 1) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 5000 );
    }
    public void onBackPressed() {

        super.onBackPressed();
        System.exit(0);
        moveTaskToBack(true);
        finish();
    }
}
