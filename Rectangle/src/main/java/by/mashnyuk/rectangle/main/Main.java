package by.mashnyuk.rectangle.main;

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

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            PointFactory pointFactory = new PointFactoryImpl();
            RectangleValidatorImpl rectangleValidator = new RectangleValidatorImpl();
            RectangleFactory rectangleFactory = new RectangleFactoryImpl(rectangleValidator);
            PointParser pointParser = new PointParser(pointFactory);
            RectangleService rectangleService = new RectangleServiceImpl();

            CustomFileReader fileReader = new CustomFileReader(rectangleFactory, pointParser);
            List<Rectangle> rectangles = fileReader.readRectangles("src/main/resources/rectangles.txt");

            RectangleRepository repository = RectangleRepository.getInstance();
            rectangles.forEach(repository::add);

            Warehouse warehouse = Warehouse.getInstance();
            rectangles.forEach(rect -> {
                double perimeter = rectangleService.calculatePerimeter(rect);
                double area = rectangleService.calculateArea(rect);
                warehouse.put(rect.getId(), perimeter, area);
            });

            // Логируем информацию о прямоугольниках
            repository.getAll().forEach(rect -> {
                logger.info("Rectangle ID: {}", rect.getId());
                logger.info("Points: {}", rect.getPoints());
                logger.info("Perimeter: {}", warehouse.getParameters(rect.getId()).getPerimeter());
                logger.info("Area: {}", warehouse.getParameters(rect.getId()).getArea());
                logger.info("Is square: {}", rectangleService.isSquare(rect));
                logger.info("Is rhombus: {}", rectangleService.isRhombus(rect));
                logger.info("Is trapezoid: {}", rectangleService.isTrapezoid(rect));
                logger.info("Is convex: {}", rectangleService.isConvex(rect));
            });

        } catch (ShapeException e) {
            logger.error("Error occurred: {}", e.getMessage(), e);
        }
    }
}
