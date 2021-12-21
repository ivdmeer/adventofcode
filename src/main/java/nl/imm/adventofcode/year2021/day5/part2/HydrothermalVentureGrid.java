package nl.imm.adventofcode.year2021.day5.part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class HydrothermalVentureGrid {

	private final List<VentLine> ventLines;

	public HydrothermalVentureGrid(List<String> stringList) {
		this.ventLines = stringList.stream()
				.map(VentLine::new)
				.filter(VentLine::hasCoordinates)
				.collect(Collectors.toList());
	}

	public int calculateOverlappingAmount() {
		return calculateOverlappingAmount(getOverlappingCount(ventLines));
	}

	public String printGrid() {
		return printGrid(getOverlappingCount(ventLines));
	}

	private int calculateOverlappingAmount(Map<Coordinate, Integer> numbersPerCoordinate) {
		int amount = 0;
		for (Integer value : numbersPerCoordinate.values()) {
			if (value > 1) {
				amount++;
			}
		}
		return amount;
	}

	private Map<Coordinate, Integer> getOverlappingCount(List<VentLine> lines) {
		Map<Coordinate, Integer> overlappingCountMap = new HashMap<>();

		for (VentLine line : lines) {
			for (Coordinate coordinate : line.getCoordinates()) {
				overlappingCountMap.putIfAbsent(coordinate, 0);
				overlappingCountMap.put(coordinate, overlappingCountMap.get(coordinate) + 1);
			}
		}

		return overlappingCountMap;
	}

	private String printGrid(Map<Coordinate, Integer> numbersPerCoordinate) {
		int maxX = 9;
		int maxY = 9;
		StringBuilder grid = new StringBuilder();

		for (int y = 0; y <= maxY; y++) {
			for (int x = 0; x <= maxX; x++) {
				Coordinate coordinate = new Coordinate(x, y);
				if (numbersPerCoordinate.containsKey(coordinate) && numbersPerCoordinate.get(coordinate) > 0) {
					grid.append(numbersPerCoordinate.get(coordinate));
				} else {
					grid.append(".");
				}
			}
			grid.append("\n");
		}

		return grid.toString();
	}

}
