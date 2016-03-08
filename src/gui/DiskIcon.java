package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import rules.Player;

public class DiskIcon {
	private Icon black;
	private Icon white;
	private Icon none;

	private static DiskIcon instance;

	private DiskIcon() {
		try {
			black = new ImageIcon((this.getClass().getResource("/images/black.png")));
			white = new ImageIcon((this.getClass().getResource("/images/white.png")));
			none = new ImageIcon((this.getClass().getResource("/images/empty.png")));
		} catch (Exception e) {
			System.err.println("unable to load images.");
			e.printStackTrace();
			System.exit(1);
		}
		instance = this;
	}

	public static DiskIcon color() {
		if (instance == null)
			return new DiskIcon();
		else
			return instance;
	}

	public Icon getIcon(Player color) {
		switch (color) {
		case BLACK:
			return black;
		case WHITE:
			return white;
		default:
			return none;
		}
	}

	public Icon getNullIcon() {
		return none;
	}

}
