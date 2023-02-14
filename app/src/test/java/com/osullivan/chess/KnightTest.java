package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class KnightTest {
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
      4 | | | |K| | | | |
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
  public void test_canMoveTo_empty(){
    Piece p = new Knight("K", true, new Square("d4"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("b5"));
    actualMoves.add(new Square("c6"));
    actualMoves.add(new Square("e6"));
    actualMoves.add(new Square("f5"));
    actualMoves.add(new Square("f3"));
    actualMoves.add(new Square("e2"));
    actualMoves.add(new Square("c2"));
    actualMoves.add(new Square("b3"));

    assertEquals(expectedMoves, actualMoves);
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
      4 | | | | | | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 |K| | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_corner1(){
    Piece p = new Knight("K", true, new Square("a1"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("b3"));
    actualMoves.add(new Square("c2"));

    assertEquals(expectedMoves, actualMoves);
  }

  /**
   *    -----------------
      8 |K| | | | | | | |
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
  public void test_canMoveTo_corner2(){
    Piece p = new Knight("K", true, new Square("a8"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("b6"));
    actualMoves.add(new Square("c7"));

    assertEquals(expectedMoves, actualMoves);
  }

  /**
   *    -----------------
      8 | | | | | | | |K|
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
  public void test_canMoveTo_corner3(){
    Piece p = new Knight("K", true, new Square("h8"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("f7"));
    actualMoves.add(new Square("g6"));

    assertEquals(expectedMoves, actualMoves);
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
      4 | | | | | | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | |K|
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_corner4(){
    Piece p = new Knight("K", true, new Square("h1"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("f2"));
    actualMoves.add(new Square("g3"));

    assertEquals(expectedMoves, actualMoves);
  }


    /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | |r| | | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | |K| | | | | | |
        -----------------
      3 | | | |R| | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_withOwnAndEnemyPieces(){
    Piece p = new Knight("K", true, new Square("b4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", false, new Square("c6"), true));
    existingPieces.add(new Rook("R", true, new Square("d3"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = p.canMoveTo(b);
    HashSet<Square> actualMoves = new HashSet<>();
    actualMoves.add(new Square("a6"));
    actualMoves.add(new Square("c6"));
    actualMoves.add(new Square("d5"));
    actualMoves.add(new Square("c2"));
    actualMoves.add(new Square("a2"));

    assertEquals(expectedMoves, actualMoves);
  }
}
