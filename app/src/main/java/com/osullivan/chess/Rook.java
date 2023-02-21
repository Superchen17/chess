package com.osullivan.chess;

import java.util.HashSet;

public class Rook extends Piece{
  private final boolean isKingSide;

  public Rook(String name, boolean isWhite, Square square, Boolean isKingSide){
    super(name, isWhite, square);
    this.isKingSide = isKingSide;
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.addAll(this.canMoveUpTo(board));
    nextMoves.addAll(this.canMoveDownTo(board));
    nextMoves.addAll(this.canMoveLeftTo(board));
    nextMoves.addAll(this.canMoveRightTo(board));
    return nextMoves;
  }

  /**
   * can only use the rook to castle when it hasn't been moved
   * @return
   */
  public boolean canCastle(){
    if(moveCounter == 0){
      return true;
    }
    return false;
  }

  /**
   * return if it is a king-side rook
   * @return
   */
  public boolean isKingSideRook(){
    return this.isKingSide;
  }

  @Override
  public HashSet<Square> canCover(Board board) {
    return this.canMoveTo(board);
  }
}
