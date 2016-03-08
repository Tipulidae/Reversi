package game;

import java.util.ArrayList;
import java.util.List;

import ai.Human;
import ai.Synth;
import gui.ClickHandler;
import rules.Player;
import rules.Position;

public class HumanInputMediator implements ClickHandler {
	private List<Human> humans;
	private Player current;
	public HumanInputMediator(List<Synth> players) {
		humans = new ArrayList<Human>();
		for (Synth s : players) {
			if (s instanceof Human) humans.add((Human)s);
		}
	}
	
	public void setCurrentPlayer(Player color) {
		current = color;
	}
	
	public void handleHumanClick(Position pos) {
		for (Human h : humans) {
			if (h.getColor().equals(current)) {
				h.receiveMove(pos);
			}
		}
	}
}
