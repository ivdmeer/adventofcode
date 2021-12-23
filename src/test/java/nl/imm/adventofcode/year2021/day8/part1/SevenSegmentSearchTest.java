package nl.imm.adventofcode.year2021.day8.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class SevenSegmentSearchTest {

	// https://adventofcode.com/2021/day/8

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day8/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day8/example-data.txt");

	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);
		List<String> simpleDigits = stringList.stream()
				.map(this::parseSimpleDigits)
				.flatMap(List::stream)
				.filter(StringUtils::isNotBlank)
				.collect(Collectors.toList());

		long countSimpleDigits = countSimpleDigits(simpleDigits);
		assertEquals(26, countSimpleDigits);
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);
		List<String> simpleDigits = stringList.stream()
				.map(this::parseSimpleDigits)
				.flatMap(List::stream)
				.filter(StringUtils::isNotBlank)
				.collect(Collectors.toList());

		long countSimpleDigits = countSimpleDigits(simpleDigits);
		assertEquals(445, countSimpleDigits);
	}

	private long countSimpleDigits(List<String> simpleDigits) {
		return simpleDigits.stream()
				.filter(str -> str.length() == Digit.ONE.getLength() ||
						str.length() == Digit.FOUR.getLength() ||
						str.length() == Digit.SEVEN.getLength() ||
						str.length() == Digit.EIGHT.getLength())
				.count();
	}

	private List<String> parseSimpleDigits(String line) {
		String[] split = line.split("\\|");
		return Arrays.asList(split[1].split("\\s"));
	}

}
