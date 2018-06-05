package ru.sbt.tictactoe;

import java.util.Scanner;

public class GameMain {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;
    private AIPlayer aiPlayer;
    private boolean isAIGame = false;
    private static Scanner in = new Scanner(System.in);
    public GameMain() {
        int bsize=3;
        System.out.println
            ("Играете за 0? [0 - за нолик, другая цифра - крестик]");
        if (in.nextInt() == 0)
            currentPlayer=Seed.NOUGHT;
        else currentPlayer=Seed.CROSS;
        System.out.println("Играем с компьютером? (0 = НЕТ, 1 = ДА)");
        isAIGame = (in.nextInt() == 0)?false:true;
            while ((bsize > 20) || (bsize < 3));
            board = new Board(bsize);
            do {
                System.out.println
                        ("Введите сторону доски от 3 до 20 клеток включительно: ");
                bsize = in.nextInt();
            }
            while ((bsize > 20) || (bsize < 3));
            board = new Board(bsize);
        this.aiPlayer = new AIPlayerMinimaxAlpha(board);
        aiPlayer.setSeed((currentPlayer == Seed.CROSS)? Seed.NOUGHT : Seed.CROSS);
        initGame();
        do {
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);
            if (currentState == GameState.CROSS_WON)
                System.out.println("'X' победил! Поздравляю!");
            else if (currentState == GameState.NOUGHT_WON)
                System.out.println("'O' победил! Поздравляю!");
            else if (currentState == GameState.DRAW)
                System.out.println("Ничья! Пока!");
            currentPlayer =
                (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);
    }

    public void initGame() {
        board.init();
        currentState = GameState.PLAYING;
    }

    public void playerMove(Seed theSeed) {
        boolean validInput = false;
        int row = 0, col = 0;
        int[] mv = {0,0};
        do {
            if (theSeed == Seed.CROSS && (theSeed != aiPlayer.mySeed
                    )) {
                System.out.printf
                    ("Игрок 'X', ваш ход (строка [1-%s] столбец[1-%s]: ",
                     board.ROWS, board.COLS);
                row = in.nextInt() - 1;
                col = in.nextInt() - 1;

                if (isAIGame && (theSeed == aiPlayer.mySeed)) {
                    mv[0] = aiPlayer.move()[1];
                    mv[1] = aiPlayer.move()[2];
                    row = mv[0];
                    col = mv[1];
                }
            }
            else
            {
                if (isAIGame && (theSeed == aiPlayer.mySeed) ) {
                    mv[0] = aiPlayer.move()[0];
                    mv[1] = aiPlayer.move()[1];
                    row = mv[0];
                    col = mv[1];
                }
                else
                {
                    System.out.printf
                        ("Игрок 'O', ваш ход (строка [1-%s] столбец [1-%s]: "
                         , board.ROWS, board.COLS);
                    row = in.nextInt() - 1;
                    col = in.nextInt() - 1;
                    if (isAIGame && (theSeed == aiPlayer.mySeed)) {
                        //int[] mv = {0,0};
                        mv[0] = aiPlayer.move()[1];
                        mv[1] = aiPlayer.move()[2];
                        row = mv[0];
                        col = mv[1];
                    }
                }
            }

            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                    && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            } else {
                System.out.println
                    ("Этот ход (" + (row + 1) + ";" + (col + 1)
                     + ") не допустим. Попробйте еще...");

            }
        } while (!validInput);
    }
    public void updateGame(Seed theSeed)
    {
        if (board.hasWon(theSeed))
            currentState=(
                    theSeed == Seed.CROSS)?
                        GameState.CROSS_WON : GameState.NOUGHT_WON;
            else  if (board.isDraw())
                currentState = GameState.DRAW;
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
