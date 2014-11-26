package com.frisodenijs.tictactoe.Game;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Friso on 14/11/25.
 */
public class Game implements Serializable {

    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        this.board = new Board();
        this.players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        this.currentPlayer = players.get(0);
    }

    public Player[][] getBoard() {
        return board.getBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        // TODO: 1. check horizontal. 2: check vertical. 3: check diagonals

        // return winner;

        // No winner found
        return null;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean makeMove(int[] position)
    {
        if(board.setPlayerAtPosition(currentPlayer, position))
        {
            this.nextPlayer();
            return true;
        }
        return false;
    }

    // TODO: change to private after testing
    public void nextPlayer() {

        // Get actual player index
        int index = players.indexOf(currentPlayer);
        // Change index
        index = (index == 1) ? 0 : 1;
        // Set the other player as current
        setCurrentPlayer(players.get(index));

    }

    public String test() {
        return "HOLAAA";
    }


    public void resetBoard() {
        this.board = new Board();
        // Set turn to first player. (Always human)
        currentPlayer = players.get(0);
    }

}
