package by.mashnyuk.rectangle.repository;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.specification.Specification;
import by.mashnyuk.rectangle.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RectangleRepository {
    private static RectangleRepository instance;
    private final List<Rectangle> rectangles = new ArrayList<>();

    private RectangleRepository() {}

    public static RectangleRepository getInstance() {
        if (instance == null) {
            instance = new RectangleRepository();
        }
        return instance;
    }

    public void add(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    // Методы запросов
    public List<Rectangle> query(Specification<Rectangle> specification) {
        List<Rectangle> result = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            if (specification.isSatisfiedBy(rectangle)) {
                result.add(rectangle);
            }
        }
        return result;
    }

    // Методы сортировки
    public List<Rectangle> sortById() {
        return sort(Comparator.comparingInt(Rectangle::getId));
    }

    public List<Rectangle> sortByPerimeter() {
        return sort(Comparator.comparingDouble(r ->
                Warehouse.getInstance().getParameters(r.getId()).getPerimeter()));
    }

    public List<Rectangle> sortByArea() {
        return sort(Comparator.comparingDouble(r ->
                Warehouse.getInstance().getParameters(r.getId()).getArea()));
    }

    public List<Rectangle> sortByShapeType(RectangleService rectangleService) {
        return sort(Comparator.comparing(rectangleService::getShapeType));
    }

    private List<Rectangle> sort(Comparator<Rectangle> comparator) {
        List<Rectangle> sorted = new ArrayList<>(rectangles);
        sorted.sort(comparator);
        return sorted;
    }

    public List<Rectangle> getAll() {
        return new ArrayList<>(rectangles);
    }
}