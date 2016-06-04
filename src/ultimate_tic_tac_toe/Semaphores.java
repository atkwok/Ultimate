package ultimate_tic_tac_toe;

/**
 * 
 * @author Alan
 *
 */
public class Semaphores extends TicTacToeGame{
	
	/**
	 * pieces is the internal 3x5 representation of the board. 
	 * 0 represents an empty space, 1 represents a blue o,
	 * 2 represents a red x, and 3 represents a red x on top of a blue o.
	 */
	private int[][] pieces;
	
	/**
	 * 
	 */
	public Semaphores() {
		this.pieces = new int[3][5];
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 5; j ++) {
				this.pieces[i][j] = 0;
			}
		}
	}
	

	/**
	 * 
	 */
	@Override
	protected void drawBoard() {
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 5; j ++) {
				if (i == 0 || i == 4) {
					continue;
				}
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			    if (this.pieces[i - 1][j] == 1) {
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/o.png", 1, 1);
			    } else if (this.pieces[i - 1][j] == 2) {
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/x.png", 1, 1);
			    } else if (this.pieces[i - 1][j] == 3) {
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/o.png", 1, 1);
			    	StdDrawPlus.picture(i + .5, j + .5, "src/img/x.png", 1, 1);
			    }
			}
		}
		
	}

	/**
	 * 
	 */
	@Override
	protected boolean gameWon(int side) {
		// checks verticals (will be horizontals once I flip board)
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 3; j ++) {
				if (this.pieces[i][j] > 0 && this.pieces[i][j] == this.pieces[i][j + 1] && this.pieces[i][j] == this.pieces[i][j + 2]) {
					System.out.println("Player " + side + " won!");
					return true;
				}
			}
		}
		// checks horizontals (will be verticals once flipped
		for (int j = 0; j < 5; j ++) {
			if (this.pieces[0][j] > 0 && this.pieces[0][j] == this.pieces[1][j] && this.pieces[0][j] == this.pieces[2][j]) {
				System.out.println("Player " + side + " won!");
				return true;
			}
		}
		// checks diagonals. needs editting once flipped
		for (int j = 0; j < 3; j ++) {
			if (this.pieces[1][j + 1] > 0 && 
					(this.pieces[0][j] == this.pieces[1][j + 1] && this.pieces[1][j + 1] == this.pieces[2][j + 2]) || 
					this.pieces[2][j] == this.pieces[1][j + 1] && this.pieces[0][j + 2] == this.pieces[1][j + 1]) {
				System.out.println("Player " + side + " won!");
				return true;				
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	protected boolean validMove(int x, int y) {
		if (this.pieces[x][y] == 3) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	@Override
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
				x = (int) StdDrawPlus.mouseX() - 1;
		        y = (int) StdDrawPlus.mouseY();
		        if (this.validMove(x, y)) {
			        if (prevX != -1 && prevY != -1) {
			        	this.pieces[prevX][prevY] --;
			        }
			        this.pieces[x][y] ++;
			        prevX = x;
			        prevY = y;
			        turn_done = true;
			        StdDrawPlus.show(10);
		        }
			}
			else if (StdDrawPlus.isSpacePressed() && turn_done) {
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
	 * 
	 */
	public static void main(String[] args) {
		Semaphores b = new Semaphores();
		StdDrawPlus.setXscale(0, 5);
		StdDrawPlus.setYscale(0, 5);
		b.playGame();
	}
}
