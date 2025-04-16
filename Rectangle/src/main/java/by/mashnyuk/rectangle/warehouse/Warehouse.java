package by.mashnyuk.rectangle.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Integer, RectangleParameters> parametersMap = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
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

}