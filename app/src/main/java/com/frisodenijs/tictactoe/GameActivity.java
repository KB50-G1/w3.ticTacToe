package com.frisodenijs.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.Player;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends ActionBarActivity {

    private Game game;

    private TextView[] playerInfo;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerInfo = new TextView[]{
                (TextView) findViewById(R.id.playerInfo),
                (TextView) findViewById(R.id.playerInfo2)
        };

        playerInfo[1] = (TextView) findViewById(R.id.playerInfo2);

        buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.b00);
        buttons[0][1] = (Button) findViewById(R.id.b01);
        buttons[0][2] = (Button) findViewById(R.id.b02);
        buttons[1][0] = (Button) findViewById(R.id.b10);
        buttons[1][1] = (Button) findViewById(R.id.b11);
        buttons[1][2] = (Button) findViewById(R.id.b12);
        buttons[2][0] = (Button) findViewById(R.id.b20);
        buttons[2][1] = (Button) findViewById(R.id.b21);
        buttons[2][2] = (Button) findViewById(R.id.b22);

        // First time Activity is created, take data (game instance) from the intent.
        if (savedInstanceState == null) {
            Log.d("GAME", "First time here!");
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("GAME", "Resuming...");
        game.setGameActivity(this);
        game.notifyPlayerToMove();
        updateGUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("GAME", "Restoring game...");
        this.game = (Game) savedInstanceState.getSerializable("game");
    }

    /*
     * Board Management
     */

    public void updateGUI() {

        Player[][] board = game.getBoard();

        // Update Board Buttons
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null)
                    buttons[i][j].setText(board[i][j].toString());
                else
                    buttons[i][j].setText("");
            }

        // Update Players Information
        for (int i = 0; i < 2; i++) {
            playerInfo[i].setEnabled(false);
            if (game.getPlayer(i).equals(game.getCurrentPlayer())) {
                playerInfo[i].setEnabled(true);
            }

            playerInfo[i].setText(game.getPlayer(i).toString() + ": " + game.getPlayer(i).getWinsCount());
        }

    }

    public void changeButtonsState(boolean boolState) {

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(boolState);
            }
    }

    /*
     * onClick Buttons functionality
     */

    public void onClickField(View view) {

        // TODO: do not update view directly. 1: Update board. 2: Re-draw the buttons
        Button buttonPressed = (Button) findViewById(view.getId());

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttonPressed.equals(buttons[i][j])) {

                    if (!game.makeMove(new int[]{i, j}))
                        Toast.makeText(this, this.getResources().getString(R.string.invalid_move), Toast.LENGTH_SHORT).show();

                }
    }

    public void onClickRestart(View view) {
        game.resetBoard();
        this.updateGUI();
    }

    public void onClickBack(View view) {

        // If we don't delete the reference to the activity, it will crash because it's not Serializable.
        game.setGameActivity(null);

        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }

     /*
     * End game control
     */

    @Override
    protected void onPause() {
        super.onPause();

        // If we don't delete the reference to the activity, it will crash because it's not Serializable.
        // onPause is called after the new activity is created. Not valid to use this code here.
        // game.setGameActivity(null);
    }

    public void goToFinishGame() {

        // Disable all the buttons.
        changeButtonsState(false);

        updateGUI();

        DialogEndGame dialogFragment = DialogEndGame.newInstance(
                "Player " + game.getLastWinner().toString() + " Wins!\n" +
                "Want to play again?"
        );
        dialogFragment.show(getFragmentManager(), "dialog");

        /*
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // If we don't delete the reference to the activity, it will crash because it's not Serializable.

                game.setGameActivity(null);

                Bundle bundle = new Bundle();
                bundle.putSerializable("game", game);
                Intent i = new Intent(GameActivity.this, EndMenuActivity.class);
                i.putExtras(bundle);

                startActivity(i);
                // Finish the activity so the user can't get back to the game with the finished board.
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000); // 3000ms = 3s
        */
    }

    public void endGameDialogYes() {
        game.resetBoard();
        updateGUI();
    }

    public void endGameDialogNo() {
        // Why someone would press this button?
    }






}
