package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
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

  private void helper_tryMakeNormalMove_error(boolean isWhite, Move move, String expectedErrMsg, String expectedBoardView) throws IOException {
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(isWhite, b);
    assertEquals(expectedErrMsg, p1.tryMakeNormalMove(move));
    assertEquals(expectedBoardView, view.displayBoard());
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * tryMakeNormalMove
   * -----------------------------------------------------------------------------------------------
   * @throws IOException
   */
  @Test
  public void test_tryMakeNormalMove_italianGame() throws IOException {
    // Italian game opening
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
  public void test_tryMakeNormalMove_queensGambitAccepted() throws IOException {
    // queen's gambit accepted
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
  public void test_tryMakeNormalMove_selectOffBoard() throws IOException {
    String expectedErrMsg = "selected squares off board";

    this.helper_tryMakeNormalMove_error(
      true, new Move(new Square(0, 3), new Square("c4")), 
      expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("a8a9"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("h1i1"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_noPieceSelected() throws IOException {
    String expectedErrMsg = "no piece selected";
    this.helper_tryMakeNormalMove_error(true, new Move("a3a4"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("h6h8"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_selectedEnemyPiece() throws IOException {
    String expectedErrMsg = "cannot select enemy piece";
    this.helper_tryMakeNormalMove_error(true, new Move("c7c5"), expectedErrMsg, this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(false, new Move("a1a2"), expectedErrMsg, this.getDefaultBoardView());
  }

  @Test
  public void test_tryMakeNormalMove_moveToInvalidPlace() throws IOException{
    this.helper_tryMakeNormalMove_error(true, new Move("d2d5"), "cannot move to or capture at new square", this.getDefaultBoardView());
    this.helper_tryMakeNormalMove_error(true, new Move("a1a3"), "cannot move to or capture at new square", this.getDefaultBoardView());
  }

  /**
   * -----------------------------------------------------------------------------------------------
   * tryCastling
   * -----------------------------------------------------------------------------------------------
   */
  @Test void test_tryCastle_valid1(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

    b.tryAddPiece(new Rook("R", false, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", true, new Square("e8")));

    String expectedErrMsg = "pieces not at castling position";
    assertEquals(expectedErrMsg, p1.tryQueenSideCastle());
    assertEquals(expectedErrMsg, p2.tryKingSideCastle());
  }

  @Test
  public void test_tryCastle_rookMoved() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
  public void test_tryCastle_kingMoved() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    b.tryAddPiece(new Rook("R", true, new Square("a1"), false));
    b.tryAddPiece(new Rook("R", false, new Square("h8"), true));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

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
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
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

  /**
   * -----------------------------------------------------------------------------------------------
   * tryEnPassant
   * -----------------------------------------------------------------------------------------------
   * @throws IOException
   */
  @Test
  public void test_tryEnPassant_valid_white() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
    b.tryAddPiece(new Pawn("P", true, new Square("e5")));
    b.tryAddPiece(new Pawn("P", false, new Square("d7")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | |p| | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p2.tryMakeNormalMove(new Move("d7d5"));
    b.appendMoveLog(new Move("d7d5"));
    assertNull(errMsg);
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | |p|P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryEnPassant(new Move("e5d6"));
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | |P| | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test
  public void test_tryEnPassant_valid_black() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
    b.tryAddPiece(new Pawn("P", true, new Square("e2")));
    b.tryAddPiece(new Pawn("P", false, new Square("d4")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | |p| | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p1.tryMakeNormalMove(new Move("e2e4"));
    b.appendMoveLog(new Move("e2e4"));
    assertNull(errMsg);
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | |p|P| | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p2.tryEnPassant(new Move("d4e3"));
    b.appendMoveLog(new Move("d4e3"));
    assertNull(errMsg);
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | |p| | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());
  }

  @Test
  public void test_tryEnPassant_usingWrongPiece(){
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    String errMsg = p1.tryEnPassant(new Move("a1b2"));
    assertEquals("cannot en passant with pieces other than Pawn", errMsg);
    assertEquals(this.getDefaultBoardView(), view.displayBoard());
  }

  @Test
  public void test_tryEnPassant_pawnOnWrongRank(){
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    String expectedErrMsg = "cannot en passant from invalid rank";
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("b2c3")));
    assertEquals(this.getDefaultBoardView(), view.displayBoard());
    assertEquals(expectedErrMsg, p2.tryEnPassant(new Move("b7c6")));
    assertEquals(this.getDefaultBoardView(), view.displayBoard());
  }

  @Test
  public void test_tryEnPassant_movingWrongDirection() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
    b.tryAddPiece(new Pawn("P", true, new Square("e5")));
    b.tryAddPiece(new Pawn("P", false, new Square("d7")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    errMsg = p2.tryMakeNormalMove(new Move("d7d5"));
    b.appendMoveLog(new Move("d7d5"));
    assertNull(errMsg);
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | |p|P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    String expectedErrMsg = "invalid en passant destination";
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("e5e6")));
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("e5e4")));
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("e5d4")));
  }

  @Test
  public void test_tryEnPassant_tryCaptureRook(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);

    b.tryAddPiece(new Rook("R", true, new Square("e4"), false));
    b.tryAddPiece(new Pawn("P", true, new Square("g5")));
    b.tryAddPiece(new Pawn("P", false, new Square("d4")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String expectedBoardView;
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | |P| |\n" +
    "  -----------------\n" +
    "4 | | | |p|R| | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    String expectedErrMsg = "no enemy Pawn to en passant capture";
    assertEquals(expectedErrMsg, p2.tryEnPassant(new Move("d4e3")));
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("g5f6")));
    assertEquals(expectedErrMsg, p2.tryEnPassant(new Move("d4e3")));
  }

  @Test
  public void test_tryEnPassant_enemyPawnMovedOneForward() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
    b.tryAddPiece(new Pawn("P", true, new Square("e5")));
    b.tryAddPiece(new Pawn("P", false, new Square("d6")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | |p| | | | |\n" +
    "  -----------------\n" +
    "5 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p2.tryMakeNormalMove(new Move("d6d5"));
    assertNull(errMsg);
    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | |p|P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    String expectedErrMsg = "enemy pawn invalid en passant status";
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("e5d6")));
    b.appendMoveLog(new Move("d6d5"));
    assertEquals(expectedErrMsg, p1.tryEnPassant(new Move("e5d6")));
  }

  @Test
  public void test_tryEnPassant_enemyDidntMovePawnLast() throws IOException{
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    Player p1 = new TextPlayer(true, b);
    Player p2 = new TextPlayer(false, b);
    
    b.tryAddPiece(new Pawn("P", true, new Square("e5")));
    b.tryAddPiece(new Pawn("P", false, new Square("d7")));
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));

    String errMsg;
    String expectedBoardView;

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | |p| | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | |P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    errMsg = p2.tryMakeNormalMove(new Move("d7d5"));
    b.appendMoveLog(new Move("d7d5"));
    assertNull(errMsg);
    errMsg = p1.tryMakeNormalMove(new Move("e1f1"));
    b.appendMoveLog(new Move("e1f1"));
    assertNull(errMsg);
    errMsg = p2.tryMakeNormalMove(new Move("e8f8"));
    b.appendMoveLog(new Move("e8f8"));
    assertNull(errMsg);

    expectedBoardView = 
    "  -----------------\n" + 
    "8 | | | | | |k| | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | |p|P| | | |\n" +
    "  -----------------\n" +
    "4 | | | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | | |K| | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedBoardView, view.displayBoard());

    assertEquals("enemy pawn invalid en passant status", p1.tryEnPassant(new Move("e5d6")));
  }
}
