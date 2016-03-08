package factory;

import ai.AlphaBetaAI;
import ai.AlphaBetaWeightedAI;
import ai.GreedyAI;
import ai.Human;
import ai.MinimaxAI;
import ai.RandomAI;
import ai.Synth;

public class SynthFactory {

	public Synth makeSynth(String str) {
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
}
