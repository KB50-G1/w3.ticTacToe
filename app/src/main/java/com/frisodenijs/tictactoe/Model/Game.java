package com.frisodenijs.tictactoe.Model;

import java.util.ArrayList;

/**
 * Created by Friso on 14/11/25.
 */
public class Game {
    private static Game instance;
    private Board board;
    private ArrayList<Player> players;

    private Game() {}

    public static Game GetInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    public void Restart() {

    }
}
