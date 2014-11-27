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

    private TextView playerInfo;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttons = new Button[3][3];
        playerInfo = (TextView) findViewById(R.id.playerInfo);

        // TODO: think about this. would be nice to avoid 9 lines of repeated code.
        /*
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                buttons[i][j] = (Button) findViewById(R.id.b00);
        */

        buttons[0][0] = (Button) findViewById(R.id.b00);
        buttons[0][1] = (Button) findViewById(R.id.b01);
        buttons[0][2] = (Button) findViewById(R.id.b02);
        buttons[1][0] = (Button) findViewById(R.id.b10);
        buttons[1][1] = (Button) findViewById(R.id.b11);
        buttons[1][2] = (Button) findViewById(R.id.b12);
        buttons[2][0] = (Button) findViewById(R.id.b20);
        buttons[2][1] = (Button) findViewById(R.id.b21);
        buttons[2][2] = (Button) findViewById(R.id.b22);

        // First time Activity is created, take data from the intent.
        if (savedInstanceState == null) {
            Log.d("GAME", "First time here!");
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
            game.notifyPlayerToMove();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("GAME", "Resuming...");
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

    private void updateGUI() {
        // TODO: get board status and update all buttons or imagebuttons in the activity

        Player[][] board = game.getBoard();

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null)
                    buttons[i][j].setText(board[i][j].toString());
                else
                    buttons[i][j].setText("");
            }

        playerInfo.setText("Current Player: " + game.getCurrentPlayer().toString());

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

                    if (game.makeMove(new int[]{i, j})) {
                        // If move is valid
                        this.updateGUI();
                        if (game.checkGameEnd()) {
                            goToFinishGame();
                        }
                    } else {
                        // If not, warn user of invalid move
                        Toast.makeText(this, this.getResources().getString(R.string.invalid_move), Toast.LENGTH_SHORT).show();
                    }

                }
    }

    public void onClickRestart(View view) {
        game.resetBoard();
        updateGUI();
    }

    public void onClickBack(View view) {
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }

     /*
     * onClick Buttons functionality
     */

    public void goToFinishGame() {

        changeButtonsState(true);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
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
    }

    private void changeButtonsState(boolean boolState) {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
            {
                buttons[i][j].setEnabled(boolState);
            }
    }
}
