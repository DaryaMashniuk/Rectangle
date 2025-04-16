package by.mashnyuk.rectangle.entity;

import by.mashnyuk.rectangle.observer.RectangleObserver;

import java.util.Arrays;
import java.util.List;

public class Rectangle {
    private final int id;
    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private final RectangleObserver observer = RectangleObserver.getInstance();

    public Rectangle(int id, Point point1, Point point2, Point point3, Point point4) {
        this.id = id;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public int getId() {
        return id;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public Point getPoint4() {
        return point4;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
        observer.update(this);
    }
    public void setPoint2(Point point2) {
        this.point2 = point2;
        observer.update(this);
    }
    public void setPoint3(Point point3) {
        this.point3 = point3;
        observer.update(this);
    }
    public void setPoint4(Point point4) {
        this.point4 = point4;
        observer.update(this);
    }

    public List<Point> getPoints() {
        return Arrays.asList(point1, point2, point3, point4);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return id == rectangle.id &&
                point1.equals(rectangle.point1) &&
                point2.equals(rectangle.point2) &&
                point3.equals(rectangle.point3) &&
                point4.equals(rectangle.point4);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + point1.hashCode();
        result = 31 * result + point2.hashCode();
        result = 31 * result + point3.hashCode();
        result = 31 * result + point4.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "id=" + id +
                ", point1=" + point1 +
                ", point2=" + point2 +
                ", point3=" + point3 +
                ", point4=" + point4 +
                '}';
    }
}