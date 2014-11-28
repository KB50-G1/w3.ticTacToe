package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.frisodenijs.tictactoe.Game.Game;


public class EndMenuActivity extends ActionBarActivity {

    private Game game;

    TextView gameResult;
    TextView gameScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);

        // Get view references
        gameResult = (TextView) findViewById(R.id.result);
        gameScore = (TextView) findViewById(R.id.gameScore);

        // Get the intent containing the game object
        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("game");

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Print the winner player or draw string.
        if(game.getLastWinner() != null)
            gameResult.setText(game.getLastWinner().toString());
        else
            gameResult.setText(getResources().getString(R.string.draw));

        // Print total score. (3 - 2)
        gameScore.setText(
                Integer.toString(game.getPlayer(0).getWinsCount())
                + " - " +
                Integer.toString(game.getPlayer(1).getWinsCount())
        );

        // Reset the board, in case the player wants to play again.
        // game.resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.game = (Game) savedInstanceState.getSerializable("game");
    }

    public void onClickPlayAgain(View view) {

        // Get the game object from the intent, restart the board and redirect to game activity
        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);
        Intent i = new Intent(this, GameActivity.class);
        i.putExtras(bundle);
        startActivity(i);
        finish();
    }

    public void onClickBack(View view) {

        // Return to Main Menu Activity
        // TODO: we could also save the user settings, or give it the chance to continue playing with the same game instance
        Intent i = new Intent(EndMenuActivity.this, MainMenuActivity.class);
        startActivity(i);
        finish();
    }
}
