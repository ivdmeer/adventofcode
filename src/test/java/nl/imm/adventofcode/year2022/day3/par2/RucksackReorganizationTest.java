package nl.imm.adventofcode.year2022.day3.par2;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class RucksackReorganizationTest {

	// https://adventofcode.com/2022/day/3#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day3/exercise-data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day3/example-data.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);


		List<Alphabet> items = handleItems(linesFromFile);

		// when
		int totalScore = calculateScore(items);

		// then
		assertEquals(70, totalScore);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);

		List<Alphabet> items = handleItems(linesFromFile);

		// when
		int totalScore = calculateScore(items);

		// then
		assertEquals(2881, totalScore);
	}

	private int calculateScore(List<Alphabet> items) {
		return items.stream()
				.map(Alphabet::getValue)
				.mapToInt(Integer::intValue)
				.sum();
	}

	private Alphabet getCommonItem(List<List<String>> strings) {

		List<String> list1 = strings.get(0);
		List<String> list2 = strings.get(1);
		List<String> list3 = strings.get(2);

		Set<String> duplicates = list1.stream()
				.filter(k -> list2.stream().anyMatch(p -> Arrays.equals(p.toCharArray(), k.toCharArray()))
						&& list3.stream().anyMatch(p -> Arrays.equals(p.toCharArray(), k.toCharArray()))
				)
				.collect(Collectors.toSet());

		if (duplicates.size() != 1) {
			throw new IllegalStateException("Found too many duplicates!");
		}

		return Alphabet.valueOf(duplicates.iterator().next());
	}

	private List<Alphabet> handleItems(List<String> lines) {

		int index = 0;
		int count = 0;
		Map<Integer, List<List<String>>> groupMap = new HashMap<>();
		for (String line : lines) {
			count++;
			groupMap.computeIfAbsent(index, k -> new ArrayList<>());
			groupMap.get(index).add(Arrays.asList(line.split("")));
			if (count == 3) {
				index++;
				count = 0;
			}
		}

		return groupMap.values()
				.stream()
				.map(this::getCommonItem)
				.collect(Collectors.toList());

	}

	enum Alphabet {
		a(1), b(2), c(3), d(4), e(5), f(6), g(7), h(8), i(9),
		j(10), k(11), l(12), m(13), n(14), o(15), p(16), q(17),
		r(18), s(19), t(20), u(21), v(22), w(23), x(24), y(25),
		z(26),
		A(27), B(28), C(29), D(30), E(31), F(32), G(33), H(34),
		I(35), J(36), K(37), L(38), M(39), N(40), O(41), P(42),
		Q(43), R(44), S(45), T(46), U(47), V(48), W(49), X(50),
		Y(51), Z(52);

		private final int value;

		Alphabet(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}


}
