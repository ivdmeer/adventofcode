package nl.imm.adventofcode.year2021.day5.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
public class VentLine {

	private final List<Coordinate> coordinates = new ArrayList<>();

	public VentLine(String coordinatesLines) {
		String[] split = coordinatesLines.split(" -> ");
		if (split.length != 2) {
			throw new IllegalArgumentException("Incorrect coordinatesLines received! Should be 'x1,y1 -> x2,y2'");
		}
		Coordinate coordinate1 = new Coordinate(split[0]);
		Coordinate coordinate2 = new Coordinate(split[1]);
		int startX = coordinate1.getX();
		int endX = coordinate2.getX();
		int startY = coordinate1.getY();
		int endY = coordinate2.getY();

		if (coordinate1.getX() > coordinate2.getX()) {
			startX = coordinate2.getX();
			endX = coordinate1.getX();
		}
		if (coordinate1.getY() > coordinate2.getY()) {
			startY = coordinate2.getY();
			endY = coordinate1.getY();
		}

		if (startX == endX || startY == endY) {
			for (int y = startY; y <= endY; y++) {
				for (int x = startX; x <= endX; x++) {
					coordinates.add(new Coordinate(x, y));
				}
			}
		}
	}
	public List<Coordinate> getCoordinates() {
		return new ArrayList<>(coordinates);
	}

	public boolean hasCoordinates() {
		return !coordinates.isEmpty();
	}
}
