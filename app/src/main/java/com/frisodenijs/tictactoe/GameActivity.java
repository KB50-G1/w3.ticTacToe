package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frisodenijs.tictactoe.Game.Game;


public class GameActivity extends ActionBarActivity {

    private Game game;

    // TODO: best way to store reference to all game buttons? i refuse to findById all 9 buttons lol

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // First time Activity is created, take data from the intent.
        if (savedInstanceState == null) {
            Toast.makeText(this, "First time here!", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resuming...", Toast.LENGTH_SHORT).show();

        // TODO: fill in buttons with the board info

        // TODO: update user information. P1 or P2 turn, etc...


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
        Toast.makeText(this, "Restoring game!", Toast.LENGTH_SHORT).show();

        this.game = (Game) savedInstanceState.getSerializable("game");

    }


    /*
     * Board Management
     */

    private void updateBoardButtons() {
        // TODO: get board status and update all buttons or imagebuttons in the activity
    }


    /*
     * onClick Buttons functionality
     */

    public void onClickField(View view) {

        // TODO: do not update view directly. 1: Update board. 2: Re-draw the buttons
        Button buttonPressed = (Button) findViewById(view.getId());

        // TODO: methods of game class are not working in this activity!
        //buttonPressed.setText(game.getCurrentPlayer().toString());
        Log.d("TEST", game.test());



    }

    public void onClickRestart(View view) {

        // TODO: really need a thread? it's not working without it. (nor with it)
        //game.resetBoard();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TEST", "Thread is working!");
                //game.resetBoard();
            }
        }).start();

    }

    public void onClickBack(View view) {
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }
}
