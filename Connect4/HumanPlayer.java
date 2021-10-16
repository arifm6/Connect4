package assignment1;

import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	//this is the meat of the class. before you continue, figure out how you want your connect 4 game state to be saved =).
	public void makeMove(Board board) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in.. unfortunately cant close without other methods so will leave open
		System.out.println(this.name + ", please input your move: ");

		int input = reader.nextInt(); // Scans the next token of the input as an int.
		if(input > board.getColumns() || input < 1) {
			System.out.println("That is not a valid input. Please try again!");
			makeMove(board);
			return;
		}
		//the below should be a method from the board class instead and make gameState private
		//the if statement is to ensure that the column has available room and if it does not, then it will recommend a new move
		if(board.isEmptyColumn(input)) {
			board.dropPieceAt(input, this.symbol);
			return;
		}
		System.out.println("That column is full, please try again");
		makeMove(board);
		return;

		//once finished
		//reader.close();
		

		
	}
}
