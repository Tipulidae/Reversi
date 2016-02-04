package rules;

public enum Player {
	NONE, BLACK, WHITE;
	
	public static Player opposite(Player c) {
		switch (c) {
		case BLACK:
			return WHITE;
		case WHITE:
			return BLACK;
		default:
			return NONE;
		}
	}
	
	public static String name(Player c) {
		switch (c) {
		case BLACK:
			return "black";
		case WHITE:
			return "white";
		default:
			return "none";
		}
	}
}
