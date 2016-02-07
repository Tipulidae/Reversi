package rules;

import java.util.ArrayList;
import java.util.Observer;

public class MutableBoard implements Board {
	
	private Disk[][] disks;
	public MutableBoard() {
		disks = new Disk[8][8];
		for (int x = 0; x<8; x++) {
			for (int y=0; y<8; y++) {
				disks[x][y] = new Disk();
			}
		}
	}
	
	public MutableBoard(Board b) {
		disks = new Disk[8][8];
		for (Position p : allDiskPositions()) {
			disks[p.x][p.y] = new Disk(b.colorAt(p));
		}
	}
	
	public void setStartPositions() {
		for (Position p : allDiskPositions()) {
			diskAt(p).reset();
		}
		disks[3][3].turnWhite();
		disks[4][4].turnWhite();
		disks[3][4].turnBlack();
		disks[4][3].turnBlack();
	}
	
	public Score currentScore() {
		int black = 0;
		int white = 0;
		
		for (Position p : allDiskPositions()) {
			if (colorAt(p) == Player.BLACK) black++;
			else if (colorAt(p) == Player.WHITE) white++;
		}
		
		return new Score(black,white);
	}
	
	public void addDiskObserver(Observer o, Position pos) {
		disks[pos.x][pos.y].addObserver(o);
	}
	
	
	public void placeDisk(Position pos, Player player) {
		disks[pos.x][pos.y].setColor(player);
	}
	
	public void placeBlack(Position pos) {
		disks[pos.x][pos.y].turnBlack();
	}
	
	public void placeWhite(Position pos) {
		disks[pos.x][pos.y].turnWhite();
	}
	
	public Disk diskAt(Position pos) {
		return disks[pos.x][pos.y];
	}
	
	public void flip(Position pos) {
		disks[pos.x][pos.y].flip();
	}
	
	@Override
	public Player colorAt(Position pos) {
		return disks[pos.x][pos.y].getColor();
	}

	@Override
	public boolean isFree(Position p) {
		return colorAt(p) == Player.NONE;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Board) {
			Board b = (Board)o;
			for (Position p : allDiskPositions()) {
				Player expected = colorAt(p);
				Player actual = b.colorAt(p);
				if (!expected.equals(actual)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private Iterable<Position> allDiskPositions() {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int x = 0; x<8; x++) {
			for (int y=0; y<8; y++) {
				positions.add(new Position(x,y));
			}
		}
		return positions;
	}
}
