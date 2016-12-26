package net.netzgut.integral.streams.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import net.netzgut.integral.streams.IntegralStreams;

public class StreamsTests {

    @Test
    public void non_null_stream_filters_out_nulls() {

        // ARRANGE
        List<String> source = new ArrayList<>();
        source.add("1");
        source.add("2");
        source.add(null);
        source.add("3");

        // ACT
        List<String> target = IntegralStreams.nonNull(source).collect(Collectors.toList());

        // ASSERT
        Assert.assertEquals(target.size(), 3);
        Assert.assertEquals(target.get(0), "1");
        Assert.assertEquals(target.get(1), "2");
        Assert.assertEquals(target.get(2), "3");
    }

    @Test
    public void of_from_iterable() {

        // ARRANGE
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterable<String> source = list;

        // ACT

        List<String> target = IntegralStreams.of(source).collect(Collectors.toList());

        // ASSERT
        Assert.assertEquals(target.size(), 3);
        Assert.assertEquals(target.get(0), "1");
        Assert.assertEquals(target.get(1), "2");
        Assert.assertEquals(target.get(2), "3");
    }

    @Test
    public void of_from_iterator() {

        // ARRANGE
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> source = list.iterator();

        // ACT

        List<String> target = IntegralStreams.of(source).collect(Collectors.toList());

        // ASSERT
        Assert.assertEquals(target.size(), 3);
        Assert.assertEquals(target.get(0), "1");
        Assert.assertEquals(target.get(1), "2");
        Assert.assertEquals(target.get(2), "3");
    }

    @Test
    public void infinit() {

        // ARRANGE: We can't really test if the stream is infinite, so we just test "a few" iterations
        String value = "123";
        int iterations = 100_000_000;
        // ARRANGE / ACT
        List<String> target = IntegralStreams.infinite(value).limit(iterations).collect(Collectors.toList());

        // ASSERT
        Assert.assertEquals(target.size(), iterations);
        for (int idx = 0; idx < iterations; idx++) {
            Assert.assertEquals(target.get(idx), value);
        }
    }
}
