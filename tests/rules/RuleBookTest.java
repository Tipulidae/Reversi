package rules;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RuleBookTest {
	
	private MutableBoard b;
	private RuleBook rules;
	
	@Before
	public void setup() {
		b = new MutableBoard();
		b.setStartPositions();
		rules = new RuleBook();
	}
	
	@Test
	public void verifyAllInitialValidMoves() {
		Set<Position> expected = new HashSet<Position>();
		expected.add(new Position(3,2));
		expected.add(new Position(2,3));
		expected.add(new Position(4,5));
		expected.add(new Position(5,4));
		
		Set<Position> actual = new HashSet<Position>();
		for (Position p : rules.allValidMoves(b, Player.BLACK)) {
			actual.add(p);
		}
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void verifyAllValidMoves2() {
		b.diskAt(new Position(3,3)).turnBlack();
		b.diskAt(new Position(3,2)).turnBlack();
		b.diskAt(new Position(2,4)).turnWhite();
		b.diskAt(new Position(3,4)).turnWhite();
		
		Set<Position> expected = new HashSet<Position>();
		expected.add(new Position(1,5));
		expected.add(new Position(2,5));
		expected.add(new Position(3,5));
		expected.add(new Position(4,5));
		expected.add(new Position(5,5));
		
		Set<Position> actual = new HashSet<Position>();
		for (Position m : rules.allValidMoves(b, Player.BLACK)) {
			actual.add(m);
		}
		
		assertEquals(expected,actual);
	}
	
}
