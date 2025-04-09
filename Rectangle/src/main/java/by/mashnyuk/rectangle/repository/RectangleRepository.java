package by.mashnyuk.rectangle.repository;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.specification.Specification;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RectangleRepository {
    private static RectangleRepository instance;
    private final List<Rectangle> rectangles = new ArrayList<>();

    private RectangleRepository() {}

    public static synchronized RectangleRepository getInstance() {
        if (instance == null) {
            instance = new RectangleRepository();
        }
        return instance;
    }

    public void add(Rectangle rectangle) {
        rectangles.add(rectangle);
    }

    public List<Rectangle> query(Specification<Rectangle> specification) {
        List<Rectangle> result = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            if (specification.isSatisfiedBy(rectangle)) {
                result.add(rectangle);
            }
        }
        return result;
    }

    public List<Rectangle> sort(Comparator<Rectangle> comparator) {
        List<Rectangle> sorted = new ArrayList<>(rectangles);
        sorted.sort(comparator);
        return sorted;
    }

    public List<Rectangle> getAll() {
        return new ArrayList<>(rectangles);
    }
}