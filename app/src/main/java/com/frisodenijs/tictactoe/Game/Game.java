package com.frisodenijs.tictactoe.Game;


import com.frisodenijs.tictactoe.GameActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Friso on 14/11/25.
 */
public class Game implements Serializable {

    private Board board;

    private ArrayList<Player> players;
    private Player currentPlayer;

    private boolean buttonsVisibility;

    private Player firstMovePlayer;
    private boolean alternateFirstPlayer;

    private GameActivity gameActivity;

    public Game(Player p1, Player p2, int firstPlayer) {
        this.board = new Board();
        this.players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);

        if(firstPlayer < 2) {
            this.currentPlayer = players.get(firstPlayer);
            alternateFirstPlayer = false;
        }
        else
        {
            this.currentPlayer = players.get(0);
            alternateFirstPlayer = true;
        }
        firstMovePlayer = currentPlayer;

        this.setButtonsVisibility(true);
    }

    public boolean getButtonsVisibility() {
        return buttonsVisibility;
    }

    public void setButtonsVisibility(boolean buttonsVisibility) {
        this.buttonsVisibility = buttonsVisibility;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
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

    public Player getPlayer(int i) {
        return players.get(i);
    }


    public boolean checkGameEnd() {

        Player winner = board.checkWinner();
        if (winner != null)
            winner.incrementWinsCount();

        return board.isFull() || winner != null;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @param position Player selected position (move)
     * @return true if moved, false if not
     */
    public boolean makeMove(int[] position) {
        if (board.setPlayerAtPosition(currentPlayer, position)) {

            gameActivity.changeButtonsVisibility(false);

            gameActivity.updateGUI();

            if (!checkGameEnd()) {
                this.nextPlayer();
                notifyPlayerToMove();
                gameActivity.updateGUI();
            } else
            {
                gameActivity.goToFinishGame();
            }


            return true;
        }

        // Invalid position. Player not saved
        return false;
    }

    // TODO: this should be private, not called from the activity.
    private void nextPlayer() {

        // Get actual player index
        int index = players.indexOf(currentPlayer);
        // Change index
        index = (index == 1) ? 0 : 1;
        // Set the other player as current
        setCurrentPlayer(players.get(index));
    }

    public void notifyPlayerToMove() {
        // If Single Player, ask for computer move. ( Random, AI, or another player class that implements makeAutoMove() ).
        if (!(currentPlayer instanceof HumanPlayer)) {
            while (!board.isFull() && !makeMove(currentPlayer.makeAutoMove(this.board))) ;
        } else {
            if(getLastWinner() == null)
                gameActivity.changeButtonsVisibility(true);
        }
    }

    public void resetBoard() {
        this.board = new Board();
        // Set current (first) player for next game.

        if(alternateFirstPlayer)
        {
            int index = players.indexOf(firstMovePlayer);
            index = (index == 1) ? 0 : 1;
            firstMovePlayer = players.get(index);
        }

        currentPlayer = firstMovePlayer;

        this.notifyPlayerToMove();
    }

}
