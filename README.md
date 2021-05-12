# Gomoku-Game
2-player board game in Java that involves both players attempting to be the first to place five of their own pieces in a row on the board.  Utilizes JavaFX to display visuals and construct two-dimensional board.
## Rules
The game is played on a grid of squares for two players: Black and White. Black moves first. Each player alternates playing pieces on the board. Once played, a piece cannot be moved. A player wins if they get five of their pieces in a row, horizontally, vertically, or diagonally.  Additionally, the players will also follow these three clauses:
- Overline: There must be exactly five in a row to win. Six or more in a row does not win, and the game continues.
- Four-Four: A player may not make a move if that move simultaneously creates two or more groups of four in a row.
- Three-Three A player may not make a move if that move simultaneously creates two or more groups of three in a row such that both ends of the three have empty squares.
## Modes
The user is able to input up to three arguments to adjust the board size and numeric win condition.
- java Gomoku starts a game with a 19x19 grid,
- java Gomoku 15 12 starts a game with 15 rows and 12 columns,
- java Gomoku 7 starts a game with a 19x19 grid and to win you need 7 in a row, and
- java Gomoku 6 15 12 starts a game with 15 rows and 12 columns and to win you need 6 in a row. 
## Testing
This game has been rigorously unit tested using JUnit framework.  Details regarding specific tests are detailed in the Gomoku Testing Document PDF file.
