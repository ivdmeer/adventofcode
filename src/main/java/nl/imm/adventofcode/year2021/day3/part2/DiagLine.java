package nl.imm.adventofcode.year2021.day3.part2;

/**
 * @author Ivo van der Meer
 */
public class DiagLine {

	private final int bit;

	private final String line;

	public DiagLine(int bit, String line) {
		this.bit = bit;
		this.line = line;
	}

	public int getBit() {
		return bit;
	}

	public String getLine() {
		return line;
	}

}
