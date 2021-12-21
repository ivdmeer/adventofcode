package nl.imm.adventofcode.year2021.day5.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
public class VentLine {

	private final List<Coordinate> coordinates = new ArrayList<>();

	private final String entry;

	public VentLine(String coordinatesLines) {
		this.entry = coordinatesLines;
		String[] split = coordinatesLines.split(" -> ");
		if (split.length != 2) {
			throw new IllegalArgumentException("Incorrect coordinatesLines received! Should be 'x1,y1 -> x2,y2'");
		}
		Coordinate source = new Coordinate(split[0]);
		Coordinate destination = new Coordinate(split[1]);

		handleHorizontalAndVerticalLines(source, destination);
		handleDiagonalLines(source, destination);
	}

	public List<Coordinate> getCoordinates() {
		return new ArrayList<>(coordinates);
	}

	public boolean hasCoordinates() {
		return !coordinates.isEmpty();
	}

	private void handleDiagonalLines(Coordinate source, Coordinate destination) {
		int startX = source.getX();
		int endX = destination.getX();
		int startY = source.getY();
		int endY = destination.getY();
		int xDirection = 1;
		int yDirection = 1;
		if (source.getX() > destination.getX()) {
			xDirection = -1;
		}
		if (source.getY() > destination.getY()) {
			yDirection = -1;
		}

		if (startX != endX && startY != endY) {
			int currentX = startX;
			int currentY = startY;
			coordinates.add(new Coordinate(currentX, currentY));
			do {

				if (currentX != endX) {
					currentX += xDirection;
				}
				if (currentY != endY) {
					currentY += yDirection;
				}
				coordinates.add(new Coordinate(currentX, currentY));
			}
			while (endX != currentX && endY != currentY);
		}
	}

	private void handleHorizontalAndVerticalLines(Coordinate source, Coordinate destination) {
		int startX = source.getX();
		int endX = destination.getX();
		int startY = source.getY();
		int endY = destination.getY();

		if (source.getX() > destination.getX()) {
			startX = destination.getX();
			endX = source.getX();
		}
		if (source.getY() > destination.getY()) {
			startY = destination.getY();
			endY = source.getY();
		}

		// handle horizontal and vertical lines
		if (startX == endX || startY == endY) {
			for (int y = startY; y <= endY; y++) {
				for (int x = startX; x <= endX; x++) {
					coordinates.add(new Coordinate(x, y));
				}
			}
		}
	}
}
