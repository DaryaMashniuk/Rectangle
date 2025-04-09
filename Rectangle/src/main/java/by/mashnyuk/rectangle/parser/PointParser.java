package by.mashnyuk.rectangle.parser;

import by.mashnyuk.rectangle.creator.PointFactory;
import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.exception.ShapeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointParser {
    private static final String POINT_DELIMITER = ";";
    private static final String COORDINATE_DELIMITER = ",";
    private static final int COORDINATES_COUNT = 2;
    private static final int REQUIRED_POINTS = 4;

    private final PointFactory pointFactory;

    public PointParser(PointFactory pointFactory) {
        this.pointFactory = pointFactory;
    }

    public List<Point> parsePoints(String line) throws ShapeException {
        List<Point> points = new ArrayList<>();
        String[] pointStrings = line.split(POINT_DELIMITER);

        if (pointStrings.length != REQUIRED_POINTS) {
            throw new ShapeException("Line must contain exactly 4 points separated by ';'");
        }

        for (String pointString : pointStrings) {
            Optional<Point> point = parsePoint(pointString.trim());
            point.ifPresent(points::add);
        }

        return points;
    }

    private Optional<Point> parsePoint(String pointString) throws ShapeException {
        String[] coordinates = pointString.split(COORDINATE_DELIMITER);

        if (coordinates.length != COORDINATES_COUNT) {
            throw new ShapeException("Point must contain exactly 2 coordinates separated by ','");
        }

        try {
            double x = Double.parseDouble(coordinates[0].trim());
            double y = Double.parseDouble(coordinates[1].trim());
            return pointFactory.createPoint(x, y);
        } catch (NumberFormatException e) {
            throw new ShapeException("Invalid coordinate format: " + pointString, e);
        }
    }
}