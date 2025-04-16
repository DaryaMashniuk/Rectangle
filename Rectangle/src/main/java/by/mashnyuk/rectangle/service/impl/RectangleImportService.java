package by.mashnyuk.rectangle.service.impl;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.io.CustomFileReader;
import by.mashnyuk.rectangle.parser.RectangleParser;
import by.mashnyuk.rectangle.repository.RectangleRepository;
import by.mashnyuk.rectangle.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class RectangleImportService {
    private static final Logger logger = LogManager.getLogger();
    private final CustomFileReader fileReader;
    private final RectangleParser rectangleParser;
    private final RectangleRepository repository;
    private final RectangleServiceImpl service;
    private final Warehouse warehouse;

    public RectangleImportService(CustomFileReader fileReader,
                                  RectangleParser rectangleParser,
                                  RectangleRepository repository,
                                  RectangleServiceImpl service,
                                  Warehouse warehouse) {
        this.fileReader = fileReader;
        this.rectangleParser = rectangleParser;
        this.repository = repository;
        this.service = service;
        this.warehouse = warehouse;
    }

    public void importFromFile(String filePath) throws ShapeException {
        List<String> lines;
        try {
            lines = fileReader.readLines(filePath);
        } catch (IOException e) {
            logger.error("Error reading file: {}", filePath, e);
            throw new ShapeException("Failed to read file: " + filePath, e);
        }

        for (String line : lines) {
            try {
                Optional<Rectangle> rectOpt = rectangleParser.parse(line);
                rectOpt.ifPresent(rect -> {
                    repository.add(rect);
                    double perimeter = service.calculatePerimeter(rect);
                    double area = service.calculateArea(rect);
                    warehouse.put(rect.getId(), perimeter, area);
                    logger.info("Added Rectangle {}: perimeter={}, area={}", rect.getId(), perimeter, area);
                });
            } catch (ShapeException e) {
                logger.warn("Skipping invalid rectangle line: {}", line, e);
            }
        }
    }
}

