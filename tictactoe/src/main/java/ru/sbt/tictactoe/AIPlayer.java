package ru.sbt.tictactoe;

public abstract class AIPlayer {
    protected int ROWS = 0;
    protected int COLS = 0;
    protected Cell[][] cells;
    protected Seed mySeed;
    protected Seed oppSeed;
    public AIPlayer(Board board)
    {
        ROWS = board.ROWS;
        COLS = board.COLS;
        cells = board.cells;
    }
    public void setSeed(Seed seed)
    {
        this.mySeed = seed;
        oppSeed = (mySeed == Seed.CROSS)? Seed.NOUGHT : Seed.CROSS;
    }
    public abstract  int[] move();
}
