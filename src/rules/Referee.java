package rules;

import java.util.Collection;
import java.util.Observer;

public class Referee {
	
	private MutableBoard board;
	private RuleBook rules;
	private Player currentPlayer;
	private Collection<Position> validMoves;
	private Status status;
	
	private int numberOfPlayersWithNoMoves = 0;
	
	private boolean gameOver = false;
	
	public Referee() {
		board = new MutableBoard();
		rules = new RuleBook();
		status = new Status(this);
	}
	
	public void initializeGame() {
		board.setStartPositions();
		currentPlayer = Player.BLACK;
		validMoves = rules.allValidMoves(board,currentPlayer);
	}
	
	public void addStatusObserver(Observer o) {
		status.addObserver(o);
		status.update();
	}
	
	public Board currentBoardState() {
		return new ReadOnlyBoard(board);
	}
	
	public Player currentPlayer() {
		return currentPlayer;
	}
	
	public RuleBook getRules() {
		return rules;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Score currentScore() {
		return board.currentScore();
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	
	public boolean makeMove(Position move) {
		if (validMoves.contains(move)) {
			board.placeDisk(move, currentPlayer);
			
			for (Position p : rules.captures(board, move, currentPlayer)) {
				board.flip(p);
			}
			
			endTurn();
			return true;
		}
		return false;
	}
	
	private void endTurn() {
		if (gameOver) return;
		if (numberOfPlayersWithNoMoves >= 2) {
			// End Game!
			gameOver = true;
		}
		
		currentPlayer = Player.opposite(currentPlayer);
		validMoves = rules.allValidMoves(board,currentPlayer);
		status.update();
		
		if (validMoves.isEmpty()) {
			numberOfPlayersWithNoMoves++;
			endTurn();
		} else {
			numberOfPlayersWithNoMoves = 0;
		}
	}
}
