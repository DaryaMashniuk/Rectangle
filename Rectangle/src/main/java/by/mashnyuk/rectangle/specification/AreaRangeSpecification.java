package by.mashnyuk.rectangle.specification;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.warehouse.Warehouse;

public class AreaRangeSpecification implements Specification<Rectangle> {
    private final double minArea;
    private final double maxArea;
    private final RectangleService rectangleService = new RectangleServiceImpl();
    private final Warehouse warehouse = Warehouse.getInstance();

    public AreaRangeSpecification(double minArea, double maxArea) {
        this.minArea = minArea;
        this.maxArea = maxArea;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle rectangle) {
        double area = warehouse.getParameters(rectangle.getId()).getArea();
        return area >= minArea && area <= maxArea;
    }
}