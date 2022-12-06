package nl.imm.adventofcode.year2022.day6.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
				.map(this::parseLine)
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
				.map(this::parseLine)
				.toList();

		// then
		assertEquals(1651, starts.get(0));
	}

	private int parseLine(String line) {

		int start = 0;
		int end = 0;
		List<String> dataList = new ArrayList<>();
		for (String character : line.split("")) {
			String substring = line.substring(start, end);
			if (substring.contains(character)) {
				while(substring.contains(character)) {
					start++;
					substring = line.substring(start, end);
				}
			}
			if (!substring.contains(character) && substring.length() == 4) {
				return end;
			}
			end ++;
		}



//		for (String character : line.split("")) {
//			if (!dataList.contains(character)) {
//				if (dataList.size() == 3) {
//					System.out.println("found start at end: " + end);
//					return end;
//				}
//			} else {
//				int index = dataList.indexOf(character);
//				for (int i = index; i >= 0; i--) {
//					try {
//						dataList.remove(index);
//					} catch (IndexOutOfBoundsException e) {
//						//
//					}
//				}
//			}
//			dataList.add(character);
//			end++;
//		}

		return 0;
	}
}
