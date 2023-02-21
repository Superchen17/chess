package com.osullivan.chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class App {
  private final Player player1;
  private final Player player2;

  public App(Player player1, Player player2){
    this.player1 = player1;
    this.player2 = player2;
  }

  private Player alternatePlayer(Player curr){
    if(curr == this.player1){
      return this.player2;
    }
    return this.player1;
  }

  public void playGame(PrintStream out) throws IOException{
    Player currentPlayer = this.player1;
    while(!currentPlayer.isCheckMate() && !currentPlayer.isStaleMate()){
      currentPlayer.makeOneMove();
      currentPlayer = this.alternatePlayer(currentPlayer);
    }
    if(currentPlayer.isCheckMate()){
      Player winner = this.alternatePlayer(currentPlayer);
      out.print((winner.isWhitePlayer() ? "White": "Black") + " wins!\n");
    }
    else if(currentPlayer.isStaleMate()){
      out.print("Stalemate!\n");
    }
  }

  public static void main(String[] args) throws IOException {
    Board b = new ChessBoard(8, 8);
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    PrintStream out = System.out;
    Player player1 = new TextPlayer(true, b, input, out);
    Player player2 = new TextPlayer(false, b, input, out);

    App app = new App(player1, player2);
    app.playGame(out);
  }
}
