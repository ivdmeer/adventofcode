package nl.imm.adventofcode.year2021.day1.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivo van der Meer
 */
public class Measurements {

	private List<Measurement> calculations = new ArrayList<>();

	private List<Measurement> values = new ArrayList<>();

	public Measurements(List<Integer> list) {
		setValues(list.stream()
				.map(Measurement::new)
				.collect(Collectors.toList()));
		initCalculatedMeasurements();
	}

	public long countIncreasedMeasurements() {
		initIncreasedMeasurements();
		return calculations.stream()
				.filter(Measurement::isIncreased)
				.count();
	}

	public List<Measurement> getCalculations() {
		return calculations;
	}

	public void setCalculations(List<Measurement> calculations) {
		this.calculations = calculations;
	}

	public List<Measurement> getValues() {
		return values;
	}

	public void setValues(List<Measurement> values) {
		this.values = values;
	}

	private void initCalculatedMeasurements() {
		for (int index = 0; index < values.size(); index++) {
			if (index + 3 <= values.size()) {
				calculations.add(initCalculatedMeasurements(index));
			}
		}
	}

	private Measurement initCalculatedMeasurements(int startIndex) {
		Measurement calculatedMeasurement = new Measurement(0);
		for (int index = startIndex; index < startIndex + 3; index++) {
			Measurement measurement = values.get(index);
			calculatedMeasurement.setValue(calculatedMeasurement.getValue() + measurement.getValue());

		}
		return calculatedMeasurement;
	}

	private void initIncreasedMeasurements() {
		Measurement previousMeasurement = null;
		for (Measurement measurement : calculations) {
			if (previousMeasurement != null && measurement.getValue() > previousMeasurement.getValue()) {
				measurement.setIncreased(true);
			}

			previousMeasurement = measurement;
		}
	}
}
