package nl.imm.adventofcode.year2022.day5.part1;

import nl.imm.adventofcode.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class SupplyStacksTest {

	// https://adventofcode.com/2022/day/5

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2022/day5/exercise-data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2022/day5/example-data.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);
		List<String> rearrangements = linesFromFile.stream()
				.filter(line -> line.startsWith("move"))
				.toList();
		//Map<Integer, Stack<String>> crateStackMap = createCrateStackMapTestInput(linesFromFile);
		Map<Integer, Stack<String>> crateStackMap = createCrateStackMap(linesFromFile, 3);

		// when
		String topCrates = rearrangeCrates(rearrangements, crateStackMap);

		// then
		assertEquals("CMZ", topCrates);
	}

	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);
		List<String> rearrangements = linesFromFile.stream()
				.filter(line -> line.startsWith("move"))
				.toList();
		Map<Integer, Stack<String>> crateStackMap = createCrateStackMap(linesFromFile, 9);

		// when
		String topCrates = rearrangeCrates(rearrangements, crateStackMap);


		// then
		assertEquals("VWLCWGSDQ", topCrates);
	}

	private Map<Integer, Stack<String>> createCrateStackMap(List<String> lines, int amountOfStacks) {
		Map<Integer, Stack<String>> cratesMap = new HashMap<>();
		for (String line : lines) {
			if (line.contains("[")) {
				int startIndex = 1;
				int untilNextIndex = 4;
				for (int stack = 1; stack <= amountOfStacks; stack++) {
					cratesMap.computeIfAbsent(stack, k -> new Stack<>());
					try {
						String crate = line.substring(startIndex, startIndex + 1);
						if (StringUtils.isNotBlank(crate)) {
							cratesMap.get(stack).push(crate);
						}
						startIndex += untilNextIndex;
					} catch (StringIndexOutOfBoundsException e) {
						//
					}
				}
			}
		}

		for (Map.Entry<Integer, Stack<String>> entry : cratesMap.entrySet()) {
			Stack<String> stack = new Stack<>();

			ListIterator<String> platesListIterator = entry.getValue().listIterator(entry.getValue().size());
			while (platesListIterator.hasPrevious()) {
				String crate = platesListIterator.previous();
				stack.push(crate);
			}

			cratesMap.put(entry.getKey(), stack);
		}


		return cratesMap;
	}
	private String rearrangeCrates(List<String> rearrangements, Map<Integer, Stack<String>> crateStackMap) {
		for (String move : rearrangements) {
			String[] data = move.split(" ");
			int amountOfCrates = Integer.parseInt(data[1]);
			int indexSrc = Integer.parseInt(data[3]);
			int indexDst = Integer.parseInt(data[5]);
			for (int count = 0; count < amountOfCrates; count++) {
				String crate = crateStackMap.get(indexSrc).pop();
				crateStackMap.get(indexDst).push(crate);
			}
		}

		return crateStackMap.values()
				.stream()
				.map(Stack::pop)
				.collect(Collectors.joining(""));
	}


}
