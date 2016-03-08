package rules;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void properToString() {
		Position a1 = new Position(0, 0);
		Position a2 = new Position(0, 1);
		Position b1 = new Position(1, 0);
		Position b2 = new Position(1, 1);
		Position h1 = new Position(7, 0);
		Position h8 = new Position(7, 7);

		assertEquals("a1", a1.toString());
		assertEquals("a2", a2.toString());
		assertEquals("b1", b1.toString());
		assertEquals("b2", b2.toString());
		assertEquals("h1", h1.toString());
		assertEquals("h8", h8.toString());
	}

	@Test
	public void canMakePositionFromString() {
		Position a1 = new Position(0, 0);
		Position a2 = new Position(0, 1);
		Position b1 = new Position(1, 0);
		Position b2 = new Position(1, 1);
		Position h1 = new Position(7, 0);
		Position h8 = new Position(7, 7);

		Position str_a1 = new Position("a1");
		Position str_a2 = new Position("a2");
		Position str_b1 = new Position("b1");
		Position str_b2 = new Position("b2");
		Position str_h1 = new Position("h1");
		Position str_h8 = new Position("h8");

		Position str_A1 = new Position("A1");
		Position str_A2 = new Position("A2");
		Position str_B1 = new Position("B1");
		Position str_B2 = new Position("B2");
		Position str_H1 = new Position("H1");
		Position str_H8 = new Position("H8");

		assertEquals(a1, str_a1);
		assertEquals(a2, str_a2);
		assertEquals(b1, str_b1);
		assertEquals(b2, str_b2);
		assertEquals(h1, str_h1);
		assertEquals(h8, str_h8);

		assertEquals(a1, str_A1);
		assertEquals(a2, str_A2);
		assertEquals(b1, str_B1);
		assertEquals(b2, str_B2);
		assertEquals(h1, str_H1);
		assertEquals(h8, str_H8);
	}
}
