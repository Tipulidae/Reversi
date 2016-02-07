package AI;

import rules.Board;
import rules.Player;
import rules.Position;
import rules.RuleBook;

public class GreedyAI extends Synth {
	
	private String name = "Greedy";
	public GreedyAI() {
	}
	public GreedyAI(Player color, Board board, RuleBook rules) {
		super(color, board, rules);
	}
	
	
	
	public Position makeMove() {
		return greedyMove();
	}
	
	private Position greedyMove() {
		int mostCaptures = 0;
		int maxCaptures = 6;
		Position greediestMove = null;
		for (Position p : rules.allValidMoves(board, color)) {
			int captures = rules.captures(board,p,color).size();
			if (captures > mostCaptures) {
				mostCaptures = captures;
				greediestMove = p;
				if (mostCaptures >= maxCaptures) break;
			}
		}
		
		return greediestMove;
	}

	public String toString() {
		return name+" "+super.toString();
	}
}
