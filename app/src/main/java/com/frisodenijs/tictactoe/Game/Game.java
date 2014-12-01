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

    private int drawCount;

    private boolean finished;

    public Game(Player p1, Player p2, int firstPlayer) {
        this.board = new Board();
        this.players = new ArrayList<Player>();
        this.drawCount = 0;
        players.add(p1);
        players.add(p2);

        if (firstPlayer >= 0 && firstPlayer < 2) {
            this.currentPlayer = players.get(firstPlayer);
            alternateFirstPlayer = false;
        } else {
            this.currentPlayer = players.get(0);
            alternateFirstPlayer = true;
        }
        firstMovePlayer = currentPlayer;

        setButtonsVisibility(true);
    }

    public boolean getButtonsVisibility() {
        return buttonsVisibility;
    }

    public void setButtonsVisibility(boolean buttonsVisibility) {
        this.buttonsVisibility = buttonsVisibility;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
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

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setGameActivity(GameActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    /**
     * Check end of the game.
     */
    public void checkGameEnd() {

        Player winner = board.checkWinner();

        if (board.isFull() || winner != null) {
            // Game is finished
            setFinished(true);
            // Check winner
            if (winner != null)
                winner.incrementWinsCount();
            else
                drawCount++;

            gameActivity.goToFinishGame();
        } else {
            nextPlayer();
            notifyPlayerToMove();
            gameActivity.updateGUI();
        }
    }

    /**
     * @param position Player selected position (move)
     * @return true if moved, false if not
     */
    public boolean makeMove(int[] position) {
        if (board.setPlayerAtPosition(currentPlayer, position)) {

            gameActivity.changeButtonsVisibility(false);
            checkGameEnd();
            return true;
        }
        // Invalid position. Player not saved
        return false;
    }

    /**
     * Change currentPlayer to the next one.
     */
    private void nextPlayer() {

        // Get actual player index
        int index = players.indexOf(currentPlayer);
        // Change index
        index = (index == 1) ? 0 : 1;
        // Set the other player as current
        setCurrentPlayer(players.get(index));
    }

    /**
     * Notify player to move.
     * <p/>
     * Human: Unlock GUI buttons so he can play
     * Robot: Notify to make a move.
     */
    public void notifyPlayerToMove() {
        if (!(currentPlayer instanceof HumanPlayer))
            while (!board.isFull() && !makeMove(currentPlayer.makeAutoMove(this.board))) ;
        else
            gameActivity.changeButtonsVisibility(true);
    }

    /**
     * Creates a new board, to play a new game.
     * <p/>
     * If random player start setting is enabled, checks the one who started current board and switch it.
     */
    public void resetBoard() {
        board = new Board();
        setFinished(false);

        // Set current (first) player for next game.
        if (alternateFirstPlayer) {
            int index = players.indexOf(firstMovePlayer);
            index = (index == 1) ? 0 : 1;
            firstMovePlayer = players.get(index);
        }

        currentPlayer = firstMovePlayer;
        notifyPlayerToMove();
    }

}
