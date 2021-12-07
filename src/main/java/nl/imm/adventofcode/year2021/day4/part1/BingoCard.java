package nl.imm.adventofcode.year2021.day4.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivo van der Meer
 */
public class BingoCard {

	// Row => Colums
	private final Map<Integer, CardLine> horizontalRowMapping = new HashMap<>();

	private final Map<Integer, CardLine> verticalRowMapping = new HashMap<>();

	public BingoCard(Collection<String> cardNumbers) {
		int index = 0;
		Map<Integer, List<Integer>> verticalMap = new HashMap<>();
		for (String num : cardNumbers) {
			CardLine value = new CardLine(num);
			horizontalRowMapping.put(index, value);
			List<Integer> numbers = value.getNumbers();
			for (int i = 0; i < numbers.size(); i++) {
				verticalMap.putIfAbsent(i, new ArrayList<>());
				verticalMap.get(i).add(numbers.get(i));
			}
			index++;
		}

		for (int i = 0; i < verticalMap.size(); i++) {
			verticalRowMapping.put(i, new CardLine(verticalMap.get(i)));
		}
	}

	public int calculateUnmarkedNumbers() {
		return horizontalRowMapping.values()
				.stream()
				.flatMap(line -> line.getUnmarkedNumbers().stream())
				.mapToInt(Integer::intValue)
				.sum();
	}

	public void checkNumber(Integer number) {
		horizontalRowMapping.values()
				.forEach(cardLine -> cardLine.checkNumber(number));
		verticalRowMapping.values()
				.forEach(cardLine -> cardLine.checkNumber(number));
	}

	public boolean isAnyRowBingo() {
		return isAnyHorizontalRowBingo() || isAnyVerticalRowBingo();
	}

	public boolean isBingo() {
		return isFullBingo() || isAnyRowBingo();
	}

	public boolean isBottomLineBingo() {
		return horizontalRowMapping.get(4).isBingo();
	}

	public boolean isTopLineBingo() {
		return horizontalRowMapping.get(0).isBingo();
	}

	private boolean isAnyHorizontalRowBingo() {
		return horizontalRowMapping.values()
				.stream()
				.anyMatch(CardLine::isBingo);
	}

	private boolean isAnyVerticalRowBingo() {
		return verticalRowMapping.values()
				.stream()
				.anyMatch(CardLine::isBingo);
	}

	private boolean isFullBingo() {
		return horizontalRowMapping.values()
				.stream()
				.allMatch(CardLine::isBingo);
	}

}
