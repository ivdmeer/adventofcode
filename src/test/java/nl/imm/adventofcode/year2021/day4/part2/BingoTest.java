package nl.imm.adventofcode.year2021.day4.part2;

import nl.imm.adventofcode.year2021.FileHelper;
import nl.imm.adventofcode.year2021.day4.part1.BingoCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
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

		Map<Integer, BingoCard> cardsWithBingoMap = new HashMap<>();
		List<BingoCard> bingoCardsToWorkWith = new ArrayList<>(bingoCards);
		// when
		for (Integer number : numbersPicked) {
			bingoCardsToWorkWith.forEach(bingoCard -> bingoCard.checkNumber(number));
			Optional<BingoCard> foundBingoCard = bingoCardsToWorkWith.stream()
					.filter(BingoCard::isBingo)
					.findFirst();

			if (foundBingoCard.isPresent()) {
				bingoCardsToWorkWith.remove(foundBingoCard.get());
				cardsWithBingoMap.put(number, foundBingoCard.get());
			}
		}

		Integer lastNumber = 0;
		for (Integer key :cardsWithBingoMap.keySet()) {
			lastNumber = key;
		}

		BingoCard cardWithBingo = cardsWithBingoMap.get(lastNumber);
		int sum = lastNumber * cardWithBingo.calculateUnmarkedNumbers();

		// then
		Assertions.assertEquals(13, lastNumber);
		Assertions.assertEquals(148, cardWithBingo.calculateUnmarkedNumbers());
		Assertions.assertEquals(1924, sum);
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

		Map<Integer, List<BingoCard>> cardsWithBingoMap = new LinkedHashMap<>();
		List<BingoCard> bingoCardsToWorkWith = new ArrayList<>(bingoCards);
		// when
		for (Integer number : numbersPicked) {
			for (BingoCard card: bingoCardsToWorkWith) {
				card.checkNumber(number);
				if (card.isBingo()) {
					cardsWithBingoMap.putIfAbsent(number, new ArrayList<>());
					cardsWithBingoMap.get(number).add(card);
				}
			}
			if (cardsWithBingoMap.containsKey(number) && !cardsWithBingoMap.get(number).isEmpty()) {
				bingoCardsToWorkWith.removeAll(cardsWithBingoMap.get(number));
			}
		}

		Integer lastNumber = 0;
		BingoCard cardWithBingo = null;
		for (Integer key: cardsWithBingoMap.keySet() ) {
			lastNumber = key;
			for (BingoCard card: cardsWithBingoMap.get(key)) {
				cardWithBingo = card;
			}
		}

		int sum = lastNumber * cardWithBingo.calculateUnmarkedNumbers();

		Assertions.assertEquals(36, lastNumber);
		Assertions.assertEquals(483, cardWithBingo.calculateUnmarkedNumbers());
		Assertions.assertEquals(17388, sum);
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
