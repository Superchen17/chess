package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_displayBoard(){
    Board board = new ChessBoard(8, 8);
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
}
