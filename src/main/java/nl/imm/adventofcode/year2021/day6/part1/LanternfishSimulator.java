package nl.imm.adventofcode.year2021.day6.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class LanternfishSimulator {

	private final String initialState;

	private List<Lanternfish> lanternfishList;

	public LanternfishSimulator(String initialState) {
		this.initialState = initialState;
		initialSimulation();
	}

	public long getFishCount() {
		return lanternfishList.size();
	}

	public String simulateDay(int days) {
		handleSimulation(days);
		return lanternfishList
				.stream()
				.map(Lanternfish::getTimer)
				.map(Objects::toString)
				.collect(Collectors.joining(","));
	}

	private void handleSimulation(int days) {

		for (int day = 0; day < days; day++) {
			List<Lanternfish> extraFishes = new ArrayList<>();
			for (Lanternfish lanternFish : lanternfishList) {
				if (lanternFish.getTimer() == 0) {
					extraFishes.add(new Lanternfish());
				}
				lanternFish.updateTimer();
			}
			lanternfishList.addAll(extraFishes);
		}
	}

	private void initialSimulation() {
		lanternfishList = Arrays.asList(this.initialState.split(","))
				.stream()
				.map(Integer::parseInt)
				.map(Lanternfish::new)
				.collect(Collectors.toList());
	}

}

