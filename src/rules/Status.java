package rules;

import java.util.Observable;

public class Status extends Observable {
	private Referee ref;

	public Status(Referee ref) {
		this.ref = ref;
	}

	public int blackScore() {
		return ref.currentScore().black;
	}

	public int whiteScore() {
		return ref.currentScore().white;
	}

	public Player currentPlayer() {
		return ref.currentPlayer();
	}

	public boolean gameOver() {
		return ref.gameOver();
	}

	public void update() {
		setChanged();
		notifyObservers();
	}
}
