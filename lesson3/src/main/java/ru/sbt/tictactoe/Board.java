package ru.sbt.tictactoe;

public class Board {

    private byte[][] board = new byte[3][3];

    public static final byte CROSS = 1;
    public static final byte CIRCLE = 2;

/*
@startuml
[*] --> State1
Состояние --> [*]
State1 : this is a string
State1 : this is another string
State1 -> State2
State2 --> [*]
@enduml
*/

    /*
    public byte[][] getBoard() {
        return board;
    }

    [1,2]
    [1]
    []
    [1,2,3]

    */

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("board.length=" + board.board.length);
    }

    public void setSymbol(byte x, byte y, byte symbol) {
        if (board[x][y] != 0) {
            throw new IllegalArgumentException("Move already made for position x=" + x + ", and y=" + y);
        }
        if (x < 0 || x > board.length - 1 || y < 0 || y > board.length - 1) {
            throw new IllegalArgumentException("x or y are not correct! x = " + x + ", y = " + y);
        }
        if (symbol != CROSS || symbol != CIRCLE) {
            throw new IllegalArgumentException("Incorrect symbol, symbol = " + symbol);
        }

        board[x][y] = symbol;
    }


    public void print() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);

            }
            System.out.println();
        }
    }



}
