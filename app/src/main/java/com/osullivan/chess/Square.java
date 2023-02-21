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

    char rowChar = Character.toUpperCase(descr.charAt(1));
    char colChar = Character.toUpperCase(descr.charAt(0));

    if(colChar < 'A' || colChar > 'Z'){
      throw new IllegalArgumentException("error: character \'" + colChar + "\' is not a valid column");
    }

    if(!Character.isDigit(rowChar)){
      throw new IllegalArgumentException("error: character \'" + rowChar + "\' is not a valid column");
    }

    this.column = colChar - 'A' + 1;
    this.row = Character.getNumericValue(rowChar);
  }

  public int getRow(){
    return this.row;
  }

  public int getColumn(){
    return this.column;
  }

  /**
   * assuming the square is empty, check if team pieces can move to it
   * @param isWhite true if white
   * @param board board
   * @return true if can be covered by that team
   */
  public boolean canBeCoveredAssumingEmptyBy(boolean isWhite, Board board){
    if(board.whatIsAtSquare(this) != null){
      throw new IllegalArgumentException("the square under cover check is not empty");
    }
    for(Piece teamPiece: board.getTeamPieces(isWhite)){
      if(teamPiece.canCover(board).contains(this)){
        return true;
      }
    }
    return false;
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
    return (char)(this.column + 'a' - 1) + Integer.toString(this.row);
  }
}
