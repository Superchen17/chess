package com.osullivan.chess;

import java.util.HashSet;

public abstract class Piece {
  protected final String name;
  protected final boolean isWhite;
  protected Square square;
  protected int moveCounter = 0;

  public Piece(String name, boolean isWhite, Square square) {
    this.name = name;
    this.isWhite = isWhite;
    this.square = square;
  }

  public String getName(){
    return this.name;
  }
  
  public boolean isWhitePiece(){
    return this.isWhite;
  }

  public Square getSquare() {
    return this.square;
  }

  public void setSquare(Square newSquare){
    this.square = newSquare;
  }

  public int getMoveCounter(){
    return this.moveCounter;
  }

  public void incrementMoveCounter() {
    this.moveCounter++;
  }

  /**
   * check if this piece is protected by other team pieces
   * @param board
   * @return true if yes
   */
  public boolean canBeProtected(Board board){
    board.tryRemovePiece(this);
    boolean result = this.square.canBeCoveredAssumingEmptyBy(isWhite, board);
    board.tryAddPiece(this);
    return result;
  }

  /**
   * 
   * @param square square try to be added to valid moves (won't be added if occupied by own piece)
   * @param nextMoves set of valid moves
   * @param board board
   * @return true if current square is blocking
   */
  private boolean blockedAfterTryAddingSquare(Square square, HashSet<Square> nextMoves, Board board){
    Piece pieceAtCurrSquare = board.whatIsAtSquare(square);       
    if(pieceAtCurrSquare != null){
      if(pieceAtCurrSquare.isWhite != this.isWhite){
        nextMoves.add(square);
      }
      return true;
    }
    nextMoves.add(square);
    return false;
  }

  /**
   * check where the piece can move up in a straight line
   * used for Rook and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveUpTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow + 1;
    while(i <= board.getHeight()){
      Square currSquare = new Square(i, currCol);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i++;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move down in a straight line
   * used for Rook and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveDownTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow - 1;
    while(i >= 1){
      Square currSquare = new Square(i, currCol);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i--;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move left in a straight line
   * used for Rook and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveLeftTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currCol - 1;
    while(i >= 1){
      Square currSquare = new Square(currRow, i);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i--;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move right in a straight line
   * used for Rook and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveRightTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currCol + 1;
    while(i <= board.getWidth()){
      Square currSquare = new Square(currRow, i);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i++;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move along the up left diagonal
   * used for Bishop and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveUpLeftDiagonalTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow + 1;
    int j = currCol - 1;
    while(i <= board.getHeight() && j >= 1){
      Square currSquare = new Square(i, j);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i++;
      j--;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move along the up right diagonal
   * used for Bishop and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveUpRightDiagonalTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow + 1;
    int j = currCol + 1;
    while(i <= board.getHeight() && j <= board.getWidth()){
      Square currSquare = new Square(i, j);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i++;
      j++;
    }
    return nextMoves;
  }

  /**
   * check where the piece can move along the down left diagonal
   * used for Bishop and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveDownLeftDiagonalTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow - 1;
    int j = currCol - 1;
    while(i >= 1 && j >= 1){
      Square currSquare = new Square(i, j);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i--;
      j--;
    }
    return nextMoves;
  }


  /**
   * check where the piece can move along the down right diagonal
   * used for Bishop and Queen
   * @param board
   * @return
   */
  protected HashSet<Square> canMoveDownRightDiagonalTo(Board board){
    HashSet<Square> nextMoves = new HashSet<>();
    int currRow = this.square.getRow();
    int currCol = this.square.getColumn();

    int i = currRow - 1;
    int j = currCol + 1;
    while(i >= 1 && j <= board.getWidth()){
      Square currSquare = new Square(i, j);
      if(this.blockedAfterTryAddingSquare(currSquare, nextMoves, board)){
        break;
      }
      i--;
      j++;
    }
    return nextMoves;
  }

  /**
   * get a set of squares that the piece can move to,
   * including the squares that the piece can capture
   * excluding castling, en passant and promotions
   * @param board
   * @return
   */
  public abstract HashSet<Square> canMoveTo(Board board);

  /**
   * move the piece to distination square and capture
   * @param newSquare
   * @param board
   * @return true if successful otherwise false
   */
  public String tryMoveTo(Square newSquare, Board board){
     // check if newSquare in valid moves
     HashSet<Square> canMoveToSquares = this.canMoveTo(board);
     if(!canMoveToSquares.contains(newSquare)){
       return "cannot move from " + this.square.toString() + " to " + newSquare.toString();
     }
 
     // capture at new square
     Piece p = board.whatIsAtSquare(newSquare);
     if(p != null){
       if(!board.tryRemovePiece(p)){
         return "cannot capture at " + newSquare.toString();
       }
     }
 
     // set piece to new square
     this.square = newSquare;
     this.moveCounter++;
     return null;
  }
}
