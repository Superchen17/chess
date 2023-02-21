package com.osullivan.chess;

import java.util.HashSet;

public class Pawn extends Piece {

  public Pawn(String name, boolean isWhite, Square square){
    super(name, isWhite, square);
  }

  /**
   * test constructor (allow manual moveCounter setting)
   * DO NOT invoke in other classes
   * @param name
   * @param isWhite
   * @param square
   * @param moveCounter
   */
  public Pawn(String name, boolean isWhite, Square square, int moveCounter){
    super(name, isWhite, square);
    this.moveCounter = moveCounter;
  }

  /**
   * helper method for Pawn captures
   * @param square
   * @param board
   * @return true if has enemy piece to capture
   */
  private boolean canCaptureOn(Square square, Board board){
    if(board.isSquareOnBoard(square)){
      Piece p = board.whatIsAtSquare(square);
      if(p != null && p.isWhite != this.isWhite){
        return true;
      }
    }
    return false;
  }

  /**
   * helper method to determine if pawn can move forward 2 squares
   * true if:
   * 1. haven't been moved
   * 2. on correct rank (sanity check, equivalent to 1)
   * 3. 1st and 2nd squares both unblocked
   * @param board
   * @return true if can
   */
  private boolean canMoveTwoSquares(Board board){
    // false if not on its first move
    if(this.moveCounter != 0){
      return false;
    }

    // false if on wrong rank
    if(this.isWhite){
      if(this.square.getRow() != 2){
        return false;
      }
    }
    else{
      if(this.square.getRow() != 7){
        return false;
      }
    }

    int direction = this.isWhite ? 1 : -1;

    // false if first square blocked
    Square firstSquare = new Square(this.square.getRow() + direction * 1, this.square.getColumn());
    if(board.whatIsAtSquare(firstSquare) != null){
      return false;
    }
    // false if second square occupied
    Square secondSquare = new Square(this.square.getRow() + direction * 2, this.square.getColumn());
    if(board.whatIsAtSquare(secondSquare) != null){
      return false;
    }
    return true;
  }

  @Override
  public HashSet<Square> canMoveTo(Board board) {
    int direction = this.isWhite ? 1 : -1;

    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    // can move forward 1 square
    Square nextMove = new Square(currRow + direction * 1, currCol);
    if(board.isSquareOnBoard(nextMove) && board.whatIsAtSquare(nextMove) == null){
      nextMoves.add(nextMove);
    }

    // can move forward 2 squares on first move
    if(this.canMoveTwoSquares(board)){
      nextMove = new Square(currRow + direction * 2, currCol);
      nextMoves.add(nextMove);
    }

    // can capture on adjacent forward diagonals
    nextMove = new Square(currRow + direction * 1, currCol - 1);
    if(this.canCaptureOn(nextMove, board)){
      nextMoves.add(nextMove);
    }
    nextMove = new Square(currRow + direction * 1, currCol + 1);
    if(this.canCaptureOn(nextMove, board)){
      nextMoves.add(nextMove);
    }

    return nextMoves;
  }

  private boolean noSelfPieceAt(Square square, Board board){
    Piece p = board.whatIsAtSquare(square);
    if(p == null || p.isWhitePiece() != this.isWhite){
      return true;
    }
    return false;
  }

  @Override
  public HashSet<Square> canCover(Board board) {
    int direction = this.isWhite ? 1 : -1;

    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();
    
    Square nextMove = new Square(currRow + direction * 1, currCol - 1);
    if(this.noSelfPieceAt(nextMove, board)){
      nextMoves.add(nextMove);
    }
    nextMove = new Square(currRow + direction * 1, currCol + 1);
    if(this.noSelfPieceAt(nextMove, board)){
      nextMoves.add(nextMove);
    }

    return nextMoves;
  } 
}
