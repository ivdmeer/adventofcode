package nl.imm.adventofcode.year2022.day1.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Ivo van der Meer
 */
class CalorieCountingTest {

	// https://adventofcode.com/2022/day/1

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day1/exercise-data-part1.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day1/example-data-part1.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);
		Map<Integer, Integer> caloriesMap = caloriesToMap(linesFromFile);

		// when
		Map.Entry<Integer, Integer> highestValue = determineHighestCalories(caloriesMap);

		// then
		assertNotNull(highestValue);
		assertEquals(24000, highestValue.getKey());
		assertEquals(4, highestValue.getValue());
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		Map<Integer, Integer> caloriesMap = caloriesToMap(linesFromFile);

		// when
		Map.Entry<Integer, Integer> highestValue = determineHighestCalories(caloriesMap);

		// then
		assertNotNull(highestValue);
		assertEquals(66306, highestValue.getKey());
		assertEquals(31, highestValue.getValue());
	}

	private Map<Integer, Integer> caloriesToMap(List<String> linesFromFile) {

		Map<Integer, Integer> caloriesMap = new HashMap<>();

		Integer index = 1;
		Integer amount = 0;
		for (String line : linesFromFile) {
			if (StringUtils.isBlank(line)) {
				caloriesMap.put(amount, index);
				index++;
				amount = 0;
			} else {
				amount += Integer.parseInt(line);
			}
		}
		caloriesMap.put(amount, index);
		return caloriesMap;
	}

	private Map.Entry<Integer, Integer> determineHighestCalories(Map<Integer, Integer> caloriesMap) {
		Map.Entry<Integer, Integer> highest = null;
		for (Map.Entry<Integer, Integer> entry : caloriesMap.entrySet()) {
			if (highest == null || entry.getKey() > highest.getKey()) {
				highest = entry;
			}
		}
		return highest;
	}


}
