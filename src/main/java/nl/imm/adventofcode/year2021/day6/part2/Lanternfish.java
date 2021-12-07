package nl.imm.adventofcode.year2021.day6.part2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
public class Lanternfish {

	private final List<Lanternfish> children = new ArrayList<>();

	private int timer;

	public Lanternfish() {
		this.timer = 8;
	}

	public Lanternfish(int timer) {
		this.timer = timer;
	}

	public void addChild(Lanternfish child) {
		children.add(child);
	}

	public List<Lanternfish> getChildren() {
		return new ArrayList<>(children);
	}

	public int getTimer() {
		return timer;
	}

	public long numberOfChildren() {
		return children.size();
	}

	public void updateTimer() {
		if (timer == 0) {
			timer = 6;
			return;
		}
		timer--;
	}

}
