package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import rules.Disk;
import rules.Position;
import rules.Player;

@SuppressWarnings("serial")
public class SquareLabel extends JLabel implements Observer {
	private Player color;
	private Position pos;

	public SquareLabel(Position pos, Player color) {
		setBackground(Color.GRAY);
		setOpaque(true);

		this.pos = pos;
		this.color = color;
		setIcon(DiskIcon.color().getNullIcon());
		refresh();
	}

	public Position getPos() {
		return pos;
	}

	public void resetDisk() {
		color = Player.NONE;
		refresh();
	}

	public void turnBlack() {
		color = Player.BLACK;
		refresh();
	}

	public void turnWhite() {
		color = Player.WHITE;
		refresh();
	}

	public void flip() {
		color = Player.opposite(color);
		refresh();
	}

	public void refresh() {
		setIcon(DiskIcon.color().getIcon(color));
	}

	@Override
	public void update(Observable o, Object arg1) {
		Disk d = (Disk) o;
		color = d.getColor();
		refresh();
	}

}
