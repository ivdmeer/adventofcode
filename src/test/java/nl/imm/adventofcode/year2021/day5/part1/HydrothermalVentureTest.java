package nl.imm.adventofcode.year2021.day5.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Ivo van der Meer
 */
class HydrothermalVentureTest {

	// https://adventofcode.com/2021/day/5

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day5/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day5/example-data.txt");

	int calculateOverlappingAmount(Map<Coordinate, Integer> numbersPerCoordinate) {
		int amount = 0;
		for (Integer value : numbersPerCoordinate.values()) {
			if (value > 1) {
				amount++;
			}
		}
		return amount;
	}

	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);

		List<VentLine> ventLines = stringList.stream()
				.map(VentLine::new)
				.filter(VentLine::hasCoordinates)
				.collect(Collectors.toList());

		assertFalse(ventLines.isEmpty());
		Map<Coordinate, Integer> overlappingCount = getOverlappingCount(ventLines);
		String printedGrid = printGrid(overlappingCount);

		assertEquals(5, calculateOverlappingAmount(overlappingCount));
		assertEquals(exampleGrid(), printedGrid);
		System.out.println(printedGrid);
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);

		List<VentLine> ventLines = stringList.stream()
				.map(VentLine::new)
				.filter(VentLine::hasCoordinates)
				.collect(Collectors.toList());

		assertFalse(ventLines.isEmpty());
		Map<Coordinate, Integer> overlappingCount = getOverlappingCount(ventLines);

		assertEquals(7436, calculateOverlappingAmount(overlappingCount));
	}

	private String exampleGrid() {
		return ".......1..\n" +
				"..1....1..\n" +
				"..1....1..\n" +
				".......1..\n" +
				".112111211\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"222111....\n";
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
