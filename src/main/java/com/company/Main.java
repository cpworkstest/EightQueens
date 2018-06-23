package com.company;

public class Main {

    private static void printUsage() {
        System.out.println("There is one parameter, which is the size of the board. Default is 8.");
    }

    public static void main(String[] args) {

        if (args.length > 1) {
            printUsage();
            return;
        }

        int boardSize = 5;

        if (args.length == 1) {
            try {
                boardSize = Integer.parseInt(args[0]);

                if (boardSize < 4) {
                    System.err.println("board too small!");
                    printUsage();
                    return;
                }

            } catch (NumberFormatException e) {
                System.err.println("invalid board size " + e.toString());
                printUsage();
                return;
            }
        }

        System.out.println("Solving for board size " + boardSize);

        int[] board = new QueenSolver(boardSize).solve();
        Debug.printBoard(board);
    }
}
