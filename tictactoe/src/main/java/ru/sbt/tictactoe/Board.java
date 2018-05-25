package ru.sbt.tictactoe;
public class Board {
    public static int ROWS = 3;
    public static int COLS = 3;
    Cell[][] cells;
    int currentRow, currentCol;
    public Board(int size)
    {

        if ( size < 2 || size > 20 ) {
            throw new IllegalArgumentException("");
        };
        ROWS=COLS=size;
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                cells[row][col] = new Cell(row, col);
    }

    public void init(){
        for (int row=0; row<ROWS;++row)
            for (int col = 0; col <COLS; ++col)
                cells[row][col].clear();
    }

    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row)
            for (int col = 0; col < COLS; ++col)
                if (cells[row][col].content == Seed.EMPTY)
                    return false;
        return true;
    }

    public boolean hasWon(Seed theSeed)
    {
        int n = COLS;
        int col=0,row=0,diag=0,rdiag=0;
        boolean winner=false;
        for (int i=0; i < n; i++) {
            if (cells[currentRow][i].content == theSeed) col++;
            if (cells[i][currentCol].content==theSeed) row++;
            if (cells[i][i].content==theSeed) diag++;
            if (cells[i][n - i - 1 ].content==theSeed) rdiag++;
            if (row == n || col == n || diag == n || rdiag == n) winner = true;
        }
        return winner;
    }
    public void paint()
    {
        for (int row=0; row<ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint();
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                for (int i = 0 ; i < COLS; i++)
                    System.out.print("----");
            }
            System.out.println();
        }

    }
}

