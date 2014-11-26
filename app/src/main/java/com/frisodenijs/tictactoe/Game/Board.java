package com.frisodenijs.tictactoe.Game;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public class Board implements Serializable {

    private Player[][] board;

    public Board() {
        board = new Player[3][3];
    }

    public Player[][] getBoard() {
        return board;
    }

    public Player getPlayerAtPosition(int[] position) {
        return board[position[0]][position[1]];
    }

    public boolean setPlayerAtPosition(Player player, int[] position) {

        // TODO: 1: check valid position. 2: save reference of player there
        if(board[position[0]][position[1]] == null) {
            this.board[position[0]][position[1]] = player;
            Log.d("BOARD", "Player: " + player.toString() + " added to " + Integer.toString(position[0]) + ", " + Integer.toString(position[1]));
            // Player move added correctly
            return true;
        }
        Log.d("BOARD", "Invalid move!");
        // Invalid position, try again
        return false;
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
