package nl.imm.adventofcode.year2021.day10.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class SyntaxScoringTest {


	// https://adventofcode.com/2021/day/10

	private final Map<Character, Integer> closingCharacter = new HashMap<>() {{
		put(')', 3);
		put(']', 57);
		put('}', 1197);
		put('>', 25137);
	}};

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day10/data.txt");

	private final Map<Character, Integer> openingCharacter = new HashMap<>() {{
		put('(', 0);
		put('[', 0);
		put('{', 0);
		put('<', 0);
	}};

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day10/example-data.txt");

	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);
		int totalScore = stringList.stream()
				.map(this::parseLine)
				.mapToInt(Integer::intValue)
				.sum();

		assertEquals(26397, totalScore);
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);


		int totalScore = 0;

		assertEquals(26397, totalScore);
	}

	private boolean isClosingChar(Character character) {
		return closingCharacter.containsKey(character);
	}

	private boolean isClosingCharForOpeningChar(Character closingChar, Character openingChar) {
		if (openingChar == '(' && ')' == closingChar) {
			return true;
		} else if (openingChar == '[' && ']' == closingChar) {
			return true;
		} else if (openingChar == '{' && '}' == closingChar) {
			return true;
		} else return openingChar == '<' && '>' == closingChar;
	}

	private boolean isOpeningChar(Character character) {
		return openingCharacter.containsKey(character);
	}

	private int parseLine(String line) {
		char[] chars = line.toCharArray();

		char lastChar = '0';
		int score = 0;
		for (char ch : chars) {
			if (isOpeningChar(ch)) {
				lastChar = ch;
			} else {
				if (!isClosingChar(lastChar) && !isClosingCharForOpeningChar(ch, lastChar)) {
					score += closingCharacter.get(ch).intValue();
				} else {
					lastChar = ch;
				}
			}
		}
		return score;
	}

}
