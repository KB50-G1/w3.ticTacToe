package com.frisodenijs.tictactoe;

/**
 * Created by Friso on 14/11/24.
 */
public final class TicTacToeAI {

    private TicTacToeAI instance;
    private boolean isHardMode;

    private TicTacToeAI(){}

    public TicTacToeAI getInstance() {
        if (instance == null) {
            instance = new TicTacToeAI();
        }

        return instance;
    }

    public void setHardMode(boolean isHardMode) {
        this.isHardMode = isHardMode;
    }

    public GameState move() {
        if (isHardMode) {
            return calculatedMove();
        } else {
            return randomMove();
        }
    }

    public GameState randomMove() {
        //TODO
        return null;
    }

    public GameState calculatedMove() {
        //TODO
        return null;
    }

    public GameState CalculateResult() {
        //TODO calculate the result of the game, ie. who won?
        return null;
    }
}
