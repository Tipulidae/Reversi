package rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RefereeTest {
	private Referee ref;
	private Board board;

	@Before
	public void setup() {
		ref = new Referee();
		ref.initializeGame();
		board = ref.currentBoardState();
	}

	@Test
	public void blackIsFirstToMove() {
		assertEquals(Player.BLACK, ref.currentPlayer());
	}

	@Test
	public void canAskRefereeToMakeMove() {
		Position move = new Position("d3");
		assertTrue(board.isFree(move));

		assertTrue(ref.makeMove(move));
		assertFalse(board.isFree(move));
		assertBlack(move);
	}

	@Test
	public void makingMoveFlipsCorrectDisks() {
		Position d3 = new Position("d3");
		Position d4 = new Position("d4");

		assertWhite(d4);
		assertTrue(ref.makeMove(d3));
		assertBlack(d3);
		assertBlack(d4);
	}

	@Test
	public void playerTurnsAlternate() {
		assertEquals(Player.BLACK, ref.currentPlayer());

		Position move1 = new Position("d3");
		Position move2 = new Position("c5");

		assertTrue(ref.makeMove(move1));
		assertEquals(Player.WHITE, ref.currentPlayer());
		assertEquals(Player.BLACK, board.colorAt(move1));

		assertTrue(ref.makeMove(new Position("c5")));
		assertEquals(Player.BLACK, ref.currentPlayer());
		assertEquals(Player.WHITE, board.colorAt(move2));
	}

	@Test
	public void cantMakeInvalidMoves() {
		Position invalidMove1 = new Position("e4");
		Position invalidMove2 = new Position("e5");
		Position invalidMove3 = new Position("c3");
		Position invalidMove4 = new Position("d6");
		Position invalidMove5 = new Position("a1");

		assertFalse(ref.makeMove(invalidMove1));
		assertFalse(ref.makeMove(invalidMove2));
		assertFalse(ref.makeMove(invalidMove3));
		assertFalse(ref.makeMove(invalidMove4));
		assertFalse(ref.makeMove(invalidMove5));
	}

	// Sequence of moves where the entire board ends up being black:
	// [c4, c3, c2, b2, e6, c1, a1, a3, c5, b3, e3, b1, a4, a2, d6, a5, e7, d1,
	// b4, f7, c6, d3, g8, b5, d2, d7, b6, c7, b7, c8, a7, d8, b8, a8, a6, f5,
	// f4, g3, g5, e8, h2, h5, f6, f3, f2, g1, h6, g6, f1, e1, f8, h4, h3, h8,
	// h7, g7, h1, g4, e2, g2]

	@Test
	public void newGameIsNotOver() {
		assertFalse(ref.gameOver());
	}

	@Test
	public void gameEndsWhenNoValidMovesLeft() {
		// Sequence of moves resulting in premature black win
		String[] moves = { "c4", "c5", "c6", "b5", "e6", "c3", "c2", "d3", "a4", "c7", "c8", "f5", "g6" };

		for (String str : moves) {
			assertTrue(ref.makeMove(new Position(str)));
		}
		assertTrue(ref.gameOver());
	}

	@Test
	public void gameEndsWhenNoValidMovesLeft2() {
		// Sequence of moves resulting in premature white win
		String[] moves = { "c4", "c3", "d3", "c5", "b2", "a1", "b4", "e3", "c2", "c1", "d2", "a3", "c6", "c7", "f4",
				"g5" };
		for (String str : moves) {
			assertTrue(ref.makeMove(new Position(str)));
		}
		assertTrue(ref.gameOver());
	}

	private void assertBlack(Position pos) {
		assertEquals(Player.BLACK, board.colorAt(pos));
	}

	private void assertWhite(Position pos) {
		assertEquals(Player.WHITE, board.colorAt(pos));
	}

}
