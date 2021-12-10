package nl.imm.adventofcode.year2021.day3.part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class DiagnosticsCalculator {

	private final Map<Integer, List<Integer>> diagnosticsMap;

	private final List<String> linesFromFile;

	private final LineParser parser;

	public DiagnosticsCalculator(LineParser parser, List<String> linesFromFile) {
		this.linesFromFile = Collections.unmodifiableList(linesFromFile);
		this.parser = parser;
		diagnosticsMap = this.parser.parseLines(this.linesFromFile);
	}

	public int calculateCO2ScrubberRating() {
		String cO2ScrubberRate = calculateMostCommonBits("0");
		return Integer.parseInt(cO2ScrubberRate, 2);
	}

	public int calculateLifeSupportRating() {
		return calculateOxygenGeneratorRating() * calculateCO2ScrubberRating();
	}

	public int calculateOxygenGeneratorRating() {
		String oxygenGeneratorRate = calculateMostCommonBits("1");
		return Integer.parseInt(oxygenGeneratorRate, 2);
	}

	public int calculatePowerConsumption() {
		String calculatedGamaRate = calculatedGamaRate();
		String calculatedEpsilonRate = invertBits(calculatedGamaRate);
		return Integer.parseInt(calculatedGamaRate, 2) * Integer.parseInt(calculatedEpsilonRate, 2);
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

		if (zeroCounter == oneCounter) {
			throw new IllegalStateException("Error both counters are the same");
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

	/**
	 * Calculate the most common bit at a column in a list and remove all values that do not share
	 * that common bit.
	 *
	 */
	private String calculateMostCommonBits(String bitCriteria) {
		String bestMatch = null;
		List<String> commonBitList = linesFromFile;
		String ZERO_BIT = "0";
		String ONE_BIT = "1";

		while (bestMatch == null) {
			for (int index = 0; index < calculatedGamaRate().length(); index++) {
				Map<Integer, List<String>> commonBitMap = new HashMap<>();
				commonBitMap.put(0, new ArrayList<>());
				commonBitMap.put(1, new ArrayList<>());
				for (String line : commonBitList) {
					String bit = line.substring(index, index + 1);
					if (bit.equals(ZERO_BIT)) {
						commonBitMap.get(Integer.parseInt(ZERO_BIT)).add(line);
					} else {
						commonBitMap.get(Integer.parseInt(ONE_BIT)).add(line);
					}
				}

				if (commonBitMap.get(Integer.parseInt(ZERO_BIT)).size() > commonBitMap.get(Integer.parseInt(ONE_BIT)).size()) {

					if (ZERO_BIT.equals(bitCriteria)) {
						// keep lowest
						commonBitList = commonBitMap.get(Integer.parseInt(ONE_BIT));
					} else {
						// keep highest
						commonBitList = commonBitMap.get(Integer.parseInt(ZERO_BIT));
					}

				} else if (commonBitMap.get(Integer.parseInt(ONE_BIT)).size() > commonBitMap.get(Integer.parseInt(ZERO_BIT)).size()) {

					if (ZERO_BIT.equals(bitCriteria)) {
						// keep lowest
						commonBitList = commonBitMap.get(Integer.parseInt(ZERO_BIT));
					} else {
						// keep highest
						commonBitList = commonBitMap.get(Integer.parseInt(ONE_BIT));
					}
				} else {
					// both bits are of equal count, now check the bitCriteria
					if (ZERO_BIT.equals(bitCriteria)) {
						commonBitList = commonBitMap.get(Integer.parseInt(ZERO_BIT));
					} else {
						commonBitList = commonBitMap.get(Integer.parseInt(ONE_BIT));
					}
				}
				if (commonBitList.size() == 1) {
					bestMatch = commonBitList.iterator().next();
				}
			}
		}
		return bestMatch;
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
