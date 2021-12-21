package nl.imm.adventofcode.year2021.day5.part2;

import java.util.Objects;

/**
 * @author Ivo van der Meer
 */
public class Coordinate {

	private final int x;

	private final int y;

	public Coordinate(String coordinateString) {
		String[] split = coordinateString.split(",");
		if (split.length != 2) {
			throw new IllegalArgumentException("Incorrect coordinateString received! Should be 'x,y'");
		}
		this.x = Integer.parseInt(split[0]);
		this.y = Integer.parseInt(split[1]);
	}

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Coordinate)) {
			return false;
		}
		return (x == ((Coordinate) o).x && y == ((Coordinate) o).y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}
}
