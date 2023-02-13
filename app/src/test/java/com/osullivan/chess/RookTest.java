package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RookTest {
  private void helper_test_canMoveTo(Piece p, Board b, int rowStart, int rowEnd, int colStart, int ColEnd){
    b.tryAddPiece(p);

    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    for(int i = colStart; i <= ColEnd; i++){
      if(i == p.getSquare().getColumn()){
        continue;
      }
      expectedMoves.add(new Square(p.getSquare().getRow(), i));
    }
    for(int i = rowStart; i <= rowEnd; i++){
      if(i == p.getSquare().getRow()){
        continue;
      }
      expectedMoves.add(new Square(i, p.getSquare().getColumn()));
    }
    assertEquals(expectedMoves, actualMoves);
  }


  @Test
  public void test_constructor(){
    Piece p = new Rook("R", true, new Square("d4"), true);

    assertTrue(p.isWhitePiece());
    assertTrue(((Rook)p).canCastle());
    assertTrue(((Rook)p).isKingSideRook());
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | | | |R| | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo1(){
    Piece p = new Rook("R", true, new Square("d4"), true);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    this.helper_test_canMoveTo(p, b, 1, 8, 1, 8);
  }

   /**
   *    -----------------
      8 | | | |R| | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 |R| | |R| |r| | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | |r| |R| | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo2(){
    Piece p = new Rook("R", true, new Square("d4"), true);
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", true, new Square("d8"), true));
    existingPieces.add(new Rook("R", false, new Square("d2"), true));
    existingPieces.add(new Rook("R", true, new Square("a4"), true));
    existingPieces.add(new Rook("R", false, new Square("f4"), true));
    existingPieces.add(new Rook("R", true, new Square("f2"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    this.helper_test_canMoveTo(p, b, 2, 7, 2, 6);
  }

  /**
   *    -----------------
      8 | | | | | | | |R|
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | | | | | | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo3(){
    Piece p = new Rook("R", true, new Square("h8"), true);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    this.helper_test_canMoveTo(p, b, 1, 7, 1, 7);
  }
}
