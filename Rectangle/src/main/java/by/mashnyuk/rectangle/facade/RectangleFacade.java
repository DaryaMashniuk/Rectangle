package by.mashnyuk.rectangle.facade;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.repository.RectangleRepository;
import by.mashnyuk.rectangle.service.impl.RectangleImportService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.specification.AreaRangeSpecification;
import by.mashnyuk.rectangle.specification.SquareSpecification;
import by.mashnyuk.rectangle.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RectangleFacade {
    private static final Logger logger = LogManager.getLogger();
    private final RectangleImportService importService;
    private final RectangleRepository repository;
    private final RectangleServiceImpl service;
    private final Warehouse warehouse;

    public RectangleFacade(RectangleImportService importService,
                           RectangleRepository repository,
                           RectangleServiceImpl service,
                           Warehouse warehouse) {
        this.importService = importService;
        this.repository = repository;
        this.service = service;
        this.warehouse = warehouse;
    }

    public void run(String filePath) throws ShapeException {
        importService.importFromFile(filePath);
    }

    public void printAllRectangles() {
        List<Rectangle> rectangles = repository.getAll();
        rectangles.forEach(this::logRectangle);
    }

    private void logRectangle(Rectangle rect) {
        logger.info("Rectangle ID: {}", rect.getId());
        logger.info("Points: {}", rect.getPoints());
        logger.info("Perimeter: {}", warehouse.getParameters(rect.getId()).getPerimeter());
        logger.info("Area: {}", warehouse.getParameters(rect.getId()).getArea());
        logger.info("Is square: {}", service.isSquare(rect));
        logger.info("Is rhombus: {}", service.isRhombus(rect));
        logger.info("Is trapezoid: {}", service.isTrapezoid(rect));
        logger.info("Is convex: {}", service.isConvex(rect));
    }

    public List<Rectangle> getSquares() {
        return repository.query(new SquareSpecification());
    }

    public List<Rectangle> getMediumRectangles() {
        return repository.query(new AreaRangeSpecification(10, 100));
    }

    public List<Rectangle> getSortedByArea() {
        return repository.sortByArea();
    }
}
