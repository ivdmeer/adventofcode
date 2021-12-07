package nl.imm.adventofcode.year2021.day6.part2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This simulator doesn't work when the amount of days is getting to high for example 200+
 * It will consume a lot of memory and there for is not efficient.
 *
 * @author Ivo van der Meer
 */
public class LanternfishSimulator {

	private final String initialState;

	private final List<Lanternfish> lanternfishList;

	public LanternfishSimulator(String initialState) {
		this.initialState = initialState;
		lanternfishList = initialSimulation();
	}

	public long getFishCount() {
		return lanternfishList.stream()
				.flatMap(Helper::flatten)
				.map(Lanternfish::numberOfChildren)
				.count();
	}

	public void simulateDay(int days) {
		handleSimulation(days);
	}

	private void handleFish(Lanternfish lanternFish) {

		handleFishes(lanternFish.getChildren());

		if (lanternFish.getTimer() == 0) {
			lanternFish.addChild(new Lanternfish());
		}
		lanternFish.updateTimer();
	}

	private void handleFishes(List<Lanternfish> lanternfishList) {
		lanternfishList.forEach(this::handleFish);
	}

	private void handleSimulation(int days) {
		if (days > 150) {
			throw new IllegalArgumentException("Number of days is too high!");
		}
		for (int day = 0; day < days; day++) {
			System.out.println(LocalDateTime.now() +" Working on day: " + day);
			handleFishes(lanternfishList);
		}
	}

	private List<Lanternfish> initialSimulation() {
		return Arrays.asList(this.initialState.split(","))
				.stream()
				.map(Integer::parseInt)
				.map(Lanternfish::new)
				.collect(Collectors.toList());
	}

}

