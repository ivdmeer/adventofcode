package nl.imm.adventofcode.year2021.day6.part2;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
class LanternfishSimulator2Test {

	// https://adventofcode.com/2021/day/6#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day6/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day6/example-data.txt");

	@Test
	void exampleDay1Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(1);

		Assertions.assertEquals(5, lanternfishSimulator.getFishCount());
	}

	@Test
	void exampleDay2Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(2);

		Assertions.assertEquals(6, lanternfishSimulator.getFishCount());
	}

	@Test
	void exampleDay18Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(18);

		Assertions.assertEquals(26, lanternfishSimulator.getFishCount());
	}

	@Test
	void exampleDay256Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(256);

		Assertions.assertEquals(26984457539L, lanternfishSimulator.getFishCount());
	}

	@Test
	void exampleDay80Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(80);

		Assertions.assertEquals(5934, lanternfishSimulator.getFishCount());
	}

	@Test
	void exerciseTest() {
		List<String> strings = FileHelper.readAllLinesFromFile(exerciseDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(strings.get(0));
		lanternfishSimulator.simulateDay(80);

		Assertions.assertEquals(388739, lanternfishSimulator.getFishCount());
	}

	@Test
	void exercise256Test() {
		List<String> strings = FileHelper.readAllLinesFromFile(exerciseDataFile);

		LanternfishSimulator2 lanternfishSimulator = new LanternfishSimulator2(strings.get(0));
		lanternfishSimulator.simulateDay(256);

		Assertions.assertEquals(1741362314973L, lanternfishSimulator.getFishCount());
	}



	private String parseSimulationValue(String value) {
		String[] split = value.split(":");
		if (split.length != 2) {
			throw new IllegalStateException("Incorrect value");
		}
		return split[1].trim();
	}
}
