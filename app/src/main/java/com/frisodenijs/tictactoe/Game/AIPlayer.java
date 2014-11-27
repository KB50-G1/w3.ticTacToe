package com.frisodenijs.tictactoe.Game;

/**
 * Created by Friso on 14/11/25.
 */
public class AIPlayer extends Player {

    public AIPlayer(Icon mark) {
        super(mark);
    }

    public AIPlayer(Icon mark, String name) {
        super(mark, name);
    }

    @Override
    public int[] makeAutoMove() {
        return new int[0];
    }

    @Override
    public int[] makeAutoMove(Board board) {

        // TODO: implement AI really difficult to understand methods.

        // TODO: this method needs to be able to read the board. How can we do this? Observer pattern?

        if (winningMove(board).length == 2) {
            return winningMove(board);
        } else if (forkingMove(board).length == 2) {
            return forkingMove(board);
        } else if (blockForkingMove(board).length == 2) {
            return blockForkingMove(board);
        } else if (centerMove(board).length == 2) {
            return centerMove(board);
        } else if (opposingCornerMove(board).length == 2) {
            return opposingCornerMove(board);
        } else if (emptyCornerMove(board).length == 2) {
            return emptyCornerMove(board);
        } else if (emptySideMove(board).length == 2) {
            return emptySideMove(board);
        } else {
            return null;
        }
    }

    private int[] winningMove(Board board){
        if (checkHorizontalWinning(board).length == 2) {
            return checkHorizontalWinning(board);
        } else if (checkVerticalWinning(board).length == 2) {
            return checkVerticalWinning(board);
        } else if (checkDiagonalWinning(board).length == 2) {
            return checkDiagonalWinning(board);
        } else {
            return null;
        }
    }

    //TODO
    private int[] forkingMove(Board board) {
        return null;
    }

    //TODO
    private int[] blockForkingMove(Board board) {
        return null;
    }

    private int[] centerMove(Board board) {
        if (board.getBoard()[1][1] != null) {
            return new int[] {1,1};
        } else {
            return null;
        }
    }

    private int[] opposingCornerMove(Board board) {
        if ((!board.getBoard()[0][0].getMark().equals(getMark())) && board.getBoard()[2][2] != null) {
            return new int[] {2,2};
        } else if ((!board.getBoard()[0][2].getMark().equals(getMark())) && board.getBoard()[2][0] != null) {
            return new int[] {2,0};
        } else if ((!board.getBoard()[2][0].getMark().equals(getMark())) && board.getBoard()[0][2] != null) {
            return new int[] {0,2};
        }else if ((!board.getBoard()[2][2].getMark().equals(getMark())) && board.getBoard()[0][0] != null) {
            return new int[] {0,0};
        } else {
            return null;
        }
    }

    private int[] emptyCornerMove(Board board) {
        if (board.getBoard()[0][0] != null) {
            return new int[] {0,0};
        } else if (board.getBoard()[0][2] != null) {
            return new int[] {0,2};
        } else if (board.getBoard()[2][0] != null) {
            return new int[] {2,0};
        } else if (board.getBoard()[2][2] != null) {
            return new int[] {2,2};
        } else {
            return null;
        }
    }

    private int[] emptySideMove(Board board) {
        if (board.getBoard()[0][1] != null) {
            return new int[] {0,2};
        } else if (board.getBoard()[1][0] != null) {
            return new int[] {1,0};
        } else if (board.getBoard()[1][2] != null) {
            return new int[] {1,2};
        } else if (board.getBoard()[2][1] != null) {
            return new int[] {2,1};
        } else {
            return null;
        }
    }


    private int[] checkHorizontalWinning(Board board) {
        for (int y = 0; y < board.getBoard().length; y++) {
            int thisCounter = 0;
            int otherCounter = 0;
            int[] position = null;
            for (int x = 0; x < board.getBoard().length; x++) {

                if (board.getBoard()[y][x] != null) {

                    if (board.getBoard()[y][x].getMark().equals(getMark())) {
                        thisCounter++;
                        if (thisCounter == 2 & otherCounter == 0) {
                            return position;
                        }
                    } else if (!board.getBoard()[y][x].getMark().equals(getMark())) {
                        otherCounter++;
                        if (otherCounter == 2 & thisCounter == 0) {
                            return position;
                        }
                    }
                } else {
                    position = new int[]{y, x};
                }
            }
        }
        return null;
    }

    private int[] checkVerticalWinning(Board board) {
        for (int y = 0; y < board.getBoard().length; y++) {
            int thisCounter = 0;
            int otherCounter = 0;
            int[] position = null;
            for (int x = 0; x < board.getBoard().length; x++) {

                if (board.getBoard()[x][y] != null) {

                    if (board.getBoard()[x][y].getMark().equals(getMark())) {
                        thisCounter++;
                        if (thisCounter == 2 & otherCounter == 0) {
                            return position;
                        }
                    } else if (!board.getBoard()[x][y].getMark().equals(getMark())) {
                        otherCounter--;
                        if (otherCounter == 2 & thisCounter == 0) {
                            return position;
                        }
                    }
                } else {
                    position = new int[]{x, y};
                }
            }
        }
        return null;
    }

    //TODO
    private int[] checkDiagonalWinning(Board board) {
        int thisCounter = 0;
        int otherCounter = 0;
        int[] position = null;


        return null;
    }


}
