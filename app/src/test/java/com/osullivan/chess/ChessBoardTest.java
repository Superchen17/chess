package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class ChessBoardTest {
  @Test
  public void test_defaultConstructor(){
    Board board = new ChessBoard(5, 4);
    assertEquals(4, board.getHeight());
    assertEquals(5, board.getWidth());

    for(int i = 1; i <= board.getHeight(); i++){
      for(int j = 1; j <= board.getWidth(); j++){
        Square square = new Square(i, j);
        assertNull(board.whatIsAtSquare(square));
      }
    }
  }

  private void helper_test_testConstructor_invalid(String squareStr, int expectedBoardWidth, int expectedBoardHeight){
    HashSet<Piece> pieces = new HashSet<>();
    Piece r1 = new Rook("R", true, new Square(squareStr), true);

    pieces.add(r1);

    assertThrows(
      IllegalArgumentException.class, 
      () -> new ChessBoard(expectedBoardWidth, expectedBoardHeight, pieces)
    );
  }

  @Test
  public void test_testConstructor_invalid(){
    this.helper_test_testConstructor_invalid("d3", 3, 4);
    this.helper_test_testConstructor_invalid("c5", 3, 4);
    this.helper_test_testConstructor_invalid("c9", 8, 8);
    this.helper_test_testConstructor_invalid("i5", 8, 8);
  }

  @Test
  public void test_whatIsAtSquare(){
    HashSet<Piece> pieces = new HashSet<>();
    Rook r1 = new Rook("R", true, new Square("a1"), true);
    Rook r2 = new Rook("R", true, new Square("b4"), true);

    pieces.add(r1);
    pieces.add(r2);

    Board board = new ChessBoard(8, 8, pieces);

    assertSame(r1, board.whatIsAtSquare(new Square("a1")));
    assertSame(r2, board.whatIsAtSquare(new Square("b4")));
  }

  @Test
  public void test_getTeamPieces() {
    HashSet<Piece> pieces = new HashSet<>();
    Piece p1 = new Rook("R", true, new Square("a1"), true);
    Piece p2 = new Knight("N", false, new Square("a2"));
    Piece p3 = new Bishop("B", true, new Square("a3"));
    Piece p4 = new Queen("Q", false, new Square("a4"));
    pieces.add(p1);
    pieces.add(p2);
    pieces.add(p3);
    pieces.add(p4);
    Board b = new ChessBoard(8, 8, pieces);

    HashSet<Piece> whitePieces = new HashSet<>();
    whitePieces.add(p1);
    whitePieces.add(p3);

    HashSet<Piece> blackPieces = new HashSet<>();
    blackPieces.add(p2);
    blackPieces.add(p4);

    assertEquals(whitePieces, b.getTeamPieces(true));
    assertEquals(blackPieces, b.getTeamPieces(false));
  }

  @Test
  public void test_tryAddPiece() {
    Board b = new ChessBoard(8, 8, new HashSet<>());
    Piece p1 = new Knight("K", true, new Square("a1"));
    Piece p2 = new Knight("K", true, new Square(5, 9));
    Piece p3 = new Bishop("B", false, new Square("a1"));

    assertTrue(b.tryAddPiece(p1));
    assertFalse(b.tryAddPiece(p2));
    assertFalse(b.tryAddPiece(p3));

    assertEquals(p1, b.whatIsAtSquare(new Square("a1")));
  }

  @Test
  public void test_tryRemovePiece() {
    Board b = new ChessBoard(8, 8, new HashSet<>());
    Piece p1 = new Knight("K", true, new Square("a1"));
    Piece p2 = new Bishop("B", true, new Square("a2"));
    b.tryAddPiece(p1);

    assertEquals(p1, b.whatIsAtSquare(new Square("a1")));
    assertTrue(b.tryRemovePiece(p1));
    assertFalse(b.tryRemovePiece(p2));
    assertFalse(b.tryRemovePiece(p1));
    assertEquals(new HashSet<>(), b.getTeamPieces(true));
  }
}
