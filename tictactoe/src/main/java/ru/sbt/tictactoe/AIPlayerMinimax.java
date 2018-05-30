package ru.sbt.tictactoe;

import java.util.*;
public class AIPlayerMinimax extends AIPlayer
{
   public AIPlayerMinimax(Board board)
   {
      super(board);
   }
   @Override
   public int[] move()
   {
      int[] result = minimax(4, mySeed);
      return result;
   }

   private int[] minimax (int depth, Seed player) {
      List <int[]> nextMoves = generateMoves();
      int bestScore = (player == mySeed)  ? Integer.MIN_VALUE:Integer.MAX_VALUE;
      int currentScore;
      int bestRow = -1;
      int bestCol = 1;
      if (nextMoves.isEmpty() || depth ==0) {
         bestScore = evaluate();
      } else  {
         for (int[] move : nextMoves) {
            this.cells[move[0]][move[1]].content = player;
            if (player == mySeed){
               currentScore = minimax(depth - 1, oppSeed)[0];
               if (currentScore > bestScore) {
                  bestScore = currentScore;
                  bestRow = move[0];
                  bestCol = move[1];
               }
         }
         cells[move[0]][move[1]].content = Seed.EMPTY;
         }
      }
      return  new int[]{bestScore, bestRow, bestCol};
   }
   private  List<int[]> generateMoves(){
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
   private int evaluate() {
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

   private int[] winningPatterns = {
           0b111000000, 0b000111000, 0b000000111, // rows
           0b100100100, 0b010010010, 0b001001001, // cols
           0b100010001, 0b001010100               // diagonals
   };

   /** Returns true if thePlayer wins */
   private boolean hasWon(Seed thePlayer) {
      int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
      for (int row = 0; row < ROWS; ++row) {
         for (int col = 0; col < COLS; ++col) {
            if (cells[row][col].content == thePlayer) {
               pattern |= (1 << (row * COLS + col));
            }
         }
      }
      for (int winningPattern : winningPatterns) {
         if ((pattern & winningPattern) == winningPattern) return true;
      }
      return false;
   }
}
