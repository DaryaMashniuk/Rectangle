// PointValidatorImpl.java
package by.mashnyuk.rectangle.validator.impl;

import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.validator.PointValidator;

public class PointValidatorImpl implements PointValidator {
    private static final double MAX_COORDINATE_VALUE = 1_000_000;
    private static final double MIN_COORDINATE_VALUE = -1_000_000;

    @Override
    public boolean isValid(Point point) {
        if (point == null) {
            return false;
        }

        double x = point.getX();
        double y = point.getY();

        return !Double.isNaN(x) && !Double.isInfinite(x) &&
                !Double.isNaN(y) && !Double.isInfinite(y) &&
                x >= MIN_COORDINATE_VALUE && x <= MAX_COORDINATE_VALUE &&
                y >= MIN_COORDINATE_VALUE && y <= MAX_COORDINATE_VALUE;
    }
}