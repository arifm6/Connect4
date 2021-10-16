package assignment1;
public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char[][] gameState = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 */
	
	//note some print statements are only there for testing
	
	public Board() {
		//TODO
		//calling reset in case java initially does not declare a char array as ' ' in each entry.
		reset();
	}
	
	public void printBoard() {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			System.out.print("|");

			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				System.out.print(gameState[i][j]);
				System.out.print("|");
			}
			System.out.print('\n');
		}
	}
	
	public boolean isEmptyColumn(int column) {
		return gameState[0][column - 1] == ' ';
	}
	public void dropPieceAt(int column, char symbol) {
		if(!isEmptyColumn(column)) {
			System.out.println("That is an invalid option as the column is full, please try again");

			return;
		}

		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {
			if(gameState[i][column - 1] == ' ') {
				gameState[i][column - 1] = symbol;
				return;
			}
			
		}
		//the above will always execute if the connect 4 board has appropriate dimensions so depending on specifications of assignment, this can be
		//modified. I just realized it may not execute if board is full and infinite loop
		System.out.println("Return did not execute in for loop, returning");
		return;
	}
	//removes the top piece of the board. This is to test winning moves for the AI although aliasing might get the job done too
	public void removeTop(int column) {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			if(gameState[i][column - 1] != ' ') {
				gameState[i][column - 1] = ' ';
				return;
			}
		}
	}
	//there is only 2 players so given one symbol, then it should not be a problem finding the other.
	public char getOpponent(char symbol) {
		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				if(gameState[i][j] != ' ' && gameState[i][j] != symbol) {
					return gameState[i][j];
				}
			}
		}
		return ' ';
		
	}
	public boolean containsWin() {
		//note: I later realized we can start to be a lot more efficient by always going along columns and if you have any empty columns,
		//you can just break out of the for loop immediately and continue to the next column since you wont have to go up the board.
		//i chose not to as I was already done implementing 
		//my current implementation starts at the very bottom row and makes its way upwards because in connect 4, 4 in a sequence is more likely to occur
		//at the very bottom
		
		
		//CHECK FOR WIN HORIZONTALLY
		//go along columns like snakes and ladders path for most efficient way. Can be optimized
		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {

			for(int j = 0; j < NUM_OF_COLUMNS - 3; j++) {

				if(gameState[i][j] != ' ')
					if(gameState[i][j] == gameState[i][j + 1] && gameState[i][j + 1] == gameState[i][j + 2] && gameState[i][j + 2] == gameState[i][j + 3]) {
						System.out.println("horizontal at i: " + i + " and j: " + j);
						return true;
					}
			}
		}
		//CHECK FOR WIN VERTICALLY
		//go along columns for most efficient path starting from the very bottom of board

		for(int i = NUM_OF_ROW - 1; i >= NUM_OF_ROW - 3; i--) {

			for(int j = 0; j <= NUM_OF_COLUMNS - 1; j++) {

				if(gameState[i][j] != ' ')
					if(gameState[i][j] == gameState[i - 1][j] && gameState[i - 1][j] == gameState[i - 2][j] && gameState[i - 2][j] == gameState[i - 3][j]) {
						System.out.println("vertical at i: " + i + " and j: " + j);

						return true;
					}
			}
		}
		//CHECK FOR WIN ASCENDING DIAGONAL
		//restrict to NUM_COLUMN - 3 and NUM ROW - 3
		for(int i = NUM_OF_ROW - 1; i >= NUM_OF_ROW - 3; i--) {
			for(int j = 0; j < NUM_OF_COLUMNS - 3; j++) {
				if(gameState[i][j] != ' ') {
					if(gameState[i][j] == gameState[i - 1][j + 1] && gameState[i - 1][j + 1] == gameState[i - 2][j + 2] && gameState[i - 2][j + 2] == 
							gameState[i - 3][j + 3]) {
						System.out.println("ascending at i: " + i + " and j: " + j);

						return true;
					}
				}
			}
		}		
		//CHECK FOR DESCENDING DIAGONAL
		//start at NUM_OF_ROW - 4 and go to NUM_OF_COLUMNS - 3

		for(int i = NUM_OF_ROW - 4; i >= 0; i--) {
			for(int j = 0; j < NUM_OF_COLUMNS - 3; j++) {
				if(gameState[i][j] != ' ') {
					if(gameState[i][j] == gameState[i + 1][j + 1] && gameState[i + 1][j + 1] == gameState[i + 2][j + 2] && gameState[i + 2][j + 2] == 
							gameState[i + 3][j + 3]) {
						System.out.println("descending at i: " + i + " and j: " + j);

						return true;
					}
				}
			}
		}

		return false;
	}
	//simple getter method 

	public int getColumns() {
		return NUM_OF_COLUMNS;
	}
	public boolean isTie() {
		/*
		 * algorithm to check if the board state is a tie
		 */
		//only need to check top row if board is empty;
		for(int i = 0; i < NUM_OF_COLUMNS; i++) {
			if(gameState[0][i] == ' ') {
				return false;
			}
		}
		if(!containsWin()) {
			return true;
		}
		return false;
		//TODO
	}
	
	public void reset() {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				gameState[i][j] = ' ';
			}
		}
	}
	
}
