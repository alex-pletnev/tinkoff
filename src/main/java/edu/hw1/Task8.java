package edu.hw1;

public class Task8 {
    boolean knightBoardCapture(int[][] board) {
        boolean result = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                     result = !(goUp(i, j, board) || goDown(i, j, board) || goLeft(i, j, board) || goRight(i, j, board));
                     if (!result) {
                         break;
                     }
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    private boolean goUp(int i, int j, int[][] board) {
        if (i < 2) {
            return false;
        }
        if (j != 0 && (board[i - 2][j - 1] == 1)) {
            return true;
        }
        if (j != 7 && (board[i - 2][j + 1] == 1)) {
            return true;
        }
        return false;
    }
    private boolean goDown(int i, int j, int[][] board) {
        if (i > 5) {
            return false;
        }
        if (j != 0 && (board[i + 2][j - 1] == 1)) {
            return true;
        }
        if (j != 7 && (board[i + 2][j + 1] == 1)) {
            return true;
        }
        return false;
    }
    private boolean goLeft(int i, int j, int[][] board) {
        if (j < 2) {
            return false;
        }
        if (i != 0 && (board[i - 1][j - 2] == 1)) {
            return true;
        }
        if (i != 7 && (board[i + 1][j - 2] == 1)) {
            return true;
        }
        return false;
    }
    private boolean goRight(int i, int j, int[][] board) {
        if (j > 5) {
            return false;
        }
        if (i != 0 && (board[i - 1][j + 2] == 1)) {
            return true;
        }
        if (i != 7 && (board[i + 1][j + 2] == 1)) {
            return true;
        }
        return false;
    }

}
