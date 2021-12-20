package nl.imm.adventofcode.year2021.day5.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

		Assertions.assertTrue(!ventLines.isEmpty());
		Map<Coordinate, Integer> overlappingCount = getOverlappingCount(ventLines);
		printGrid(overlappingCount);

		Assertions.assertEquals(5, calculateOverlappingAmount(overlappingCount));
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);

		List<VentLine> ventLines = stringList.stream()
				.map(VentLine::new)
				.filter(VentLine::hasCoordinates)
				.collect(Collectors.toList());

		Assertions.assertTrue(!ventLines.isEmpty());
		Map<Coordinate, Integer> overlappingCount = getOverlappingCount(ventLines);

		Assertions.assertEquals(7436, calculateOverlappingAmount(overlappingCount));
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

	private void printGrid(Map<Coordinate, Integer> numbersPerCoordinate) {
		int maxX = 9;
		int maxY = 9;

		for (int y = 0; y <= maxY; y++) {
			for (int x = 0; x <= maxX; x++) {
				Coordinate coordinate = new Coordinate(x, y);
				if (numbersPerCoordinate.containsKey(coordinate) && numbersPerCoordinate.get(coordinate) > 0) {
					System.out.printf(" %d ", numbersPerCoordinate.get(coordinate));
				} else {
					System.out.print(" . ");
				}
			}
			System.out.print("\n");
		}
	}
}
