package com.frisodenijs.tictactoe.Game;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public abstract class Player implements Serializable {

    private Icon mark;
    private String name;
    private int winsCount;

    public enum Icon {
        DRAW_X, DRAW_O
    }

    protected Player(Icon mark) {
        this.mark = mark;
        this.winsCount = 0;
    }

    public Icon getMark() {
        return mark;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public void incrementWinsCount() {
        this.winsCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int[] makeAutoMove();

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

