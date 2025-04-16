package by.mashnyuk.rectangle.repository.impl;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.repository.RectangleRepository;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.specification.Specification;
import by.mashnyuk.rectangle.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RectangleRepositoryImpl implements RectangleRepository {
    private static RectangleRepositoryImpl instance;
    private final List<Rectangle> rectangles = new ArrayList<>();

    private RectangleRepositoryImpl() {}

    public static RectangleRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RectangleRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    @Override
    public List<Rectangle> query(Specification<Rectangle> specification) {
        List<Rectangle> result = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            if (specification.isSatisfiedBy(rectangle)) {
                result.add(rectangle);
            }
        }
        return result;
    }

    @Override
    public List<Rectangle> sortById() {
        return sort(Comparator.comparingInt(Rectangle::getId));
    }

    @Override
    public List<Rectangle> sortByPerimeter() {
        return sort(Comparator.comparingDouble(r ->
                Warehouse.getInstance().getParameters(r.getId()).getPerimeter()));
    }

    @Override
    public List<Rectangle> sortByArea() {
        return sort(Comparator.comparingDouble(r ->
                Warehouse.getInstance().getParameters(r.getId()).getArea()));
    }

    @Override
    public List<Rectangle> sortByShapeType(RectangleService rectangleService) {
        return sort(Comparator.comparing(rectangleService::getShapeType));
    }

    private List<Rectangle> sort(Comparator<Rectangle> comparator) {
        List<Rectangle> sorted = new ArrayList<>(rectangles);
        sorted.sort(comparator);
        return sorted;
    }

    @Override
    public List<Rectangle> getAll() {
        return new ArrayList<>(rectangles);
    }
}