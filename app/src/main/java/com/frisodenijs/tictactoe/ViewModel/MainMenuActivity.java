package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.frisodenijs.tictactoe.R;


public class MainMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickOnePlayer(View view) {
        Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(i);
    }

    public void onClickTwoPlayers(View view) {
        Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(i);
    }

}
