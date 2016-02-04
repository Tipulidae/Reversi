package rules;

import java.util.Observer;

public interface Board {
	public Player colorAt(Position p);
	public boolean isFree(Position p);
	public void addDiskObserver(Observer o, Position pos);
}