package nl.imm.adventofcode.year2021.day2.part2;

/**
 * @author Ivo van der Meer
 */
public class Submarine {

	private int aim = 0;

	private int depthPosition = 0;

	private int horizontalPosition = 0;

	public int calculatePosition() {
		return horizontalPosition * depthPosition;
	}

	public void decreaseAim(int amount) {
		this.aim -= amount;
	}

	public void goDown(int amount) {
		increaseAim(amount);
	}

	public void goForward(int amount) {
		this.horizontalPosition += amount;
		this.depthPosition = aim * amount + this.depthPosition;
	}

	public void goUp(int amount) {
		decreaseAim(amount);
	}

	public void increaseAim(int amount) {
		this.aim += amount;
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
