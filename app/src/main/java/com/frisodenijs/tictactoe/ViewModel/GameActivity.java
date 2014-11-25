package com.frisodenijs.tictactoe.ViewModel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.frisodenijs.tictactoe.Model.Game;
import com.frisodenijs.tictactoe.R;


public class GameActivity extends ActionBarActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState == null)
        {
            Toast.makeText(this, "First time here", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            game = (Game) intent.getSerializableExtra("game");
        }

        Log.v("LOG", "Creating");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button restart = (Button) findViewById(R.id.restartButton);
        restart.setText(Integer.toString(game.getTest()));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle bundle = new Bundle();
        bundle.putSerializable("game", "game");

        outState.putBundle("game", bundle);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        this.game = (Game) savedInstanceState.getSerializable("game");
        Toast.makeText(this, "Restoring...", Toast.LENGTH_SHORT).show();
        Log.v("LOG", "Restoring");

    }

    public void onClickField(View view) {
        Intent i = new Intent(GameActivity.this, EndMenuActivity.class);
        startActivity(i);
    }

    public void onClickRestart(View view) {
    }
}
