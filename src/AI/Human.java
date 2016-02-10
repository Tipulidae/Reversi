package AI;

import rules.Position;

public class Human extends Synth {
	
	private String name = "Human";
	@Override
	public Position makeMove(long stopTime) {
		return null;
	}
	
	public String toString() {
		return name+" "+super.toString();
	}
}
