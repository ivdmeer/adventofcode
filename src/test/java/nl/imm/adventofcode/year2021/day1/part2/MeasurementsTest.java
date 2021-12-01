package nl.imm.adventofcode.year2021.day1.part2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
class MeasurementsTest {

	// https://adventofcode.com/2021/day/1#part2
	// Part 2

	private final Path resourceFile = Paths.get(
			"src/main/resources/year2021/day1/measurements.txt");

	Path getResourceFilePath() {
		return resourceFile;
	}

	@Test
	void test() throws IOException {

		List<Integer> list = Files.readAllLines(getResourceFilePath(), Charset.defaultCharset())
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		Measurements measurements = new Measurements(list);
		long countIncreasedCalculatedMeasurements = measurements.countIncreasedMeasurements();

		System.out.println("Amount increased: " + countIncreasedCalculatedMeasurements);
	}

}
