package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

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
}
