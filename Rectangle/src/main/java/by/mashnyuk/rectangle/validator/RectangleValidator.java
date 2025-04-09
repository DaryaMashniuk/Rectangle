package by.mashnyuk.rectangle.validator;

import by.mashnyuk.rectangle.entity.Point;

public interface RectangleValidator {
    boolean isValid(Point p1, Point p2, Point p3, Point p4);
}