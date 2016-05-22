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
	private int[][] pieces;
	private int[][] boards;
	int currBoardX;
	int currBoardY;
	
	/**
	 * Constructor of standard Ultimate Tic Tac Toe board of size 9
	 */
	public UltimateTicTacToe() {
		this.pieces = new int[11][11];
		this.boards = new int[3][3];
		this.currBoardX = -1;
		this.currBoardY = -1;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				this.pieces[i][j] = -1;
			}
		}
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				this.boards[m][n] = -1;
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
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					// Add png file for grid maybe?
					continue;
				} else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
			    } else {
			    	StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
			    }
			    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			    if (this.pieces[i][j] == 0) {
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/x.png", 1, 1);
			    } else if (this.pieces[i][j] == 1) {
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/o.png", 1, 1);
			    }
			}
		}
		return;
	}
	
	/**
	 * 
	 */
	public void playGame() {
		int side = 0;
		int prevX = -1;
		int prevY = -1;
		int x;
		int y;
		boolean turn_done = false;
		boolean gameOver = false;
		while (true) {
			this.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				x = (int) StdDrawPlus.mouseX();
		        y = (int) StdDrawPlus.mouseY();
		        if (this.validMove(x, y)) {
			        if (prevX != -1 && prevY != -1) {
			        	this.pieces[prevX][prevY] = -1;
			        }
			        this.pieces[x][y] = side;
			        prevX = x;
			        prevY = y;
			        turn_done = true;
			        StdDrawPlus.show(10);
		        }
			}
			else if (StdDrawPlus.isSpacePressed() && turn_done) {
				this.boardWon(side, prevX, prevY);
				this.updateCurrBoard(prevX, prevY);
				if (this.gameWon(side)) {
					gameOver = true;
				}
				prevX = -1;
				prevY = -1;
				side = 1 - side;
				turn_done = false;
			}
			StdDrawPlus.show(10);
			if (gameOver) {
				StdDrawPlus.show(5000);
				return;
			}
		}
	}
	
	/**
	 * Checks whether a move at grid square coordinate x,y is valid or not.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	protected boolean validMove(int x, int y) {
		if (this.pieces[x][y] != -1 || x == 3 || x == 7 || y == 3 || y == 7) {
			return false;
		}
		int boardX;
		int boardY;
		if (x < 3) {
			boardX = 0;
		} else if (x < 7) {
			boardX = 1;
		} else {
			boardX = 2;
		}
		if (y < 3) {
			boardY = 0;
		} else if (y < 7) {
			boardY = 1;
		} else {
			boardY = 2;
		}
		if (boardX != this.currBoardX || boardY != this.currBoardY) {
			if (this.currBoardX == -1 && this.currBoardY == -1 && this.boards[boardX][boardY] == -1) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	protected void updateCurrBoard(int x, int y) {
		if (x > 7) {
			x--;
		}
		if (x > 3) {
			x--;
		}
		if (y > 7) {
			y--;
		}
		if (y > 3) {
			y--;
		}
		this.currBoardX = x % 3;
		this.currBoardY = y % 3;
		if (this.boards[this.currBoardX][this.currBoardY] != -1) {
			this.currBoardX = -1;
			this.currBoardY = -1;
		}
		return;
	}
	
	/**
	 * 
	 * @param side
	 * @param x
	 * @param y
	 * @return
	 */
	protected void boardWon(int side, int x, int y) {
		if (x < 3) {
			x = 0;
		} else if (x < 7) {
			x = 4;
		} else {
			x = 8;
		}
		if (y < 3) {
			y = 0;
		} else if (y < 7) {
			y = 4;
		} else {
			y = 8;
		}
		for (int i = 0; i < 3; i++) {
			if (this.pieces[x + i][y] == this.pieces[x + i][y + 1] && 
					this.pieces[x + i][y + 1] == this.pieces[x + i][y + 2] && 
					this.pieces[x + i][y + 1] == side) {
				this.boards[this.currBoardX][this.currBoardY] = side;
				return;
			}
			if (this.pieces[x][y + i] == this.pieces[x + 1][y + i] && 
					this.pieces[x + 1][y + i] == this.pieces[x + 2][y + i] && pieces[x + 1][y + i] == side) {
				this.boards[this.currBoardX][this.currBoardY] = side;
				return;
			}
		}
		if ((this.pieces[x][y] == this.pieces[x + 1][y + 1] && 
				this.pieces[x + 1][y + 1] == this.pieces[x + 2][y + 2] ||
				this.pieces[x + 2][y] == this.pieces[x + 1][y + 1] && 
				this.pieces[x + 1][y + 1] == this.pieces[x][y + 2]) 
				&& this.pieces[x + 1][y + 1] == side) {
			this.boards[this.currBoardX][this.currBoardY] = side;
			return;
		}
		int total = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				if (this.pieces[x + i][y + j] >= 0) {
					total++;
				}
			}
		}
		if (total == 9) {
			this.boards[this.currBoardX][this.currBoardY] = 3;
			return;
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
	protected boolean gameWon(int side) {
		for (int i = 0; i < 3; i++) {
			if (this.boards[i][0] == this.boards[i][1] && 
					this.boards[i][1] == this.boards[i][2] && 
					this.boards[i][1] == side) {
				System.out.println("Player " + side + " won!");
					return true;
			}
			if (this.boards[0][i] == this.boards[1][i] && 
					this.boards[1][i] == this.boards[2][i] && 
					this.boards[1][i] == side) {
				System.out.println("Player " + side + " won!");
				return true;
			}
		}
		if ((this.boards[0][0] == this.boards[1][1] && this.boards[1][1] == this.boards[2][2] ||
				this.boards[2][0] == this.boards[1][1] && this.boards[1][1] == this.boards[0][2]) 
				&& this.boards[1][1] == side) {
			System.out.println("Player " + side + " won!");
			return true;
		}
		int total = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				if (this.boards[i][j] >= 0) {
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
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		UltimateTicTacToe b = new UltimateTicTacToe();
		StdDrawPlus.setScale(0, 11);
		b.playGame();
	}
}
