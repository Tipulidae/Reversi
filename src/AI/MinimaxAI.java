package AI;

import java.util.Collection;

import rules.Board;
import rules.MutableBoard;
import rules.Player;
import rules.Position;
import rules.RuleBook;

public class MinimaxAI extends Synth {
	private int currentDepth = 0;
	private int currentMaxDepth = 0;
	private String name = "Minimax";
	private Position bestMove;
	public final int MAXDEPTH = 3;
	
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
		while (currentMaxDepth < MAXDEPTH) {
			currentMaxDepth++;
			bestMove = IDS();
		}
		return bestMove;
	}
	
	public Position IDS() {
		Position bestMove = null;
		int bestScore = 0;
		for (Position p : rules.allValidMoves(board, color)) {
			
			Board state = result(board, p, color);
			currentDepth = 0;
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
		currentDepth++;
		if (cutoffTest(b)) {
			currentDepth--;
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
		currentDepth--;
		return v;
	}
	
	private int maxValue(Board b) {
		currentDepth++;
		if (cutoffTest(b)) {
			currentDepth--;
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
		currentDepth--;
		return v;
	}
	
	private boolean cutoffTest(Board state) {
		boolean maxDepthReached = currentDepth >= currentMaxDepth;
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
