package rules;

import java.util.ArrayList;
import java.util.Collection;

public class RuleBook {
	private Player activePlayer;
	private Board board;
	
	public Iterable<Position> captures(Board board, Position move, Player activePlayer) {
		this.board = board;
		this.activePlayer = activePlayer;
		Player otherPlayer = Player.opposite(activePlayer);
		ArrayList<Position> disks = new ArrayList<Position>();
		
		
		for (Position n : allNeighboursOf(move)) {
			if (board.colorAt(n) == otherPlayer) {
				ArrayList<Position> potential = new ArrayList<Position>();
				Position direction = Position.sub(n, move);
				
				boolean anchorIsSameColor = false;
				while (true) {
					potential.add(n);
					n = Position.add(n,direction);
					if (!withinBounds(n) || board.isFree(n)) {
						break;
					} else if (board.colorAt(n) == activePlayer) {
						anchorIsSameColor = true;
						break;
					}
				}
				
				if (anchorIsSameColor) {
					disks.addAll(potential);
				}
				
			}
		}
		
		return disks;
	}
	
	public Collection<Position> allValidMoves(Board b, Player activePlayer) {
		this.board = b;
		this.activePlayer = activePlayer;
		Player otherPlayer = Player.opposite(activePlayer);
		ArrayList<Position> validMoves = new ArrayList<Position>();
		
		for (Position p : allFreeMoves()) {
			for (Position n : allNeighboursOf(p)) {
				if (b.colorAt(n) == otherPlayer) {
					Position direction = Position.sub(n,p);
					if (lineEndsWithActiveColor(n, direction)) {
						validMoves.add(p);
					}
				}
			}
		}
		
		return validMoves;
	}
	
	private Iterable<Position> allFreeMoves() {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int x=0; x<8; x++) {
			for (int y=0; y<8; y++) {
				Position pos = new Position(x,y);
				if (board.isFree(pos))
					positions.add(pos);
			}
		}
		return positions;
	}
	
	private Iterable<Position> allNeighboursOf(Position pos) {
		ArrayList<Position> neighbours = new ArrayList<Position>();
		for (int x=pos.x-1; x<=pos.x+1; x++) {
			for (int y=pos.y-1; y<=pos.y+1; y++) {
				Position p = new Position(x,y);
				if (withinBounds(p) && !p.equals(pos))
					neighbours.add(p);
			}
		}
		return neighbours;
	}
	
	private boolean lineEndsWithActiveColor(Position pos, Position direction) {
		while (true) {
			pos = Position.add(pos,direction);
			if (!withinBounds(pos)) return false;
			if (board.isFree(pos)) {
				return false;
			} else if (board.colorAt(pos) == activePlayer) {
				return true;
			}
		}
	}
	
	private boolean withinBounds(Position m) {
		return withinBounds(m.x, m.y);
	}
	
	private boolean withinBounds(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}
}
