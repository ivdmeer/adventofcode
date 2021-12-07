package nl.imm.adventofcode.year2021.day6.part2;

import java.util.stream.Stream;

/**
 * @author Ivo van der Meer
 */
public class Helper {
	private Helper() {
	}

	public static Stream<Lanternfish> flatten(Lanternfish lanternfish) {
		return Stream.concat(
				Stream.of(lanternfish),
				lanternfish.getChildren().stream().flatMap(Helper::flatten));
	}
}
