package AI;

import rules.Board;
import rules.Player;
import rules.Position;
import rules.RuleBook;

public abstract class Synth {
	protected Player color;
	protected Board board;
	protected RuleBook rules;
	
	public Synth() {
	}
	
	public Synth(Player color, Board board, RuleBook rules) {
		this.color = color;
		this.board = board;
		this.rules = rules;
	}
	
	public void giveColor(Player color) {
		this.color = color;
	}

	public void giveBoard(Board board) {
		this.board = board;
		
	}
	
	public void giveRules(RuleBook rules) {
		this.rules = rules;
	}
	
	public abstract Position makeMove();
	
	public String toString() {
		return "["+color+"]";
	}
}
