package by.mashnyuk.rectangle.parser;

import by.mashnyuk.rectangle.creator.RectangleFactory;
import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;

import java.util.List;
import java.util.Optional;

public class RectangleParser {
    private final PointParser pointParser;
    private final RectangleFactory rectangleFactory;

    public RectangleParser(PointParser pointParser, RectangleFactory rectangleFactory) {
        this.pointParser = pointParser;
        this.rectangleFactory = rectangleFactory;
    }

    public Optional<Rectangle> parse(String line) throws ShapeException {
        List<Point> points = pointParser.parsePoints(line);
        if (points.size() != 4) {
            throw new ShapeException("Rectangle requires 4 points");
        }

        return rectangleFactory.createRectangle(
                points.get(0),
                points.get(1),
                points.get(2),
                points.get(3)
        );
    }
}
