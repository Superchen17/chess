package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
