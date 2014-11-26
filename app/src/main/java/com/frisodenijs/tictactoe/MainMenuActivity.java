package com.frisodenijs.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.frisodenijs.tictactoe.Game.AIPlayer;
import com.frisodenijs.tictactoe.Game.Game;
import com.frisodenijs.tictactoe.Game.HumanPlayer;
import com.frisodenijs.tictactoe.Game.Player;


public class MainMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickOnePlayer(View view) {

        Game game = new Game(
                new HumanPlayer(Player.Icon.DRAW_X),
                new HumanPlayer(Player.Icon.DRAW_O)
        );

        this.loadGameActivity(game);
    }

    public void onClickTwoPlayers(View view) {

        Game game = new Game(
                new HumanPlayer(Player.Icon.DRAW_X),
                new AIPlayer(Player.Icon.DRAW_O)
        );

        this.loadGameActivity(game);
    }

    private void loadGameActivity(Game game) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);

        Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(i);
    }

}
