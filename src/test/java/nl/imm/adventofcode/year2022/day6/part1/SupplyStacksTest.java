package nl.imm.adventofcode.year2022.day6.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class SupplyStacksTest {

	// https://adventofcode.com/2022/day/6

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day6/exercise-data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day6/example-data.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);


		// when
		List<Integer> starts = linesFromFile.stream()
				.map(line -> parseLine(line, 0, 0, 4))
				.toList();

		// then
		assertEquals(7, starts.get(0));
		assertEquals(5, starts.get(1));
		assertEquals(6, starts.get(2));
		assertEquals(10, starts.get(3));
		assertEquals(11, starts.get(4));
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);

		// when
		List<Integer> starts = linesFromFile.stream()
				.map(line -> parseLine(line, 0, 0, 4))
				.toList();

		// then
		assertEquals(1651, starts.get(0));
	}
	private int parseLine(String line, int start, int end, int maxLength) {

		String[] characterArray = line.split("");

		for (int index = start; index < characterArray.length -1; index++) {
			String character = characterArray[index];
			String substring = line.substring(start, end);
			if (substring.length() == maxLength) {
				return end;
			}
			if (substring.contains(character)) {
				while (substring.contains(character)) {
					start++;
					substring = line.substring(start, end);
				}
			}

			end++;
		}
		return 0;
	}
}
