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
        if (board[position[0]][position[1]] == null) {
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
     * @return boolean
     */
    public boolean isFull() {
        // TODO: loop through all board, if finds empty (null) cell, return false
        for (Player[] aBoard : board) {
            for (Player anABoard : aBoard) {
                if (anABoard == null) {
                    return false;
                }
            }
        }
        // Board is full
        return true;
    }

    // Checks if there are 3 in a row horizontally.
    public Player checkHorizontal() {
        for (int y = 0; y < board.length; y++) {
            int counter = 0;
            for (int x = 0; x < board.length; x++) {

                if (board[y][x] != null) {

                    if (board[y][x].getMark().equals(Player.Icon.DRAW_X)) {
                        counter++;
                        if (counter == 3) {
                            return this.board[y][x];
                        }
                    } else if (board[y][x].getMark().equals(Player.Icon.DRAW_O)) {
                        counter--;
                        if (counter == -3) {
                            return this.board[y][x];
                        }
                    }
                }
            }
        }
        return null;
    }

    /*
    public Player checkHorizontal2() {

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
            {

            }


        return null;
    }
    */

    // Checks if there are 3 in a row vertically.
    public Player checkVertical() {
        for (int y = 0; y < board.length; y++) {
            int counter = 0;
            for (int x = 0; x < board.length; x++) {

                if (board[x][y] != null) {

                    if (board[x][y].getMark().equals(Player.Icon.DRAW_X)) {
                        counter++;
                        if (counter == 3) {
                            return board[x][y];
                        }
                    } else if (board[x][y].getMark().equals(Player.Icon.DRAW_O)) {
                        counter--;
                        if (counter == -3) {
                            return board[x][y];
                        }
                    }
                }
            }
        }
        return null;
    }

    // Checks if there are 3 in a row diagonally.
    public Player checkDiagonal() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {

            if (board[i][i] != null) {

                if (board[i][i].getMark().equals(Player.Icon.DRAW_X)) {
                    counter++;
                    if (counter == 3) {
                        return board[i][i];
                    }
                } else if (board[i][i].getMark().equals(Player.Icon.DRAW_O)) {
                    counter--;
                    if (counter == -3) {
                        return board[i][i];
                    }
                } else if (board[board.length - i - 1][i].getMark().equals(Player.Icon.DRAW_X)) {
                    counter++;
                    if (counter == 3) {
                        return board[i][i];
                    }
                } else if (board[board.length - i - 1][i].getMark().equals(Player.Icon.DRAW_O)) {
                    counter--;
                    if (counter == -3) {
                        return board[i][i];
                    }
                }
            }
        }
        return null;
    }


}
