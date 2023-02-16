package com.osullivan.chess;

import java.util.HashSet;

public class ChessBoard implements Board {
  private final int width;
  private final int height;
  private HashSet<Piece> pieces;

  @Override
  public boolean isSquareOnBoard(Square s){
    if(s.getRow() < 1 || s.getRow() > this.height || s.getColumn() < 1 || s.getColumn() > this.width){
        return false;
      }
      return true;
  }

  public ChessBoard(int width, int height){
    this.width = width;
    this.height = height;
    this.pieces = new HashSet<>();
  }

  /**
   * testing constructor to set arbitrary pieces
   * DO NOT invoke by other classes
   * @param width
   * @param height
   * @param pieces
   */
  public ChessBoard(int width, int height, HashSet<Piece> pieces){
    this.width = width;
    this.height = height;

    for(Piece p: pieces){
      if(!this.isSquareOnBoard(p.getSquare())){
        throw new IllegalArgumentException(
          "cannot set piece at \'" + p.getSquare().toString() + "\' for board of size " + 
          Integer.toString(this.height) + " x " +  Integer.toString(this.width)
        );
      }
    }
    this.pieces = pieces;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public HashSet<Piece> getTeamPieces(boolean isWhite){
    HashSet<Piece> teamPieces = new HashSet<>();
    for(Piece p: this.pieces){
      if(p.isWhite == isWhite){
        teamPieces.add(p);
      }
    }
    return teamPieces;
  }

  @Override
  public Piece whatIsAtSquare(Square square){
    for(Piece p: this.pieces){
      if(p.getSquare().equals(square)){
        return p;
      }
    } 
    return null; 
  }

  @Override
  public boolean tryAddPiece(Piece p) {
    // check if piece is off board
    if(!this.isSquareOnBoard(p.getSquare())){
      return false;
    }

    // check if piece collide with other pieces
    for(Piece exitingPiece: this.pieces){
      if(p.getSquare().equals(exitingPiece.getSquare())){
        return false;
      }
    }
    this.pieces.add(p);
    return true;
  }

  @Override
  public boolean tryRemovePiece(Piece p){
    if(this.pieces.contains(p)){
      this.pieces.remove(p);
      return true;
    }
    return false;
  }

}
