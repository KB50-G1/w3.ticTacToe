package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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
    private TextView[] playersIcons;
    private TextView[] playersNames;
    private TextView[] playersScores;
    private TextView drawScore;
    private Button[][] buttons;

    Typeface customFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        customFont = Typeface.createFromAsset(getAssets(), "fonts/HaloHandletter.otf");

        playersNames = new TextView[]{
                (TextView) findViewById(R.id.playerOneName),
                (TextView) findViewById(R.id.playerTwoName)
        };

        playersIcons = new TextView[]{
                (TextView) findViewById(R.id.playerOneIcon),
                (TextView) findViewById(R.id.playerTwoIcon)
        };

        playersScores = new TextView[]{
                (TextView) findViewById(R.id.playerOneScores),
                (TextView) findViewById(R.id.playerTwoScores)
        };

        drawScore = (TextView) findViewById(R.id.drawScore);

        buttons = new Button[game.getBoard().length][game.getBoard().length];
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
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.setGameActivity(this);

        if( ! game.isFinished())
            game.notifyPlayerToMove();
        changeButtonsVisibility(game.getButtonsVisibility());
        updateGUI();
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
        this.game = (Game) savedInstanceState.getSerializable("game");
    }

    /*
     * Board Management
     */

    public void updateGUI() {

        Player[][] board = game.getBoard();

        // Update Board Buttons
        for (int i = 0; i < game.getBoard().length; i++)
            for (int j = 0; j < game.getBoard().length; j++) {
                if (board[i][j] != null) {
                    // Place the player text (X or O) in the button.
                    buttons[i][j].setText(board[i][j].toString());
                    // Change the color of the text.
                    buttons[i][j].setTextColor(board[i][j].getColor());
                    buttons[i][j].setTypeface(customFont);
                } else
                    buttons[i][j].setText("");
            }

        // Update Players Information
        for (int i = 0; i < game.getPlayers().size(); i++) {
            playersIcons[i].setVisibility(View.INVISIBLE);
            playersNames[i].setTypeface(null, Typeface.NORMAL);
            if (game.getPlayer(i).equals(game.getCurrentPlayer())) {
                playersIcons[i].setVisibility(View.VISIBLE);
                playersNames[i].setTypeface(null, Typeface.BOLD);
            }

            // Update players icon
            playersIcons[i].setText(game.getPlayer(i).toString());
            // Update players icon color
            playersIcons[i].setTextColor(game.getPlayer(i).getColor());

            // Update players name
            playersNames[i].setText(game.getPlayer(i).getName());
            // Update players name color
            playersNames[i].setTextColor(game.getPlayer(i).getColor());

            // Update player win count.
            playersScores[i].setText(Integer.toString(game.getPlayer(i).getWinsCount()));
        }

        // Update Draw Score Count
        drawScore.setText(Integer.toString(game.getDrawCount()));

        // Update restart button text
        Button restartButton = (Button) findViewById(R.id.restartButton);

        // Update Restart / Play Again button text.
        if (game.getLastWinner() != null)
        {
            restartButton.setText(getResources().getString(R.string.play_again));
            restartButton.setBackgroundColor(getResources().getColor(R.color.sea1));
        }
        else
        {
            restartButton.setText(getResources().getString(R.string.restart));
            restartButton.setBackgroundColor(getResources().getColor(R.color.white));
        }

    }

    public void changeButtonsVisibility(boolean boolState) {

        // Save the buttons state, to be able to recover it.
        game.setButtonsVisibility(boolState);

        for (int i = 0; i < game.getBoard().length; i++)
            for (int j = 0; j < game.getBoard().length; j++) {
                buttons[i][j].setEnabled(boolState);
            }
    }

    /*
     * onClick Buttons functionality
     */

    public void onClickBoardButton(View view) {

        Button buttonPressed = (Button) findViewById(view.getId());

        for (int i = 0; i < game.getBoard().length; i++)
            for (int j = 0; j < game.getBoard().length; j++)
                if (buttonPressed.equals(buttons[i][j]))
                    if (!game.makeMove(new int[]{i, j}))
                        Toast.makeText(this, this.getResources().getString(R.string.invalid_move), Toast.LENGTH_SHORT).show();
    }

    public void onClickRestart(View view) {
        game.resetBoard();
        updateGUI();
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

        // This will update scores and restart button.
        updateGUI();

        // If dialogs are supported, show it.
        if (android.os.Build.VERSION.SDK_INT >= 11) {

            String dialogString;
            if (game.getLastWinner() != null)
                dialogString = game.getLastWinner().getName() + " " + getResources().getString(R.string.wins);
            else
                dialogString = getResources().getString(R.string.its_a_draw);

            DialogEndGame dialogFragment = DialogEndGame.newInstance(dialogString);
            dialogFragment.show(getFragmentManager(), "endGameDialog");
        }
    }

    public void endGameDialogYes() {
        game.resetBoard();
        updateGUI();
    }

    public void endGameDialogNo() {
        // Why someone would press this button?
    }

}
