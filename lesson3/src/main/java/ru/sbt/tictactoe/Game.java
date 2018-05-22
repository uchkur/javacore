package ru.sbt.tictactoe;

import java.util.Scanner;

public class Game {

    private Board board;
    private Player[] players;
    private int currentPlayerIndex;

    public Game() {
        board = new Board();
        players = new Player[2];
        players[0] = new Player(Board.CROSS, board, "Player 1");
        players[1] = new Player(Board.CIRCLE, board, "Player 2");
        currentPlayerIndex = 0;
    }

    public static void main(String... args) {
        Game game = new Game();
        game.startGame();
    }

    public void startGame() {
        System.out.println("SUPER GAME");
        System.out.println();
        board.print();
        System.out.println("[" + players[currentPlayerIndex] + "] Make your move:>");
        String inputLine = System.console().readLine(); // "11"

        //char first = inputLine.charAt(0);
        //char second = inputLine.charAt(1);
        String first = inputLine.substring(0,1);
        String second = inputLine.substring(1, 2);

        //players[currentPlayerIndex].makeMove(Byte.parseByte(new String(new char[]{first})), (byte)second);
        players[currentPlayerIndex].makeMove(Byte.parseByte(first), Byte.parseByte(second));
    }
}
