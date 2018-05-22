package ru.sbt.tictactoe;

public class Player {

    private byte symbol = 0;
    private Board board;
    private String name;

    public Player(byte symbol, Board board, String name) {
        this.symbol = symbol;
        this.board = board;
        this.name = name;
    }

    public void makeMove(byte x, byte y) {
        board.setSymbol(x, y, symbol);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
