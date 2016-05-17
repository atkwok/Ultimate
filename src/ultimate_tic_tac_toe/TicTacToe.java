package ultimate_tic_tac_toe;

import java.awt.event.KeyEvent;

/**
 * Plays a game of Tic Tac Toe.
 * @author Alan Kwok https://github.com/atkwok
 */
public class TicTacToe {
  /**
   * pieces is the internal representation of the board
   *         0 is X, 1 is O, -1 is an empty space
   */
  private int[][] pieces;

  /**
   * Constructor for a standard TicTacToe board of size 3
   */
  public TicTacToe() {
    pieces = new int[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        pieces[i][j] = -1;
      }
    }
  }

  /**
   * Draws the board based on the configuration of Pieces
   */
  private void drawBoard() {
    for (int i = 0; i < pieces.length; i++) {
      for (int j = 0; j < pieces[0].length; j++) {
        if ((i + j) % 2 == 0) {
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        } else {
          StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
        }
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        if (pieces[i][j] == 0) {
          StdDrawPlus.picture(i + .5, j + .5, "src/img/x.png", 1, 1);
        } else if (pieces[i][j] == 1) {
          StdDrawPlus.picture(i + .5, j + .5, "src/img/o.png", 1, 1);
        }
      }
    }
  }
  
  /**
   * Determines whether the game is over after that player has moved.
   * If a player has won or if there is a tie, prints out 
   * 
   * @param side - The current player's side, either a 0 for X or a 1 for O
   * 
   * @return - Returns true if the game is over, and false otherwise.
   */
  private boolean boardWon(int side) {
	  for (int i = 0; i < 3; i++) {
		  if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][1] == side) {
			  System.out.println("Player " + side + " won!");
			  return true;
		  }
		  if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[1][i] == side) {
			  System.out.println("Player " + side + " won!");
			  return true;
		  }
	  }
	  if ((pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] ||
		  pieces[2][0] == pieces[1][1] && pieces[1][1] == pieces[0][2]) && pieces[1][1] == side) {
		  System.out.println("Player " + side + " won!");
		  return true;
	  }
	  int total = 0;
	  for (int i = 0; i < 3; i++) {
		  for (int j = 0; j < 3; j ++) {
			  if (pieces[i][j] >= 0) {
				  total++;
			  }
		  }
	  }
	  if (total == 9) {
		  System.out.println("Nobody won. It's a tie!");
		  return true;
	  }
	  return false;
  }

  /**
   * Runs a simple game of Tic Tac Toe, and prints out which player won, or if there was a tie.
   */
  public static void main(String[] args) {
    TicTacToe b = new TicTacToe();
    StdDrawPlus.setScale(0, 3);
    int side = 0;
    int prevX = -1;
    int prevY = -1;
    boolean turn_done = false;
    boolean gameOver = false;
    while (true) {
      b.drawBoard();
      if (StdDrawPlus.mousePressed()) {
        int x = (int) StdDrawPlus.mouseX();
        int y = (int) StdDrawPlus.mouseY();
        if (b.pieces[x][y] == -1) {
	        if (prevX != -1 && prevY != -1) {
	        	b.pieces[prevX][prevY] = -1;
	        }
	        b.pieces[x][y] = side;
	        prevX = x;
	        prevY = y;
	        turn_done = true;
	        StdDrawPlus.show(10);
        }
      }
      else if (StdDrawPlus.isSpacePressed() && turn_done) {
        side = 1 - side;
        turn_done = false;
        prevX = -1;
        prevY = -1;
      }
      StdDrawPlus.show(10);
      if (gameOver) {
    	  StdDrawPlus.show(5000);
    	  return;
      }
      if (b.boardWon(side)) {
    	  gameOver = true;
      }
    }
  }	
}
