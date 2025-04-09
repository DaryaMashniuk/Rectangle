package by.mashnyuk.rectangle.main;

import by.mashnyuk.rectangle.exception.ShapeException;
import by.mashnyuk.rectangle.facade.RectangleFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            RectangleFacade app = new RectangleFacade();
            app.run("src/main/resources/rectangles.txt");
        } catch (ShapeException e) {
            logger.error("Application error: {}", e.getMessage(), e);
        }
    }
}
