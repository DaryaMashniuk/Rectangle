package by.mashnyuk.rectangle.warehouse;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Integer, RectangleParameters> parametersMap = new HashMap<>();
    private final RectangleService rectangleService = new RectangleServiceImpl();

    private Warehouse() {}

    public static synchronized Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(int rectangleId, double perimeter, double area) {
        parametersMap.put(rectangleId, new RectangleParameters(perimeter, area));
    }

    public RectangleParameters getParameters(int rectangleId) {
        return parametersMap.get(rectangleId);
    }

    public void update(Rectangle rectangle) {
        RectangleParameters parameters = parametersMap.get(rectangle.getId());
        if (parameters != null) {
            double perimeter = rectangleService.calculatePerimeter(rectangle);
            double area = rectangleService.calculateArea(rectangle);
            parameters.setPerimeter(perimeter);
            parameters.setArea(area);
        }
    }
}