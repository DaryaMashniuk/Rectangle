package by.mashnyuk.rectangle.creator.impl;

import by.mashnyuk.rectangle.creator.RectangleFactory;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.validator.RectangleValidator;
import java.util.Optional;

public class RectangleFactoryImpl implements RectangleFactory {
    private static int idCounter = 1;
    private final RectangleValidator validator;

    public RectangleFactoryImpl(RectangleValidator validator) {
        this.validator = validator;
    }

    @Override
    public Optional<Rectangle> createRectangle(Point point1, Point point2, Point point3, Point point4) {
        return validator.isValid(point1, point2, point3, point4) ?
                Optional.of(new Rectangle(idCounter++, point1, point2, point3, point4)) :
                Optional.empty();
    }
}