package com.osullivan.chess;

import java.util.List;

public class Player {
  private final boolean isWhite;
  private Board board;

  public Player(boolean isWhite, Board board) {
    this.isWhite = isWhite;
    this.board = board;
  }

  private String areMoveSquaresValid(Move move){
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
    return null;
  }

  /**
   * make normal moves
   * excluding castling, en passant and promotion
   * @param move
   * @return
   */
  public String tryMakeNormalMove(Move move){
    String errMsg = this.areMoveSquaresValid(move);
    if(errMsg != null){
      return errMsg;
    }

    Piece p = this.board.whatIsAtSquare(move.getSquareFrom());
    errMsg = p.tryMoveTo(move.getSquareTo(), this.board);
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

  private String isEnPassantDirectionValid(Move move){
    String errMsg = "invalid en passant destination";
    int forwardDir = this.isWhite ? 1 : -1;
    Square squareFrom = move.getSquareFrom();
    Square squareTo = move.getSquareTo();
    if(squareTo.getRow() - squareFrom.getRow() != forwardDir){
      return errMsg;
    }
    if(Math.abs(squareTo.getColumn() - squareFrom.getColumn()) != 1){
      return errMsg;
    }
    return null;
  }

  private String isEnemyPawnAtEnPassantCapture(Piece pieceToBeCaptured){
    String errMsg = "no enemy Pawn to en passant capture";
    if(pieceToBeCaptured == null){
      return errMsg;
    }
    if(!(pieceToBeCaptured instanceof Pawn)){
      return errMsg;
    }
    return null;
  }

  private String didEnemyPawnJustMoveTwoForward(Square squareToBeCaptured){
    String errMsg = "enemy pawn invalid en passant status";
    List<Move> moveLog = this.board.getMoveLog();
    if(moveLog.size() == 0){
      return errMsg;
    }
    Move enemyLastMove = moveLog.get(moveLog.size() - 1);
    Square enemyLastMoveFrom = enemyLastMove.getSquareFrom();
    Square enemyLastMoveTo = enemyLastMove.getSquareTo();
    if(!enemyLastMoveTo.equals(squareToBeCaptured)){
      return errMsg;
    }
    if(Math.abs(enemyLastMoveFrom.getRow() - enemyLastMoveTo.getRow()) != 2){
      return errMsg;
    }
    return null;
  }

  /**
   * try en passant capture
   * @param move
   * @return
   */
  public String tryEnPassant(Move move){
    String errMsg = this.areMoveSquaresValid(move);
    if(errMsg != null){
      return errMsg;
    }

    // cannot en passant if selected piece is not a pawn
    Piece p = this.board.whatIsAtSquare(move.getSquareFrom());
    if(!(p instanceof Pawn)){
      return "cannot en passant with pieces other than Pawn";
    }

    // cannot en passant if piece on wrong ranks
    // white: rank 5, black: rank 4
    int enPassantRowFrom = this.isWhite ? 5 : 4;
    if(p.getSquare().getRow() != enPassantRowFrom){
      return "cannot en passant from invalid rank";
    }

    // cannot en passant if Pawn not moving in forward diagonals
    errMsg = this.isEnPassantDirectionValid(move);
    if(errMsg != null){
      return errMsg;
    }

    // cannot en passant if there is no enemy Pawn at capturing square
    Square squareToBeCaptured = new Square(p.getSquare().getRow(), move.getSquareTo().getColumn());
    Piece pieceToBeCaptured = this.board.whatIsAtSquare(squareToBeCaptured);
    errMsg = this.isEnemyPawnAtEnPassantCapture(pieceToBeCaptured);
    if(errMsg != null){
      return errMsg;
    }

    // can only en passant if the enemy Pawn just moved forward 2 squares
    errMsg = this.didEnemyPawnJustMoveTwoForward(squareToBeCaptured);
    if(errMsg != null){
      return errMsg;
    }

    // do en passant capture
    p.setSquare(move.getSquareTo());
    this.board.tryRemovePiece(pieceToBeCaptured);

    return null;
  }
}
