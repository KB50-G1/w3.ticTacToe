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
    private Player winner;

    public Game(Player p1, Player p2) {
        this.board = new Board();
        this.players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);
        this.currentPlayer = players.get(0);
    }

    public Player getWinner() {
        return winner;
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

    public Player checkWinner() {
        // TODO: 1. check horizontal. 2: check vertical. 3: check diagonals

        // return winner;
        if(board.checkHorizontal() != null)
            return winner = board.checkHorizontal();
        if(board.checkVertical() != null)
            return winner = board.checkVertical();
        if(board.checkDiagonal() != null)
            return winner = board.checkDiagonal();

        // No winner found
        return null;
    }

    public boolean checkGameEnd() {
        // TODO: check board full, check winner

        return board.isFull() || checkWinner() != null;

    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean makeMove(int[] position) {
        if (board.setPlayerAtPosition(currentPlayer, position)) {
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

        if (currentPlayer instanceof RandomPlayer) {
            while(!board.isFull() && !makeMove(((RandomPlayer) currentPlayer).makeAutoMove()));
        }
    }

    public void resetBoard() {
        this.board = new Board();
        // Set turn to first player. (Always human)
        currentPlayer = players.get(0);
    }

}
