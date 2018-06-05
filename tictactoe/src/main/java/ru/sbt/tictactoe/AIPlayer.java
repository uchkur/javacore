package ru.sbt.tictactoe;

import java.util.ArrayList;
import java.util.List;

public abstract class AIPlayer {
    protected int ROWS = 0;
    protected int COLS = 0;
    protected Cell[][] cells;
    protected Seed mySeed;
    protected Seed oppSeed;
    private Board board;
    public AIPlayer(Board board)
    {
        ROWS = board.ROWS;
        COLS = board.COLS;
        cells = board.cells;
        this.board = board;
    }
    public void setSeed(Seed seed)
    {
        this.mySeed = seed;
        oppSeed = (mySeed == Seed.CROSS)? Seed.NOUGHT : Seed.CROSS;
    }
    public abstract  int[] move();

    protected int evaluate() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
        score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
        score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
        score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
        score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
        score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
        score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
        return score;
    }
    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (cells[row1][col1].content == mySeed) {
            score = 1;
        } else if (cells[row1][col1].content == oppSeed) {
            score = -1;
        }

        // Second cell
        if (cells[row2][col2].content == mySeed) {
            if (score == 1) {   // cell1 is mySeed
                score = 10;
            } else if (score == -1) {  // cell1 is oppSeed
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (cells[row2][col2].content == oppSeed) {
            if (score == -1) { // cell1 is oppSeed
                score = -10;
            } else if (score == 1) { // cell1 is mySeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (cells[row3][col3].content == mySeed) {
            if (score > 0) {  // cell1 and/or cell2 is mySeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (cells[row3][col3].content == oppSeed) {
            if (score < 0) {  // cell1 and/or cell2 is oppSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is mySeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        return score;
    }

    public boolean hasWon(Seed theSeed)
    {
        int n = COLS;
        int col=0,row=0,diag=0,rdiag=0;
        boolean winner=false;
        for (int i=0; i < n; i++) {
            if (cells[board.currentRow][i].content == theSeed) col++;
            if (cells[i][board.currentCol].content==theSeed) row++;
            if (cells[i][i].content==theSeed) diag++;
            if (cells[i][n - i - 1 ].content==theSeed) rdiag++;
            if (row == n || col == n || diag == n || rdiag == n) winner = true;
        }
        return winner;
    }

    protected List<int[]> generateMoves(){
        List<int[]> nextMoves = new ArrayList<int[]>();
        if (hasWon(mySeed) || hasWon(oppSeed)){
            return  nextMoves;
        }
        for (int row = 0; row < ROWS; ++row) {
            for (int col=0; col < COLS; ++col){
                if (cells[row][col].content == Seed.EMPTY) {
                    nextMoves.add(new int[] {row, col});
                }
            }
        }
        return  nextMoves;
    }
}
