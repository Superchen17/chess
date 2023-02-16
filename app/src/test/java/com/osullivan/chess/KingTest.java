package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  public void test_canMoveTo_withPiecesAround() {
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
}
