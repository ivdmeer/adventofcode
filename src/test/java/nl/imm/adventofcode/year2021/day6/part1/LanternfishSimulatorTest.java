package nl.imm.adventofcode.year2021.day6.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
class LanternfishSimulatorTest {

	// https://adventofcode.com/2021/day/6

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day6/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day6/example-data.txt");

	@Test
	void exampleDay1Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(parseSimulationValue(strings.get(0)));
		String simulationValue = lanternfishSimulator.simulateDay(1);

		Assertions.assertEquals("2,3,2,0,1", simulationValue);
	}

	@Test
	void exampleDay2Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(parseSimulationValue(strings.get(0)));
		String simulationValue = lanternfishSimulator.simulateDay(2);

		Assertions.assertEquals("1,2,1,6,0,8", simulationValue);
	}

	@Test
	void exampleDay12Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(parseSimulationValue(strings.get(0)));
		String simulationValue = lanternfishSimulator.simulateDay(12);

		Assertions.assertEquals("5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8", simulationValue);
	}

	@Test
	void exampleDay18Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(parseSimulationValue(strings.get(0)));
		String simulationValue = lanternfishSimulator.simulateDay(18);

		Assertions.assertEquals("6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8", simulationValue);
		Assertions.assertEquals(26, lanternfishSimulator.getFishCount());
	}


	@Test
	void exampleDay80Test() {

		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(parseSimulationValue(strings.get(0)));
		lanternfishSimulator.simulateDay(80);

		Assertions.assertEquals(5934, lanternfishSimulator.getFishCount());
	}

	@Test
	void exerciseTest() {
		List<String> strings = FileHelper.readAllLinesFromFile(exerciseDataFile);

		LanternfishSimulator lanternfishSimulator = new LanternfishSimulator(strings.get(0));
		lanternfishSimulator.simulateDay(80);

		Assertions.assertEquals(388739, lanternfishSimulator.getFishCount());
	}


	private String parseSimulationValue(String value) {
		String[] split = value.split(":");
		if (split.length != 2) {
			throw new IllegalStateException("Incorrect value");
		}
		return split[1].trim();
	}
}
