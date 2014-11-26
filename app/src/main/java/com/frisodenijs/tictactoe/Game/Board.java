package com.frisodenijs.tictactoe.Game;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public class Board implements Serializable {

    private Player[][] board;

    public Board() {
        board = new Player[3][3];
    }


    public Player getPlayerAtPosition(int[] position) {
        return board[position[0]][position[1]];
    }

    public boolean setPlayerAtPosition(Player player, int[] position) {
        // TODO: 1: check valid position. 2: save reference of player there

        // Player move added correctly
        return true;

        // Invalid position, try again
        // return false;
    }

    /**
     *
     * @return
     */
    public boolean isFull() {
        // TODO: loop through all board, if finds empty (null) cell, return false

        return true;
    }


}
