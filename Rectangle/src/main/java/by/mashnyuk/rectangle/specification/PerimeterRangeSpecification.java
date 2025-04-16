package by.mashnyuk.rectangle.specification;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.warehouse.Warehouse;

public class PerimeterRangeSpecification implements Specification<Rectangle> {
    private final double minPerimeter;
    private final double maxPerimeter;
    private final RectangleService rectangleService = new RectangleServiceImpl();
    private final Warehouse warehouse = Warehouse.getInstance();

    public PerimeterRangeSpecification(double minPerimeter, double maxPerimeter) {
        this.minPerimeter = minPerimeter;
        this.maxPerimeter = maxPerimeter;
    }

    @Override
    public boolean isSatisfiedBy(Rectangle rectangle) {
        double perimeter = warehouse.getParameters(rectangle.getId()).getPerimeter();
        return perimeter >= minPerimeter && perimeter <= maxPerimeter;
    }
}