package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class QueenTest {

  private HashSet<Square> getCanMoveToUDLR(Piece p, Board b, Square left, Square right, Square top, Square bottom){
    // basic sanity checks
    assertEquals(left.getRow(), right.getRow());
    assertEquals(top.getColumn(), bottom.getColumn());
    assertEquals(left.getRow(), p.getSquare().getRow());
    assertEquals(top.getColumn(), p.getSquare().getColumn());

    HashSet<Square> expectedMoves = new HashSet<>();
    for(int i = left.getColumn(); i <= right.getColumn(); i++){
      if(i == p.getSquare().getColumn()){
        continue;
      }
      expectedMoves.add(new Square(left.getRow(), i));
    }
    for(int i = bottom.getRow(); i <= top.getRow(); i++){
      if(i == p.getSquare().getRow()){
        continue;
      }
      expectedMoves.add(new Square(i, top.getColumn()));
    }
    return expectedMoves;
  }

  private HashSet<Square> getCanMoveToDiagonals(Piece p, Board b, Square topLeft, Square bottomLeft, Square topRight, Square bottomRight) {
    HashSet<Square> expectedMoves = new HashSet<>();

    int i = topLeft.getRow();
    int j = topLeft.getColumn();
    // from top-left to bottom-right
    while(i >= bottomRight.getRow() && j <= bottomRight.getColumn()){
      Square square = new Square(i, j);
      if(!square.equals(p.getSquare())){
        expectedMoves.add(square);
      }
      i--;
      j++;
    }
    // from bottom-left to top-right
    i = bottomLeft.getRow();
    j = bottomLeft.getColumn();
    while(i <= topRight.getRow() && j <= topRight.getColumn()){
      Square square = new Square(i, j);
      if(!square.equals(p.getSquare())){
        expectedMoves.add(square);
      }
      i++;
      j++;
    }
    return expectedMoves;
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
      4 | | | |Q| | | | |
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
  public void test_canMoveTo1() {
    Piece p = new Queen("Q", true, new Square("d4"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.addAll(this.getCanMoveToUDLR(p, b, 
      new Square("a4"), new Square("h4"), new Square("d8"), new Square("d1")
    ));
    expectedMoves.addAll(this.getCanMoveToDiagonals(p, b, 
      new Square("a7"), new Square("a1"), new Square("h8"), new Square("g1")
    ));
    assertEquals(expectedMoves, actualMoves);
  }

  /**
   *    -----------------
      8 | | | |Q| | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | |q| |q| | | | |
        -----------------
      5 | | | | |Q| | | |
        -----------------
      4 | |Q| |Q| | | | |
        -----------------
      3 | | | | | |q| | |
        -----------------
      2 | | | |Q| | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  @Test
  public void test_canMoveTo2() {
    Piece p = new Queen("Q", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Queen("Q", false, new Square("b6")));
    existingPieces.add(new Queen("Q", true, new Square("b4")));
    existingPieces.add(new Queen("Q", true, new Square("d8")));
    existingPieces.add(new Queen("Q", false, new Square("d6")));
    existingPieces.add(new Queen("Q", true, new Square("d2")));
    existingPieces.add(new Queen("Q", true, new Square("e5")));
    existingPieces.add(new Queen("Q", false, new Square("f3")));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.addAll(this.getCanMoveToUDLR(p, b, 
      new Square("c4"), new Square("h4"), new Square("d6"), new Square("d3")
    ));
    expectedMoves.addAll(this.getCanMoveToDiagonals(p, b, 
      new Square("b6"), new Square("a1"), new Square("c3"), new Square("g1")
    ));
    assertEquals(expectedMoves, actualMoves);
  }

  /**
   *    -----------------
      8 |Q| | | | | | | |
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
  public void test_canMoveTo3() {
    Piece p = new Queen("Q", true, new Square("a8"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);
    HashSet<Square> actualMoves = p.canMoveTo(b);
    HashSet<Square> expectedMoves = new HashSet<>();
    expectedMoves.addAll(this.getCanMoveToUDLR(p, b, 
      new Square("b8"), new Square("h8"), new Square("a7"), new Square("a1")
    ));
    expectedMoves.addAll(this.getCanMoveToDiagonals(p, b, 
      new Square("b7"), new Square("a8"), new Square("a8"), new Square("h1")
    ));
    assertEquals(expectedMoves, actualMoves);
  }

}
