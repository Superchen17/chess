package com.osullivan.chess;

public class Player {
  private final boolean isWhite;
  private Board board;

  public Player(boolean isWhite, Board board) {
    this.isWhite = isWhite;
    this.board = board;
  }

  /**
   * make normal moves
   * excluding castling, en passant and promotion
   * @param move
   * @return
   */
  public String tryMakeNormalMove(Move move){
    if(!this.board.isSquareOnBoard(move.getSquareFrom()) || !this.board.isSquareOnBoard(move.getSquareTo())){
      return "selected squares off board";
    }

    Piece p = this.board.whatIsAtSquare(move.getSquareFrom());
    if(p == null){
      return "no piece selected";
    }
    if(p.isWhitePiece() != this.isWhite){
      return "cannot select enemy piece";
    }

    String errMsg = p.tryMoveTo(move.getSquareTo(), this.board);
    if(errMsg != null){
      return errMsg;
    }

    return null;
  }

  private boolean enemyCanAttack(Square square){
    for(Piece p: board.getTeamPieces(!this.isWhite)){
      if(p.canMoveTo(board).contains(square)){
        return true;
      }
    }
    return false;
  }

  private String castlingPiecesAreInPlace(Square rookSquare, Square kingSquare){
    String errMsg = "pieces not at castling position";

    Piece rook = this.board.whatIsAtSquare(rookSquare);
    Piece king = this.board.whatIsAtSquare(kingSquare);
    if(rook == null || king == null){
      // cannot castle if no pieces at castling position
      return errMsg;
    }
    if(rook.isWhitePiece() != this.isWhite || king.isWhitePiece() != this.isWhite){
      // cannot castle if pieces have wrong color
      return errMsg;
    }
    if(!(rook instanceof Rook) || !(king instanceof King)){
      // cannot castle if wrong pieces at castling position
      return errMsg;
    }
    if(rook.getMoveCounter() > 0 || king.getMoveCounter() > 0){
      return "castling pieces moved";
    }
    return null;
  }

  private String castlingPathIsBlocked(Square rookSquare, Square kingSquare){
    int startColFromKingToRook = Math.min(rookSquare.getColumn(), kingSquare.getColumn()) + 1;
    int stopColFromKingToRook = Math.max(rookSquare.getColumn(), kingSquare.getColumn()) - 1;
    for(int i = startColFromKingToRook; i <= stopColFromKingToRook; i++){
      Square squareInBetween = new Square(rookSquare.getRow(), i);
      if(this.board.whatIsAtSquare(squareInBetween) != null){
        return "castling path blocked";
      }
    }
    return null;
  }

  private String isUnderCheckWhenCastling(Square kingFrom, Square kingTo){
    int startCol = Math.min(kingFrom.getColumn(), kingTo.getColumn());
    int stopCol = Math.max(kingFrom.getColumn(), kingTo.getColumn());
    for(int i = startCol; i <= stopCol; i++){
      Square squareInBetween = new Square(kingFrom.getRow(), i);
      if(this.enemyCanAttack(squareInBetween)){
        return "cannot castling through checks";
      }
    }
    return null;
  }

  private String tryCastle(Square kingFrom, Square KingTo, Square rookFrom, Square rookTo){
    // cannot castle if pieces are not in place
    String errMsg = this.castlingPiecesAreInPlace(rookFrom, kingFrom);
    if(errMsg != null){
      return errMsg;
    }

    // cannot castle if there are pieces between king and rook
    errMsg = this.castlingPathIsBlocked(rookFrom, kingFrom);
    if(errMsg != null){
      return errMsg;
    }
        
    // king cannot be in check throughtout castling
    errMsg = this.isUnderCheckWhenCastling(kingFrom, KingTo);
    if(errMsg != null){
      return errMsg;
    }

    // if can castle, move pieces
    Piece rook = this.board.whatIsAtSquare(rookFrom);
    Piece king = this.board.whatIsAtSquare(kingFrom);
    rook.setSquare(rookTo);
    king.setSquare(KingTo);
    rook.incrementMoveCounter();
    king.incrementMoveCounter();

    return null;
  }

  /**
   * queen side castling
   * @return error message
   */
  public String tryQueenSideCastle(){
    Square rookFrom = new Square(this.isWhite ? "a1" : "a8");
    Square kingFrom = new Square(this.isWhite ? "e1" : "e8");
    Square rookTo = new Square(this.isWhite ? "d1" : "d8");
    Square kingTo = new Square(this.isWhite ? "c1" : "c8");
    return this.tryCastle(kingFrom, kingTo, rookFrom, rookTo);
  }

  /**
   * king side castling
   * @return error message
   */
  public String tryKingSideCastle(){
    Square rookFrom = new Square(this.isWhite ? "h1" : "h8");
    Square kingFrom = new Square(this.isWhite ? "e1" : "e8");
    Square rookTo = new Square(this.isWhite ? "f1" : "f8");
    Square kingTo = new Square(this.isWhite ? "g1" : "g8");
    return this.tryCastle(kingFrom, kingTo, rookFrom, rookTo);
  }
}
