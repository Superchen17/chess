package com.osullivan.chess;

public class PieceTextView {
  private final Piece piece;

  public PieceTextView(Piece piece){
    this.piece = piece;
  }

  private String displayPieceForWhite(){
    return this.piece.getName().toUpperCase();
  }

  private String displayPieceForBlack(){
    return this.piece.getName().toLowerCase();
  }

  public String displayPiece(){
    if(this.piece.isWhitePiece()){
      return this.displayPieceForWhite();
    }
    return this.displayPieceForBlack();
  }
}
