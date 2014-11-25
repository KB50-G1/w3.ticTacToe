package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.frisodenijs.tictactoe.Model.Game;
import com.frisodenijs.tictactoe.R;


public class GameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    public void onClickField(View view) {
        Intent i = new Intent(GameActivity.this, EndMenuActivity.class);
        startActivity(i);
    }

    public void onClickRestart(View view) {
        Game.GetInstance().Restart();
    }
}
