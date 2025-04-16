package by.mashnyuk.rectangle.service;

import by.mashnyuk.rectangle.entity.Rectangle;

public interface RectangleService {
    String getShapeType(Rectangle rectangle);
    double calculatePerimeter(Rectangle rectangle);
    double calculateArea(Rectangle rectangle);
    boolean isConvex(Rectangle rectangle);
    boolean isSquare(Rectangle rectangle);
    boolean isRhombus(Rectangle rectangle);
    boolean isTrapezoid(Rectangle rectangle);
}
