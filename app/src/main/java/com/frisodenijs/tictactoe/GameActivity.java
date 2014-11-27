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
import com.frisodenijs.tictactoe.Game.Player;

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
        changeButtonsVisibility(game.getButtonsVisibility());
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

        // Update restart button text
        Button restartButton = (Button) findViewById(R.id.restartButton);

        if(game.getLastWinner() != null)
            restartButton.setText(getResources().getString(R.string.play_again));
        else
            restartButton.setText(getResources().getString(R.string.restart));

    }

    public void changeButtonsVisibility(boolean boolState) {

        // Save the buttons state, to be able to recover it.
        game.setButtonsVisibility(boolState);
        Log.d("BUTTON", "Changed visibility to: " + Boolean.toString(boolState));

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

    public void goToFinishGame() {

        // Disable all the buttons.
        changeButtonsVisibility(false);

        updateGUI();

        final DialogEndGame dialogFragment = DialogEndGame.newInstance(
                "Player " + game.getLastWinner().toString() + " Wins!\n" +
                "Want to play again?"
        );

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 500);

    }

    public void endGameDialogYes() {
        game.resetBoard();
        updateGUI();
    }

    public void endGameDialogNo() {
        // Why someone would press this button?
    }






}
