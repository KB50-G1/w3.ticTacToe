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

    private TextView[] playersNames;
    private TextView[] playersScores;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        playersNames = new TextView[]{
                (TextView) findViewById(R.id.playerOneName),
                (TextView) findViewById(R.id.playerTwoName)
        };

        playersScores = new TextView[]{
                (TextView) findViewById(R.id.playerOneScores),
                (TextView) findViewById(R.id.playerTwoScores)
        };

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
    protected void onPause() {
        super.onPause();
        // If we don't delete the reference to the activity, it will crash because it's not Serializable.
        game.setGameActivity(null);
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
                {
                    // Place the player text (X or O) in the button.
                    buttons[i][j].setText(board[i][j].toString());
                    // Change the color of the text.
                    buttons[i][j].setTextColor(board[i][j].getColor());
                }
                else
                    buttons[i][j].setText("");
            }

        // Update Players Information
        for (int i = 0; i < 2; i++) {
            playersNames[i].setEnabled(false);
            // playersNames[i].setBackgroundColor(getResources().getColor(R.color.white));
            if (game.getPlayer(i).equals(game.getCurrentPlayer())) {
                playersNames[i].setEnabled(true);
                // playersNames[i].setBackgroundColor(getResources().getColor(R.color.sea2));
            }

            playersNames[i].setText(game.getPlayer(i).toString() + "\n" + game.getPlayer(i).getName());
            playersNames[i].setTextColor(game.getPlayer(i).getColor());

            playersScores[i].setText(Integer.toString(game.getPlayer(i).getWinsCount()));
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

        finish();
    }

     /*
     * End game control
     */

    public void goToFinishGame() {

        // Disable all the buttons.
        changeButtonsVisibility(false);

        updateGUI();

        String dialogString;
        if(game.getLastWinner() != null)
            dialogString = "Player " + game.getLastWinner().toString() + " Wins!";
        else
            dialogString = getResources().getString(R.string.draw);

        DialogEndGame dialogFragment = DialogEndGame.newInstance(dialogString + "\nWant to play again?");
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    public void endGameDialogYes() {
        game.resetBoard();
        updateGUI();
    }

    public void endGameDialogNo() {
        // Why someone would press this button?
    }

}
