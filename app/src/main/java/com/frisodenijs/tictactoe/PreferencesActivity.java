package com.frisodenijs.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;


public class PreferencesActivity extends ActionBarActivity {

    SharedPreferences sharedPreferences;

    private EditText playerOneName;
    private RadioButton playerOneIconX;
    private RadioButton playerOneIconO;

    private EditText playerTwoName;
    private RadioButton firstMoveX;
    private RadioButton firstMoveO;
    private RadioButton firstMoveXO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        playerOneName = (EditText) findViewById(R.id.setPlayerOneName);
        playerTwoName = (EditText) findViewById(R.id.setPlayerTwoName);

        playerOneIconX = (RadioButton) findViewById(R.id.playerOneIconX);
        playerOneIconO = (RadioButton) findViewById(R.id.playerOneIconO);

        firstMoveX = (RadioButton) findViewById(R.id.firstMoveX);
        firstMoveO = (RadioButton) findViewById(R.id.firstMoveO);
        firstMoveXO = (RadioButton) findViewById(R.id.firstMoveXO);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        playerOneName.setText(sharedPreferences.getString("playerOneName", "Player 1"));
        playerTwoName.setText(sharedPreferences.getString("playerTwoName", "Player 2"));

        playerOneIconX.setChecked(sharedPreferences.getBoolean("playerOneIconX", true));
        playerOneIconO.setChecked(sharedPreferences.getBoolean("playerOneIconO", false));

        firstMoveX.setChecked(sharedPreferences.getBoolean("firstMoveX", true));
        firstMoveO.setChecked(sharedPreferences.getBoolean("firstMoveO", false));
        firstMoveXO.setChecked(sharedPreferences.getBoolean("firstMoveXO", false));

    }

    public void savePreferences(View view) {

        // Save things in Shared Preferences and go back to main menu
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("playerOneName", playerOneName.getText().toString());
        editor.putString("playerTwoName", playerTwoName.getText().toString());

        editor.putBoolean("playerOneIconX", playerOneIconX.isChecked());
        editor.putBoolean("playerOneIconO", playerOneIconO.isChecked());

        editor.putBoolean("firstMoveX", firstMoveX.isChecked());
        editor.putBoolean("firstMoveO", firstMoveO.isChecked());
        editor.putBoolean("firstMoveXO", firstMoveXO.isChecked());

        editor.commit();
        finish();
    }
}
