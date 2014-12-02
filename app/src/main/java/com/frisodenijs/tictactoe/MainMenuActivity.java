package com.frisodenijs.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.HumanPlayer;
import com.frisodenijs.tictactoe.Game.Player;
import com.frisodenijs.tictactoe.Game.RandomPlayer;
import com.frisodenijs.tictactoe.Game.AIPlayer;

public class MainMenuActivity extends ActionBarActivity {

    SharedPreferences sharedPreferences;

    Game game;

    String playerOneName;
    String playerTwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerOneName = sharedPreferences.getString("playerOneName", "Player 1");
        playerTwoName = sharedPreferences.getString("playerTwoName", "Player 2");
    }

    public void onClickOnePlayer(View view) {

        if (sharedPreferences.getBoolean("hardMode", false)) {
            game = new Game(
                    new RandomPlayer(selectIcon(0), playerOneName),
                    new RandomPlayer(selectIcon(1), playerTwoName),
                    selectFirstPlayer()
            );
        } else {
            game = new Game(
                    new HumanPlayer(selectIcon(0), playerOneName),
                    new AIPlayer(selectIcon(1), playerTwoName),
                    selectFirstPlayer()
            );
        }

        this.loadGameActivity(game);
    }

    public void onClickTwoPlayers(View view) {

        game = new Game(
                new HumanPlayer(selectIcon(0), playerOneName),
                new HumanPlayer(selectIcon(1), playerTwoName),
                selectFirstPlayer()
        );

        loadGameActivity(game);
    }

    private int selectFirstPlayer() {
        if (sharedPreferences.getBoolean("firstMoveX", true))
            return 0;
        else if (sharedPreferences.getBoolean("firstMoveO", false))
            return 1;

        return 2;
    }

    private Player.Icon selectIcon(int playerNumber) {
        if (playerNumber == 0) {
            if (sharedPreferences.getBoolean("playerOneIconX", true))
                return Player.Icon.DRAW_X;
            return Player.Icon.DRAW_O;
        }

        if (sharedPreferences.getBoolean("playerOneIconX", true))
            return Player.Icon.DRAW_O;
        return Player.Icon.DRAW_X;

    }

    private void loadGameActivity(Game game) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);

        Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void goToSettings(View view) {
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }
}
