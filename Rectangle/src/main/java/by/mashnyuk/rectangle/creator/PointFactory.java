package by.mashnyuk.rectangle.creator;

import by.mashnyuk.rectangle.entity.Point;

import java.util.Optional;

public interface PointFactory {
    Optional<Point> createPoint(double x, double y);
}
