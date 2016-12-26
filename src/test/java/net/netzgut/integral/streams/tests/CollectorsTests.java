package net.netzgut.integral.streams.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import net.netzgut.integral.streams.IntegralCollectors;

public class CollectorsTests {

    @Test
    public void toLinkedList() {

        // ARRANGE
        int count = 100_000;
        List<String> source = new ArrayList<>();
        for (int idx = 0; idx < count; idx++) {
            source.add(UUID.randomUUID().toString());
        }

        // ACT
        Map<String, String> linked =
            source.stream().collect(IntegralCollectors.toLinkedMap(key -> key, value -> value));

        // ASSERT
        ArrayList<String> values = new ArrayList<>(linked.values());
        for (int idx = 0; idx < count; idx++) {
            Assert.assertEquals(source.get(idx), values.get(idx));
        }
    }

    @Test
    public void toImmutableList() {

        // ARRANGE
        List<String> source = new ArrayList<>();
        source.add("1");
        source.add("2");
        source.add("3");

        // ACT
        List<String> target = source.stream().collect(IntegralCollectors.toImmutableList());

        // ASSERT
        Assert.assertThrows(UnsupportedOperationException.class, () -> target.add("22"));
    }

}
