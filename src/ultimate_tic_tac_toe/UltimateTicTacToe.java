package ultimate_tic_tac_toe;

import java.awt.event.KeyEvent;

/**
 * Plays a game of Ultimate Tic Tac Toe
 * @author Alan Kwok https://github.com/atkwok
 */
public class UltimateTicTacToe extends TicTacToeGame {
	/**
	 * pieces is the internal representation of a 3x3 array of 3x3 arrays.
	 * In other words, it is a tic tac toe board of tic tac toe boards
	 * boards is the 3x3 array of the current state of the 9x9 board
	 * Again, 0 is X, 1 is O, -1 is an empty space
	 */
	private int[][][][] pieces;
	private int[][] boards;
	
	/**
	 * Constructor of standard Ultimate Tic Tac Toe board of size 9
	 */
	public UltimateTicTacToe() {
		pieces = new int[3][3][3][3];
		boards = new int[3][3];
		int[][] currboard;
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				currboard = new int[3][3];
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						currboard[i][j] = -1;
					}
				}
				pieces[m][n] = currboard;
				boards[m][n] = -1;
			}
		}
	}
	
	/**
	 * Draws the board based on the configuration of the pieces.
	 */
	protected void drawBoard() {
		for (int i = 0; i < 11; i ++) {
			for (int j = 0; j < 11; j ++) {
				if (i == 3 || i == 7 || j == 3 || j == 7) {
					StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
			    } else {
			    	StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
			    }
			    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		return;
	}
	
	/**
	   * Determines whether the game is over after that player has moved.
	   * If a player has won or if there is a tie, prints out 
	   * 
	   * @param side - The current player's side, either a 0 for X or a 1 for O
	   * 
	   * @return - Returns true if the game is over, and false otherwise.
	   */
	protected boolean boardWon(int side) {
		for (int i = 0; i < 3; i++) {
			if (boards[i][0] == boards[i][1] && boards[i][1] == boards[i][2] && boards[i][1] == side) {
				System.out.println("Player " + side + " won!");
					return true;
			}
			if (boards[0][i] == boards[1][i] && boards[1][i] == boards[2][i] && boards[1][i] == side) {
				System.out.println("Player " + side + " won!");
				return true;
			}
		}
		if ((boards[0][0] == boards[1][1] && boards[1][1] == boards[2][2] ||
			boards[2][0] == boards[1][1] && boards[1][1] == boards[0][2]) && boards[1][1] == side) {
			System.out.println("Player " + side + " won!");
			return true;
		}
		int total = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				if (boards[i][j] >= 0) {
					total++;
				}
			}
		}
		if (total == 9) {
			System.out.println("Nobody lost. It's a tie!");
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		UltimateTicTacToe b = new UltimateTicTacToe();
		StdDrawPlus.setScale(0, 11);
		int side = 0;
		int prevX = -1;
		int prevY = -1;
		int x;
		int y;
		boolean turn_done = false;
		boolean gameOver = false;
		while (true) {
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();
				prevX = x;
		        prevY = y;
		        turn_done = true;
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
