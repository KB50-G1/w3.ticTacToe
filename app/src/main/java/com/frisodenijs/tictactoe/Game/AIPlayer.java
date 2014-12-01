package com.frisodenijs.tictactoe.Game;

import android.util.Log;

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
    public int[] makeAutoMove(Board board) {
        try {
        if (winningMove(board).length == 2) {
            Log.d("AI", "Winning Move");
            return winningMove(board);
        } else if (forkingMove(board).length == 2) {
            Log.d("AI", "forking move");
            return forkingMove(board);
        } else if (centerMove(board).length == 2) {
            Log.d("AI", "center move");
            return centerMove(board);
        } else if (opposingCornerMove(board).length == 2) {
            Log.d("AI", "opposing corner move");
            return opposingCornerMove(board);
        } else if (emptyCornerMove(board).length == 2) {
            Log.d("AI", "empty corner move");
            return emptyCornerMove(board);
        } else if (emptySideMove(board).length == 2) {
            Log.d("AI", "empty side move");
            return emptySideMove(board);
        } else {
            Log.d("AI", "failed to move");
            return null;
        }
        } catch (NullPointerException e) {
            Log.d("AI.makeAutoMove()", board.toString());
            return null;
        }
    }

    private int[] winningMove(Board board) {
        try {
            if (checkHorizontalWinning(board).length == 2) {
                return checkHorizontalWinning(board);
            } else if (checkVerticalWinning(board).length == 2) {
                return checkVerticalWinning(board);
            } else if (checkDiagonalLeftToRightWinning(board).length == 2) {
                return checkDiagonalLeftToRightWinning(board);
            } else if (checkDiagonalRightToLeftWinning(board).length == 2) {
                return checkDiagonalRightToLeftWinning(board);
            } else {
                return new int[0];
            }
        }catch (NullPointerException e) {
            Log.d("AI, Winning Move", board.toString());
            return null;
        }
    }

    private int[] forkingMove(Board board) {
        if (board.getBoard()[0][0].getMark().equals(getMark()) &&
                board.getBoard()[2][2].getMark().equals(getMark())) {
            return emptyCornerMove(board);
        } else if (board.getBoard()[0][2].getMark().equals(getMark()) &&
                board.getBoard()[2][0].getMark().equals(getMark())) {
            return  emptyCornerMove(board);
        } else if (!board.getBoard()[0][0].getMark().equals(getMark()) ||
                !board.getBoard()[2][2].getMark().equals(getMark()) ||
                !board.getBoard()[0][2].getMark().equals(getMark()) ||
                !board.getBoard()[2][0].getMark().equals(getMark())) {
            return opposingCornerMove(board);
        } else {
            return new int[0];
        }
    }

    private int[] centerMove(Board board) {
        if (board.getBoard()[1][1] != null) {
            return new int[]{1, 1};
        } else {
            return new int[0];
        }
    }

    private int[] opposingCornerMove(Board board) {
        if ((!board.getBoard()[0][0].getMark().equals(getMark())) && board.getBoard()[2][2] != null) {
            return new int[]{2, 2};
        } else if ((!board.getBoard()[0][2].getMark().equals(getMark())) && board.getBoard()[2][0] != null) {
            return new int[]{2, 0};
        } else if ((!board.getBoard()[2][0].getMark().equals(getMark())) && board.getBoard()[0][2] != null) {
            return new int[]{0, 2};
        } else if ((!board.getBoard()[2][2].getMark().equals(getMark())) && board.getBoard()[0][0] != null) {
            return new int[]{0, 0};
        } else {
            return new int[0];
        }
    }

    private int[] emptyCornerMove(Board board) {
        if (board.getBoard()[0][0] != null) {
            return new int[]{0, 0};
        } else if (board.getBoard()[0][2] != null) {
            return new int[]{0, 2};
        } else if (board.getBoard()[2][0] != null) {
            return new int[]{2, 0};
        } else if (board.getBoard()[2][2] != null) {
            return new int[]{2, 2};
        } else {
            return new int[0];
        }
    }

    private int[] emptySideMove(Board board) {
        if (board.getBoard()[0][1] != null) {
            return new int[]{0, 2};
        } else if (board.getBoard()[1][0] != null) {
            return new int[]{1, 0};
        } else if (board.getBoard()[1][2] != null) {
            return new int[]{1, 2};
        } else if (board.getBoard()[2][1] != null) {
            return new int[]{2, 1};
        } else {
            return new int[0];
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
        return new int[0];
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
        return new int[0];
    }

    private int[] checkDiagonalLeftToRightWinning(Board board) {
        int thisCounter = 0;
        int otherCounter = 0;
        int[] position = null;

        for (int i = 0; i < 3; i++) {
            if (board.getBoard()[i][i] != null) {
                if (board.getBoard()[i][i].getMark().equals(getMark())) {
                    thisCounter++;
                    if (thisCounter == 2 && otherCounter == 0) {
                        return position;
                    }
                } else if (!board.getBoard()[i][i].getMark().equals(getMark())) {
                    otherCounter++;
                    if (otherCounter == 2 && thisCounter == 0) {
                        return position;
                    }
                }
            } else {
                position = new int[]{i, i};
            }
        }

        return new int[0];
    }


    private int[] checkDiagonalRightToLeftWinning(Board board) {
        int thisCounter = 0;
        int otherCounter = 0;
        int[] position = null;

        if (board.getBoard()[0][2] != null) {
            if (board.getBoard()[0][2].getMark().equals(getMark())) {
                thisCounter++;
            } else if (!board.getBoard()[0][2].getMark().equals(getMark())) {
                otherCounter++;
            } else {
            }
        } else {
            position = new int[]{0, 2};
        }

        if (board.getBoard()[1][1] != null) {
            if (board.getBoard()[1][1].getMark().equals(getMark())) {
                thisCounter++;
            } else if (!board.getBoard()[1][1].getMark().equals(getMark())) {
                otherCounter++;
            } else {
            }
        } else {
            position = new int[]{1, 1};
        }

        if (board.getBoard()[2][0] != null) {
            if (board.getBoard()[2][0].getMark().equals(getMark())) {
                thisCounter++;
            } else if (!board.getBoard()[2][0].getMark().equals(getMark())) {
                otherCounter++;
            } else {
            }
        } else {
            position = new int[]{2, 0};
        }

        if ((thisCounter == 2 && otherCounter == 0) || (otherCounter == 2 && thisCounter == 0)){
            return position;
        } else {
            return new int[0];
        }
    }
}
