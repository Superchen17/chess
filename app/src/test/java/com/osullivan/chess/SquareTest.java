package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class SquareTest {
  @Test
  public void test_constructor_int_valid(){
    Square square1 = new Square(0, 1);
    assertEquals(0, square1.getRow());
    assertEquals(1, square1.getColumn());

    Square square2 = new Square(-1, -2);
    assertEquals(-1, square2.getRow());
    assertEquals(-2, square2.getColumn());
  }

  @Test
  public void test_constructor_string_valid(){
    Square square1 = new Square("A1");
    assertEquals(1, square1.getColumn());
    assertEquals(1, square1.getRow());

    Square square2 = new Square("D5");
    assertEquals(4, square2.getColumn());
    assertEquals(5, square2.getRow());

    Square square3 = new Square("Z7");
    assertEquals(26, square3.getColumn());
    assertEquals(7, square3.getRow());

    Square square4 = new Square("a1");
    assertEquals(1, square4.getColumn());
    assertEquals(1, square4.getRow());
  }

  @Test
  public void test_constructor_string_invalid(){
    assertThrows(IllegalArgumentException.class, () -> new Square("a01"));
    assertThrows(IllegalArgumentException.class, () -> new Square("@1"));
    assertThrows(IllegalArgumentException.class, () -> new Square("a@"));
    assertThrows(IllegalArgumentException.class, () -> new Square("a/"));
    assertThrows(IllegalArgumentException.class, () -> new Square("/1"));
    assertThrows(IllegalArgumentException.class, () -> new Square(" 1"));
    assertThrows(IllegalArgumentException.class, () -> new Square("a"));
  }

  @Test
  public void test_equals() {
    Square square1 = new Square(1, 2);
    Square square2 = new Square(1, 2);
    Square square3 = new Square(1, 3);
    Square square4 = new Square(2, 2);

    assertEquals(square1, square1);
    assertEquals(square1, square2);
    assertEquals(square2, square1);
    assertNotEquals(square1, square3);
    assertNotEquals(square1, square4);
    assertNotEquals(square3, square4);
    assertNotEquals(square1, 1);
    assertNotEquals(square1, "b2");
    assertNotEquals("b2", square1);

  }

  @Test
  public void test_toString() {
    Square square1 = new Square(2, 1);
    Square square2 = new Square(5, 2);
    Square square3 = new Square("a1");

    assertEquals("a2", square1.toString());
    assertEquals("b5", square2.toString());
    assertEquals("a1", square3.toString());
  }

  @Test
  public void test_hashCode() {
    Square square1 = new Square(1, 2);
    Square square2 = new Square(1, 2);
    Square square3 = new Square(1, 3);
    Square square4 = new Square(2, 2);

    assertEquals(square1.hashCode(), square2.hashCode());
    assertNotEquals(square1.hashCode(), square3.hashCode());
    assertNotEquals(square3.hashCode(), square4.hashCode());

    HashSet<Square> squares = new HashSet<>();
    squares.add(square1);
    assertTrue(squares.contains(square1));
    assertTrue(squares.contains(square2));
  }


   /**
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | |n| | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | | | |R| | | | |
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
  public void test_canBeCoveredAssumingEmptyBy() {
    HashSet<Piece> pieces = new HashSet<>();
    pieces.add(new Rook("R", true, new Square("d4"), true));
    pieces.add(new Knight("N", false, new Square("d6")));
    Board b = new ChessBoard(8, 8, pieces);

    Square s1 = new Square("d2");
    assertTrue(s1.canBeCoveredAssumingEmptyBy(true, b));

    Square s2 = new Square("e3");
    assertFalse(s2.canBeCoveredAssumingEmptyBy(true, b));

    Square s3 = new Square("c8");
    assertTrue(s3.canBeCoveredAssumingEmptyBy(false, b));

    Square s4 = new Square("d4");
    Square s5 = new Square("d6");
    assertThrows(IllegalArgumentException.class, 
      ()->s4.canBeCoveredAssumingEmptyBy(false, b));
    assertThrows(IllegalArgumentException.class, 
      ()->s5.canBeCoveredAssumingEmptyBy(false, b));

    Square s7 = new Square("d7");
    assertFalse(s7.canBeCoveredAssumingEmptyBy(true, b));
  }
}
