package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_displayBoard_empty(){
    Board board = new ChessBoard(8, 8, new HashSet<>());
    BoardTextView boardView = new BoardTextView(board);

    String expected = 
    "  -----------------\n" + 
    "8 | | | | | | | | |\n" + 
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
    "1 | | | | | | | | |\n" +
    "  -----------------\n" +
    "   a b c d e f g h \n";

    assertEquals(expected, boardView.displayBoard());
  }

  @Test
  public void test_displayBoard_default(){
    Board board = new ChessBoard(8, 8);
    BoardTextView boardView = new BoardTextView(board);

    String expected = 
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

    assertEquals(expected, boardView.displayBoard());
  }
}
