package nl.imm.adventofcode.year2021.day7.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Ivo van der Meer
 */
public class FuelOptimizerTest {

	// https://adventofcode.com/2021/day/7

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day7/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day7/example-data.txt");

	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);

		List<Integer> allAlignmentPositions = stringList.stream()
				.map(str -> Arrays.asList(str.split(",")))
				.flatMap(List::stream)
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		Map.Entry<Integer, Long> cheapestAlignedPositionEntry = determineCheapestAlignedPosition(allAlignmentPositions);
		int cheapestAlignedPosition = cheapestAlignedPositionEntry.getKey();
		long fuelConsumption = cheapestAlignedPositionEntry.getValue();

		assertEquals(2, cheapestAlignedPosition);
		assertEquals(37, fuelConsumption);
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);
		List<Integer> allAlignmentPositions = stringList.stream()
				.map(str -> Arrays.asList(str.split(",")))
				.flatMap(List::stream)
				.map(Integer::valueOf)
				.collect(Collectors.toList());

		assertFalse(allAlignmentPositions.isEmpty());

		Map.Entry<Integer, Long> cheapestAlignedPositionEntry = determineCheapestAlignedPosition(allAlignmentPositions);
		int cheapestAlignedPosition = cheapestAlignedPositionEntry.getKey();
		assertEquals(339, cheapestAlignedPosition);

		long fuelConsumption = cheapestAlignedPositionEntry.getValue();
		assertEquals(355764, fuelConsumption); // 65 best post and fuel consumption 431292 too high
	}

	private Map<Integer, Long> calculateAllFuelConsumptions(List<Integer> allAlignmentPositions) {
		Map<Integer, Long> fuelConsumptionPositionMap = new HashMap<>();
		Integer maxAlignementPosition = allAlignmentPositions.stream()
				.max(Integer::compareTo)
				.orElseThrow(IllegalStateException::new);

		for (int position = 0; position <= maxAlignementPosition; position++) {
			fuelConsumptionPositionMap.putIfAbsent(position, calculateFuelConsumption(allAlignmentPositions, position));
		}

		return fuelConsumptionPositionMap;
	}

	private long calculateFuelConsumption(List<Integer> allAlignmentPositions, int cheapestAlignedPosition) {
		long fuelConsumption = 0;

		for (Integer position : allAlignmentPositions) {
			if (position < cheapestAlignedPosition) {
				fuelConsumption += cheapestAlignedPosition - position;
			} else {
				fuelConsumption += position - cheapestAlignedPosition;
			}
		}

		return fuelConsumption;
	}

	private Map.Entry<Integer, Long> determineCheapestAlignedPosition(List<Integer> allAlignmentPositions) {
		Map<Integer, Long> fuelConsumptionPositionMap = calculateAllFuelConsumptions(allAlignmentPositions);
		Map.Entry<Integer, Long> cheapestEntry = null;
		for (Map.Entry<Integer, Long> entry : fuelConsumptionPositionMap.entrySet()) {
			if (cheapestEntry == null) {
				cheapestEntry = entry;
			}
			if (entry.getValue() < cheapestEntry.getValue()) {
				cheapestEntry = entry;
			}
		}
		return cheapestEntry;
	}

}
