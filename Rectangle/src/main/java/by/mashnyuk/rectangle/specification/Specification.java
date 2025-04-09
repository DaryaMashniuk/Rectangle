package by.mashnyuk.rectangle.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}