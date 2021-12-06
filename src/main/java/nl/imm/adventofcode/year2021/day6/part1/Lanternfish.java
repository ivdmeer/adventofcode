package nl.imm.adventofcode.year2021.day6.part1;

/**
 * @author Ivo van der Meer
 */
public class Lanternfish {

	private int timer;

	public Lanternfish() {
		this.timer = 8;
	}

	public Lanternfish(int timer) {
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}

	public void updateTimer() {
		if (timer == 0) {
			timer = 6;
			return;
		}
		timer--;
	}
}
