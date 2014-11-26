package com.frisodenijs.tictactoe.Game;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public abstract class Player implements Serializable {

    private Icon mark;

    public enum Icon {
        DRAW_X, DRAW_O
    }

    protected Player(Icon mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {

        switch (mark) {
            case DRAW_X:
                return "X";
            case DRAW_O:
                return "O";
            default:
                // Never gonna happen
                return "-";
        }
    }

}

