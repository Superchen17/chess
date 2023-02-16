package com.osullivan.chess;

import java.util.HashSet;

public class King extends Piece {

  public King(String name, boolean isWhite, Square square){
    super(name, isWhite, square);
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    // King can move these adjacent places:
    // 1. empty square not covered by enemy
    // 2. enemy-occupied square not defended by another enemy piece
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.getSquare().getRow();
    int currCol = this.getSquare().getColumn();
    for(int i = -1; i <= 1; i++){
      for(int j = -1; j <= 1; j++){
        Square square = new Square(currRow + i, currCol + j);
        if(square.equals(this.square) || !board.isSquareOnBoard(square)){
          continue;
        }
        Piece p = board.whatIsAtSquare(square);
        if(p == null){
          if(!square.canBeCoveredAssumingEmptyBy(!this.isWhite, board)){
            nextMoves.add(square);
          }
        }
        else{
          if(p.isWhite != this.isWhite){
            if(!p.canBeProtected(board)){
              nextMoves.add(square);
            }
          }
        }
      }
    }
    return nextMoves;
  }
}
