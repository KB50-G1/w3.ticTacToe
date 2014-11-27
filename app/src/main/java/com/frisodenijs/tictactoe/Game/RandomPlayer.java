package com.frisodenijs.tictactoe.Game;

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
    public int[] makeAutoMove() {

            int randomX = (int) (Math.random() * 3);
            int randomY = (int) (Math.random() * 3);

            return new int[]{randomX, randomY};
    }

    @Override
    public int[] makeAutoMove(Board board) {
        return new int[0];
    }
}
