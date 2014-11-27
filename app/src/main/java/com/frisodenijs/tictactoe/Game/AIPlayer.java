package com.frisodenijs.tictactoe.Game;

/**
 * Created by Friso on 14/11/25.
 */
public class AIPlayer extends Player {

    public AIPlayer(Icon mark) {
        super(mark);
    }

    @Override
    public int[] makeAutoMove() {

        // TODO: implement AI really difficult to understand methods.

        // TODO: this method needs to be able to read the board. How can we do this? Observer pattern?

        int posX = 0;
        int posY = 0;

        return new int[]{posX, posY};
    }
}
