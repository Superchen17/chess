package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class PlayerTest {
  /**
   * -----------------------------------------------------------------------------------------------
   * private helpers
   * -----------------------------------------------------------------------------------------------
   */
  private String getDefaultBoardView(){
    return  
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p|p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 |P|P|P|P|P|P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B|N|R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
  }

  private void helper_tryMakeNormalMove_error(boolean isWhite, Move move, String expectedErrMsg, String expectedBoardView) {
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(isWhite, b);
    assertEquals(expectedErrMsg, p1.tryMakeNormalMove(move));
    assertEquals(expectedBoardView, view.displayBoard());
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * tryMakeNormalMove
   * -----------------------------------------------------------------------------------------------
   */
  @Test
  public void test_tryMakeNormalMove_italianGame() {
    // Italian game opening
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    String errMsg; 
    String expectedBoardView;

    errMsg = p1.tryMakeNormalMove(new Move("e2e4"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("e7e5"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p| |p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | |p| | | |\n" +
    "  -----------------\n" +
    "4 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 |P|P|P|P| |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B|N|R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("g1f3"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("b8c6"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r| |b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p| |p|p|p|\n" +
    "  -----------------\n" +
    "6 | | |n| | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | |p| | | |\n" +
    "  -----------------\n" +
    "4 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "3 | | | | | |N| | |\n" +
    "  -----------------\n" +
    "2 |P|P|P|P| |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B| |R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("f1c4"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("f8c5"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r| |b|q|k| |n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p| |p|p|p|\n" +
    "  -----------------\n" +
    "6 | | |n| | | | | |\n" +
    "  -----------------\n" +
    "5 | | |b| |p| | | |\n" +
    "  -----------------\n" +
    "4 | | |B| |P| | | |\n" +
    "  -----------------\n" +
    "3 | | | | | |N| | |\n" +
    "  -----------------\n" +
    "2 |P|P|P|P| |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K| | |R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test
  public void test_tryMakeNormalMove_queensGambitAccepted() {
    // queen's gambit accepted
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    String errMsg; 
    String expectedBoardView;

    errMsg = p1.tryMakeNormalMove(new Move("d2d4"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("d7d5"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p| |p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | |p| | | | |\n" +
    "  -----------------\n" +
    "4 | | | |P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 |P|P|P| |P|P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B|N|R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("c2c4"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("d5c4"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p| |p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | |p|P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 |P|P| | |P|P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B|N|R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("g1f3"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("g8f6"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b| |r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p| |p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | |n| | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | |p|P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | |N| | |\n" +
    "  -----------------\n" +
    "2 |P|P| | |P|P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B| |R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("e2e3"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("e7e6"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b| |r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p| | |p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | |p|n| | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | |p|P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | |P|N| | |\n" +
    "  -----------------\n" +
    "2 |P|P| | | |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K|B| |R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("f1c4"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("c7c5"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b| |r|\n" + 
    "  -----------------\n" + 
    "7 |p|p| | | |p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | |p|n| | |\n" +
    "  -----------------\n" +
    "5 | | |p| | | | | |\n" +
    "  -----------------\n" +
    "4 | | |B|P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | |P|N| | |\n" +
    "  -----------------\n" +
    "2 |P|P| | | |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q|K| | |R|\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryKingSideCastle();
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("a7a6"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b| |r|\n" + 
    "  -----------------\n" + 
    "7 | |p| | | |p|p|p|\n" +
    "  -----------------\n" +
    "6 |p| | | |p|n| | |\n" +
    "  -----------------\n" +
    "5 | | |p| | | | | |\n" +
    "  -----------------\n" +
    "4 | | |B|P| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | |P|N| | |\n" +
    "  -----------------\n" +
    "2 |P|P| | | |P|P|P|\n" +
    "  -----------------\n" +
    "1 |R|N|B|Q| |R|K| |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test
  public void test_tryMakeNormalMove_selectOffBoard() {
    String expectedErrMsg = "selected squares off board";

    this.helper_tryMakeNormalMove_error(
      true, new Move(new Square(0, 3), new Square("c4")), 
      expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("a8a9"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("h1i1"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_noPieceSelected() {
    String expectedErrMsg = "no piece selected";
    this.helper_tryMakeNormalMove_error(true, new Move("a3a4"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("h6h8"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_selectedEnemyPiece() {
    String expectedErrMsg = "cannot select enemy piece";
    this.helper_tryMakeNormalMove_error(true, new Move("c7c5"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(false, new Move("a1a2"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_moveToInvalidPlace(){
    this.helper_tryMakeNormalMove_error(true, new Move("d2d5"), "cannot move from d2 to d5", this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("a1a3"), "cannot move from a1 to a3", this.getDefaultBoardView());
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * tryCastling
   * -----------------------------------------------------------------------------------------------
   */
  @Test void test_tryCastle_valid1(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    errMsg = p1.tryQueenSideCastle();
    assertNull(errMsg);
    errMsg = p2.tryKingSideCastle();
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | | |r|k| |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | |K|R| | | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test void test_tryCastle_valid2(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Rook("R", true, new Square("h1"), true));
    b.tryAddPiece(new Rook("R", false, new Square("a8"), false));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    errMsg = p1.tryKingSideCastle();
    assertNull(errMsg);
    errMsg = p2.tryQueenSideCastle();
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | |k|r| | | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | | |R|K| |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test void test_tryCastle_piecesNotInPlace(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Rook("R", true, new Square("a2"), false));
    b.tryAddPiece(new Rook("R", false, new Square("g8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String expectedErrMsg = "pieces not at castling position";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());
  }

  @Test
  public void test_tryCastle_wrongPieces(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Knight("N", true, new Square("a1")));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new Knight("N", false, new Square("e8")));

    String expectedErrMsg = "pieces not at castling position";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());
  }

  @Test
  public void test_tryCastle_wrongColor(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Rook("R", false, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", true, new Square("e8")));

    String expectedErrMsg = "pieces not at castling position";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());
  }

  @Test
  public void test_tryCastle_rookMoved(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    String errMsg;

    errMsg = p1.tryMakeNormalMove(new Move("a1a2"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("h8h7"));
    assertNull(errMsg);

    errMsg = p1.tryMakeNormalMove(new Move("a2a1"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("h7h8"));
    assertNull(errMsg);

    String expectedErrMsg = "castling pieces moved";
    errMsg = p1.tryQueenSideCastle();
    assertEquals(expectedErrMsg, errMsg);
    errMsg = p2.tryKingSideCastle();
    assertEquals(expectedErrMsg, errMsg);
  }

  @Test
  public void test_tryCastle_kingMoved(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    String errMsg;

    errMsg = p1.tryMakeNormalMove(new Move("e1e2"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("e8e7"));
    assertNull(errMsg);

    errMsg = p1.tryMakeNormalMove(new Move("e2e1"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("e7e8"));
    assertNull(errMsg);

    String expectedErrMsg = "castling pieces moved";
    errMsg = p1.tryQueenSideCastle();
    assertEquals(expectedErrMsg, errMsg);
    errMsg = p2.tryKingSideCastle();
    assertEquals(expectedErrMsg, errMsg);
  }

  @Test
  public void test_tryCastle_blocked(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);

    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Knight("N", true, new Square("b1")));
    b.tryAddPiece(new Knight("N", false, new Square("g8")));

    String expectedErrMsg = "castling path blocked";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());

    String expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| |n|r|\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 |R|N| | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  private void helper_tryCastle_throughCheck(Piece whiteCheckingPiece, Piece blackCheckingPiece, String expectedBoardView){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new Player(true, b);
    Player p2 = new Player(false, b);
    
    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(whiteCheckingPiece);
    b.tryAddPiece(blackCheckingPiece);

    String expectedErrMsg = "cannot castling through checks";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test
  public void test_tryCastle_throughChecks_rooks(){
    String expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | |r|\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | |R| | |\n" +
    "  -----------------\n" +
    "4 | | | |r| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 |R| | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";

    Piece whiteR = new Rook("R", true, new Square("f5"), true);
    Piece blackR = new Rook("R", false, new Square("d4"), false);
    this.helper_tryCastle_throughCheck(whiteR, blackR, expectedBoardView);
  }

  @Test
  public void test_tryCastle_throughChecks_otherPieces(){
    String expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | |r|\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | |B| | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | |n| | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 |R| | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";

    Piece whiteR = new Bishop("B", true, new Square("b5"));
    Piece blackR = new Knight("N", false, new Square("c3"));
    this.helper_tryCastle_throughCheck(whiteR, blackR, expectedBoardView);
  }

  @Test
  public void test_tryCastle_checkBeforeCastling(){
    String expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | |r|\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 |b| | | | | | | |\n" +
    "  -----------------\n" +
    "4 |B| | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 |R| | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";

    Piece whiteP = new Bishop("B", true, new Square("a4"));
    Piece blackP = new Bishop("B", false, new Square("a5"));
    this.helper_tryCastle_throughCheck(whiteP, blackP, expectedBoardView);
  }

  @Test
  public void test_tryCastle_checkIntoCastling(){
    String expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | |r|\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 |b| | | | | | | |\n" +
    "  -----------------\n" +
    "2 |B| | | | | | | |\n" +
    "  -----------------\n" +
    "1 |R| | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";

    Piece whiteP = new Bishop("B", true, new Square("a2"));
    Piece blackP = new Bishop("B", false, new Square("a3"));
    this.helper_tryCastle_throughCheck(whiteP, blackP, expectedBoardView);
  }
}
