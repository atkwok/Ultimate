# UltimateUltimateTicTacToe

This project (when done) should include the games Tic Tac Toe, Ultimate Tic Tac Toe, and Ultimate Ultimate Tic Tac Toe
with simple AIs implemented. 
The rules of Tic Tac Toe are as usual.
The rules for Ultimate Tic Tac Toe are also the standard ones - First turn player can move anywhere. Every turn after
player moves in the corresponding board to where last player moved on the smaller board. In the case of a completed
board player can move anywhere.

The rules for Ultimate Ultimate Tic Tac Toe are my own creation. They are as follows:
As probably suggested by the title, the board is a tic tac toe grid of Ultimate Tic Tac Toe grids.
As usual, the first turn the player can move anywhere.
Moves consist of marking 2 squares, one with your symbol then one with your opponent's symbol, on a single 3x3 board
The first mark, your symbol, still corresponds to the 3x3 grid where your opponent must move, and the second mark,
your opponent's symbol, corresponds to the 9x9 grid which contains the said 3x3 grid where your opponent must move.
In order to have access to a wider variety of boards from a single board, each 3-in-a-row on a small 3x3 board does
not automatically win that board, and it is cleared of all symbols. A 3-in-a-row generates a tally for that specific 
3x3 board for that player, and a 3x3 board is only truly won when a player has obtained 3 tallies on it. When a
player has 3 tallies on a board, their symbol is placed over it and it is fully completed for their side.
For 9x9 boards and the 27x27 board, only a single 3-in-a-row of respective smaller boards is necessary.

There are many edge cases, and in this game, achieving victory in a board gives you a slight bonus of sorts.
In the case of a tie due to a full 3x3 board, generate a tally for both players, and then clear the board. If this 
gives both players 3 tallies, the player whose turn it is truly wins or completes that board.
In the case of 8 pieces on a 3x3, place your symbol first. If that generates your 3rd tally for that board, you
may choose the 9x9 board where your opponent goes. Otherwise, clear the board, and place your opponent's symbol
anywhere on the newly cleared board. 
If you achieve a 3-in-a-row on the first symbol placement on a turn, finish placing your opponent's symbol, then 
evaluate that board as your win even if your opponent has also achieved a three in a row through your move. 
