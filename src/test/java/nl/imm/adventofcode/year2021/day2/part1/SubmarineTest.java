package nl.imm.adventofcode.year2021.day2.part1;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class SubmarineTest {

	// https://adventofcode.com/2021/day/2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day2/data-part1.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day2/example-data-part1.txt");

	@DisplayName("Testing with the example data given for this exercise")
	@Test
	void exampleTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(testDataFile);

		Submarine submarine = new Submarine();
		assertEquals(0, submarine.calculatePosition());

		// when
		linesFromFile.forEach(submarine::moveToDirection);

		// then
		assertEquals(150, submarine.calculatePosition());
	}


	@Test
	void exerciseTest() {
		// given
		List<String> linesFromFile = FileHelper.readAllLinesFromFile(exerciseDataFile);

		Submarine submarine = new Submarine();
		assertEquals(0, submarine.calculatePosition());

		// when
		linesFromFile.forEach(submarine::moveToDirection);

		// then
		System.out.println("The calculated position is: " + submarine.calculatePosition());
		assertEquals(2070300, submarine.calculatePosition());
	}
}
