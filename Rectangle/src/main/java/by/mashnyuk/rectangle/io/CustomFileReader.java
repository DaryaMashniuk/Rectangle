package by.mashnyuk.rectangle.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger();

    public List<String> readLines(String filePath) throws IOException {
        return Files.lines(Path.of(filePath))
                .filter(line -> !line.trim().isEmpty())
                .toList();
    }
}
