package nl.imm.adventofcode.year2022.day6.part2;

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

		int startPacketIndexEnd0 = parseLine(linesFromFile.get(0), 0,0,4);
		int startPacketIndexEnd1 = parseLine(linesFromFile.get(1), 0,0,4);
		int startPacketIndexEnd2 = parseLine(linesFromFile.get(2), 0,0,4);
		int startPacketIndexEnd3 = parseLine(linesFromFile.get(3), 0,0,4);
		int startPacketIndexEnd4 = parseLine(linesFromFile.get(4), 0,0,4);


		int messageIndexEnd0 = parseLine(linesFromFile.get(0), startPacketIndexEnd0 -4, startPacketIndexEnd0 -4,14);
		int messageIndexEnd1 = parseLine(linesFromFile.get(1), startPacketIndexEnd1 -4, startPacketIndexEnd1 -4,14);
		int messageIndexEnd2 = parseLine(linesFromFile.get(2), startPacketIndexEnd2 -4, startPacketIndexEnd2 -4,14);
		int messageIndexEnd3 = parseLine(linesFromFile.get(3), startPacketIndexEnd3 -4, startPacketIndexEnd3 -4,14);
		int messageIndexEnd4 = parseLine(linesFromFile.get(4), startPacketIndexEnd4 -4, startPacketIndexEnd4 -4,14);

		// then
		assertEquals(19, messageIndexEnd0);
		assertEquals(23, messageIndexEnd1);
		assertEquals(23, messageIndexEnd2);
		assertEquals(29, messageIndexEnd3);
		assertEquals(26, messageIndexEnd4);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		int startPacketIndexEnd0 = parseLine(linesFromFile.get(0), 0,0,4);

		// when
		int messageIndexEnd0 = parseLine(linesFromFile.get(0), startPacketIndexEnd0 -4, startPacketIndexEnd0 -4,14);

		// then
		assertEquals(3837, messageIndexEnd0);
	}


	//"mjq jp qmgbljsphdztnv jf qwrcgsmlb"
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
