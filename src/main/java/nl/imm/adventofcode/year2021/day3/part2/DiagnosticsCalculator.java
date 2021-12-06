package nl.imm.adventofcode.year2021.day3.part2;

import java.util.Collections;
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
		String cO2ScrubberRate = findTheBestMatchingValue(invertBits(calculatedGamaRate()), "0");
		return Integer.parseInt(cO2ScrubberRate, 2);
	}

	public int calculateLifeSupportRating() {
		return calculateOxygenGeneratorRating() * calculateCO2ScrubberRating();
	}

	public int calculateOxygenGeneratorRating() {
		String gamaRate = calculatedGamaRate();
		String oxygenGeneratorRate = findTheBestMatchingValue(gamaRate, "1");
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

	private String findTheBestMatchingValue(String rate, String suffix) {
		String bestMatch = null;
		List<String> startData = linesFromFile;
		for (int index = 0; index < rate.length(); index++) {
			String searchString = rate.substring(0, index + 1);
			List<String> results = startData.stream()
					.filter(item -> item.startsWith(searchString))
					.collect(Collectors.toList());

			startData = results;

			if (results.size() > 2) {
				continue;
			}

			if (results.size() == 2) {
				int secondIndex = index + 2;
				while (bestMatch == null) {
					for (String item : startData) {
						String substring = item.substring(0, secondIndex);
						if (substring.endsWith(suffix)) {
							bestMatch = item;
							break;
						}
					}
					secondIndex++;
				}

				break;
			}

			bestMatch = results.iterator().next();
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
