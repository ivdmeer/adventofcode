package nl.imm.adventofcode.year2021.day1.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class Measurements {

	private List<Measurement> values = new ArrayList<>();

	public Measurements(List<Integer> list) {
		setValues(list.stream()
				.map(Measurement::new)
				.collect(Collectors.toList()));
	}

	public List<Measurement> getValues() {
		return values;
	}

	public void setValues(List<Measurement> values) {
		this.values = values;
	}

	public long countIncreasedMeasurements() {
		initIncreasedMeasurements();
		return values.stream()
				.filter(Measurement::isIncreased)
				.count();
	}

	private void initIncreasedMeasurements() {
		Measurement previousMeasurement = null;
		for (Measurement measurement : values) {
			if (previousMeasurement != null && measurement.getValue() > previousMeasurement.getValue()) {
				measurement.setIncreased(true);
			}

			previousMeasurement = measurement;
		}
	}
}
