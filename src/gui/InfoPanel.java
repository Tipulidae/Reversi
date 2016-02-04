package gui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rules.Status;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements Observer {
	private JLabel scoreLabel;
	private JLabel turnLabel;
	
	public InfoPanel() {
		super(new GridLayout(1, 2, 1, 1));
		
		scoreLabel = new JLabel("SCORE");
		turnLabel = new JLabel("TURN");
		add(turnLabel);
		add(scoreLabel);
	}

	@Override
	public void update(Observable o, Object arg) {
		Status status = (Status)o;
		if (status.gameOver()) {
			turnLabel.setText("Game Over!");
		} else {
			turnLabel.setText(status.currentPlayer()+" to move.");
		}
		scoreLabel.setText("SCORE: "+status.blackScore()+"-"+status.whiteScore());
		
	}
}
