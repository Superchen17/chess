package com.osullivan.chess;

import java.util.HashSet;

public class Knight extends Piece{

  public Knight(String name, boolean isWhite, Square square){
    super(name, isWhite, square);
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    int currRow = this.getSquare().getRow();
    int currCol = this.getSquare().getColumn();
    HashSet<Square> nextMoves = new HashSet<>();

    for(int i = -2; i <= 2; i++){
      for(int j = -2; j <= 2; j++){
        if(i == 0 || j == 0 || Math.abs(i) == Math.abs(j)){
          continue;
        }
        Square square = new Square(currRow + i, currCol + j);
        if(board.isSquareOnBoard(square)){
          Piece p = board.whatIsAtSquare(square);
          if(p == null || (p != null && p.isWhite != this.isWhite)){
            nextMoves.add(square);
          }
        }
      }
    }
    return nextMoves;
  }

  @Override
  public HashSet<Square> canCover(Board board) {
    return this.canMoveTo(board);
  }
}
