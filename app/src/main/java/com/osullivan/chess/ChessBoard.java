package com.osullivan.chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChessBoard implements Board {
  private final int width;
  private final int height;
  private HashSet<Piece> pieces;
  private List<Move> moveLog;

  @Override
  public boolean isSquareOnBoard(Square s){
    if(s.getRow() < 1 || s.getRow() > this.height || s.getColumn() < 1 || s.getColumn() > this.width){
        return false;
      }
      return true;
  }

  private void setUpPawns(boolean isWhite){
    for(int i = 1; i <= this.width; i++){
      Square square = new Square(isWhite ? 2 : this.height -2 + 1, i);
      this.pieces.add(new Pawn("P", isWhite, square));
    }    
  }

  private void setUpFirstRankPieces(boolean isWhite){
    int row = isWhite ? 1 : this.height;

    // pair of Rooks
    this.pieces.add(new Rook("R", isWhite, new Square(row, 1), true));
    this.pieces.add(new Rook("R", isWhite, new Square(row, 8), false));
    // pair of Knights
    this.pieces.add(new Knight("N", isWhite, new Square(row, 2)));
    this.pieces.add(new Knight("N", isWhite, new Square(row, 7)));
    // pair of Bishops
    this.pieces.add(new Bishop("B", isWhite, new Square(row, 3)));
    this.pieces.add(new Bishop("B", isWhite, new Square(row, 6)));
    // Queen
    this.pieces.add(new Queen("Q", isWhite, new Square(row, 4)));
    this.pieces.add(new King("K", isWhite, new Square(row, 5)));
  }

  private void setUpPieces(){
    this.setUpPawns(true);
    this.setUpFirstRankPieces(true);
    this.setUpPawns(false);
    this.setUpFirstRankPieces(false);
  }

  public ChessBoard(int width, int height){
    this.width = width;
    this.height = height;
    this.pieces = new HashSet<>();
    this.setUpPieces();
    this.moveLog = new ArrayList<>();
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
    this.moveLog = new ArrayList<>();
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

  @Override
  public void appendMoveLog(Move move) {
    this.moveLog.add(move);
  }

  @Override
  public List<Move> getMoveLog() {
    return this.moveLog;
  }
}
