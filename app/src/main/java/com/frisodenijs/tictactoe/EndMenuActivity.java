package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class EndMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);
    }

    public void onClickPlayAgain(View view) {

        // TODO: get the game object from the intent, restart the board and redirect to game activity

        Intent i = new Intent(EndMenuActivity.this, GameActivity.class);
        startActivity(i);
    }

    public void onClickBack(View view) {

        Intent i = new Intent(EndMenuActivity.this, MainMenuActivity.class);
        startActivity(i);
    }
}
