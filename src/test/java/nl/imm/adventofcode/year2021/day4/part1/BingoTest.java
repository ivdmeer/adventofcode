package nl.imm.adventofcode.year2021.day4.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
class BingoTest {

	// https://adventofcode.com/2021/day/4

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day4/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day4/example-data.txt");

	@Test
	void exampleTest() {
		// given
		List<String> strings = FileHelper.readAllLinesFromFile(testDataFile);
		String numbersPickedString = strings.get(0);
		List<String> cardNumbers = new ArrayList<>(strings);
		cardNumbers.remove(numbersPickedString);

		List<BingoCard> bingoCards = parseLines(cardNumbers);
		List<Integer> numbersPicked = Arrays.asList(numbersPickedString.split(","))
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		// when
		int sum = 0;
		int lastNumber = 0;
		BingoCard cardWithBingo = null;
		for (Integer number : numbersPicked) {
			bingoCards.forEach(bingoCard -> bingoCard.checkNumber(number));
			Optional<BingoCard> foundBingoCard = bingoCards.stream()
					.filter(BingoCard::isBingo)
					.findFirst();

			if (foundBingoCard.isPresent()) {
				cardWithBingo = foundBingoCard.get();
				lastNumber = number;
				sum = number * cardWithBingo.calculateUnmarkedNumbers();
				break;
			}

		}

		// then
		Assertions.assertEquals(24, lastNumber);
		Assertions.assertEquals(188, cardWithBingo.calculateUnmarkedNumbers());
		Assertions.assertEquals(4512, sum);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> strings = FileHelper.readAllLinesFromFile(exerciseDataFile);
		String numbersPickedString = strings.get(0);
		List<String> cardNumbers = new ArrayList<>(strings);
		cardNumbers.remove(numbersPickedString);

		List<BingoCard> bingoCards = parseLines(cardNumbers);
		List<Integer> numbersPicked = Arrays.asList(numbersPickedString.split(","))
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		// when
		int sum = 0;
		int lastNumber = 0;
		BingoCard cardWithBingo = null;
		List<BingoCard> cardWithBingoList = new ArrayList<>();
		stopBingo:
		for (Integer number : numbersPicked) {
			for (BingoCard bingoCard : bingoCards) {
				bingoCard.checkNumber(number);
				if (bingoCard.isBottomLineBingo()) {
					cardWithBingo = bingoCard;
					lastNumber = number;
					sum = number * cardWithBingo.calculateUnmarkedNumbers();
					cardWithBingoList.add(bingoCard);
					break stopBingo;
				}
			}
/*			if (cardWithBingoList.size() > 0) {
				break stopBingo;
			}*/
		}

		// then
		Assertions.assertEquals(53, lastNumber);
		Assertions.assertEquals(776, cardWithBingo.calculateUnmarkedNumbers());
		Assertions.assertEquals(41128, sum);
	}

	private List<BingoCard> parseLines(List<String> lines) {
		List<String> cardLines = new ArrayList<>();
		List<BingoCard> cards = new ArrayList<>();
		for (String line : lines) {
			if (StringUtils.isNotBlank(line)) {
				cardLines.add(line);
			}
			if (cardLines.size() == 5) {
				cards.add(new BingoCard(cardLines));
				cardLines = new ArrayList<>();
			}
		}
		return cards;
	}

}
