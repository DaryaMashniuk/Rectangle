package by.mashnyuk.rectangle.service.impl;

import by.mashnyuk.rectangle.entity.Point;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;

public class RectangleServiceImpl implements RectangleService {
    private static final double EPSILON = 0.0001;

    @Override
    public double calculatePerimeter(Rectangle rectangle) {
        Point p1 = rectangle.getPoint1();
        Point p2 = rectangle.getPoint2();
        Point p3 = rectangle.getPoint3();
        Point p4 = rectangle.getPoint4();

        return calculateDistance(p1, p2) +
                calculateDistance(p2, p3) +
                calculateDistance(p3, p4) +
                calculateDistance(p4, p1);
    }

    @Override
    public double calculateArea(Rectangle rectangle) {
        Point[] points = {
                rectangle.getPoint1(),
                rectangle.getPoint2(),
                rectangle.getPoint3(),
                rectangle.getPoint4()
        };

        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < points.length; i++) {
            Point current = points[i];
            Point next = points[(i + 1) % points.length];
            sum1 += current.getX() * next.getY();
            sum2 += current.getY() * next.getX();
        }

        return 0.5 * Math.abs(sum1 - sum2);
    }

    @Override
    public boolean isConvex(Rectangle rectangle) {
        Point[] points = rectangle.getPoints().toArray(new Point[0]);
        int n = points.length;
        if (n < 4) return true;

        int sign = 0;
        for (int i = 0; i < n; i++) {
            double crossProduct = calculateCrossProduct(
                    points[i],
                    points[(i + 1) % n],
                    points[(i + 2) % n]
            );

            if (crossProduct < -EPSILON) {
                if (sign > 0) return false;
                sign = -1;
            } else if (crossProduct > EPSILON) {
                if (sign < 0) return false;
                sign = 1;
            }
        }
        return true;
    }

    @Override
    public boolean isSquare(Rectangle rectangle) {
        if (!isConvex(rectangle)) {
            return false;
        }

        Point[] points = rectangle.getPoints().toArray(new Point[0]);
        double[] distances = new double[4];

        for (int i = 0; i < 4; i++) {
            distances[i] = calculateDistance(points[i], points[(i + 1) % 4]);
        }

        double diagonal1 = calculateDistance(points[0], points[2]);
        double diagonal2 = calculateDistance(points[1], points[3]);

        return Math.abs(distances[0] - distances[1]) < EPSILON &&
                Math.abs(distances[1] - distances[2]) < EPSILON &&
                Math.abs(distances[2] - distances[3]) < EPSILON &&
                Math.abs(diagonal1 - diagonal2) < EPSILON;
    }

    @Override
    public boolean isRhombus(Rectangle rectangle) {
        if (!isConvex(rectangle)) {
            return false;
        }

        Point[] points = rectangle.getPoints().toArray(new Point[0]);
        double[] distances = new double[4];

        for (int i = 0; i < 4; i++) {
            distances[i] = calculateDistance(points[i], points[(i + 1) % 4]);
        }

        double diagonal1 = calculateDistance(points[0], points[2]);
        double diagonal2 = calculateDistance(points[1], points[3]);

        return Math.abs(distances[0] - distances[1]) < EPSILON &&
                Math.abs(distances[1] - distances[2]) < EPSILON &&
                Math.abs(distances[2] - distances[3]) < EPSILON &&
                Math.abs(diagonal1 - diagonal2) > EPSILON;
    }

    @Override
    public boolean isTrapezoid(Rectangle rectangle) {
        if (!isConvex(rectangle)) {
            return false;
        }

        Point[] points = rectangle.getPoints().toArray(new Point[0]);
        boolean parallel1 = areLinesParallel(points[0], points[1], points[2], points[3]);
        boolean parallel2 = areLinesParallel(points[1], points[2], points[3], points[0]);

        return parallel1 || parallel2;
    }

    private double calculateDistance(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double calculateCrossProduct(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - a.getY()) -
                (b.getY() - a.getY()) * (c.getX() - a.getX());
    }

    private boolean areLinesParallel(Point p1, Point p2, Point p3, Point p4) {
        double slope1 = calculateSlope(p1, p2);
        double slope2 = calculateSlope(p3, p4);

        if (Double.isInfinite(slope1)) {
            return Double.isInfinite(slope2);
        }

        return Math.abs(slope1 - slope2) < EPSILON;
    }

    private double calculateSlope(Point p1, Point p2) {
        double dx = p2.getX() - p1.getX();
        if (Math.abs(dx) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }
        return (p2.getY() - p1.getY()) / dx;
    }
}