package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.frisodenijs.tictactoe.Model.Board;
import com.frisodenijs.tictactoe.Model.EasyModeBot;
import com.frisodenijs.tictactoe.Model.Game;
import com.frisodenijs.tictactoe.Model.HumanPlayer;
import com.frisodenijs.tictactoe.Model.Player;
import com.frisodenijs.tictactoe.R;


public class MainMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void onClickOnePlayer(View view) {

        Game game = new Game(new HumanPlayer(), new HumanPlayer());
        this.loadGameActivity(game);
    }

    public void onClickTwoPlayers(View view) {

        Game game = new Game(new HumanPlayer(), new EasyModeBot());
        this.loadGameActivity(game);
    }

    private void loadGameActivity(Game game)
    {

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", game);

        Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
        startActivity(i);
    }

}
