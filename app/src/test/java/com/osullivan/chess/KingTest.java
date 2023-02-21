package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class KingTest {
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
  public void test_canMoveTo_clear() {
    Piece p = new King("K", true, new Square("d4"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.add(new Square("c5"));
    nextMoves.add(new Square("d5"));
    nextMoves.add(new Square("e5"));
    nextMoves.add(new Square("c4"));
    nextMoves.add(new Square("e4"));
    nextMoves.add(new Square("c3"));
    nextMoves.add(new Square("d3"));
    nextMoves.add(new Square("e3"));

    assertEquals(nextMoves, p.canMoveTo(b));
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
  public void test_canMoveTo_bottomLeft() {
    Piece p = new King("K", true, new Square("a1"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.add(new Square("a2"));
    nextMoves.add(new Square("b2"));
    nextMoves.add(new Square("b1"));

    assertEquals(nextMoves, p.canMoveTo(b));
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
  public void test_canMoveTo_topRight() {
    Piece p = new King("K", true, new Square("h8"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.add(new Square("g8"));
    nextMoves.add(new Square("g7"));
    nextMoves.add(new Square("h7"));

    assertEquals(nextMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | |N| | | |
        -----------------
      4 | | |n|K|r| | | |
        -----------------
      3 | |q| | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo_withPiecesAround1() {
    Piece p = new King("K", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Queen("Q", false, new Square("b3")));
    existingPieces.add(new Knight("N", false, new Square("c4")));
    existingPieces.add(new Knight("N", true, new Square("e5")));
    existingPieces.add(new Rook("R", false, new Square("e4"), true));

    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.add(new Square("c5"));
    nextMoves.add(new Square("d5"));
    nextMoves.add(new Square("e4"));

    assertEquals(nextMoves, p.canMoveTo(b));
  }

  /**
   *    -----------------
     8 | | | | | | | | |
        -----------------
     7 | | | | | | | | |
        -----------------
     6 | | | | | | | | |
        -----------------
     5 | | |b| |N| | | |
        -----------------
     4 | | | |K|r| | | |
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
  public void test_canMoveTo_withPiecesAround2() {
    Piece p = new King("K", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Bishop("B", false, new Square("c5")));
    existingPieces.add(new Knight("N", true, new Square("e5")));
    existingPieces.add(new Rook("R", false, new Square("e4"), true));

    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> nextMoves = new HashSet<>();
    nextMoves.add(new Square("c5"));
    nextMoves.add(new Square("d5"));
    nextMoves.add(new Square("c3"));
    nextMoves.add(new Square("d3"));
    nextMoves.add(new Square("e4"));

    assertEquals(nextMoves, p.canMoveTo(b));
    assertEquals(b.whatIsAtSquare(new Square("d4")), p);
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
     2 | |r| | |K| | | |
        -----------------
     1 | | | | | | | | |
        -----------------
        a b c d e f g h
   */
  @Test
  public void test_canMoveTo_check() {
    Piece p = new King("K", true, new Square("e2"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", false, new Square("b2"),true));

    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("d3"));
    expectedMoves.add(new Square("e3"));
    expectedMoves.add(new Square("f3"));
    expectedMoves.add(new Square("d1"));
    expectedMoves.add(new Square("e1"));
    expectedMoves.add(new Square("f1"));

    assertEquals(expectedMoves, p.canMoveTo(b));
    assertTrue(b.getTeamPieces(true).contains(p));
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
        2 | |r| | |q| | | |
          -----------------
        1 | | | | |K| | | |
          -----------------
           a b c d e f g h
     */
  @Test
  public void test_canMoveTo_mate() {
    Piece p = new King("K", true, new Square("e1"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Queen("Q", false, new Square("e2")));
    existingPieces.add(new Rook("R", false, new Square("b2"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    assertEquals(new HashSet<>(), p.canMoveTo(b));
  }

  /**
    *  -----------------
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
     2 | |r|K|b| | | | |
       -----------------
     1 | | | | | | | | |
       -----------------
        a b c d e f g h
   */
  @Test
  public void test_canMoveTo_notCoveredButCannotTake(){
    // black Bishop is not covered, but cannot be taken
    Piece p = new King("K", true, new Square("c2"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", false, new Square("b2"), true));
    existingPieces.add(new Bishop("B", false, new Square("d2")));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.add(new Square("b2"));
    expectedMoves.add(new Square("d3"));
    expectedMoves.add(new Square("d1"));

    assertEquals(expectedMoves, p.canMoveTo(b));
    assertTrue(b.getTeamPieces(true).contains(p));
  }

  /**
   *  -----------------
    8 |r|n| | | |r| | |
      -----------------
    7 |p|b|p|p|q| |p| |
      -----------------
    6 | |p| | |p|N| | |
      -----------------
    5 | | | | | | |k| |
      -----------------
    4 | | | |P| | |N|P|
      -----------------
    3 | | | |B| | | | |
      -----------------
    2 |P|P|P| | |P|P| |
      -----------------
    1 |R| | | |K| | |R|
      -----------------
       a b c d e f g h
   */
  @Test
  public void test_canMoveTo_complicated(){
    Piece p = new King("K", false, new Square("g5"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    b.tryAddPiece(new Knight("K", true, new Square("f6")));
    b.tryAddPiece(new Knight("K", true, new Square("g4")));
    b.tryAddPiece(new Pawn("P", true, new Square("h4")));
    b.tryAddPiece(new Bishop("B", true, new Square("d3")));
    b.tryAddPiece(new Rook("R", true, new Square("h1"), true));
    b.tryAddPiece(new Pawn("P", true, new Square("f2")));

    HashSet<Square> expected = new HashSet<>();
    expected.add(new Square("f4"));
    assertEquals(expected, p.canMoveTo(b));
  }
}
