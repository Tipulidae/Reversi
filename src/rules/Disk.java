package rules;

import java.util.Observable;

public class Disk extends Observable {
	private Player color;

	public Disk() {
		color = Player.NONE;
	}

	public Disk(Player color) {
		this.color = color;
	}

	public void setColor(Player color) {
		this.color = color;
		refresh();
	}

	public void flip() {
		setColor(Player.opposite(color));
	}

	public void turnBlack() {
		setColor(Player.BLACK);
		refresh();
	}

	public void turnWhite() {
		setColor(Player.WHITE);
		refresh();
	}

	public void reset() {
		setColor(Player.NONE);
	}

	public Player getColor() {
		return color;
	}

	public boolean isBlack() {
		return color == Player.BLACK;
	}

	public boolean isWhite() {
		return color == Player.WHITE;
	}

	public boolean isFree() {
		return color == Player.NONE;
	}

	private void refresh() {
		setChanged();
		notifyObservers();
	}
}
