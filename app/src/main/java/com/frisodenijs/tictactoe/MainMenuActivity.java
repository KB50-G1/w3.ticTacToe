package com.frisodenijs.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;

import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.HumanPlayer;
import com.frisodenijs.tictactoe.Game.Player;
import com.frisodenijs.tictactoe.Game.RandomPlayer;


public class MainMenuActivity extends ActionBarActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
         sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    private int selectFirstPlayer()
    {
        if(sharedPreferences.getBoolean("firstMoveX", true))
            return 0;
        else if(sharedPreferences.getBoolean("firstMoveO", false))
            return 1;
        else
            return (int) (Math.random() * 2);
    }

    private Player.Icon selectIcon(int playerNumber)
    {
        if(playerNumber == 0)
        {
            if(sharedPreferences.getBoolean("playerOneIconX", true))
                return Player.Icon.DRAW_X;
            return Player.Icon.DRAW_O;
        }

        if(sharedPreferences.getBoolean("playerOneIconX", true))
            return Player.Icon.DRAW_O;
        return Player.Icon.DRAW_X;


    }

    public void onClickOnePlayer(View view) {

        Game game;

        if(sharedPreferences.getBoolean("hardMode", false))
        {
            game = new Game(
                    new RandomPlayer(selectIcon(0)),
                    new RandomPlayer(selectIcon(1)),
                    this.selectFirstPlayer()
            );
        }
        else
        {
            game = new Game(
                    new HumanPlayer(selectIcon(0)),
                    new RandomPlayer(selectIcon(1)),
                    this.selectFirstPlayer()
            );
        }

        this.loadGameActivity(game);
    }

    public void onClickTwoPlayers(View view) {

        Game game = new Game(
                new HumanPlayer(selectIcon(0)),
                new HumanPlayer(selectIcon(1)),
                this.selectFirstPlayer()
        );

        this.loadGameActivity(game);
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
