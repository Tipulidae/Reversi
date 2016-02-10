package gui;

import java.util.Observer;

import javax.swing.*;

import rules.Referee;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

@SuppressWarnings("serial")
public class ReversiGUI extends JFrame {
	private Referee ref;
	public ReversiGUI() {
		super("Reversi");
		
		InfoPanel ip = new InfoPanel();
		
		ref = new Referee();
		ref.initializeGame();
		ref.addStatusObserver((Observer)ip);
		
		
		BoardPanel panel = new BoardPanel(ref);
		
		
		
		add(NORTH,ip);
		add(SOUTH,panel);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		
		pack();
		setLocation(500,300);
	}
	
	public Referee getReferee() {
		return ref;
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
