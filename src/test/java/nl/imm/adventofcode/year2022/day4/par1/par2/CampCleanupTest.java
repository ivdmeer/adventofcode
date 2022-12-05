package nl.imm.adventofcode.year2022.day4.par1.par2;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class CampCleanupTest {

	// https://adventofcode.com/2022/day/4#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day4/exercise-data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day4/example-data.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);

		// when
		int total = parseLines(linesFromFile);

		// then
		assertEquals(4, total);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);

		// when
		int total = parseLines(linesFromFile);

		// then
		assertEquals(919, total);
	}

	private int parseLines(List<String> lines) {

		int count = 0;
		for (String line : lines) {
			String[] locations = line.split(",");
			if (locations.length != 2) {
				throw new IllegalStateException("Line does not locations as expected!");
			}
			String[] range1 = locations[0].split("-");
			String[] range2 = locations[1].split("-");
			List<Integer> locationList1 = IntStream.range(Integer.parseInt(range1[0]), Integer.parseInt(range1[1]) + 1)
					.boxed()
					.toList();
			List<Integer> locationList2 = IntStream.range(Integer.parseInt(range2[0]), Integer.parseInt(range2[1]) + 1)
					.boxed()
					.toList();

			Set<Integer> overlappingWithList2 = locationList1.stream()
					.filter(k -> locationList2.stream().anyMatch(p -> p.equals(k)))
					.collect(Collectors.toSet());

			Set<Integer> overlappingWithList1 = locationList2.stream()
					.filter(k -> locationList1.stream().anyMatch(p -> p.equals(k)))
					.collect(Collectors.toSet());
			if (!overlappingWithList1.isEmpty() || !overlappingWithList2.isEmpty()) {
				count++;
			}

		}
		return count;

	}


}
