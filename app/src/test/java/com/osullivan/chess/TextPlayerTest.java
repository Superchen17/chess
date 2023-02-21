package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
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

  private TextPlayer createTextPlayer(boolean isWhite, Board board, String inputData, OutputStream bytes){
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    TextPlayer p1 =  new TextPlayer(isWhite, board, input, output);
    return p1;
  }

  @Test
  public void test_readMove_valid() throws IOException{
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "d2d4\n", bytes);
    String prompt = "white\'s turn\n";
    Move m = p1.readMove(prompt);

    assertEquals(prompt, bytes.toString());
    assertEquals(m, new Move("d2d4"));
    bytes.reset();
  }

  @Test
  public void test_readMove_invalidSyntex() throws IOException{
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "d2d\nd2d4\n", bytes);
    
    String prompt = "white\'s turn\n";
    String errMsg = "java.lang.IllegalArgumentException: move only accept string with length of 4\n";
    Move m = p1.readMove(prompt);

    assertEquals(prompt + errMsg + prompt, bytes.toString());
    assertEquals(m, new Move("d2d4"));
    bytes.reset();
  }

  @Test
  public void test_readMove_srcDistSame() throws IOException{
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "d2d2\nd2d4\n", bytes);
    
    String prompt = "white\'s turn\n";
    String errMsg = "java.lang.IllegalArgumentException: move source and destination must not be equal\n";
    Move m = p1.readMove(prompt);

    assertEquals(prompt + errMsg + prompt, bytes.toString());
    assertEquals(m, new Move("d2d4"));
    bytes.reset();
  }

  @Test
  public void test_readMove_null(){
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "d2d", bytes);
    
    String prompt = "white\'s turn\n";
    assertThrows(EOFException.class, ()->p1.readMove(prompt));
    bytes.reset();
  }

  @Test
  public void test_makeOneMove_valid() throws IOException{
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "d2d4\n", bytes);

    String makeMovePrompt = "White's turn\n";
    String readMovePrompt = "enter move:\n";
    p1.makeOneMove();

    String expectedBoardViewAfter = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p|p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
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
    assertEquals(expectedBoardViewAfter, view.displayBoard());
    assertEquals(this.getDefaultBoardView() + makeMovePrompt + readMovePrompt + expectedBoardViewAfter, bytes.toString());
    bytes.reset();
  }

  @Test
  public void test_makeOneMove_squareOffBoard() throws IOException{
    Board b = new ChessBoard(8, 8);
    BoardTextView view = new BoardTextView(b);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "i2a1\nd2d4\n", bytes);

    String makeMovePrompt = "White's turn\n";
    String readMovePrompt = "enter move:\n";
    String errMsg = "Invalid move, enter move again\n"; 
    p1.makeOneMove();

    String expectedBoardViewAfter = 
    "  -----------------\n" + 
    "8 |r|n|b|q|k|b|n|r|\n" + 
    "  -----------------\n" + 
    "7 |p|p|p|p|p|p|p|p|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
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
    assertEquals(expectedBoardViewAfter, view.displayBoard());

    String expectedOutStr = this.getDefaultBoardView() + makeMovePrompt + readMovePrompt + 
      errMsg + readMovePrompt + expectedBoardViewAfter;
    assertEquals(expectedOutStr, bytes.toString());
    bytes.reset();
  }

  @Test
  public void test_getPromotedPiece_valid() throws IOException {
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "n\n", bytes);

    Piece p = p1.getPromotedPiece();
    String prompt = "Choose your promotion piece (R, N, B, Q):\n";
    assertEquals(prompt, bytes.toString());
    assertEquals(Knight.class.getName(), p.getClass().getName());
    bytes.reset();
  }

  @Test
  public void test_getPromotedPiece_invalid() throws IOException {
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "c\nqn\nq\n", bytes);

    String prompt = "Choose your promotion piece (R, N, B, Q):\n";
    String errMsg = "invalid promotion choice\n";
    Piece p = p1.getPromotedPiece();
    assertEquals(prompt + errMsg + prompt + errMsg + prompt, bytes.toString());
    assertEquals(Queen.class.getName(), p.getClass().getName());
    bytes.reset();
  }

  @Test
  public void test_getPromotedPiece_null() throws IOException {
    Board b = new ChessBoard(8, 8);
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "\n", bytes);

    assertThrows(IOException.class, ()->p1.getPromotedPiece());
    bytes.reset();
  }

  @Test 
  public void test_normalMoveWithPromotion_white() throws IOException {
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Pawn("P", true, new Square("b7")));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p1 = this.createTextPlayer(true, b, "b7b8\nr\n", bytes);

    String expectedView;
    expectedView = 
    "  -----------------\n" + 
    "8 | | | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | |P| | | | | | |\n" +
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
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());

    p1.makeOneMove();

    expectedView = 
    "  -----------------\n" + 
    "8 | |R| | |k| | | |\n" + 
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
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    bytes.reset();
  }

  @Test
  public void test_normalMoveWithPromotion_black() throws IOException {
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Pawn("P", false, new Square("g2")));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p2 = this.createTextPlayer(false, b, "g2g1\nb\n", bytes);

    String expectedView;
    expectedView = 
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
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | |p| |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());

    p2.makeOneMove();

    expectedView = 
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
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| |b| |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    bytes.reset();
  }

  @Test
  public void test_isCheckMate_checkmate(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Rook("R", true, new Square("a8"), true));
    b.tryAddPiece(new Rook("R", true, new Square("h7"), false));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p2 = this.createTextPlayer(false, b, "g2g1\nb\n", bytes);
    
    String expectedView;
    expectedView = 
    "  -----------------\n" + 
    "8 |R| | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | |R|\n" +
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
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    assertTrue(p2.isCheckMate());
    bytes.reset();
  }

  @Test
  public void test_isCheckMate_canBlock(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Rook("R", true, new Square("a8"), true));
    b.tryAddPiece(new Rook("R", true, new Square("h7"), false));
    b.tryAddPiece(new Rook("R", false, new Square("b4"), false));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p2 = this.createTextPlayer(false, b, "g2g1\nb\n", bytes);
    
    String expectedView;
    expectedView = 
    "  -----------------\n" + 
    "8 |R| | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | |R|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | |r| | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    assertFalse(p2.isCheckMate());
    bytes.reset();
  }

  @Test
  public void test_isCheckMate_canCapture(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("e8")));
    b.tryAddPiece(new Rook("R", true, new Square("a8"), true));
    b.tryAddPiece(new Rook("R", true, new Square("h7"), false));
    b.tryAddPiece(new Rook("R", false, new Square("a4"), false));

    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer p2 = this.createTextPlayer(false, b, "g2g1\nb\n", bytes);
    
    String expectedView;
    expectedView = 
    "  -----------------\n" + 
    "8 |R| | | |k| | | |\n" + 
    "  -----------------\n" + 
    "7 | | | | | | | |R|\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 |r| | | | | | | |\n" +
    "  -----------------\n" +
    "3 | | | | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    assertFalse(p2.isCheckMate());
    bytes.reset();
  }

  @Test
  public void test_isStaleMate_stalemate(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("h8")));
    b.tryAddPiece(new Pawn("P", true, new Square("b3")));
    b.tryAddPiece(new Pawn("P", false, new Square("b4")));
    b.tryAddPiece(new Queen("Q", true, new Square("f7")));

    Player p2 = new TextPlayer(false, b);

    String expectedView = 
    "  -----------------\n" + 
    "8 | | | | | | | |k|\n" + 
    "  -----------------\n" + 
    "7 | | | | | |Q| | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | |p| | | | | | |\n" +
    "  -----------------\n" +
    "3 | |P| | | | | | |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    assertTrue(p2.isStaleMate());
  }

  @Test
  public void test_isStaleMate_piecesCanMove(){
    Board b = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView view = new BoardTextView(b);
    b.tryAddPiece(new King("K", true, new Square("e1")));
    b.tryAddPiece(new King("K", false, new Square("h8")));
    b.tryAddPiece(new Pawn("P", true, new Square("b3")));
    b.tryAddPiece(new Pawn("P", false, new Square("b4")));
    b.tryAddPiece(new Queen("Q", true, new Square("f7")));
    b.tryAddPiece(new Rook("R", false, new Square("g3"), true));

    Player p2 = new TextPlayer(false, b);

    String expectedView = 
    "  -----------------\n" + 
    "8 | | | | | | | |k|\n" + 
    "  -----------------\n" + 
    "7 | | | | | |Q| | |\n" +
    "  -----------------\n" +
    "6 | | | | | | | | |\n" +
    "  -----------------\n" +
    "5 | | | | | | | | |\n" +
    "  -----------------\n" +
    "4 | |p| | | | | | |\n" +
    "  -----------------\n" +
    "3 | |P| | | | |r| |\n" +
    "  -----------------\n" +
    "2 | | | | | | | | |\n" +
    "  -----------------\n" +
    "1 | | | | |K| | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";
    assertEquals(expectedView, view.displayBoard());
    assertFalse(p2.isStaleMate());
  }
}
