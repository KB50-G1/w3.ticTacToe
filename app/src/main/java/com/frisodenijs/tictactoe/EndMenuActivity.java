package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.frisodenijs.tictactoe.Game.Game;


public class EndMenuActivity extends ActionBarActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);

        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("game");
        // Reset the board, in case the player wants to play again.
        game.resetBoard();

    }

    public void onClickPlayAgain(View view) {

        // TODO: get the game object from the intent, restart the board and redirect to game activity

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);
        Intent i = new Intent(this, GameActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void onClickBack(View view) {

        // TODO: we can also save the user settings, or give it the chance to continue playing with the same game instance
        Intent i = new Intent(EndMenuActivity.this, MainMenuActivity.class);
        startActivity(i);
    }
}
