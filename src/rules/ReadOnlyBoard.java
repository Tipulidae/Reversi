package rules;

import java.util.Observer;

// The purpose of this wrapper class is that you won't 
// be able to cast it to a MutableBoard to illegally access
// methods that would allow you to break the rules. 
// Only the Referee may alter the Board (because he 
// follows the rules!)

public class ReadOnlyBoard implements Board {
	Board delegate;
	public ReadOnlyBoard(Board mutableBoard) {
		delegate = mutableBoard;
	}
	
	public Player colorAt(Position m) {
		return delegate.colorAt(m);
	}

	@Override
	public boolean isFree(Position p) {
		return delegate.isFree(p);
	}

	@Override
	public void addDiskObserver(Observer o, Position pos) {
		delegate.addDiskObserver(o,pos);
	}

	@Override
	public Score currentScore() {
		return delegate.currentScore();
	}
	
	@Override
	public boolean equals(Object o) {
		return delegate.equals(o);
	}
}
