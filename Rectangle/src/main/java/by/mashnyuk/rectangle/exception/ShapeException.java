package by.mashnyuk.rectangle.exception;

public class ShapeException extends Exception {
    public ShapeException(String message) {
        super(message);
    }

    public ShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}