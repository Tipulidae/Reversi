package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import rules.Board;
import rules.Player;
import rules.Position;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private ClickHandler clickHandler;
	public BoardPanel() {
		super(new GridLayout(8, 8, 1, 1));
		setBackground(Color.RED);
		setSize(500, 500);

		MouseAdapter ma = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				SquareLabel sl = (SquareLabel) me.getSource();
				squareClicked(sl);
			}
		};

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Position pos = new Position(x, y);
				SquareLabel sl = new SquareLabel(pos, Player.NONE);
				sl.addMouseListener(ma);
				add(sl);
			}
		}
	}

	public void connect(Board board, ClickHandler ch) {
		this.clickHandler = ch;
		for (int y=0; y<8; y++) {
			for (int x=0; x<8; x++) {
				SquareLabel sl = (SquareLabel)getComponent(x+8*y);
				board.addDiskObserver(sl, new Position(x,y));
			}
		}
	}
	
	private void squareClicked(SquareLabel sl) {
		clickHandler.handleHumanClick(sl.getPos());
	}
}
