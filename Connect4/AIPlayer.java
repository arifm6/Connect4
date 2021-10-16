package assignment1;

public class AIPlayer extends Player {
	
	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	//this is the meat of the class. before you continue, figure out how you want your connect 4 game state to be saved =).
	//however, also figure out an optimal algorithm to always win connect 4 OR just find/block winning moves and randome move generator.
	public void makeMove(Board board) {

		//NOTE, ALTHOUGH THIS USES A FOR LOOP, IT DOES NOT ACCESS THE array of Board. IT IS SIMPLY TESTING ALL COLUMNS TO SEE IF IT MAKES A WINNING MOVE
		//note dont confuse the indexing as the game starts from 1-7 whereas array starts from 0.
		// find winning move if exists by testing all columns
		for(int i = 1; i <= board.getColumns(); i++) {
			if(board.isEmptyColumn(i)) {
				board.dropPieceAt(i, symbol);		
				if(board.containsWin()) {
					return;
				}
				board.removeTop(i);
			}
		}
		//find opponents symbol
		//then test all columns with opponent symbol to see if is a winning move
		char opponentSymbol = board.getOpponent(symbol);
		if(opponentSymbol != ' ') {
			for(int i = 1; i <= board.getColumns(); i++) {
				if(board.isEmptyColumn(i)) {
					board.dropPieceAt(i, opponentSymbol);
					if(board.containsWin()) {
						board.removeTop(i);
						board.dropPieceAt(i, symbol);
						return;
					}
					board.removeTop(i);
					
				}
			}
		}
		//make random move. I might just impliment it as try column 1, then 2, then 3, ...
		for(int i = 1; i < board.getColumns(); i++) {
			if(board.isEmptyColumn(i)) {
				board.dropPieceAt(i, symbol);
				break;
			}

			continue;
			
		}
		
	}
	

}
