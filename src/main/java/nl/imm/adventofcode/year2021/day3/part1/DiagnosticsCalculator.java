package nl.imm.adventofcode.year2021.day3.part1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class DiagnosticsCalculator {

	private final LineParser parser;

	private Map<Integer, List<Integer>> diagnosticsMap = new HashMap<>();

	public DiagnosticsCalculator(LineParser parser) {
		this.parser = parser;
	}

	public void addDiagnosticsData(List<String> linesFromFile) {
		diagnosticsMap = parser.parseLines(linesFromFile);
	}

	public int calculatePowerConsumption() {
		String calculatedGamaRate = calculatedGamaRate();
		String calculatedEpsilonRate = invertBits(calculatedGamaRate);
		return Integer.parseInt(calculatedGamaRate,2) * Integer.parseInt(calculatedEpsilonRate,2);
	}

	private int calcCommonBit(List<Integer> bits) {
		int zeroCounter = 0;
		int oneCounter = 0;

		for (Integer bit : bits) {
			if (bit == 0) {
				zeroCounter++;
			} else {
				oneCounter++;
			}
		}

		return zeroCounter > oneCounter ? 0 : 1;
	}

	private String calculatedGamaRate() {
		return diagnosticsMap.values()
				.stream()
				.map(this::calcCommonBit)
				.map(Objects::toString)
				.collect(Collectors.joining(""));
	}

	private String invertBits(String bits) {
		StringBuilder invertedBits = new StringBuilder();
		for (int index = 0; index < bits.length(); index++) {
			int bit = Integer.parseInt(bits.substring(index, index + 1));
			invertedBits.append(bit == 0 ? 1 : 0);
		}

		return invertedBits.toString();
	}
}
