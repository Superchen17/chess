package com.osullivan.chess;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashSet;

public class TextPlayer extends Player {
  private final BufferedReader inputReader;
  private final PrintStream out;
  private final BoardTextView view;
  
  public TextPlayer(boolean isWhite, Board board, BufferedReader inputSource, PrintStream out) {
    super(isWhite, board);
    this.inputReader = inputSource;
    this.out = out;
    this.view = new BoardTextView(board);
  }

  /**
   * test constructor
   * DO NOT invoke in other classes
   * @param isWhite
   * @param board
   */
  public TextPlayer(boolean isWhite, Board board){
    super(isWhite, board);
    this.inputReader = new BufferedReader(new InputStreamReader(System.in));
    this.out = System.out;
    this.view = new BoardTextView(board);
  }

  protected Move readMove(String prompt) throws IOException{
    Move move = null;
    while(move == null){
      try{
        this.out.print(prompt);
        String s = this.inputReader.readLine();
        if(s == null){
          throw new EOFException();
        }
        move = new Move(s);
      }
      catch(IllegalArgumentException e){
        this.out.print(e + "\n");
      }
    }
    return move;
  }

  protected String tryExecuteMove(Move move) throws IOException{
    String errMsg;
    // check squares on board
    errMsg = this.areMoveSquaresValid(move);
    if(errMsg != null){
      return errMsg;
    }

    // try castle
    Move expectedQueenSideCastleMove = new Move(this.isWhite ? "e1c1" : "e8c8");
    Move expectedKingSideCastelMove = new Move(this.isWhite ? "e1g1" : "e8g8");
    if(move.equals(expectedQueenSideCastleMove)){
      errMsg = this.tryQueenSideCastle();
      if(errMsg == null){
        return null;
      }
    }
    else if(move.equals(expectedKingSideCastelMove)){
      errMsg = this.tryKingSideCastle();
      if(errMsg == null){
        return null;
      }
    }

    // try normal moves
    errMsg = this.tryMakeNormalMove(move);
    if(errMsg == null){
      return null;
    }

    // try en passant
    errMsg = this.tryEnPassant(move);
    if(errMsg == null){
      return null;
    }

    return errMsg;
  }

  @Override
  public void makeOneMove() throws IOException{
    this.out.print(view.displayBoard());
    this.out.print((this.isWhite ? "White" : "Black") + "\'s turn\n");

    String errMsg = "";
    while(errMsg != null){
      Move move = this.readMove("enter move:\n");
      errMsg = this.tryExecuteMove(move);
      if(errMsg != null){
        this.out.print("Invalid move, enter move again\n");
      }
      else{
        this.board.appendMoveLog(move);
      }
    }
    this.out.print(view.displayBoard());
  }

  private String readPromotionChoice(String prompt, String errMsg) throws IOException{
    String choice = null;
    while(choice == null){
      this.out.print(prompt);
      String s = this.inputReader.readLine();
      if(s == null){
        throw new EOFException();
      }

      if(s.length() == 1){
        HashSet<String> allowedChoices = new HashSet<>();
        allowedChoices.add("R");
        allowedChoices.add("N");
        allowedChoices.add("B");
        allowedChoices.add("Q");
        
        if(allowedChoices.contains(s.toUpperCase())){
          choice = s.toUpperCase();
        }
      }
      if(choice == null){
        this.out.print(errMsg);
      }
    }
    return choice;
  }

  @Override
  protected Piece getPromotedPiece() throws IOException {
    String prompt = "Choose your promotion piece (R, N, B, Q):\n";
    String errMsg = "invalid promotion choice\n";
    String choice = this.readPromotionChoice(prompt, errMsg);

    switch (choice) {
      case "R":
        return new Rook("R", this.isWhite, null, true);
      case "N":
        return new Knight("N", this.isWhite, null);
      case "B":
        return new Bishop("B", this.isWhite, null);
      case "Q":
        return new Queen("Q", isWhite, null);
      default:
        throw new IllegalAccessError("failed to determine promotion type");
    }
  }
}
