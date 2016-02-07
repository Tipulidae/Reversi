package AI;

import java.util.Collection;
import java.util.Random;

import rules.Board;
import rules.Player;
import rules.Position;
import rules.RuleBook;

public class RandomAI extends Synth {
	
	private String name = "Random";
	public RandomAI() {
	}
	public RandomAI(Player color, Board board, RuleBook rules) {
		super( color, board, rules);
	}
	
	public Position makeMove() {
		return randomMove();
	}
	
	private Position randomMove() {
		Collection<Position> validMoves = rules.allValidMoves(board, color);
		int size = validMoves.size();
		Random r = new Random();
		int choice = r.nextInt(size);
		
		return (Position)validMoves.toArray()[choice];
	}
	
	public String toString() {
		return name+" "+super.toString();
	}
}
