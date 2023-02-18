package com.osullivan.chess;

public class Move {
  private final Square squareFrom;
  private final Square squareTo;

  private void validate(){
    if(this.squareFrom.equals(this.squareTo)){
      throw new IllegalArgumentException("move source and destination must not be equal");
    }
  }

  public Move(Square squareFrom, Square squareTo){
    this.squareFrom = squareFrom;
    this.squareTo = squareTo;
    this.validate();
  }

  public Move(String descr){
    if(descr.length() != 4){
      throw new IllegalArgumentException("move only accept string with length of 4");
    }
    this.squareFrom = new Square(descr.substring(0, 2));
    this.squareTo = new Square(descr.substring(2));
    this.validate();
  }

  public Square getSquareFrom(){
    return this.squareFrom;
  }

  public Square getSquareTo(){
    return this.squareTo;
  }

  @Override
  public boolean equals(Object o) {
    if(o.getClass().equals(this.getClass())){
      Move rhs = (Move)o;
      return this.squareFrom.equals(rhs.squareFrom) 
        && this.squareTo.equals(rhs.squareTo);
    }
    return false;
  }

  @Override
  public int hashCode(){
    return this.toString().hashCode();
  }

  @Override
  public String toString(){
    return this.squareFrom.toString() + this.squareTo.toString();
  }
}
