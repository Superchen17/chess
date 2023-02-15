package com.osullivan.chess;

import java.util.HashSet;

public class Queen extends Piece {

  public Queen(String name, boolean isWhite, Square square){
    super(name, isWhite, square);
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.addAll(this.canMoveLeftTo(board));
    nextMoves.addAll(this.canMoveRightTo(board));
    nextMoves.addAll(this.canMoveUpTo(board));
    nextMoves.addAll(this.canMoveDownTo(board));
    nextMoves.addAll(this.canMoveUpLeftDiagonalTo(board));
    nextMoves.addAll(this.canMoveUpRightDiagonalTo(board));
    nextMoves.addAll(this.canMoveDownLeftDiagonalTo(board));
    nextMoves.addAll(this.canMoveDownRightDiagonalTo(board));
    return nextMoves;
  }
}
