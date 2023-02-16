package com.osullivan.chess;

import java.util.HashSet;

public interface Board {
  public int getWidth();
  public int getHeight();

  /**
   * get all pieces of a player
   * @param isWhite true if is white
   * @return set of pieces
   */
  public HashSet<Piece> getTeamPieces(boolean isWhite);

  /**
   * check if the given square is on board
   * @param square
   * @return true if on board
   */
  public boolean isSquareOnBoard(Square s);

  /**
   * get the piece at that square
   * @param square
   * @return piece at that square
   */
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
