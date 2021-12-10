package nl.imm.adventofcode.year2021.day3.part2;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
class DiagnosticsSimulatorTest {

	// https://adventofcode.com/2021/day/3#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day3/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day3/example-data.txt");

	@Test
	void calculatePowerConsumptionExampleTest() {
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);
		LineParser lineParser = new LineParser();
		DiagnosticsCalculator diagnosticsCalculator = new DiagnosticsCalculator(lineParser, linesFromFile);

		Assertions.assertEquals(198, diagnosticsCalculator.calculatePowerConsumption());
		Assertions.assertEquals(23, diagnosticsCalculator.calculateOxygenGeneratorRating());
		Assertions.assertEquals(10, diagnosticsCalculator.calculateCO2ScrubberRating());
		Assertions.assertEquals(230, diagnosticsCalculator.calculateLifeSupportRating());
	}

	@Test
	void calculatePowerConsumptionExerciseTest() {
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		LineParser lineParser = new LineParser();
		DiagnosticsCalculator diagnosticsCalculator = new DiagnosticsCalculator(lineParser, linesFromFile);

		Assertions.assertEquals(3277364, diagnosticsCalculator.calculatePowerConsumption());
		Assertions.assertEquals(3583, diagnosticsCalculator.calculateOxygenGeneratorRating());
		Assertions.assertEquals(1601, diagnosticsCalculator.calculateCO2ScrubberRating());
		Assertions.assertEquals(5736383, diagnosticsCalculator.calculateLifeSupportRating());
	}
}
