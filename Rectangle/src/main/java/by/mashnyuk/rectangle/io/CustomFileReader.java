package by.mashnyuk.rectangle.io;

import by.mashnyuk.rectangle.creator.RectangleFactory;
import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.parser.PointParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class CustomFileReader {
    private static final Logger logger = LogManager.getLogger();

    private final RectangleFactory rectangleFactory;
    private final PointParser pointParser;

    public CustomFileReader(RectangleFactory rectangleFactory, PointParser pointParser) {
        this.rectangleFactory = rectangleFactory;
        this.pointParser = pointParser;
    }

    public List<Rectangle> readRectangles(String filePath) throws ShapeException {
        try {
            return Files.lines(Path.of(filePath))
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseRectangle)
                    .flatMap(Optional::stream)
                    .toList();
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
            throw new ShapeException("Failed to read file: " + filePath, e);
        }
    }

    private Optional<Rectangle> parseRectangle(String line) {
        try {
            List<Point> points = pointParser.parsePoints(line);
            return rectangleFactory.createRectangle(
                    points.get(0), points.get(1),
                    points.get(2), points.get(3));
        } catch (ShapeException e) {
            logger.warn("Invalid line in file: {}", line, e);
            return Optional.empty();
        }
    }
}