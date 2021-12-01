package nl.imm.adventofcode.year2021.day1.part2;

/**
 * @author Ivo van der Meer
 */
public class Measurement {

	private boolean increased;

	private int value;

	public Measurement(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isIncreased() {
		return increased;
	}

	public void setIncreased(boolean increased) {
		this.increased = increased;
	}
}
