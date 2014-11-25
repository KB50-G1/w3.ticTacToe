package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.frisodenijs.tictactoe.Model.Game;
import com.frisodenijs.tictactoe.R;


public class GameActivity extends ActionBarActivity {

    private Game game;

    // TODO: best way to store reference to all game buttons? i refuse to findById all 9 buttons lol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // First time Activity is created, take data from the intent.
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: fill in buttons with the board info

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", "game");

        outState.putBundle("game", bundle);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.game = (Game) savedInstanceState.getSerializable("game");

    }

    public void onClickField(View view) {
        Intent i = new Intent(GameActivity.this, EndMenuActivity.class);
        startActivity(i);
    }

    public void onClickRestart(View view) {

        // TODO: Call restart method on board class & clear buttons

    }
}
