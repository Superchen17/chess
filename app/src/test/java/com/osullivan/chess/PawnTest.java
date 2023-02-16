package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class PawnTest {
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
      2 | |P| | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_clear() {
    Piece p = new Pawn("P", true, new Square("b2"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b3"));
    expectedMoves.add(new Square("b4"));
    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | |p| |
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
  public void test_canMoveTo_black_clear() {
    Piece p = new Pawn("P", false, new Square("g7"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("g6"));
    expectedMoves.add(new Square("g5"));
    assertEquals(expectedMoves, p.canMoveTo(b));
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
      3 | |N| | | | | | |
        -----------------
      2 | |P| | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_1stBlocked() {
    Piece p = new Pawn("P", true, new Square("b2"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Knight("N", true, new Square("b3")));

    assertEquals(new HashSet<>(), p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | |p| |
        -----------------
      6 | | | | | | |n| |
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
  public void test_canMoveTo_black_1stBlocked() {
    Piece p = new Pawn("P", false, new Square("g7"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Knight("N", false, new Square("g6")));

    assertEquals(new HashSet<>(), p.canMoveTo(b));
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
      4 | |N| | | | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | |P| | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_2ndBlocked() {
    Piece p = new Pawn("P", true, new Square("b2"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Knight("N", true, new Square("b4")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b3"));
    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | |p| |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | | | |n| |
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
  public void test_canMoveTo_black_2ndBlocked() {
    Piece p = new Pawn("P", false, new Square("g7"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Knight("N", false, new Square("g5")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("g6"));
    assertEquals(expectedMoves, p.canMoveTo(b));
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
      3 |b| |b| | | | | |
        -----------------
      2 | |P| | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_clearWithCaptures() {
    Piece p = new Pawn("P", true, new Square("b2"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Bishop("B", false, new Square("a3")));
    b.tryAddPiece(new Bishop("B", false, new Square("c3")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b3"));
    expectedMoves.add(new Square("b4"));
    expectedMoves.add(new Square("a3"));
    expectedMoves.add(new Square("c3"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | |p| |
        -----------------
      6 | | | | | |B| |B|
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
  public void test_canMoveTo_black_clearWithCaptures() {
    Piece p = new Pawn("P", false, new Square("g7"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Bishop("B", true, new Square("f6")));
    b.tryAddPiece(new Bishop("B", true, new Square("h6")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("g6"));
    expectedMoves.add(new Square("g5"));
    expectedMoves.add(new Square("f6"));
    expectedMoves.add(new Square("h6"));

    assertEquals(expectedMoves, p.canMoveTo(b));
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
      3 | |b| | | | | | |
        -----------------
      2 |P| | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_cornerWithCaptures() {
    Piece p = new Pawn("P", true, new Square("a2"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Bishop("B", false, new Square("b3")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("a3"));
    expectedMoves.add(new Square("a4"));
    expectedMoves.add(new Square("b3"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | |p|
        -----------------
      6 | | | | | | |B| |
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
  public void test_canMoveTo_black_cornerWithCaptures() {
    Piece p = new Pawn("P", false, new Square("h7"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Bishop("B", true, new Square("g6")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("h6"));
    expectedMoves.add(new Square("h5"));
    expectedMoves.add(new Square("g6"));

    assertEquals(expectedMoves, p.canMoveTo(b));
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
      3 | |P| | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_clearAlreadyMoved() {
    Piece p = new Pawn("P", true, new Square("b3"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b4"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | |p| |
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
  public void test_canMoveTo_black_clearAlreadyMoved() {
    Piece p = new Pawn("P", false, new Square("g6"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("g5"));

    assertEquals(expectedMoves, p.canMoveTo(b));
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
      4 | |Q| | | | | | |
        -----------------
      3 | |P| | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_blockedAlreadyMoved() {
    Piece p = new Pawn("P", true, new Square("b3"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Queen("Q", true, new Square("b4")));

    assertEquals(new HashSet<>(), p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | |p| |
        -----------------
      5 | | | | | | |q| |
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
  public void test_canMoveTo_black_blockedAlreadyMoved() {
    Piece p = new Pawn("P", false, new Square("g6"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Queen("Q", true, new Square("g5")));

    assertEquals(new HashSet<>(), p.canMoveTo(b));
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
      4 | | |q| | | | | |
        -----------------
      3 | |P| | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_white_AlreadyMovedWithCapture() {
    Piece p = new Pawn("P", true, new Square("b3"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Queen("Q", false, new Square("c4")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b4"));
    expectedMoves.add(new Square("c4"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | |p| |
        -----------------
      5 | | | | | |Q| | |
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
  public void test_canMoveTo_black_AlreadyMovedWithCapture() {
    Piece p = new Pawn("P", false, new Square("g6"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Queen("Q", true, new Square("f5")));

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("g5"));
    expectedMoves.add(new Square("f5"));

    assertEquals(expectedMoves, p.canMoveTo(b));
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
      2 | |P| | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_sanity1() {
    // testing move logic with invalid placement
    // it is impossible to have white Pawn on rank 2 which is already moved
    Piece p = new Pawn("P", true, new Square("b2"), 1);
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b3"));
    assertEquals(expectedMoves, p.canMoveTo(b));
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
      3 | |P| | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_sanity2() {
    // testing move logic with invalid placement
    // it is impossible to have white Pawn on rank 3 which has not been moved
    Piece p = new Pawn("P", true, new Square("b3"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b4"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | |p| | | |
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
  public void test_canMoveTo_sanity3() {
    // testing move logic with invalid placement
    // it is impossible to have black Pawn on rank 6 which has not been moved
    Piece p = new Pawn("P", false, new Square("e6"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("e5"));

    assertEquals(expectedMoves, p.canMoveTo(b));
  }
}