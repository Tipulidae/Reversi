package gui;

import java.io.File;

import javax.imageio.ImageIO;
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
			none = new ImageIcon((this.getClass().getResource("/images/none.png")));
		}
		catch (Exception e) {
		}
		instance = this;
	}
	
	public static DiskIcon color() {
		if (instance == null) return new DiskIcon();
		else return instance;
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
