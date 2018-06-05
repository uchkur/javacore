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
}
