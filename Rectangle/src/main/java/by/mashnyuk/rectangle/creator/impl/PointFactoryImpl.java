package by.mashnyuk.rectangle.creator.impl;

import by.mashnyuk.rectangle.creator.PointFactory;
import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.validator.PointValidator;
import by.mashnyuk.rectangle.validator.impl.PointValidatorImpl;

import java.util.Optional;

public class PointFactoryImpl implements PointFactory {
    private final PointValidator pointValidator;

    public PointFactoryImpl() {
        this.pointValidator = new PointValidatorImpl();
    }

    public Optional<Point> createPoint(double x, double y) {
        Point point = new Point(x, y);
        return pointValidator.isValid(point) ? Optional.of(point) : Optional.empty();
    }
}
