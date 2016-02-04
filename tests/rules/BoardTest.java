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

}
