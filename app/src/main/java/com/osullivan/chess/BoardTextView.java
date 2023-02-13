package com.osullivan.chess;

public class BoardTextView {
  private final Board board;

  public BoardTextView(Board board) {
    this.board = board;
  }

  private String getRowSeparator(){
    return "  -----------------";
  }

  private String getRowLetters() {
    return "   a b c d e f g h ";
  }

  /**
   * @return layout
   *    -----------------
      8 | | | | | | | | |
        -----------------
      7 | | | | | | | | |
        -----------------
      6 | | | | | | | | |
        -----------------
      5 | | | | | | | | |
        -----------------
      4 | | | | | | | | |
        -----------------
      3 | | | | | | | | |
        -----------------
      2 | | | | | | | | |
        -----------------
      1 | | | | | | | | |
        -----------------
         a b c d e f g h 
   */
  public String displayBoard(){
    StringBuilder view = new StringBuilder("");
    view.append(this.getRowSeparator() + "\n");
    for(int i = this.board.getHeight(); i >= 1; i--){
      view.append(Integer.toString(i) + " |");
      for(int j = 1; j < this.board.getWidth() + 1; j++){
        Square square = new Square(i, j);
        Piece p = this.board.whatIsAtSquare(square);
        String pieceDisplay = " ";
        if(p != null){
          PieceTextView pieceTextView = new PieceTextView(p);
          pieceDisplay = pieceTextView.displayPiece();
        }
        view.append(pieceDisplay + "|");
      }
      view.append("\n");
      view.append(this.getRowSeparator() + "\n");
    }
    view.append(this.getRowLetters() + "\n");
    return view.toString();
  }
}
