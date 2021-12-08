package nl.imm.adventofcode.year2021.day4.part1;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class CardLine {

	private final List<Integer> markedNumbers = new ArrayList<>();

	private final List<Integer> numbers;

	public CardLine(List<Integer> numbers) {
		this.numbers = numbers;
	}

	public CardLine(String numbers) {
		this.numbers = Arrays.stream(numbers.split("\\s+"))
				.filter(StringUtils::hasText)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public void checkNumber(int number) {
		if (numbers.contains(number) && !markedNumbers.contains(number)) {
			markedNumbers.add(number);
		}
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public List<Integer> getUnmarkedNumbers() {
		return numbers.stream().filter(number -> !markedNumbers.contains(number)).collect(Collectors.toList());
	}

	public boolean isBingo() {
		return markedNumbers.size() == numbers.size();
	}
}
