package nl.imm.adventofcode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Ivo van der Meer
 */
public class FileHelper {

	private FileHelper() {
		throw new IllegalStateException("This is a static helper class!");
	}

	public static List<String> readAllLinesFromFile(Path pathToFile) {
		try {
			return Files.readAllLines(pathToFile, Charset.defaultCharset());
		} catch (IOException e) {
			throw new RuntimeException("Unable to read your file: " + pathToFile);
		}
	}

}
