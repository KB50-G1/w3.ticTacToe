package com.frisodenijs.tictactoe.Game;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public class Board implements Serializable {

    public static final int SIZE = 3;

    private Player[][] board;
    private Player winner;

    public Board() {
        board = new Player[Board.SIZE][Board.SIZE];
        winner = null;
    }

    public Player[][] getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    /**
     *
     * @param player
     * @param position
     * @return
     */
    public boolean setPlayerAtPosition(Player player, int[] position) {

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

        // Loop through all board, if finds empty (null) cell, return false
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

    /**
     *
     * @return Player or null
     */
    public Player checkWinner() {

        // 1. check horizontal. 2: check vertical. 3: check diagonals
        if (checkHorizontal() != null)
            winner = checkHorizontal();
        if (checkVertical() != null)
            winner = checkVertical();
        if (checkDiagonalLeftToRight() != null)
            winner = checkDiagonalLeftToRight();
        if (checkDiagonalRightToLeft() != null)
            winner = checkDiagonalRightToLeft();

        // Return Player if winner is found, null otherwise (default is null when board is created).
        return winner;
    }

    /**
     *
     * Warning: not the most efficient code in here!
     */
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
    public Player checkDiagonalLeftToRight() {
        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
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
                }
            }
        }
        return null;
    }

    public Player checkDiagonalRightToLeft() {

        if (board[0][2] != null) {
            if (board[1][1] != null) {
                if (board[2][0] != null) {
                    if (board[0][2].getMark().equals(Player.Icon.DRAW_X) &&
                            board[1][1].getMark().equals(Player.Icon.DRAW_X) &&
                            board[2][0].getMark().equals(Player.Icon.DRAW_X)) {
                        return board[2][0];
                    } else if (board[2][0].getMark().equals(Player.Icon.DRAW_O) &&
                            board[1][1].getMark().equals(Player.Icon.DRAW_O) &&
                            board[0][2].getMark().equals(Player.Icon.DRAW_O)) {
                        return board[2][0];
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
        return null;
    }


}
