package com.frisodenijs.tictactoe.Game;

/**
 * Created by Friso on 14/11/25.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(Icon mark) {
        super(mark);
    }

    public HumanPlayer(Icon mark, String name) {
        super(mark, name);
    }

    @Override
    public int[] makeAutoMove() {
        return new int[0];
    }
}
