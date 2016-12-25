package net.netzgut.integral.streams;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class IntegralPredicates {

    /**
     * Negates a predicate.
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

    /**
     * Compares with ==.
     */
    public static <T> Predicate<T> is(T value) {
        return input -> input == value;
    }

    /**
     * Checks if an object is assignable from a class.
     */
    public static <T> Predicate<T> assignableFrom(Class<?> clazz) {
        return input -> input.getClass().isAssignableFrom(clazz);
    }

    /**
     * Ensures all predicates are met.
     */
    @SafeVarargs
    public static <T> Predicate<T> allOf(Predicate<T>... predicates) {
        return Stream.of(predicates) //
                     .reduce(always(), //
                             (p1, p2) -> p1.and(p2));
    }

    /**
     * Ensures any predicate is met.
     */
    @SafeVarargs
    public static <T> Predicate<T> anyOf(Predicate<T>... predicates) {
        return Stream.of(predicates) //
                     .reduce(never(), //
                             (p1, p2) -> p1.or(p2));
    }

    /**
     * Ensures no predicate is met at all.
     */
    @SafeVarargs
    public static <T> Predicate<T> none(Predicate<T>... predicates) {
        return Stream.of(predicates) //
                     .reduce(never(), //
                             (p1, p2) -> not(p1.and(p2)));
    }

    public static <T> Predicate<T> always() {
        return ignored -> true;
    }

    public static <T> Predicate<T> never() {
        return ignored -> false;
    }

}
