package com.osullivan.chess;

import java.io.IOException;
import java.util.List;

public abstract class Player {
  protected final boolean isWhite;
  protected Board board;

  public Player(boolean isWhite, Board board) {
    this.isWhite = isWhite;
    this.board = board;
  }

  public boolean isWhitePlayer(){
    return this.isWhite;
  }

  protected String areMoveSquaresValid(Move move){
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

  private void movePieceTo(Piece p, Square newSquare){
    Piece enemyPiece = this.board.whatIsAtSquare(newSquare);
    if(enemyPiece != null){
      if(!this.board.tryRemovePiece(enemyPiece)){
        throw new IllegalAccessError("failed to remove piece from board");
      }
    }
    p.setSquare(newSquare);
    p.incrementMoveCounter();
  }

  private void revertPiecesAfterDetectingChecks(Move move, Piece selfPiece, Piece enemyPiece){
    selfPiece.setSquare(move.getSquareFrom());
    if(enemyPiece != null){
      if(!this.board.tryAddPiece(enemyPiece)){
        throw new IllegalAccessError("failed to add piece to board");
      }
    }
  }

  private String moveWillLeadToSelfChecks(Move move){
    Piece selfPiece = this.board.whatIsAtSquare(move.getSquareFrom());
    Piece enemyPiece = this.board.whatIsAtSquare(move.getSquareTo());
    if(enemyPiece != null){
      if(!this.board.tryRemovePiece(enemyPiece)){
        throw new IllegalAccessError("failed to remove piece from board");
      }
    }
    selfPiece.setSquare(move.getSquareTo());
    if(this.isUnderCheck()){
      this.revertPiecesAfterDetectingChecks(move, selfPiece, enemyPiece);
      return "move will leave King in check";
    }
    this.revertPiecesAfterDetectingChecks(move, selfPiece, enemyPiece);
    return null;
  }

  /**
   * make normal moves
   * excluding castling, en passant and promotion
   * @param move
   * @return
   * @throws IOException
   */   
  public String tryMakeNormalMove(Move move) throws IOException{
    String errMsg = this.areMoveSquaresValid(move);
    if(errMsg != null){
      return errMsg;
    }

    Piece p = this.board.whatIsAtSquare(move.getSquareFrom());
    if(!p.canMoveTo(board).contains(move.getSquareTo())){
      return "cannot move to or capture at new square";
    }

    errMsg = this.moveWillLeadToSelfChecks(move);
    if(errMsg != null){
      return errMsg;
    }

    this.movePieceTo(p, move.getSquareTo());
    if(this.canPromote(p)){
      Piece promotedPiece = this.getPromotedPiece();
      promotedPiece.setSquare(p.getSquare());
      if(!this.board.tryRemovePiece(p)){
        throw new IllegalAccessError("failed to remove piece from board");
      }
      if(!this.board.tryAddPiece(promotedPiece)){
        throw new IllegalAccessError("failed to add piece to board");
      }
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

  protected String canEnPassant(Move move) {
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

    // cannot en passant if move lead to checks
    errMsg = this.moveWillLeadToSelfChecks(move);
    if(errMsg != null){
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
    String errMsg = this.canEnPassant(move);
    if(errMsg != null){
      return errMsg;
    }

    // do en passant capture
    Piece p = this.board.whatIsAtSquare(move.getSquareFrom());
    Square squareToBeCaptured = new Square(p.getSquare().getRow(), move.getSquareTo().getColumn());
    Piece pieceToBeCaptured = this.board.whatIsAtSquare(squareToBeCaptured);

    p.setSquare(move.getSquareTo());
    this.board.tryRemovePiece(pieceToBeCaptured);
    p.incrementMoveCounter();

    return null;
  }

  private Piece getKing(){
    for(Piece p: this.board.getTeamPieces(this.isWhite)){
      if(p instanceof King){
        return p;
      }
    }
    throw new IllegalAccessError("no King found");
  }

  /**
   * check if is under check
   * @return true if under check
   */
  public boolean isUnderCheck(){
    Piece king = this.getKing();
    if(this.enemyCanAttack(king.getSquare())){
      return true;
    }
    return false;
  }

  protected boolean canPromote(Piece p){
    // check if the piece is piece is Pawn
    if(!(p instanceof Pawn)){
      return false;
    } 

    // can only promote when moving to bottom ranks
    int bottomRank = this.isWhite ? this.board.getHeight() : 1;
    if(p.getSquare().getRow() != bottomRank){
      return false;
    }

    return true;
  }

  protected boolean stillHasLegalMoves(){
    for(Piece p: this.board.getTeamPieces(this.isWhite)){
      // check normal moves
      for(Square s: p.canMoveTo(this.board)){
        String errMsg = this.moveWillLeadToSelfChecks(new Move(p.getSquare(), s));
        if(errMsg == null){
          return true;
        }
      }
      // check en passant
      if(p instanceof Pawn){
        int forwardDir = this.isWhite ? 1 : -1;
        Move enPassantMove1 = new Move(
          p.getSquare(), 
          new Square(p.getSquare().getRow() + forwardDir, p.getSquare().getColumn() + 1)
        );
        Move enPassantMove2 = new Move(
          p.getSquare(), 
          new Square(p.getSquare().getRow() + forwardDir, p.getSquare().getColumn() - 1)
        );
        if(this.canEnPassant(enPassantMove1) == null || this.canEnPassant(enPassantMove2) == null){
          return true;
        }
      }
    }
    return false;
  }

  /**
   * determine if it is stalemate
   * 1. King is NOT under check
   * 2. no legal moves
   * @return
   */
  protected boolean isStaleMate(){
    // is stalemate when king is Not under check
    if(this.isUnderCheck()){
      return false;
    }
    // is stalemate when no pieces can be moved without putting king under check
    if(this.stillHasLegalMoves()){
      return false;
    }
    return true;
  }

  /**
   * determine if it is checkmate
   * 1. King is under check
   * 2. King has no squares to move to
   * 3. enemy attacking piece cannot be captured
   * 4. enemy attacking path cannot be blocked
   * @return true if checkmate
   */
  protected boolean isCheckMate(){
    if(!this.isUnderCheck()){
      return false;
    }

    Piece king = this.getKing();
    if(king.canMoveTo(this.board).size() > 0){
      return false;
    }

    // for(Piece p: this.board.getTeamPieces(!this.isWhite)){
    //   if(p.canMoveTo(board).contains(new Square("f4"))){
    //     System.out.println(p);
    //     System.out.println(p.getSquare());
    //   }
    // }

    if(this.stillHasLegalMoves()){
      return false;
    }

    return true;
  }

  public abstract void makeOneMove() throws IOException;

  protected abstract Piece getPromotedPiece() throws IOException;
}
