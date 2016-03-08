package rules;

public class Score {
	public final int black;
	public final int white;

	public Score(int black, int white) {
		this.black = black;
		this.white = white;
	}

	public int score(Player color) {
		switch (color) {
		case BLACK:
			return black;
		case WHITE:
			return white;
		default:
			return 0;
		}
	}
}
