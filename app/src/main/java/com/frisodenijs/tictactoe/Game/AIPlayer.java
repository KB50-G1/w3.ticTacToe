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
                Log.d("AI", "winning move");
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
                Log.d("AI", "empty side move" + emptySideMove(board)[0] + ", " + emptySideMove(board)[1]);
                return emptySideMove(board);
            } else {
                Log.d("AI", "failed to move");
                return null;
            }
        } catch (NullPointerException e) {
            Log.d("AI.makeAutoMove()", "Null pointer exception");
            return null;
        }
    }

    private int[] winningMove(Board board) {
        Log.d("AI", "winning move called");
        try {
            if (checkHorizontalWinning(board).length == 2) {
                Log.d("AIPlayer.winningMove()", "check horizontal winning move");
                return checkHorizontalWinning(board);
            } else if (checkVerticalWinning(board).length == 2) {
                Log.d("AIPlayer.winningMove()", "check vertical winning move");
                return checkVerticalWinning(board);
            } else if (checkDiagonalLeftToRightWinning(board).length == 2) {
                Log.d("AIPlayer.winningMove()", "check left to right winning move");
                return checkDiagonalLeftToRightWinning(board);
            } else if (checkDiagonalRightToLeftWinning(board).length == 2) {
                Log.d("AIPlayer.winningMove()", "check right to left winning move");
                return checkDiagonalRightToLeftWinning(board);
            } else {
                Log.d("AIPlayer.winningMove()", "return int[0]");
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.winningMove()", "null pointer exception");
            return null;
        }
    }

    private int[] forkingMove(Board board) {
        Log.d("AI", "forking move called");

        try {
            if ((board.getBoard()[0][0] != null && board.getBoard()[0][0].getMark().equals(getMark())) &&
                    (board.getBoard()[2][2] != null && board.getBoard()[2][2].getMark().equals(getMark()))) {
                Log.d("AI.forkingMove(board)", "First if statement");
                return emptyCornerMove(board);
            } else if ((board.getBoard()[0][2] != null && board.getBoard()[0][2].getMark().equals(getMark())) &&
                    (board.getBoard()[2][0] != null && board.getBoard()[2][0].getMark().equals(getMark()))) {
                Log.d("AI.forkingMove(board)", "Second if statement");
                return emptyCornerMove(board);
            } else if ((board.getBoard()[0][0] != null && !board.getBoard()[0][0].getMark().equals(getMark())) ||
                    (board.getBoard()[2][2] != null && !board.getBoard()[2][2].getMark().equals(getMark())) ||
                    (board.getBoard()[0][2] != null && !board.getBoard()[0][2].getMark().equals(getMark())) ||
                    (board.getBoard()[2][0] != null && !board.getBoard()[2][0].getMark().equals(getMark()))) {
                Log.d("AI.forkingMove(board)", "Third if statement");
                return opposingCornerMove(board);
            } else {
                Log.d("AI.forkingMove(board)", "Final statement");
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.forkingMove()", "null pointer exception");
            return null;
        }
    }

    private int[] centerMove(Board board) {
        Log.d("AI", "center move called");
        try {
            if (board.getBoard()[1][1] == null) {
                return new int[]{1, 1};
            } else {
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.centerMove()", "null pointer exception");
            return null;
        }
    }

    private int[] opposingCornerMove(Board board) {
        Log.d("AI", "opposing corner move called");

        try {
            if (board.getBoard()[0][0] != null && !board.getBoard()[0][0].getMark().equals(getMark()) && board.getBoard()[2][2] == null) {
                return new int[]{2, 2};
            } else if (board.getBoard()[0][2] != null && !board.getBoard()[0][2].getMark().equals(getMark()) && board.getBoard()[2][0] == null) {
                return new int[]{2, 0};
            } else if (board.getBoard()[2][0] != null && !board.getBoard()[2][0].getMark().equals(getMark()) && board.getBoard()[0][2] == null) {
                return new int[]{0, 2};
            } else if (board.getBoard()[2][2] != null && !board.getBoard()[2][2].getMark().equals(getMark()) && board.getBoard()[0][0] == null) {
                return new int[]{0, 0};
            } else {
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.opposingCornerMove()", "null pointer exception");
            return null;
        }
    }

    private int[] emptyCornerMove(Board board) {
        Log.d("AI", "empty corner move called");

        try {
            if (board.getBoard()[0][0] == null) {
                return new int[]{0, 0};
            } else if (board.getBoard()[0][2] == null) {
                return new int[]{0, 2};
            } else if (board.getBoard()[2][0] == null) {
                return new int[]{2, 0};
            } else if (board.getBoard()[2][2] == null) {
                return new int[]{2, 2};
            } else {
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.emptyCornerMove()", "null pointer exception");
            return null;
        }
    }

    private int[] emptySideMove(Board board) {
        Log.d("AI", "empty side move called");

        try {
            if (board.getBoard()[0][1] == null) {
                return new int[]{0, 1};
            } else if (board.getBoard()[1][0] == null) {
                return new int[]{1, 0};
            } else if (board.getBoard()[1][2] == null) {
                return new int[]{1, 2};
            } else if (board.getBoard()[2][1] == null) {
                return new int[]{2, 1};
            } else {
                return new int[0];
            }
        } catch (NullPointerException e) {
            Log.d("AI.emptySideMove()", "null pointer exception");
            return null;
        }
    }


    private int[] checkHorizontalWinning(Board board) {
        Log.d("AI", "check horizontal winning move called");

        int[] position = null;


        for (int x = 0; x < board.getBoard().length; x++) {

            int thisCounter = 0;
            int otherCounter = 0;

            if (board.getBoard()[x][0] != null) {
                if (board.getBoard()[x][0] != null && board.getBoard()[x][0].getMark().equals(getMark())) {
                    thisCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", this counter: " + thisCounter);
                } else if (board.getBoard()[x][0] != null && !board.getBoard()[x][0].getMark().equals(getMark())) {
                    otherCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", other counter: " + thisCounter);
                }
            } else {
                position = new int[]{x, 0};
            }

            if (board.getBoard()[x][1] != null) {
                if (board.getBoard()[x][1] != null && board.getBoard()[x][1].getMark().equals(getMark())) {
                    thisCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", this counter: " + thisCounter);
                } else if (board.getBoard()[x][1] != null && !board.getBoard()[x][1].getMark().equals(getMark())) {
                    otherCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", other counter: " + otherCounter);
                }
            } else {
                position = new int[]{x, 1};
            }

            if (board.getBoard()[x][2] != null) {
                if (board.getBoard()[x][2] != null && board.getBoard()[x][2].getMark().equals(getMark())) {
                    thisCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", this counter: " + thisCounter);
                } else if (board.getBoard()[x][2] != null && !board.getBoard()[x][2].getMark().equals(getMark())) {
                    otherCounter++;
                    Log.d("AIPlayer.checkHorizontalWinning()", "Row: " + x + ", other counter: " + otherCounter);
                }
            } else {
                position = new int[]{x, 2};
            }

            if (((thisCounter == 2 && otherCounter == 0) || (thisCounter == 0 && otherCounter == 2)) && position != null) {
                if (board.getBoard()[position[0]][position[1]] == null) {
                    return position;
                }
            }
        }

        return new int[0];
    }

    private int[] checkVerticalWinning(Board board) {
        Log.d("AI", "check vertical winning move called");

        int[] position = null;

        for (int y = 0; y < board.getBoard().length; y++) {
            int thisCounter = 0;
            int otherCounter = 0;

            if (board.getBoard()[0][y] != null) {
                if (board.getBoard()[0][y] != null && board.getBoard()[0][y].getMark().equals(getMark())) {
                    thisCounter++;
                } else if (board.getBoard()[0][y] != null && !board.getBoard()[0][y].getMark().equals(getMark())) {
                    otherCounter++;
                }
            } else {
                position = new int[]{0, y};
            }


            if (board.getBoard()[1][y] != null) {
                if (board.getBoard()[1][y] != null && board.getBoard()[1][y].getMark().equals(getMark())) {
                    thisCounter++;
                } else if (board.getBoard()[1][y] != null && !board.getBoard()[1][y].getMark().equals(getMark())) {
                    otherCounter++;
                }
            } else {
                position = new int[]{1, y};
            }


            if (board.getBoard()[2][y] != null) {
                if (board.getBoard()[2][y] != null && board.getBoard()[2][y].getMark().equals(getMark())) {
                    thisCounter++;
                } else if (board.getBoard()[2][y] != null && !board.getBoard()[2][y].getMark().equals(getMark())) {
                    otherCounter++;
                }
            } else {
                position = new int[]{2, y};
            }

            if (((thisCounter == 2 && otherCounter == 0) || (thisCounter == 0 && otherCounter == 2)) && position != null) {
                if (board.getBoard()[position[0]][position[1]] == null) {
                    Log.d("AI.checkHorizontalWinning", "return position statement");
                    return position;
                }
            }
        }

        Log.d("AI.checkHorizontalWinning", "return int[0]");
        return new int[0];
    }

    private int[] checkDiagonalLeftToRightWinning(Board board) {
        Log.d("AI", "check diagonal left to right winning move called");
        int thisCounter = 0;
        int otherCounter = 0;
        int[] position = null;

        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.getBoard()[i][i] != null) {
                if (board.getBoard()[i][i] != null && board.getBoard()[i][i].getMark().equals(getMark())) {
                    thisCounter++;
                } else if (board.getBoard()[i][i] != null && !board.getBoard()[i][i].getMark().equals(getMark())) {
                    otherCounter++;
                }
            } else {
                position = new int[]{i, i};
            }

            if (((thisCounter == 2 && otherCounter == 0) || (thisCounter == 0 && otherCounter == 2)) && position != null) {
                if (board.getBoard()[position[0]][position[1]] == null) {
                    return position;
                } else {
                    return new int[0];
                }
            } else {
                return new int[0];
            }
        }

        if (((thisCounter == 2 && otherCounter == 0) || (thisCounter == 0 && otherCounter == 2)) && position != null) {
            if (board.getBoard()[position[0]][position[1]] == null) {
                return position;
            }
        }

        return new int[0];
    }

    private int[] checkDiagonalRightToLeftWinning(Board board) {
        Log.d("AI", "check diagonal right to left winning move called");
        int thisCounter = 0;
        int otherCounter = 0;
        int[] position = null;

        if (board.getBoard()[2][0] != null) {
            if (board.getBoard()[2][0] != null && board.getBoard()[2][0].getMark().equals(getMark())) {
                thisCounter++;
            } else if (board.getBoard()[2][0] != null && !board.getBoard()[2][0].getMark().equals(getMark())) {
                otherCounter++;
            }
        } else {
            position = new int[]{2, 0};
        }

        if (board.getBoard()[1][1] != null) {
            if (board.getBoard()[1][1] != null && board.getBoard()[1][1].getMark().equals(getMark())) {
                thisCounter++;
            } else if (board.getBoard()[1][1] != null && !board.getBoard()[1][1].getMark().equals(getMark())) {
                otherCounter++;
            }
        } else {
            position = new int[]{1, 1};
        }

        if (board.getBoard()[0][2] != null) {
            if (board.getBoard()[0][2] != null && board.getBoard()[0][2].getMark().equals(getMark())) {
                thisCounter++;
            } else if (board.getBoard()[0][2] != null && !board.getBoard()[0][2].getMark().equals(getMark())) {
                otherCounter++;
            }
        } else {
            position = new int[]{0, 2};
        }

        if (((thisCounter == 2 && otherCounter == 0) || (thisCounter == 0 && otherCounter == 2)) && position != null) {
            if (board.getBoard()[position[0]][position[1]] == null) {
                return position;
            }
        }
        return new int[0];
    }
}
