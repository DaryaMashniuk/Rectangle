package by.mashnyuk.rectangle.repository;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface RectangleRepository {
    void add(Rectangle rectangle);
    List<Rectangle> query(Specification<Rectangle> specification);
    List<Rectangle> sortById();
    List<Rectangle> sortByPerimeter();
    List<Rectangle> sortByArea();
    List<Rectangle> sortByShapeType(RectangleService rectangleService);
    List<Rectangle> getAll();
}
