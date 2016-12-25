package net.netzgut.integral.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class IntegralCollectors {

    private IntegralCollectors() {
        // Prevent Instantion
    }

    /**
     * Collects a stream to a LinkedHashMap.
     */
    public static <T, K, U> Collector<T, ?, Map<K, U>> toLinkedMap(Function<? super T, ? extends K> keyMapper,
                                                                   Function<? super T, ? extends U> valueMapper) {
        return Collectors.toMap(keyMapper, //
                                valueMapper,
                                (u, v) -> {
                                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                                },
                                LinkedHashMap::new);
    }

    /**
     * Collects to an ImmutableList.
     */
    public static <T, A extends List<T>> Collector<T, A, List<T>> toImmutableList(Supplier<A> collectionFactory) {
        return Collector.of(collectionFactory, //
                            List::add,
                            (l, r) -> {
                                l.addAll(r);
                                return l;
                            },
                            Collections::unmodifiableList);
    }

    /**
     * Collects to an ImmutableList.
     */
    public static <T> Collector<T, List<T>, List<T>> toImmutableList() {
        return toImmutableList(ArrayList::new);
    }

}
