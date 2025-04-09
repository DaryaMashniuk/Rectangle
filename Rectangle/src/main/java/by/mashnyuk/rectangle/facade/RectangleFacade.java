package by.mashnyuk.rectangle.facade;

import by.mashnyuk.rectangle.creator.PointFactory;
import by.mashnyuk.rectangle.creator.RectangleFactory;
import by.mashnyuk.rectangle.creator.impl.PointFactoryImpl;
import by.mashnyuk.rectangle.creator.impl.RectangleFactoryImpl;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.io.CustomFileReader;
import by.mashnyuk.rectangle.parser.PointParser;
import by.mashnyuk.rectangle.repository.RectangleRepository;
import by.mashnyuk.rectangle.service.RectangleService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.validator.impl.RectangleValidatorImpl;
import by.mashnyuk.rectangle.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RectangleFacade {
    private static final Logger logger = LogManager.getLogger();

    private final CustomFileReader fileReader;
    private final RectangleService rectangleService;
    private final RectangleRepository repository;
    private final Warehouse warehouse;

    public RectangleFacade() {
        PointFactory pointFactory = new PointFactoryImpl();
        RectangleValidatorImpl rectangleValidator = new RectangleValidatorImpl();
        RectangleFactory rectangleFactory = new RectangleFactoryImpl(rectangleValidator);
        PointParser pointParser = new PointParser(pointFactory);
        this.fileReader = new CustomFileReader(rectangleFactory, pointParser);

        this.rectangleService = new RectangleServiceImpl();
        this.repository = RectangleRepository.getInstance();
        this.warehouse = Warehouse.getInstance();
    }

    public void run(String filePath) throws ShapeException {
        List<Rectangle> rectangles = fileReader.readRectangles(filePath);

        rectangles.forEach(repository::add);

        rectangles.forEach(rect -> {
            double perimeter = rectangleService.calculatePerimeter(rect);
            double area = rectangleService.calculateArea(rect);
            warehouse.put(rect.getId(), perimeter, area);
        });

        rectangles.forEach(rect -> {
            logger.info("Rectangle ID: {}", rect.getId());
            logger.info("Points: {}", rect.getPoints());
            logger.info("Perimeter: {}", warehouse.getParameters(rect.getId()).getPerimeter());
            logger.info("Area: {}", warehouse.getParameters(rect.getId()).getArea());
            logger.info("Is square: {}", rectangleService.isSquare(rect));
            logger.info("Is rhombus: {}", rectangleService.isRhombus(rect));
            logger.info("Is trapezoid: {}", rectangleService.isTrapezoid(rect));
            logger.info("Is convex: {}", rectangleService.isConvex(rect));
        });
    }
}
