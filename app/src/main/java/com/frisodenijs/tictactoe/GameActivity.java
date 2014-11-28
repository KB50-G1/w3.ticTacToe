package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.Player;


public class GameActivity extends ActionBarActivity {

    private Game game;
    private AnimationDrawable animationDrawable;

    private TextView playerInfo;
    //private Button[][] imageButton;
    ImageView[][] imageView = new ImageView[3][3];
    private ImageView[][] animationIV = new ImageView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerInfo = (TextView) findViewById(R.id.playerInfo);

        // TODO: think about this. would be nice to avoid 9 lines of repeated code.

        /*
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                imageButton[i][j] = (Button) findViewById(R.id.b00);
        */
        imageView[0][0] = (ImageView)findViewById(R.id.b00);
        imageView[0][1] = (ImageView)findViewById(R.id.b01);
        imageView[0][2] = (ImageView)findViewById(R.id.b02);
        imageView[1][0] = (ImageView)findViewById(R.id.b10);
        imageView[1][1] = (ImageView)findViewById(R.id.b11);
        imageView[1][2] = (ImageView)findViewById(R.id.b12);
        imageView[2][0] = (ImageView)findViewById(R.id.b20);
        imageView[2][1] = (ImageView)findViewById(R.id.b21);
        imageView[2][2] = (ImageView)findViewById(R.id.b22);
        animationIV[0][0] = (ImageView) findViewById(R.id.b00);
        animationIV[0][1] = (ImageView) findViewById(R.id.b01);
        animationIV[0][2] = (ImageView) findViewById(R.id.b02);
        animationIV[1][0] = (ImageView) findViewById(R.id.b10);
        animationIV[1][1] = (ImageView) findViewById(R.id.b11);
        animationIV[1][2] = (ImageView) findViewById(R.id.b12);
        animationIV[2][0] = (ImageView) findViewById(R.id.b20);
        animationIV[2][1] = (ImageView) findViewById(R.id.b21);
        animationIV[2][2] = (ImageView) findViewById(R.id.b22);

        /*imageButton[0][0] = (Button) findViewById(R.id.b00);
        imageButton[0][1] = (Button) findViewById(R.id.b01);
        imageButton[0][2] = (Button) findViewById(R.id.b02);
        imageButton[1][0] = (Button) findViewById(R.id.b10);
        imageButton[1][1] = (Button) findViewById(R.id.b11);
        imageButton[1][2] = (Button) findViewById(R.id.b12);
        imageButton[2][0] = (Button) findViewById(R.id.b20);
        imageButton[2][1] = (Button) findViewById(R.id.b21);
        imageButton[2][2] = (Button) findViewById(R.id.b22);*/

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
        // TODO: get board status and update all Button or imagButton in the activity

        final Player[][] board = game.getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != null) {
                    if(board[i][j].toString().equals("O"))
                        animationIV[i][j].setImageResource(R.drawable.o);
                    if(board[i][j].toString().equals("X"))
                        animationIV[i][j].setImageResource(R.drawable.x);
                        animationDrawable = (AnimationDrawable) animationIV[i][j].getDrawable();
                        animationDrawable.start();
                }
                else {
                    animationIV[i][j].setImageResource(R.drawable.clean);
                    animationDrawable = (AnimationDrawable) animationIV[i][j].getDrawable();
                    animationDrawable.start();
                }
            }
        }

        playerInfo.setText("Current Player: " + game.getCurrentPlayer().toString());

    }

    /*
     * onClick imageButton functionality
     */

    public void onClickField(View view) {

        // TODO: do not update view directly. 1: Update board. 2: Re-draw the imageButton
        ImageView buttonPressed = (ImageView) findViewById(view.getId());

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttonPressed.equals(imageView[i][j])) {

                    if (game.makeMove(new int[]{i, j})) {
                        // If move is valid
                        this.updateGUI();
                        if (game.getLastWinner() != null) {
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
     * onClick imageButton functionality
     */

    public void goToFinishGame() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);
        Intent i = new Intent(this, EndMenuActivity.class);
        i.putExtras(bundle);
        startActivity(i);
        // Finish the activity so the user can't get back to the game with the finished board.
        finish();
    }
}
