package nl.imm.adventofcode.year2021.day8.part1;

/**
 * @author Ivo van der Meer
 */
public enum Digit {

	ONE(2),
	FOUR(4),
	SEVEN(3),
	EIGHT(7);

	private final Integer length;

	Digit(Integer length) {
		this.length = length;
	}

	public Integer getLength() {
		return length;
	}
}
