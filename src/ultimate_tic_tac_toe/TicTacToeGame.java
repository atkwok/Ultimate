package ultimate_tic_tac_toe;

public abstract class TicTacToeGame {
	abstract protected void drawBoard();
	abstract protected boolean gameWon(int side);
	abstract public void playGame();
}
