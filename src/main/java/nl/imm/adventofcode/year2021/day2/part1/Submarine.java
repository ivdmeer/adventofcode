package nl.imm.adventofcode.year2021.day2.part1;

/**
 * @author Ivo van der Meer
 */
public class Submarine {

	private int depthPosition = 0;

	private int horizontalPosition = 0;

	public int calculatePosition() {
		return horizontalPosition * depthPosition;
	}

	public void goDown(int amount) {
		this.depthPosition += amount;
	}

	public void goForward(int amount) {
		this.horizontalPosition += amount;
	}

	public void goUp(int amount) {
		this.depthPosition -= amount;
	}

	public void moveToDirection(String position) {
		int amount = Integer.parseInt(position.split("\\s")[1]);
		if (position.startsWith("forward")) {
			goForward(amount);
		} else if (position.startsWith("down")) {
			goDown(amount);
		} else if (position.startsWith("up")) {
			goUp(amount);
		} else {
			throw new IllegalArgumentException("Unknown position");
		}
	}
}
