package by.mashnyuk.rectangle.observer;

public interface RectangleObservable {
    void attach(RectangleObserver observer);
    void detach(RectangleObserver observer);
    void notifyObservers();
}
