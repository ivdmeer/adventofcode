package nl.imm.adventofcode.year2021.day8.part1;

import java.util.Arrays;

/**
 * @author Ivo van der Meer
 */
public enum Digit {

	ZERO("abcdeg", 0),
	ONE("ab", 1),
	TWO("acdfg", 2),
	THREE("abcdf", 3),
	FOUR("abef", 4),
	FIVE("bcdef", 5),
	SIX("bcdefg", 6),
	SEVEN("abd", 7),
	EIGHT("abcdefg", 8),
	NINE("abcdef", 9);

	private final Integer length;

	private final int number;

	private final String pattern;

	Digit(String pattern, int number) {
		this.pattern = pattern;
		this.number = number;
		this.length = pattern.length();
	}

	public static Digit fromString(String digitString) {
		String sortedDigit = digitString.chars()
				.sorted()
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return Arrays.stream(Digit.values())
				.filter(digit -> digit.getPattern().equals(sortedDigit))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public static boolean isParsable(String digitString) {
		try {
			fromString(digitString);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer getLength() {
		return length;
	}

	public int getNumber() {
		return number;
	}

	public String getPattern() {
		return pattern;
	}
}
