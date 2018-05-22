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
        int size = 3;
        System.out.println("Играем с компьютером? (0 = НЕТ, 1 = ДА)");
        isAIGame = (in.nextInt() == 0)?false:true;
        if (isAIGame)
        {
            board = new Board(3); //todo add 2..20 board support here
        }
        else {
            System.out.println("Введите сторону доски от 2 до 20 клеток включительно: ");
            board = new Board(in.nextInt()); //todo refactor it
        }
        this.aiPlayer = new AIPlayerTableLookup(board);
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
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        } while (currentState == GameState.PLAYING);
    }

    public void initGame() {
        board.init();
        currentPlayer = Seed.CROSS;
        currentState = GameState.PLAYING;
    }

    public void playerMove(Seed theSeed) {
        boolean validInput = false;
        int row = 0, col = 0;
        do {
            if (theSeed == Seed.CROSS) {
                System.out.printf("Игрок 'X', ваш ход (строка [1-%s] столбец[1-%s]: ", board.ROWS, board.COLS);
                row = in.nextInt() - 1;
                col = in.nextInt() - 1;
            }
            else
            {
                if (isAIGame) {
                    int[] mv;
                    mv = aiPlayer.move();
                    row = mv[0];
                    col = mv[1];
                }
                else
                {
                    System.out.printf("Игрок 'O', ваш ход (строка [1-%s] столбец [1-%s]: ", board.ROWS, board.COLS);
                    row = in.nextInt() - 1;
                    col = in.nextInt() - 1;
                }


            }

            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                    && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            } else {
                System.out.println("Этот ход (" + (row + 1) + ";" + (col + 1) + ") не допустим. Попробйте еще...");
            }
        } while (!validInput);
    }
    public void updateGame(Seed theSeed)
    {
        if (board.hasWon(theSeed))
            currentState=(theSeed == Seed.CROSS)?GameState.CROSS_WON : GameState.NOUGHT_WON;
        else  if (board.isDraw())
            currentState = GameState.DRAW;
    }

    public static void main(String[] args) {
        new GameMain();
    }
}
