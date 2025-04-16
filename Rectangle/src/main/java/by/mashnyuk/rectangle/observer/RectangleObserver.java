package by.mashnyuk.rectangle.observer;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.warehouse.Warehouse;

public class RectangleObserver {
    private static RectangleObserver instance;
    private final Warehouse warehouse = Warehouse.getInstance();
    private final RectangleService rectangleService = new RectangleServiceImpl();

    private RectangleObserver() {}

    public static RectangleObserver getInstance() {
        if (instance == null) {
            instance = new RectangleObserver();
        }
        return instance;
    }

    public void update(Rectangle rectangle) {
        double perimeter = rectangleService.calculatePerimeter(rectangle);
        double area = rectangleService.calculateArea(rectangle);
        warehouse.put(rectangle.getId(), perimeter, area);
    }
}