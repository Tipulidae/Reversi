package rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
	public final int x;
	public final int y;
	public static final String label = "abcdefgh";
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(String str) {
		Pattern p = Pattern.compile("([a-hA-H])([1-8])");
		Matcher m = p.matcher(str);
		if (m.matches()) {
			x = label.indexOf(m.group(1).toLowerCase());
			y = Integer.valueOf(m.group(2))-1;
		} else {
			x = 0;
			y = 0;
		}
	}
	
	public static Position add(Position a, Position b) {
		return new Position(a.x+b.x, a.y+b.y);
	}
	
	public static Position sub(Position a, Position b) {
		return new Position(a.x-b.x, a.y-b.y);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Position) {
			Position other = (Position)o;
			return other.x == x && other.y == y;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
	public String toString() {
		return label.charAt(x)+""+(y+1);
	}
}
