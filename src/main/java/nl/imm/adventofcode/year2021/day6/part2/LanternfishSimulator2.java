package nl.imm.adventofcode.year2021.day6.part2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class LanternfishSimulator2 {

	private final String initialState;

	private final Map<Integer, Long> lanternfishMap;

	public LanternfishSimulator2(String initialState) {
		this.initialState = initialState;
		lanternfishMap = initialSimulation();
	}

	public long getFishCount() {
		return lanternfishMap.values()
				.stream().mapToLong(Long::longValue)
				.sum();
	}

	public void simulateDay(int days) {
		for (int day = 0; day < days; day++) {
			Long pregnantFishes = lanternfishMap.get(0);

			for (int index = 1; index < lanternfishMap.size(); index++) {
				lanternfishMap.put(index - 1, lanternfishMap.get(index));
			}

			lanternfishMap.put(6, lanternfishMap.get(6) + pregnantFishes);
			lanternfishMap.put(8, pregnantFishes);
		}
	}

	private Map<Integer, Long> initialSimulation() {
		Map<Integer, Long> fishMap = Arrays.asList(this.initialState.split(","))
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		for (int index = 0; index < 9; index++) {
			fishMap.putIfAbsent(index, 0L);
		}

		return fishMap;
	}

}

