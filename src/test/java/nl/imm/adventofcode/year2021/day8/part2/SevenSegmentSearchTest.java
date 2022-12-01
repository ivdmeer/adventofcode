package nl.imm.adventofcode.year2021.day8.part2;

import nl.imm.adventofcode.year2021.FileHelper;
import nl.imm.adventofcode.year2021.day8.part1.Digit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

	// https://adventofcode.com/2021/day/#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day8/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day8/example-data.txt");

	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);
		List<List<String>> simpleDigits = stringList.stream()
				.map(this::parseSimpleDigits)
				.collect(Collectors.toList());

		Digit cefdb = Digit.fromString("cefdb");
		Digit gcbe = Digit.fromString("cefbgd");
		long countSimpleDigits = countAllDigits(simpleDigits);
		assertEquals(61229, countSimpleDigits);
	}

	@ParameterizedTest
	@CsvSource(value = {
			"gc:1", "cg:1", "cdgba:2", "fcadb:3", "cdbaf:3", "gcbe:4", "cdfeb:5", "cdfeb:5", "gadfec:6", "gadfec:6",
			"bgf:7", "cbg:7", "fdgacbe:8", "gbdfcae:8", "cefbgd:9", "efabcd:9"
	}, delimiter = ':')
	void decodingTest(String input, Integer expected) {
		Digit digit = Digit.fromString(input);
		assertEquals(expected, digit.getNumber());
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

	private long countAllDigits(List<List<String>> simpleDigits) {
		List<Long> collect = simpleDigits.stream()
				.map(this::countDigit)
				.collect(Collectors.toList());

		return simpleDigits.stream()
				.map(this::countDigit)
				.mapToLong(Long::longValue)
				.sum();
	}

	private long countDigit(List<String> digits) {
		StringBuilder value = new StringBuilder();
		for (String digitString: digits) {
			if (Digit.isParsable(digitString)) {
				Digit digit = Digit.fromString(digitString);
				value.append(digit.getNumber());
			}
		}

		String output = value.toString();
		if (StringUtils.isBlank(output)) {
			output = "0";
		}
		return Long.parseLong(output);
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
