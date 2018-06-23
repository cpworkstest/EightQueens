package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class QueenSolver {

    private static final int NOT_SET = Integer.MIN_VALUE;
    private int BOARD_SIZE;

    public QueenSolver(int boardSize) {
        BOARD_SIZE = boardSize;
    }

    private boolean hasThreePointsOnLine(int[] board, int row, int position) {
        if (row < 2 || position >= BOARD_SIZE){
            return false;
        }

        final Set<Double> set = new HashSet<>();
        for (int i = 0; i < row; i++) {
            double slope = (row - i) / (double)(position - board[i]);
            // it should be safe enough to compare doubles like that in our case
            if (set.contains(slope)) {
                return true;
            } else {
                set.add(slope);
            }
        }

        return false;
    }

    private int findNextQueenPosition(int[] board, int row, int pos) {
        if (pos >= BOARD_SIZE) {
            return pos;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == pos ||
                row - i == pos - board[i] ||
                row - i == -pos + board[i]) {
                i = -1;
                pos++;
            }
        }

        return hasThreePointsOnLine(board, row, pos)
                ? findNextQueenPosition(board, row, pos + 1)
                : pos;
    }

    public int[] solve() {
        final int[] board = new int[BOARD_SIZE];

        Arrays.fill(board, NOT_SET);

        board[0] = 0;
        int row = 1;
        int position = 0;
        int step = 0;

        while (row < BOARD_SIZE) {
            Debug.debugln(String.format("step %s:", step++));

            if (position >= BOARD_SIZE) {
                row--;
                if (row < 0) {
                    // no solution
                    break;
                }

                position = board[row] + 1;
                board[row] = NOT_SET;
                Debug.debugln("backtrack 2");

                Debug.debugPrintBoard(board);
                continue;
            }

            position = findNextQueenPosition(board, row, position);

            if (position >= BOARD_SIZE) {
                position = board[row-1] + 1;

                board[row] = NOT_SET;
                board[row-1] = NOT_SET;
                row--;
                Debug.debugln("backtrack");
            } else {
                board[row] = position;
                row++;
                position = 0;
            }

            Debug.debugPrintBoard(board);
        }

        Debug.debugln("steps: " + step);

        return board;
    }
}
