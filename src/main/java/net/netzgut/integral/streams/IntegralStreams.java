package net.netzgut.integral.streams;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IntegralStreams {

    private IntegralStreams() {
        // Prevent Instantiation
    }

    /**
     * Creates a stream and filters out null objects.
     */
    public static <T> Stream<T> nonNull(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Stream.empty();
        }

        return collection.stream().filter(Objects::nonNull);
    }

    /**
     * Creates a stream out of an Iterable.
     */
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> of(Iterable<? extends T> iterable) {
        if (iterable == null || iterable.iterator().hasNext() == false) {
            return Stream.empty();
        }
        return (Stream<T>) StreamSupport.stream(iterable.spliterator(), false);
    }

    /**
     * Creates a stream out of an Iterator.
     */
    public static <T> Stream<T> of(Iterator<T> iterator) {
        if (iterator == null || iterator.hasNext() == false) {
            return Stream.empty();
        }

        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false);
    }

    /**
     * Creates an infinite stream with a constant value.
     */
    public static <T> Stream<T> infinite(T value) {
        return Stream.generate(() -> value);

    }

}
