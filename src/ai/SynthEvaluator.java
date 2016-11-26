package ai;

import java.util.HashSet;
import java.util.Set;

import rules.Board;
import rules.Player;
import rules.Position;
import rules.Referee;
import rules.RuleBook;
import rules.Score;

public class SynthEvaluator {
	private Synth black;
	private Synth white;
	private Referee ref;

	private int rounds;
	private int blackWins, whiteWins;
	private int ties;
	private long timeLimit;
	private Set<String> history;

	public SynthEvaluator(Synth black, Synth white, long timeLimit) {
		this.black = black;
		this.white = white;
		this.timeLimit = timeLimit;
		history = new HashSet<String>();
	}

	public void runEvaluation(int rounds) {
		this.rounds = rounds;
		setup();
		simulateGames();
		presentResults();
	}

	private void setup() {
		ref = new Referee();
		Board board = ref.currentBoardState();
		RuleBook rules = ref.getRules();

		black.giveColor(Player.BLACK);
		black.giveBoard(board);
		black.giveRules(rules);

		white.giveColor(Player.WHITE);
		white.giveBoard(board);
		white.giveRules(rules);

		blackWins = 0;
		whiteWins = 0;
		ties = 0;
	}

	private void simulateGames() {
		for (int i = 0; i < rounds; i++) {
			// if (i%10 == 0) System.out.print("*");
			simulateOneGame();
			countScore();
		}
	}

	private void simulateOneGame() {
		ref.initializeGame();
		StringBuilder sb = new StringBuilder();
		while (!ref.gameOver()) {
			if (ref.currentPlayer() == Player.BLACK) {
				Position move = black.makeMove(System.currentTimeMillis() + timeLimit);
				if (!ref.makeMove(move))
					System.out.println("No valid moves.");
				else
					sb.append(move.toString());
			} else {
				Position move = white.makeMove(System.currentTimeMillis() + timeLimit);
				if (!ref.makeMove(move))
					System.out.println("No valid moves.");
				else
					sb.append(move.toString());
			}
		}
		history.add(sb.toString());
	}

	private void countScore() {
		Score score = ref.currentScore();
		if (score.black > score.white) {
			blackWins++;
		} else if (score.black < score.white) {
			whiteWins++;
		} else {
			ties++;
		}
	}

	private void presentResults() {
		double blackPercentage = (double) blackWins * 100 / rounds;
		double whitePercentage = (double) whiteWins * 100 / rounds;
		double tiesPercentage = (double) ties * 100 / rounds;

		System.out.println();
		System.out.println(black + " won " + blackWins + "/" + rounds + " = " + blackPercentage + "% of the games.");
		System.out.println(white + " won " + whiteWins + "/" + rounds + " = " + whitePercentage + "% of the games.");
		System.out.println("There were " + ties + "/" + rounds + " = " + tiesPercentage + "% ties.");
		System.out.println(history.size() + "/" + rounds + " different games played.");
	}

	public static void main(String[] args) {
		Synth black = new AlphaBetaAI();
		Synth white = new MinimaxAI();
		SynthEvaluator se = new SynthEvaluator(black, white, 20);
		se.runEvaluation(10);
		
	}
}
