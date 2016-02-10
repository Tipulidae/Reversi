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
			black = new ImageIcon(ImageIO.read(new File("images/black.png")));
			white = new ImageIcon(ImageIO.read(new File("images/white.png")));
			none = new ImageIcon(ImageIO.read(new File("images/empty.png")));
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
