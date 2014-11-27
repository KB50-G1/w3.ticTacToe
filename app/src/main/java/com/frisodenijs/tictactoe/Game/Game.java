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

    public Player getLastWinner() {
        return board.getWinner();
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

    public Player getPlayer(int i)
    {
        return players.get(i);
    }


    private boolean checkGameEnd() {

        Player winner = board.checkWinner();
        if (winner != null)
            winner.incrementWinsCount();

        return board.isFull() || winner != null;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     *
     * @param position Player selected position (move)
     * @return true if moved, false if not
     */
    public boolean makeMove(int[] position) {
        if (board.setPlayerAtPosition(currentPlayer, position)) {
            this.nextPlayer();
            return true;
        }
        return false;
    }

    // TODO: this should be private, not called from the activity.
    public void nextPlayer() {

        // Get actual player index
        int index = players.indexOf(currentPlayer);
        // Change index
        index = (index == 1) ? 0 : 1;
        // Set the other player as current
        setCurrentPlayer(players.get(index));

        if(!checkGameEnd())
            notifyPlayerToMove();

    }

    public void notifyPlayerToMove()
    {
        // If Single Player, ask for computer move. ( Random, AI, or another player class that implements makeAutoMove() ).
        if (!(currentPlayer instanceof HumanPlayer)) {
            while (!board.isFull() && !makeMove(currentPlayer.makeAutoMove(this.board)));
        }

    }

    public void resetBoard() {
        this.board = new Board();
        // Set turn to first player. (Always human)
        currentPlayer = players.get(0);

    }

}
