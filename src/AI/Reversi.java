package AI;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import gui.ReversiGUI;
import rules.*;

public class Reversi {
	private Board board;
	private RuleBook rules;
	private Synth blackSynth;
	private Synth whiteSynth;
	private long timeLimit;
	public void run(String[] args) {
		// Time limit, blackAI, whiteAI
		// java -jar reversiAI.jar 2000 human alphabeta
		
		timeLimit = Integer.valueOf(args[0]);
		blackSynth = parseSynthString(args[1]);
		whiteSynth = parseSynthString(args[2]);
		
		if (args.length > 3) {
			if (blackSynth != null && whiteSynth != null) {
				new SynthEvaluator(blackSynth, whiteSynth, timeLimit).runEvaluation(Integer.valueOf(args[3]));
			} else {
				System.err.println("Synth evaluation requires both players to be non-human!");
				System.exit(1);
			}
		} else {

					ReversiGUI reversi = new ReversiGUI();
					Referee ref = reversi.getReferee();
					board = ref.currentBoardState();
					rules = ref.getRules();

					
					blackSynth.giveBoard(board);
					whiteSynth.giveBoard(board);
					blackSynth.giveRules(rules);
					whiteSynth.giveRules(rules);
					blackSynth.giveColor(Player.BLACK);
					whiteSynth.giveColor(Player.WHITE);
					runGameLoop(ref);
				
			
		}
	}
	
	private Synth parseSynthString(String str) {
		Synth s = null;
		switch (str.toLowerCase()) {
		case "random":
			s = new RandomAI();
			break;
		case "greedy":
			s = new GreedyAI();
			break;
		case "minimax":
			s = new MinimaxAI();
			break;
		case "alphabeta":
			s = new AlphaBetaAI();
			break;
		case "human":
			break;
		default:
			System.err.println("Invalid input: " + str + " not recognized!");
			System.exit(1);
		}
		return s;
	}

	private void runGameLoop(Referee ref) {
		ArrayList<Position> history = new ArrayList<Position>();
		long stopTime;
		while (!ref.gameOver()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			stopTime = System.currentTimeMillis() + timeLimit;

			if (ref.currentPlayer() == Player.BLACK) {
				if (blackSynth != null) {
					Position move = blackSynth.makeMove(stopTime);
					if (ref.makeMove(move)) {
						System.out.println(blackSynth + ": " + move);
						history.add(move);
					}
				}
			} else {
				if (whiteSynth != null) {
					Position move = whiteSynth.makeMove(stopTime);
					if (ref.makeMove(move)) {
						System.out.println(whiteSynth + ": " + move);
						history.add(move);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Reversi().run(args);
			}
		});
		
	}
}
