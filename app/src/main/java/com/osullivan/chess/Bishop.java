package com.osullivan.chess;

import java.util.HashSet;

public class Bishop extends Piece{

  public Bishop(String name, boolean isWhite, Square square){
    super(name, isWhite, square);
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.addAll(this.canMoveDownLeftDiagonalTo(board));
    nextMoves.addAll(this.canMoveDownRightDiagonalTo(board));
    nextMoves.addAll(this.canMoveUpLeftDiagonalTo(board));
    nextMoves.addAll(this.canMoveUpRightDiagonalTo(board));
    return nextMoves;
  }

  @Override
  public HashSet<Square> canCaptureAt(Board board) {
    return this.canMoveTo(board);
  }
}
