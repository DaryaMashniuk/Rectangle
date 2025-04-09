package by.mashnyuk.rectangle.creator;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.entity.Point;
import java.util.Optional;

public interface RectangleFactory {
    Optional<Rectangle> createRectangle(Point point1, Point point2, Point point3, Point point4);
}