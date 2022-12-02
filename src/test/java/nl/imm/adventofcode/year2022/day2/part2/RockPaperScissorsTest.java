package nl.imm.adventofcode.year2022.day2.part2;

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
class RockPaperScissorsTest {

	// https://adventofcode.com/2022/day/2#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day2/exercise-data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day2/example-data.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);

		// when
		int totalScore = linesFromFile.stream()
				.map(this::calculateMatchScoreByLine)
				.mapToInt(Integer::intValue)
				.sum();

		// then
		assertEquals(12, totalScore);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);

		// when
		int totalScore = linesFromFile.stream()
				.map(this::calculateMatchScoreByLine)
				.mapToInt(Integer::intValue)
				.sum();

		// then
		assertEquals(12767, totalScore);
	}

	private int calculateMatchScore(RPS player, MatchResult matchResult) {
		return player.getScore() + matchResult.getScore();
	}

	private int calculateMatchScoreByLine(String line) {
		String[] values = line.split("\s");
		if (values.length != 2) {
			throw new IllegalStateException("Parsing of line failed");
		}

		RPS player1 = RPS.parseString(values[0]);
		RPS player2 = handleNewStrategy(player1, RPS.parseString(values[1]));

		MatchResult matchResult = determineMatchResult(player1, player2);
		return calculateMatchScore(player2, matchResult);
	}

	private MatchResult determineMatchResult(RPS player1, RPS player2) {

		return switch (player2) {
			case ROCK -> {
				if (player1 == RPS.ROCK) {
					yield MatchResult.DRAW;
				} else if (player1 == RPS.PAPER) {
					yield MatchResult.LOST;
				} else {
					yield MatchResult.WIN;
				}
			}
			case PAPER -> {
				if (player1 == RPS.ROCK) {
					yield MatchResult.WIN;
				} else if (player1 == RPS.PAPER) {
					yield MatchResult.DRAW;
				} else {
					yield MatchResult.LOST;
				}
			}
			case SCISSORS -> {
				if (player1 == RPS.ROCK) {
					yield MatchResult.LOST;
				} else if (player1 == RPS.PAPER) {
					yield MatchResult.WIN;
				} else {
					yield MatchResult.DRAW;
				}
			}
		};

	}

	private RPS handleNewStrategy(RPS player1, RPS player2) {
		return switch (player2) {
			case ROCK -> {
				if (player1 == RPS.ROCK) {
					yield RPS.SCISSORS;
				} else if (player1 == RPS.PAPER) {
					yield RPS.ROCK;
				} else {
					yield RPS.PAPER;
				}
			}
			case PAPER -> {
				if (player1 == RPS.ROCK) {
					yield RPS.ROCK;
				} else if (player1 == RPS.PAPER) {
					yield RPS.PAPER;
				} else {
					yield RPS.SCISSORS;
				}
			}
			case SCISSORS -> {
				if (player1 == RPS.ROCK) {
					yield RPS.PAPER;
				} else if (player1 == RPS.PAPER) {
					yield RPS.SCISSORS;
				} else {
					yield RPS.ROCK;
				}
			}
		};
	}

	enum MatchResult {

		WIN(6),
		DRAW(3),
		LOST(0);

		private final int score;

		MatchResult(int score) {
			this.score = score;
		}

		public int getScore() {
			return score;
		}
	}

	enum RPS {
		ROCK(1),
		PAPER(2),
		SCISSORS(3);

		private final int score;

		RPS(int score) {
			this.score = score;
		}

		public static RPS parseString(String value) {
			return switch (value) {
				case "A", "X" -> RPS.ROCK;
				case "B", "Y" -> RPS.PAPER;
				case "C", "Z" -> RPS.SCISSORS;
				default -> throw new IllegalStateException("Unexpected value");
			};
		}

		public int getScore() {
			return score;
		}

	}

}
