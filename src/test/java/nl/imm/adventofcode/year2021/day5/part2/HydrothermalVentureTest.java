package nl.imm.adventofcode.year2021.day5.part2;

import nl.imm.adventofcode.year2021.FileHelper;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ivo van der Meer
 */
class HydrothermalVentureTest {

	// https://adventofcode.com/2021/day/5#part2

	private final Path exerciseDataFile = Paths.get(
			"src/main/resources/year2021/day5/data.txt");

	private final Path testDataFile = Paths.get(
			"src/main/resources/year2021/day5/example-data.txt");


	@Test
	void exampleTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(testDataFile);

		HydrothermalVentureGrid hydrothermalVentureGrid = new HydrothermalVentureGrid(stringList);

		String printedGrid = hydrothermalVentureGrid.printGrid();

		assertEquals(12, hydrothermalVentureGrid.calculateOverlappingAmount());
		assertEquals(exampleGrid(), printedGrid);
		System.out.println(printedGrid);
	}

	@Test
	void exerciseTest() {
		List<String> stringList = FileHelper.readAllLinesFromFile(exerciseDataFile);
		HydrothermalVentureGrid hydrothermalVentureGrid = new HydrothermalVentureGrid(stringList);

		assertEquals(21104, hydrothermalVentureGrid.calculateOverlappingAmount());
	}

	private String exampleGrid() {
		return "1.1....11.\n" +
				".111...2..\n" +
				"..2.1.111.\n" +
				"...1.2.2..\n" +
				".112313211\n" +
				"...1.2....\n" +
				"..1...1...\n" +
				".1.....1..\n" +
				"1.......1.\n" +
				"222111....\n";
	}


}
