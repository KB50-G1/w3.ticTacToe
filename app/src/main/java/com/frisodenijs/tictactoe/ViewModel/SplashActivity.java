package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.frisodenijs.tictactoe.R;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(i);

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

}
