package nl.imm.adventofcode.year2021.day3;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
class DiagnosticsCalculatorTest {

	// https://adventofcode.com/2021/day/3

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day3/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day3/example-data.txt");

	@Test
	void calculatePowerConsumptionExampleTest() {
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);
		LineParser lineParser = new LineParser();
		DiagnosticsCalculator diagnosticsCalculator = new DiagnosticsCalculator(lineParser);
		diagnosticsCalculator.addDiagnosticsData(linesFromFile);

		Assertions.assertEquals(198, diagnosticsCalculator.calculatePowerConsumption());
	}

	@Test
	void calculatePowerConsumptionExerciseTest() {
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		LineParser lineParser = new LineParser();
		DiagnosticsCalculator diagnosticsCalculator = new DiagnosticsCalculator(lineParser);
		diagnosticsCalculator.addDiagnosticsData(linesFromFile);

		Assertions.assertEquals(3277364, diagnosticsCalculator.calculatePowerConsumption());
	}
}
