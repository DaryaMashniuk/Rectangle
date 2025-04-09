package by.mashnyuk.rectangle;

import by.mashnyuk.rectangle.creator.impl.RectangleFactoryImpl;
import by.mashnyuk.rectangle.entity.Point;

import by.mashnyuk.rectangle.entity.Rectangle;
import by.mashnyuk.rectangle.validator.impl.RectangleValidatorImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

public class RectangleFactoryImplTest {
    private RectangleFactoryImpl factory;
    private Point validPoint1;
    private Point validPoint2;
    private Point validPoint3;
    private Point validPoint4;

    @BeforeClass
    public void setUp() {
        factory = new RectangleFactoryImpl(new RectangleValidatorImpl());
        validPoint1 = new Point(0, 0);
        validPoint2 = new Point(0, 1);
        validPoint3 = new Point(1, 1);
        validPoint4 = new Point(1, 0);
    }

    @Test
    public void testCreateRectangleWithValidPoints() {
        Optional<Rectangle> result = factory.createRectangle(
                validPoint1, validPoint2, validPoint3, validPoint4);
        assertTrue(result.isPresent());
        assertEquals(result.get().getPoints().size(), 4);
    }

    @Test
    public void testCreateRectangleIncrementsId() {
        Optional<Rectangle> rect1 = factory.createRectangle(
                validPoint1, validPoint2, validPoint3, validPoint4);
        Optional<Rectangle> rect2 = factory.createRectangle(
                validPoint1, validPoint2, new Point(2, 2), new Point(2, 0));

        assertTrue(rect1.isPresent());
        assertTrue(rect2.isPresent());
        assertEquals(rect2.get().getId(), rect1.get().getId() + 1);
    }
}