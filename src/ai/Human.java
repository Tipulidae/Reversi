package ai;

import rules.Position;

public class Human extends Synth {

	private String name = "Human";
	private Position lastReceivedMove;

	@Override
	public Position makeMove(long stopTime) {
		Position ans = lastReceivedMove;
		lastReceivedMove = null;
		return ans;
	}
	
	public String toString() {
		return name + " " + super.toString();
	}
	
	public void receiveMove(Position move) {
		lastReceivedMove = move;
	}
}
