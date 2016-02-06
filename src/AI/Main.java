package AI;

import gui.Reversi;
import rules.*;

public class Main {
	public static void main(String[] args) {
		Reversi reversi = new Reversi();
		Referee ref = reversi.getReferee();
		Board board = ref.currentBoardState();
		RuleBook rules = ref.getRules();
		
		Synth blackSynth = new Synth(board, rules);
		Synth whiteSynth = new Synth(board, rules);
		
		while(!ref.gameOver()) {
			if (ref.currentPlayer() == Player.BLACK) {
				System.out.println("black");
				Position move = blackSynth.makeMove();
				ref.makeMove(move);
			} else {
				System.out.println("white");
				Position move = whiteSynth.makeMove();
				ref.makeMove(move);
			}
		}
	}
}
