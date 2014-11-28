package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;

import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.HumanPlayer;
import com.frisodenijs.tictactoe.Game.Player;
import com.frisodenijs.tictactoe.Game.RandomPlayer;


public class MainMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // TODO: give the change to change player name (both players), and draw icon to the first one.

    public void onClickOnePlayer(View view) {

        Game game;
        CheckBox hardMode = (CheckBox) findViewById(R.id.hardModeCheckBox);

        if(hardMode.isChecked())
        {
            game = new Game(
                    new RandomPlayer(Player.Icon.DRAW_X),
                    new RandomPlayer(Player.Icon.DRAW_O)
            );
        }
        else
        {
            game = new Game(
                    new RandomPlayer(Player.Icon.DRAW_X),
                    new HumanPlayer(Player.Icon.DRAW_O)
            );
        }

        this.loadGameActivity(game);
    }

    public void onClickTwoPlayers(View view) {

        Game game = new Game(
                new HumanPlayer(Player.Icon.DRAW_X),
                new HumanPlayer(Player.Icon.DRAW_O)
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
