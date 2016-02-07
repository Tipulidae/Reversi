package rules;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void newBoardHasStandardSetup() {
		MutableBoard b = new MutableBoard();
		b.setStartPositions();
		
		for (int x=0; x<8; x++) {
			for (int y=0; y<8; y++) {
				if ((x == 3 || x == 4) && (y == 3 || y == 4)) continue;
				assertTrue(b.diskAt(new Position(x,y)).isFree());
			}
		}
		
		assertTrue(b.diskAt(new Position(3,3)).isWhite());
		assertTrue(b.diskAt(new Position(4,4)).isWhite());
		assertTrue(b.diskAt(new Position(3,4)).isBlack());
		assertTrue(b.diskAt(new Position(4,3)).isBlack());
	}
	
	@Test
	public void canPlaceOneBlackDisk() {
		MutableBoard b = new MutableBoard();
		b.placeBlack(new Position(0,0));
		assertTrue(b.diskAt(new Position(0,0)).isBlack());
	}
	
	@Test
	public void boardsWithSameSetupAreEqual() {
		MutableBoard boardA = new MutableBoard();
		MutableBoard boardB = new MutableBoard();
		assertEquals(boardA, boardB);
		
		boardA.setStartPositions();
		boardB.setStartPositions();
		assertEquals(boardA,boardB);
		
		boardA.placeBlack(new Position("a1"));
		assertNotEquals(boardA,boardB);
		
		boardB.placeBlack(new Position("a1"));
		assertEquals(boardA,boardB);
	}
	
	
	@Test
	public void mutableBoardConstructorCopiesInputBoard() {
		MutableBoard boardA = new MutableBoard();
		boardA.setStartPositions();
		
		MutableBoard boardB = new MutableBoard(boardA);
		
		assertEquals(boardA, boardB);
		
		boardA.placeBlack(new Position("c3"));
		assertNotEquals(boardA, boardB);
		
		boardB.placeBlack(new Position("c3"));
		boardB.placeWhite(new Position("d7"));
		assertNotEquals(boardA, boardB);
	}
	/*
	private Board result(Board state, Position pos, Player color) {
		MutableBoard nextState = new MutableBoard(state);
		nextState.placeDisk(pos, color);
		
		for (Position capture : rules.captures(nextState, pos, color)) {
			nextState.flip(capture);
		}
		return nextState;
	}*/
}
