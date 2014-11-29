package com.frisodenijs.tictactoe.Game;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Friso on 14/11/25.
 */
public abstract class Player implements Serializable {

    private Icon mark;
    private String name = "Player";
    private int color;
    private int winsCount;

    public enum Icon {
        DRAW_X, DRAW_O
    }

    protected Player(Icon mark) {
        this.mark = mark;
        this.winsCount = 0;
        setColor();
    }

    protected Player(Icon mark, String name) {
        this.mark = mark;
        this.name = name;
        this.winsCount = 0;
        setColor();
    }

    private void setColor() {
        if(this.mark == Icon.DRAW_X)
            color = Color.RED;
        else
            color = Color.BLUE;
    }

    public int getColor() {
        return color;
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
    public abstract int[] makeAutoMove(Board board);

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

