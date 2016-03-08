package gui;

import java.util.Observer;

import javax.swing.*;

import rules.Referee;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

@SuppressWarnings("serial")
public class ReversiGUI extends JFrame {
	//private Referee ref;
	private InfoPanel ip;
	private BoardPanel panel;
	
	public ReversiGUI() {
		super("Reversi");

		ip = new InfoPanel();

		//ref = new Referee();
		//ref.initializeGame();
		//ref.addStatusObserver((Observer) ip);

		panel = new BoardPanel();

		add(NORTH, ip);
		add(SOUTH, panel);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);

		pack();
		setLocation(500, 300);
	}
	
	public void connect(Referee ref, ClickHandler ch) {
		ref.addStatusObserver((Observer)ip);
		panel.connect(ref.currentBoardState(), ch);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ReversiGUI();
			}
		});
	}
}
