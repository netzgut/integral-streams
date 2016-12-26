package net.netzgut.integral.streams.tests;

import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.Test;

import net.netzgut.integral.streams.IntegralPredicates;

public class PredicatesTests {

    @Test
    public void not() {

        // ARRANGE
        Predicate<Long> isEvenPredicate = input -> input % 2 == 0;

        // ACT
        boolean shouldBeTrue = IntegralPredicates.not(isEvenPredicate).test(3l);
        boolean shouldBeFalse = IntegralPredicates.not(isEvenPredicate).test(4l);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void is() {

        // ARRANGE
        Long value = 5l;
        Long anotherValue = new Long(5l);

        // ACT
        boolean shouldBeTrue = IntegralPredicates.identity(value).test(value);
        boolean shouldBeFalse = IntegralPredicates.identity(value).test(anotherValue);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void assignableFrom() {

        // ARRANGE
        java.sql.Date sqlDate = new java.sql.Date(123l);

        // ACT
        boolean shouldBeTrue = IntegralPredicates.assignableFrom(java.util.Date.class).test(sqlDate);
        boolean shouldBeFalse = IntegralPredicates.assignableFrom(java.time.LocalDate.class).test(sqlDate);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void isInstance() {

        // ARRANGE
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(123l);

        // ACT
        boolean shouldBeTrue1 = IntegralPredicates.isInstance(java.util.Date.class).test(date);
        boolean shouldBeTrue2 = IntegralPredicates.isInstance(java.util.Date.class).test(sqlDate);
        boolean shouldBeFalse = IntegralPredicates.isInstance(java.sql.Date.class).test(date);

        // ASSERT
        Assert.assertTrue(shouldBeTrue1);
        Assert.assertTrue(shouldBeTrue2);
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void allOf() {

        // ARRANGE
        Predicate<Long> isEven = input -> input % 2 == 0;
        Predicate<Long> greaterTen = input -> input > 10;

        // ACT
        boolean shouldBeTrue = IntegralPredicates.allOf(isEven, greaterTen).test(12l);
        boolean shouldBeFalse1 = IntegralPredicates.allOf(isEven, greaterTen).test(9l);
        boolean shouldBeFalse2 = IntegralPredicates.allOf(isEven, greaterTen).test(8l);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
        Assert.assertFalse(shouldBeFalse1);
        Assert.assertFalse(shouldBeFalse2);
    }

    @Test
    public void anyOf() {

        // ARRANGE
        Predicate<Long> isEven = input -> input % 2 == 0;
        Predicate<Long> greaterTen = input -> input > 10;

        // ACT
        boolean shouldBeTrue1 = IntegralPredicates.anyOf(isEven, greaterTen).test(12l);
        boolean shouldBeTrue2 = IntegralPredicates.anyOf(isEven, greaterTen).test(8l);
        boolean shouldBeTrue3 = IntegralPredicates.anyOf(isEven, greaterTen).test(11l);
        boolean shouldBeFalse = IntegralPredicates.anyOf(isEven, greaterTen).test(9l);

        // ASSERT
        Assert.assertTrue(shouldBeTrue1);
        Assert.assertTrue(shouldBeTrue2);
        Assert.assertTrue(shouldBeTrue3);
        Assert.assertFalse(shouldBeFalse);
    }

    @Test
    public void none() {

        // ARRANGE
        Predicate<Long> isEven = input -> input % 2 == 0;
        Predicate<Long> greaterTen = input -> input > 10;

        // ACT
        boolean shouldBeTrue = IntegralPredicates.none(isEven, greaterTen).test(7l);
        boolean shouldBeFalse1 = IntegralPredicates.none(isEven, greaterTen).test(11l);
        boolean shouldBeFalse2 = IntegralPredicates.none(isEven, greaterTen).test(8l);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
        Assert.assertFalse(shouldBeFalse1);
        Assert.assertFalse(shouldBeFalse2);
    }

    @Test
    public void always() {

        // ARRANGE / ACT
        boolean shouldBeTrue = IntegralPredicates.always().test(1);

        // ASSERT
        Assert.assertTrue(shouldBeTrue);
    }

    @Test
    public void never() {

        // ARRANGE / ACT
        boolean shouldBeFalse = IntegralPredicates.never().test(1);

        // ASSERT
        Assert.assertFalse(shouldBeFalse);
    }

}
