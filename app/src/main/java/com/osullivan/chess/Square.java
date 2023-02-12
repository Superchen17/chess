package com.osullivan.chess;

public class Square{
  private final int row;
  private final int column;

  public Square(int row, int column){
    this.row = row;
    this.column = column;
  }

  public Square(String descr){
    if(descr.length() != 2){
      throw new IllegalArgumentException("error: square requires exactly 2 characters");
    }

    char rowChar = Character.toUpperCase(descr.charAt(0));
    char colChar = Character.toUpperCase(descr.charAt(1));

    if(rowChar < 'A' || rowChar > 'Z'){
      throw new IllegalArgumentException("error: character \'" + rowChar + "\' is not a valid row");
    }

    if(!Character.isDigit(colChar)){
      throw new IllegalArgumentException("error: character \'" + rowChar + "\' is not a valid column");
    }

    this.row = rowChar - 'A';
    this.column = Character.getNumericValue(colChar);
  }

  public int getRow(){
    return this.row;
  }

  public int getColumn(){
    return this.column;
  }

  @Override
  public boolean equals(Object o) {
    if(o.getClass().equals(this.getClass())){
      Square rhs = (Square)o;
      return this.row == rhs.row && this.column == rhs.column;
    }
    return false;
  }

  @Override
  public int hashCode(){
    return this.toString().hashCode();
  }

  @Override
  public String toString() {
    return (char)(this.row + 'a') + Integer.toString(this.column);
  }
}
