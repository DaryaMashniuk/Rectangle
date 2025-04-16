package by.mashnyuk.rectangle.main;

import by.mashnyuk.rectangle.creator.PointFactory;
import by.mashnyuk.rectangle.creator.RectangleFactory;
import by.mashnyuk.rectangle.creator.impl.PointFactoryImpl;
import by.mashnyuk.rectangle.creator.impl.RectangleFactoryImpl;
import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.facade.RectangleFacade;
import by.mashnyuk.rectangle.io.CustomFileReader;
import by.mashnyuk.rectangle.parser.PointParser;
import by.mashnyuk.rectangle.parser.RectangleParser;
import by.mashnyuk.rectangle.repository.RectangleRepository;
import by.mashnyuk.rectangle.service.impl.RectangleImportService;
import by.mashnyuk.rectangle.service.impl.RectangleServiceImpl;
import by.mashnyuk.rectangle.validator.impl.RectangleValidatorImpl;
import by.mashnyuk.rectangle.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // 1. Создание зависимостей (factories, parsers, services)
            PointFactory pointFactory = new PointFactoryImpl();
            RectangleValidatorImpl validator = new RectangleValidatorImpl();
            RectangleFactory rectangleFactory = new RectangleFactoryImpl(validator);
            PointParser pointParser = new PointParser(pointFactory);
            RectangleParser rectangleParser = new RectangleParser(pointParser, rectangleFactory);
            CustomFileReader fileReader = new CustomFileReader();
            RectangleRepository repository = RectangleRepository.getInstance();
            RectangleServiceImpl rectangleService = new RectangleServiceImpl();
            Warehouse warehouse = Warehouse.getInstance();

            RectangleImportService importService = new RectangleImportService(
                    fileReader,
                    rectangleParser,
                    repository,
                    rectangleService,
                    warehouse
            );

            // 2. Создание фасада
            RectangleFacade facade = new RectangleFacade(importService, repository, rectangleService, warehouse);

            // 3. Путь к ресурсу
            URL resource = Main.class.getClassLoader().getResource("rectangles.txt");
            if (resource == null) {
                throw new ShapeException("Файл 'rectangles.txt' не найден в ресурсах.");
            }
            Path path = Paths.get(resource.toURI());

            // 4. Импорт прямоугольников
            facade.run(path.toString());

            // 5. Вывод всех прямоугольников
            facade.printAllRectangles();

            // 6. Фильтрация и сортировка
            List<Rectangle> squares = facade.getSquares();
            List<Rectangle> mediumArea = facade.getMediumRectangles();
            List<Rectangle> sortedByArea = facade.getSortedByArea();

            logger.info("=== Квадраты ===");
            squares.forEach(r -> logger.info("ID: {}, Area: {}", r.getId(), warehouse.getParameters(r.getId()).getArea()));

            logger.info("=== Средние по площади (10-100) ===");
            mediumArea.forEach(r -> logger.info("ID: {}, Area: {}", r.getId(), warehouse.getParameters(r.getId()).getArea()));

            logger.info("=== Сортировка по площади ===");
            sortedByArea.forEach(r -> logger.info("ID: {}, Area: {}", r.getId(), warehouse.getParameters(r.getId()).getArea()));

        } catch (ShapeException | URISyntaxException e) {
            logger.error("Ошибка запуска приложения: {}", e.getMessage(), e);
        }
    }
}
