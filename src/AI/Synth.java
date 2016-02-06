package AI;

import rules.Board;
import rules.Position;
import rules.RuleBook;

public class Synth {
	private Board board;
	private RuleBook rules;
	public Synth(Board board, RuleBook rules) {
		this.board = board;
		this.rules = rules;
	}
	
	public Position makeMove() {
		
		return new Position(0,0);
	}
}
