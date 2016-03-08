package game;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ai.Synth;
import gui.ClickHandler;
import gui.ReversiGUI;
import rules.Board;
import rules.Player;
import rules.Position;
import rules.Referee;
import rules.RuleBook;
import rules.Score;

public class Game {
	private Referee ref;
	private Synth black;
	private Synth white;
	private long timeLimit;
	private HumanInputMediator him;
	
	public void playWithGUI(Synth black, Synth white, long timeLimit) {
		setup(black, white, timeLimit);
		connectGUI();
		ref.initializeGame();
		runGameLoop();
	}
	
	private void connectGUI() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					ReversiGUI reversi = new ReversiGUI();
					reversi.connect(ref, (ClickHandler)him);
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void setup(Synth black, Synth white, long timeLimit) {
		this.black = black;
		this.white = white;
		this.timeLimit = timeLimit;
		
		ref = new Referee();
		Board board = ref.currentBoardState();
		RuleBook rules = ref.getRules();

		black.giveColor(Player.BLACK);
		black.giveBoard(board);
		black.giveRules(rules);

		white.giveColor(Player.WHITE);
		white.giveBoard(board);
		white.giveRules(rules);
		
		List<Synth> players = new ArrayList<Synth>();
		players.add(black);
		players.add(white);
		him = new HumanInputMediator(players);
	}
	
	private void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void runGameLoop() {
		System.out.println("Starting game loop with "+black+" and "+white);
		ArrayList<Position> history = new ArrayList<Position>();
		long stopTime;
		while (!ref.gameOver()) {
			him.setCurrentPlayer(ref.currentPlayer());
			wait(100);
			stopTime = System.currentTimeMillis() + timeLimit;
			
			if (ref.currentPlayer() == Player.BLACK) {
				Position move = black.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(black + ": " + move);
					history.add(move);
				}
			} else {
				Position move = white.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(white + ": " + move);
					history.add(move);
				}
			}
		}
		presentResults(history);
	}
	
	private void presentResults(ArrayList<Position> history) {
		Score s = ref.currentScore();
		Synth winner = null;
		if (s.black > s.white)
			winner = black;
		else if (s.black < s.white)
			winner = white;

		String result = "Game Over! ";
		if (winner == null)
			result += "It's a draw!";
		else
			result += winner.toString() + " wins!";

		System.out.println(result);
		System.out.println("Move history:\n" + history);
	}
}
