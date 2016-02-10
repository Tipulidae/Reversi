package AI;

import java.util.ArrayList;

import gui.Reversi;
import rules.*;

public class Main {
	public static void main(String[] args) {
		Reversi reversi = new Reversi();
		Referee ref = reversi.getReferee();
		Board board = ref.currentBoardState();
		RuleBook rules = ref.getRules();
		
		Synth blackSynth = new AlphaBetaAI(Player.BLACK, board, rules);
		Synth whiteSynth = new RandomAI(Player.WHITE, board, rules);
		
		ArrayList<Position> history = new ArrayList<Position>();
		
		final long timeLimit = 2000;
		long stopTime;
		
		while(!ref.gameOver()) {
			/*
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}*/
			
			stopTime = System.currentTimeMillis() + timeLimit;
			
			if (ref.currentPlayer() == Player.BLACK) {
				Position move = blackSynth.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(blackSynth+": "+move);
					history.add(move);
				}
			} else {
				Position move = whiteSynth.makeMove(stopTime);
				if (ref.makeMove(move)) {
					System.out.println(whiteSynth+": "+move);
					history.add(move);
				}
			}
		}
		
		System.out.println(history);
	}
}
