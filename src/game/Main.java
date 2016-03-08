package game;


import ai.Synth;
import ai.SynthEvaluator;
import factory.SynthFactory;

public class Main {
	public static void main(String[] args) {
		Synth black;
		Synth white;
		long timeLimit;
		SynthFactory sf = new SynthFactory();
		if (args.length < 3 || args.length > 4) {
			errorMessage();
			black = sf.makeSynth("random");
			white = sf.makeSynth("human");
			timeLimit = 2000;
		} else {
		
			
			timeLimit = Integer.valueOf(args[0]);
			black = sf.makeSynth(args[1]);
			white = sf.makeSynth(args[2]);
	
			if (args.length > 3) {
				if (black != null && white != null) {
					new SynthEvaluator(black, white, timeLimit).runEvaluation(Integer.valueOf(args[3]));
				} else {
					System.err.println("Synth evaluation requires both players to be non-human!");
					System.exit(1);
				}
			}
		}
		
		Game reversi = new Game();
		reversi.playWithGUI(black, white, timeLimit);
	}
	
	private static void errorMessage() {
		System.err.println("Syntax: java -jar reversi.jar timeLimit playerBlack playerWhite (rounds).");
		System.err.println("");
		System.err.println("timeLimit   is specified in milliseconds.");
		System.err.println("");
		System.err.println("playerBlack and playerWhite   respectively must be one of");
		System.err
				.println("\"random\", \"greedy\", \"minimax\", \"alphabeta\", \"alphabetaweighted\" or \"human\".");
		System.err.println("");
		System.err.println("rounds   is optional and if specified makes the program run an evaluation");
		System.err.println("between the types of playerBlack and playerWhite for the given number of rounds.");
		//System.exit(1);
	}
}
