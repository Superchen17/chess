package com.osullivan.chess;

public interface Board {
  public int getWidth();
  public int getHeight();
  public Piece whatIsAtSquare(Square square);

  /**
   * add piece to board, used during promotion 
   * @param p new piece
   * @return true if successful
   */
  public boolean tryAddPiece(Piece p);

  /**
   * remove piece from board, used during capture
   * @param p piece to be removed
   * @return true if successful
   */
  public boolean tryRemovePiece(Piece p);
} 
