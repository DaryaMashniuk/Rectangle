package by.mashnyuk.rectangle.validator.impl;

import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.validator.RectangleValidator;

public class RectangleValidatorImpl implements RectangleValidator {
    @Override
    public boolean isValid(Point p1, Point p2, Point p3, Point p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return false;
        }

        // Check if any three points are colinear
        if (areColinear(p1, p2, p3) || areColinear(p2, p3, p4) ||
                areColinear(p3, p4, p1) || areColinear(p4, p1, p2)) {
            return false;
        }

        return isConvexQuadrilateral(p1, p2, p3, p4);
    }

    private boolean areColinear(Point a, Point b, Point c) {
        double area = (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());
        return Math.abs(area) < 1e-10;
    }

    private boolean isConvexQuadrilateral(Point p1, Point p2, Point p3, Point p4) {
        double cross1 = crossProduct(p1, p2, p3);
        double cross2 = crossProduct(p2, p3, p4);
        double cross3 = crossProduct(p3, p4, p1);
        double cross4 = crossProduct(p4, p1, p2);

        boolean hasPositive = false;
        boolean hasNegative = false;

        if (cross1 > 0) hasPositive = true;
        if (cross1 < 0) hasNegative = true;
        if (cross2 > 0) hasPositive = true;
        if (cross2 < 0) hasNegative = true;
        if (cross3 > 0) hasPositive = true;
        if (cross3 < 0) hasNegative = true;
        if (cross4 > 0) hasPositive = true;
        if (cross4 < 0) hasNegative = true;

        return !(hasPositive && hasNegative);
    }

    private double crossProduct(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());
    }
}