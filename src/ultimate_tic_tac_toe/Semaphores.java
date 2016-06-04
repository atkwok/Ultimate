package ultimate_tic_tac_toe;

/**
 * 
 * @author Alan
 *
 */
public class Semaphores extends TicTacToeGame{
	
	/**
	 * pieces is the internal 3x5 representation of the board. 
	 * 0 represents an empty space, 1 represents a blue piece,
	 * 2 represents a green piece, and 3 represents a red piece.
	 */
	private int[][] pieces;
	
	/**
	 * 
	 */
	public Semaphores() {
		
	}
	

	/**
	 * 
	 */
	@Override
	protected void drawBoard() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	protected boolean gameWon(int side) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public static void main(String[] args) {
		Semaphores b = new Semaphores();
		StdDrawPlus.setXscale(0, 3);
		StdDrawPlus.setYscale(0, 5);
		b.playGame();
	}
}
