package AI;

import java.util.Collection;

import rules.Board;
import rules.MutableBoard;
import rules.Player;
import rules.Position;
import rules.RuleBook;

public class MinimaxAI extends Synth {
	private int depth = 0;
	private int maxDepth;
	private String name = "Minimax";
	public MinimaxAI() {
		
	}
	
	public MinimaxAI(Player color, Board board, RuleBook rules) {
		super(color, board, rules);
	}
	
	public String toString() {
		return name+" "+super.toString();
	}
	
	@Override
	public Position makeMove() {
		maxDepth = 3;
		Position bestMove = null;
		int bestScore = 0;
		for (Position p : rules.allValidMoves(board, color)) {
			
			Board state = result(board, p, color);
			depth = 0;
			int score = minValue(state);
			if (score > bestScore) {
				bestScore = score;
				bestMove = p;
			}
		}
		return bestMove;
	}
	
	private Board result(Board state, Position pos, Player color) {
		MutableBoard nextState = new MutableBoard(state);
		nextState.placeDisk(pos, color);
		
		for (Position capture : rules.captures(nextState, pos, color)) {
			nextState.flip(capture);
		}
		return nextState;
	}
	
	private int minValue(Board b) {
		depth++;
		if (cutoffTest(b)) {
			depth--;
			return evaluationFunction(b);
		}
		Collection<Position> validMoves = rules.allValidMoves(b,Player.opposite(color));
		if (validMoves.isEmpty()) {
			return maxValue(b);
		}
		
		int v = Integer.MAX_VALUE;
		for (Position p : validMoves) {
			Board nextState = result(b,p,Player.opposite(color));
			v = Math.min(v,maxValue(nextState));
		}
		depth--;
		return v;
	}
	
	private int maxValue(Board b) {
		depth++;
		if (cutoffTest(b)) {
			depth--;
			return evaluationFunction(b);
		}
		Collection<Position> validMoves = rules.allValidMoves(b,color);
		if (validMoves.isEmpty()) {
			return minValue(b);
		}
		
		int v = Integer.MIN_VALUE;
		for (Position p : validMoves) {
			Board nextState = result(b,p,color);
			v = Math.max(v,minValue(nextState));
		}
		depth--;
		return v;
	}
	
	private boolean cutoffTest(Board state) {
		boolean maxDepthReached = depth >= maxDepth;
		return maxDepthReached || isGameOver(state);
	}
	
	private boolean isGameOver(Board state) {
		boolean noWhiteMove = rules.allValidMoves(state,Player.WHITE).isEmpty();
		boolean noBlackMove = rules.allValidMoves(state,Player.BLACK).isEmpty();
		return noWhiteMove && noBlackMove;
	}
	
	private int evaluationFunction(Board state) {
		return state.currentScore().score(color);
	}
}
