package by.mashnyuk.rectangle.warehouse;

public class RectangleParameters {
    private double perimeter;
    private double area;

    public RectangleParameters(double perimeter, double area) {
        this.perimeter = perimeter;
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "RectangleParameters{" +
                "perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }
}