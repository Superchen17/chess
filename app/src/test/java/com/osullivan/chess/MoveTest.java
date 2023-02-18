package com.osullivan.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MoveTest {
  @Test
  public void test_defaultConstructor() {
    Square from = new Square(1, 3);
    Square to = new Square(3,1);
    Move move = new Move(from, to);
    assertEquals(from, move.getSquareFrom());
    assertEquals(to, move.getSquareTo());
  }

  @Test
  public void test_overloadedConstructor() {
    Square from = new Square("a1");
    Square to = new Square("a2");
    Move move = new Move(from, to);
    assertEquals(from, move.getSquareFrom());
    assertEquals(to, move.getSquareTo());
  }

  @Test
  public void test_constructor_invalid_syntax() {
    assertThrows(IllegalArgumentException.class, () -> new Move(""));
    assertThrows(IllegalArgumentException.class, () -> new Move("a1b"));
    assertThrows(IllegalArgumentException.class, () -> new Move("a1bx"));
    assertThrows(IllegalArgumentException.class, () -> new Move("axb1"));
    assertThrows(IllegalArgumentException.class, () -> new Move("11b1"));
    assertThrows(IllegalArgumentException.class, () -> new Move("a1b35"));
  }

  @Test
  public void test_constructor_fromToSame(){
    assertThrows(IllegalArgumentException.class, () -> new Move("a1a1"));
    assertThrows(IllegalArgumentException.class, () -> new Move(new Square("a1"), new Square("a1")));
  }

  @Test
  public void test_equals(){
    Move m1 = new Move("a1a2");
    Move m2 = new Move(new Square("a1"), new Square("a2"));
    Move m3 = new Move("a2a1");

    assertEquals(m1, m1);
    assertEquals(m1, m2);
    assertEquals(m2, m1);
    assertNotEquals(m1, m3);
    assertNotEquals(m2, m3);
    assertNotEquals(m1, 1);
    assertNotEquals(m1, "a1a2");
    assertNotEquals(1, m1);
  }

  @Test
  public void test_hashCode(){
    Move m1 = new Move("a1a2");
    Move m2 = new Move(new Square("a1"), new Square("a2"));
    Move m3 = new Move("a2a1");

    assertEquals(m1.hashCode(), m1.hashCode());
    assertEquals(m1.hashCode(), m2.hashCode());
    assertEquals(m2.hashCode(), m1.hashCode());
    assertNotEquals(m1.hashCode(), m3.hashCode());
    assertNotEquals(m2.hashCode(), m3.hashCode());
  }

  @Test void test_toString(){
    Move m1 = new Move("a1a2");
    Move m2 = new Move(new Square(1, 1), new Square(2, 1));

    assertEquals("a1a2", m1.toString());
    assertEquals("a1a2", m2.toString());
  }
}
