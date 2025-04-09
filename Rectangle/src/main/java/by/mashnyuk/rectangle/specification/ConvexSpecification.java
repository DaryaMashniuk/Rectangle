package by.mashnyuk.rectangle.specification;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;

public class ConvexSpecification implements Specification<Rectangle> {
    private final RectangleService rectangleService = new RectangleServiceImpl();

    @Override
    public boolean isSatisfiedBy(Rectangle rectangle) {
        return rectangleService.isConvex(rectangle);
    }
}