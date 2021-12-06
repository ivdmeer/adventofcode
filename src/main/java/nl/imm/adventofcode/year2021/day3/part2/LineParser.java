package nl.imm.adventofcode.year2021.day3.part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivo van der Meer
 */
public class LineParser {
	Map<Integer, List<Integer>> map = new HashMap<>();

	public Map<Integer, List<Integer>> parseLines(List<String> lines) {
		lines.forEach(this::parseLine);
		return map;
	}

	private void parseLine(String line) {
		for (int index = 0; index < line.length(); index++) {
			List<Integer> diagLines = map.containsKey(index) ? map.get(index) : new ArrayList<>();
			diagLines.add(Integer.parseInt(line.substring(index, index + 1)));
			map.put(index, diagLines);
		}
	}
}
