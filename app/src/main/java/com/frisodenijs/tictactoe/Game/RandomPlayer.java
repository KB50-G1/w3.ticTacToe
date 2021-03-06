package com.frisodenijs.tictactoe.Game;

import android.util.Log;
/**
 * Created by Friso on 14/11/25.
 */
public class RandomPlayer extends Player {

    public RandomPlayer(Icon mark) {
        super(mark);
    }

    public RandomPlayer(Icon mark, String name) {
        super(mark, name);
    }

    @Override
    public int[] makeAutoMove(Board board) {

        int randomX = (int) (Math.random() * board.getBoard().length);
        int randomY = (int) (Math.random() * board.getBoard().length);

        return new int[]{randomX, randomY};
    }


}
