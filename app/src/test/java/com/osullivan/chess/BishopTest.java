package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BishopTest {
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
      4 | | | |B| | | | |
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
  public void helper_canMoveTo1() {
    Piece p = new Bishop("B", true, new Square("d4"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    
    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("c5"));
    expectedMoves.add(new Square("b6"));
    expectedMoves.add(new Square("a7"));

    expectedMoves.add(new Square("e5"));
    expectedMoves.add(new Square("f6"));
    expectedMoves.add(new Square("g7"));
    expectedMoves.add(new Square("h8"));

    expectedMoves.add(new Square("e3"));
    expectedMoves.add(new Square("f2"));
    expectedMoves.add(new Square("g1"));

    expectedMoves.add(new Square("c3"));
    expectedMoves.add(new Square("b2"));
    expectedMoves.add(new Square("a1"));
    
    assertEquals(expectedMoves, actualMoves);
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 |B| | | | | | | |
        -----------------
      6 | | | | | |b| | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | | | |B| | | | |
        -----------------
      3 | | |B| | | |B| |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void helper_canMoveTo2() {
    Piece p = new Bishop("B", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Bishop("B", true, new Square("a7")));
    existingPieces.add(new Bishop("B", true, new Square("c3")));
    existingPieces.add(new Bishop("B", false, new Square("f6")));
    existingPieces.add(new Bishop("B", true, new Square("g3")));

    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);
    
    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("c5"));
    expectedMoves.add(new Square("b6"));

    expectedMoves.add(new Square("e5"));
    expectedMoves.add(new Square("f6"));

    expectedMoves.add(new Square("e3"));
    expectedMoves.add(new Square("f2"));
    expectedMoves.add(new Square("g1"));
    
    assertEquals(expectedMoves, actualMoves);
  }
}
