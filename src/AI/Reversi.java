package AI;

import java.util.ArrayList;

import gui.ReversiGUI;
import rules.*;

public class Reversi {
	private Board board;
	private RuleBook rules;
	private Synth blackSynth;
	private Synth whiteSynth;
	private long timeLimit;

	public void run(String[] args) {
		if (args.length < 3 || args.length > 4) {
			System.err.println("Syntax: java -jar reversi.jar timeLimit playerBlack playerWhite (rounds).");
			System.err.println("");
			System.err.println("timeLimit   is specified in milliseconds.");
			System.err.println("");
			System.err.println("playerBlack and playerWhite   respectively must be one of");
			System.err.println("\"random\", \"greedy\", \"minimax\", \"alphabeta\", \"alphabetaweighted\" or \"human\".");
			System.err.println("");
			System.err.println("rounds   is optional and if specified makes the program run an evaluation");
			System.err.println("between the types of playerBlack and playerWhite for the given number of rounds.");
			System.exit(1);
		}

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
			blackSynth.giveRules(rules);
			blackSynth.giveColor(Player.BLACK);
			whiteSynth.giveBoard(board);
			whiteSynth.giveRules(rules);
			whiteSynth.giveColor(Player.WHITE);

			runGameLoop(ref);
		}
	}

	private Synth parseSynthString(String str) {
		Synth s = new Human();
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
		case "alphabetaweighted":
			s = new AlphaBetaWeightedAI();
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
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			stopTime = System.currentTimeMillis() + timeLimit;

			if (ref.currentPlayer() == Player.BLACK) {
				Position move = blackSynth.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(blackSynth + ": " + move);
					history.add(move);
				}
			} else {
				Position move = whiteSynth.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(whiteSynth + ": " + move);
					history.add(move);
				}
			}
		}
		Score s = ref.currentScore();
		Synth winner = null;
		if (s.black > s.white) winner = blackSynth;
		else if (s.black < s.white) winner = whiteSynth;
		
		String result = "Game Over! ";
		if (winner == null) result += "It's a draw!";
		else result += winner.toString()+" wins!";
		
		System.out.println(result);
		System.out.println("Move history:\n"+history);
	}

	public static void main(String[] args) {
		new Reversi().run(args);
	}
}