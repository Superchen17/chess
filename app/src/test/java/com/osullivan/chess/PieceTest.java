package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class PieceTest {

  /**
   * -----------------------------------------------------------------------------------------------
   * private helpers
   * -----------------------------------------------------------------------------------------------
   */
  private void helper_test_canMoveAnyDirTo_onEdge(Piece p, Function<Board, HashSet<Square>> getMoveToFn){
    Board board = new ChessBoard(8, 8, new HashSet<>());
    board.tryAddPiece(p);
    HashSet<Square> actualSquares = getMoveToFn.apply(board);
    HashSet<Square> expectedSquares = new HashSet<>();
    assertEquals(expectedSquares, actualSquares);
  }

  private void helper_test_canMoveAnyDirTo_ownPieceBlocking(
    Piece p1, String p2Square, HashSet<Square> expectedSquares, Function<Board, HashSet<Square>> getMoveToFn
  ){
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", p1.isWhitePiece(), new Square(p2Square), true));
    Board board = new ChessBoard(8, 8, existingPieces);
    board.tryAddPiece(p1);

    HashSet<Square> actualSquares = getMoveToFn.apply(board);
    assertEquals(expectedSquares, actualSquares);
  }

  private void helper_test_canMoveAnyDirTo_enemyPieceBlocking(
    Piece p1, String p2Square, HashSet<Square> expectedSquares, Function<Board, HashSet<Square>> getMoveToFn
  ){
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", !p1.isWhitePiece(), new Square(p2Square), true));
    Board board = new ChessBoard(8, 8, existingPieces);
    board.tryAddPiece(p1);

    HashSet<Square> actualSquares = getMoveToFn.apply(board);
    assertEquals(expectedSquares, actualSquares);
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveUpTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveUpTo_cleanPath(){
    Piece p = new Rook("R", true, new Square("d4"), true);
    Board board = new ChessBoard(8, 8, new HashSet<>());

    board.tryAddPiece(p);
    HashSet<Square> actualSquares  = p.canMoveUpTo(board);
    HashSet<Square> expectedSquares = new HashSet<>();
    for(int i = p.getSquare().getRow() + 1; i <= board.getHeight(); i++){
      Square square = new Square(i, p.getSquare().getColumn());
      expectedSquares.add(square);
    }
    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveUpTo_alreadyOnTop() {
    Piece p = new Rook("R", true, new Square("d8"), true);
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveUpTo(b));
  }

  @Test
  public void test_canMoveUpTo_ownPieceBlocking(){
    Piece p = new Rook("R", true, new Square("d4"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d5"));
    expectedSquares.add(new Square("d6"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "d7", expectedSquares, (b)->p.canMoveUpTo(b));
  }

  @Test
  public void test_canMoveUpTo_enemyPieceBlocking(){
    Piece p = new Rook("R", true, new Square("d4"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d5"));
    expectedSquares.add(new Square("d6"));
    expectedSquares.add(new Square("d7"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "d7", expectedSquares, (b)->p.canMoveUpTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveDownTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveDownTo_cleanPath() {
    Piece p = new Rook("R", true, new Square("d4"), true);
    Board board = new ChessBoard(8, 8, new HashSet<>());
    board.tryAddPiece(p);

    HashSet<Square> actualSquares  = p.canMoveDownTo(board);
    HashSet<Square> expectedSquares = new HashSet<>();
    for(int i = p.getSquare().getRow() - 1; i >= 1; i--){
      Square square = new Square(i, p.getSquare().getColumn());
      expectedSquares.add(square);
    }
    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveDownTo_alreadyOnBottom(){
    Piece p = new Rook("R", true, new Square("d1"), true);
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveDownTo(b));
  }

  @Test
  public void test_canMoveDownTo_ownPieceBlocking(){
    Piece p = new Rook("R", true, new Square("d5"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d4"));
    expectedSquares.add(new Square("d3"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "d2", expectedSquares, (b)->p.canMoveDownTo(b));
  }

  @Test
  public void test_canMoveDownTo_enemyPieceBlocking(){
    Piece p = new Rook("R", false, new Square("d5"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d4"));
    expectedSquares.add(new Square("d3"));
    expectedSquares.add(new Square("d2"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "d2", expectedSquares, (b)->p.canMoveDownTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveLeftTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveLeftTo_cleanPath() {
    Piece p = new Rook("R", true, new Square("h5"), true);
    Board board = new ChessBoard(8, 8, new HashSet<>());
    board.tryAddPiece(p);

    HashSet<Square> actualSquares  = p.canMoveLeftTo(board);
    HashSet<Square> expectedSquares = new HashSet<>();
    for(int i = p.getSquare().getColumn() - 1; i >= 1; i--){
      Square square = new Square(p.getSquare().getRow(), i);
      expectedSquares.add(square);
    }
    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveLeftTo_alreadyOnEdge(){
    Piece p = new Rook("R", true, new Square("a5"), true);
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveLeftTo(b));
  }

  @Test
  public void test_canMoveLeftTo_ownPieceBlocking(){
    Piece p = new Rook("R", true, new Square("f1"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e1"));
    expectedSquares.add(new Square("d1"));
    expectedSquares.add(new Square("c1"));
    expectedSquares.add(new Square("b1"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "a1", expectedSquares, (b)->p.canMoveLeftTo(b));
  }

  @Test
  public void test_canMoveLeftTo_enemyPieceBlocking(){
    Piece p = new Rook("R", true, new Square("f1"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e1"));
    expectedSquares.add(new Square("d1"));
    expectedSquares.add(new Square("c1"));
    expectedSquares.add(new Square("b1"));
    expectedSquares.add(new Square("a1"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "a1", expectedSquares, (b)->p.canMoveLeftTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveRightTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveRightTo_cleanPath() {
    Piece p = new Rook("R", true, new Square("c3"), true);
    Board board = new ChessBoard(8, 8, new HashSet<>());
    board.tryAddPiece(p);

    HashSet<Square> actualSquares  = p.canMoveRightTo(board);
    HashSet<Square> expectedSquares = new HashSet<>();
    for(int i = p.getSquare().getColumn() + 1; i <= board.getWidth(); i++){
      Square square = new Square(p.getSquare().getRow(), i);
      expectedSquares.add(square);
    }
    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveRightTo_alreadyOnEdge(){
    Piece p = new Rook("R", true, new Square("h3"), true);
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveRightTo(b));
  }

  @Test
  public void test_canMoveRightTo_ownPieceBlocking(){
    Piece p = new Rook("R", true, new Square("e1"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("f1"));
    expectedSquares.add(new Square("g1"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "h1", expectedSquares, (b)->p.canMoveRightTo(b));
  }

  @Test
  public void test_canMoveRightTo_enemyPieceBlocking(){
    Piece p = new Rook("R", true, new Square("c2"), true);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d2"));
    expectedSquares.add(new Square("e2"));
    expectedSquares.add(new Square("f2"));
    expectedSquares.add(new Square("g2"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "g2", expectedSquares, (b)->p.canMoveRightTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveUpLeftDiagonalTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveUpLeftDiagonalTo_cleanPath(){
    Piece p = new Bishop("B", true, new Square("d4"));
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(p);

    HashSet<Square> actualSquares = p.canMoveUpLeftDiagonalTo(b);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("c5"));
    expectedSquares.add(new Square("b6"));
    expectedSquares.add(new Square("a7"));

    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveUpLeftDiagonalTo_alreadyOnEdge(){
    Piece p = new Bishop("B", true, new Square("a8"));
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveUpLeftDiagonalTo(b));
  }

  @Test
  public void test_canMoveUpLeftDiagonalTo_ownPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("e3"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d4"));
    expectedSquares.add(new Square("c5"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "b6", expectedSquares, (b)->p.canMoveUpLeftDiagonalTo(b));
  }

  @Test
  public void test_canMoveUpLeftDiagonalTo_enemyPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("e3"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("d4"));
    expectedSquares.add(new Square("c5"));
    expectedSquares.add(new Square("b6"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "b6", expectedSquares, (b)->p.canMoveUpLeftDiagonalTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveUpRightDiagonalTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveUpRightDiagonalTo_clearPath() {
    Piece p = new Bishop("B", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", true, new Square("b5"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> actualSquares = p.canMoveUpRightDiagonalTo(b);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e5"));
    expectedSquares.add(new Square("f6"));
    expectedSquares.add(new Square("g7"));
    expectedSquares.add(new Square("h8"));

    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveUpRightDiagonalTo_alreadyOnEdge(){
    Piece p = new Bishop("B", true, new Square("h8"));
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveUpRightDiagonalTo(b));
  }

  @Test
  public void test_canMoveUpRightDiagonalTo_ownPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("d1"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e2"));
    expectedSquares.add(new Square("f3"));
    expectedSquares.add(new Square("g4"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "h5", expectedSquares, (b)->p.canMoveUpRightDiagonalTo(b));
  }

  @Test
  public void test_canMoveUpRightDiagonalTo_enemyPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("d1"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e2"));
    expectedSquares.add(new Square("f3"));
    expectedSquares.add(new Square("g4"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "g4", expectedSquares, (b)->p.canMoveUpRightDiagonalTo(b));
  }

  
  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveUpRightDiagonalTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveDownLeftDiagonalTo_clearPath() {
    Piece p = new Bishop("B", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", true, new Square("c2"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> actualSquares = p.canMoveDownLeftDiagonalTo(b);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("a1"));
    expectedSquares.add(new Square("b2"));
    expectedSquares.add(new Square("c3"));

    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveDownLeftDiagonalTo_alreadyOnEdge(){
    Piece p = new Bishop("B", true, new Square("a1"));
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveDownLeftDiagonalTo(b));
  }

  @Test
  public void test_canMoveDownLeftDiagonalTo_ownPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("e6"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("c4"));
    expectedSquares.add(new Square("d5"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "b3", expectedSquares, (b)->p.canMoveDownLeftDiagonalTo(b));
  }

  @Test
  public void test_canMoveDownLeftDiagonalTo_enemyPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("e6"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("b3"));
    expectedSquares.add(new Square("c4"));
    expectedSquares.add(new Square("d5"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "b3", expectedSquares, (b)->p.canMoveDownLeftDiagonalTo(b));
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * canMoveUpRightDiagonalTo
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_canMoveDownRightDiagonalTo_clearPath() {
    Piece p = new Bishop("B", true, new Square("d4"));
    HashSet<Piece> existingPieces = new HashSet<>();
    existingPieces.add(new Rook("R", true, new Square("e2"), true));
    Board b = new ChessBoard(8, 8, existingPieces);
    b.tryAddPiece(p);

    HashSet<Square> actualSquares = p.canMoveDownRightDiagonalTo(b);
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e3"));
    expectedSquares.add(new Square("f2"));
    expectedSquares.add(new Square("g1"));

    assertEquals(expectedSquares, actualSquares);
  }

  @Test
  public void test_canMoveDownRightDiagonalTo_alreadyOnEdge(){
    Piece p = new Bishop("B", true, new Square("h1"));
    this.helper_test_canMoveAnyDirTo_onEdge(p, (b)->p.canMoveDownRightDiagonalTo(b));
  }

  @Test
  public void test_canMoveDownRightDiagonalTo_ownPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("d5"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e4"));
    expectedSquares.add(new Square("f3"));
    this.helper_test_canMoveAnyDirTo_ownPieceBlocking(p, "g2", expectedSquares, (b)->p.canMoveDownRightDiagonalTo(b));
  }

  @Test
  public void test_canMoveDownRightDiagonalTo_enemyPieceBlocking(){
    Piece p = new Bishop("B", true, new Square("d5"));
    HashSet<Square> expectedSquares = new HashSet<>();
    expectedSquares.add(new Square("e4"));
    expectedSquares.add(new Square("f3"));
    expectedSquares.add(new Square("g2"));
    this.helper_test_canMoveAnyDirTo_enemyPieceBlocking(p, "g2", expectedSquares, (b)->p.canMoveDownRightDiagonalTo(b));
  }
}
