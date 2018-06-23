package com.company;

import java.util.function.Consumer;

public class Debug {

    private static final boolean DEBUG = false;

    public static void debugPrintBoard(int[] board) {
        printBoard(board,
                   s -> debug(s),
                   s -> debugln(s));
    }

    public static void printBoard(int[] board) {
        printBoard(board,
                   s -> System.out.print(s),
                   s -> System.out.println(s));
    }

    public static void printBoard(int[] board, Consumer<String> print, Consumer<String> println) {
        for (int y = 0; y < board.length; y++)
        {
            for (int x = 0; x < board.length; x++)
            {
                print.accept(board[y] == x ? "Q" : ".");
                print.accept(" ");
            }
            println.accept("");
        }

        println.accept("");
    }

    public static void debugln(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    public static void debug(String s) {
        if (DEBUG) {
            System.out.print(s);
        }
    }
}
