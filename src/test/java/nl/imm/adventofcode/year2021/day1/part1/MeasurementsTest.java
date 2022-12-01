package nl.imm.adventofcode.year2021.day1.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
class MeasurementsTest {

	// https://adventofcode.com/2021/day/1
	// Part 1

	private final Path resourceFile = Paths.get(
			"src/main/resources/year2021/day1/measurements.txt");

	@Test
	void exerciseTest() {
		List<Integer> list = FileHelper.readAllLinesFromFile(resourceFile)
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		Measurements measurements = new Measurements(list);
		long countIncreasedMeasurements = measurements.countIncreasedMeasurements();

		System.out.println("Amount increased: " + countIncreasedMeasurements);
		Assertions.assertTrue(countIncreasedMeasurements > 0);
	}

}
