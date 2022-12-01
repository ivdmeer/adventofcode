package nl.imm.adventofcode.year2022.day1.part2;

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

	// https://adventofcode.com/2022/day/1#part2

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
		int highestValue = determineHighestCalories(caloriesMap);

		// then
		assertNotNull(highestValue);
		assertEquals(45000, highestValue);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		Map<Integer, Integer> caloriesMap = caloriesToMap(linesFromFile);

		// when
		int highestValue = determineHighestCalories(caloriesMap);

		// then
		assertEquals(195292, highestValue);
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

	private int determineHighestCalories(Map<Integer, Integer> caloriesMap) {

		int highest1 = 0;
		int highest2 = 0;
		int highest3 = 0;

		for (Map.Entry<Integer, Integer> entry : caloriesMap.entrySet()) {
			if (entry.getKey() > highest1) {
				highest3 = highest2;
				highest2 = highest1;
				highest1 = entry.getKey();
			} else if (entry.getKey() < highest1 && entry.getKey() > highest2) {
				highest3 = highest2;
				highest2 = entry.getKey();
			} else if (entry.getKey() < highest2 && entry.getKey() > highest3) {
				highest3 = entry.getKey();
			}
		}
		return highest1 + highest2 + highest3;
	}


}
