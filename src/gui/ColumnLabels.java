package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColumnLabels extends JPanel {
	public ColumnLabels() {
		super(new GridLayout(1, 8, 1, 1));
		for (int i = 0; i < 8; i++) {
			JLabel label = new JLabel("" + (i + 1));
			add(label);
		}
	}
}
