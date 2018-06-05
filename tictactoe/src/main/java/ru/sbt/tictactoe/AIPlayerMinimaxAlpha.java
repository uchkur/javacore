package ru.sbt.tictactoe;

import java.util.*;
public class AIPlayerMinimaxAlpha extends AIPlayer
{
   public AIPlayerMinimaxAlpha (Board board)
   {
      super(board);
   }
   @Override
   public  int[] move() {
      int[] result = minimax(5, mySeed, Integer.MIN_VALUE, Integer.MAX_VALUE);
      return new int[] {result[1], result[2]};   // row, col
   }

   private int[] minimax(int depth, Seed player, int alpha, int beta) {
      List<int[]> nextMoves = generateMoves();

      int score;
      int bestRow = -1;
      int bestCol = -1;

      if (nextMoves.isEmpty() || depth == 0) {
         score = evaluate();
         return new int[] {score, bestRow, bestCol};
      } else {
         for (int[] move : nextMoves) {
            cells[move[0]][move[1]].content = player;
            if (player == mySeed) {  // mySeed (computer) is maximizing player
               score = minimax(depth - 1, oppSeed, alpha, beta)[0];
               if (score > alpha) {
                  alpha = score;
                  bestRow = move[0];
                  bestCol = move[1];
               }
            } else {  // oppSeed is minimizing player
               score = minimax(depth - 1, mySeed, alpha, beta)[0];
               if (score < beta) {
                  beta = score;
                  bestRow = move[0];
                  bestCol = move[1];
               }
            }
            // undo move
            cells[move[0]][move[1]].content = Seed.EMPTY;
            // cut-off
            if (alpha >= beta) break;
         }
         return new int[] {(player == mySeed) ? alpha : beta, bestRow, bestCol};
      }
   }



}
