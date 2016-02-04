package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import rules.Board;
import rules.Position;
import rules.Referee;


@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Board board;
	private Referee ref;
	public BoardPanel(Referee ref) {
		super(new GridLayout(8, 8, 1, 1));
		
		this.ref = ref;
		board = ref.currentBoardState();
		
		
		setBackground(Color.RED);
		setSize(500,500);
		
		MouseAdapter ma = new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				SquareLabel sl = (SquareLabel)me.getSource();
				squareClicked(sl);
			}
		};
		
		for (int y=0; y<8; y++) {
			for (int x=0; x<8; x++) {
				Position pos = new Position(x,y);
				SquareLabel sl = new SquareLabel(pos, board.colorAt(pos));
				sl.addMouseListener(ma);
				add(sl);
				board.addDiskObserver(sl,pos);
			}
		}
	}
	
	private void squareClicked(SquareLabel sl) {
		ref.makeMove(sl.getPos());
	}
}
