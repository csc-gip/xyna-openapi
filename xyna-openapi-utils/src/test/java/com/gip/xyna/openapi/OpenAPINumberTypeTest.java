package com.gip.xyna.openapi;

import org.junit.jupiter.api.Test;

public class OpenAPINumberTypeTest {

    @Test
    void testMinInteger() {
        OpenAPINumberType<Integer> oi = new OpenAPINumberType<Integer>("int", 10);
        oi.setMin(-10);
        assert (oi.isValid());

        oi.setMin(0);
        assert (oi.isValid());

        oi.setMin(10);
        assert (oi.isValid());

        oi.setExcludeMin();
        assert (!oi.isValid());

        oi.setMin(11);
        assert (!oi.isValid());

        OpenAPINumberType<Integer> oMax = new OpenAPINumberType<Integer>("int", Integer.MAX_VALUE);
        oMax.setMin(Integer.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMin();
        assert (!oMax.isValid());

        OpenAPINumberType<Integer> oMin = new OpenAPINumberType<Integer>("int", Integer.MIN_VALUE);
        oMin.setMin(Integer.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMin();
        assert (!oMin.isValid());
    }

    @Test
    void testMinLong() {
        OpenAPINumberType<Long> oi = new OpenAPINumberType<Long>("long", 10L);
        oi.setMin(-10L);
        assert (oi.isValid());

        oi.setMin(0L);
        assert (oi.isValid());

        oi.setMin(10L);
        assert (oi.isValid());

        oi.setExcludeMin();
        assert (!oi.isValid());

        oi.setMin(11L);
        assert (!oi.isValid());

        OpenAPINumberType<Long> oMax = new OpenAPINumberType<Long>("long", Long.MAX_VALUE);
        oMax.setMin(Long.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMin();
        assert (!oMax.isValid());

        OpenAPINumberType<Long> oMin = new OpenAPINumberType<Long>("long", Long.MIN_VALUE);
        oMin.setMin(Long.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMin();
        assert (!oMin.isValid());
    }

    @Test
    void testMinFloat() {
        OpenAPINumberType<Float> oi = new OpenAPINumberType<Float>("float", 0.1f);
        oi.setMin(-1.1f);
        assert (oi.isValid());

        oi.setMin(0f);
        assert (oi.isValid());

        oi.setMin(0.1f);
        assert (oi.isValid());

        oi.setExcludeMin();
        assert (!oi.isValid());

        oi.setMin(0.1f + java.lang.Math.ulp(0.1f));
        assert (!oi.isValid());

        OpenAPINumberType<Float> oMax = new OpenAPINumberType<Float>("float", Float.MAX_VALUE);
        oMax.setMin(Float.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMin();
        assert (!oMax.isValid());

        OpenAPINumberType<Float> oMin = new OpenAPINumberType<Float>("float", Float.MIN_VALUE);
        oMin.setMin(Float.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMin();
        assert (!oMin.isValid());
    }

    @Test
    void testMinDouble() {
        OpenAPINumberType<Double> oi = new OpenAPINumberType<Double>("double", 0.1d);
        oi.setMin(-1.1d);
        assert (oi.isValid());

        oi.setMin(0d);
        assert (oi.isValid());

        oi.setMin(0.1d);
        assert (oi.isValid());

        oi.setExcludeMin();
        assert (!oi.isValid());

        oi.setMin(0.1d + java.lang.Math.ulp(0.1d));
        assert (!oi.isValid());

        OpenAPINumberType<Double> oMax = new OpenAPINumberType<Double>("double", Double.MAX_VALUE);
        oMax.setMin(Double.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMin();
        assert (!oMax.isValid());

        OpenAPINumberType<Double> oMin = new OpenAPINumberType<Double>("double", Double.MIN_VALUE);
        oMin.setMin(Double.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMin();
        assert (!oMin.isValid());
    }

    @Test
    void testMaxInteger() {
        OpenAPINumberType<Integer> oi = new OpenAPINumberType<Integer>("int", 10);
        oi.setMax(Integer.MAX_VALUE);
        assert (oi.isValid());

        oi.setMax(10);
        assert (oi.isValid());

        oi.setExcludeMax();
        assert (!oi.isValid());

        oi.setMax(11);
        assert (oi.isValid());

        OpenAPINumberType<Integer> oMax = new OpenAPINumberType<Integer>("int", Integer.MAX_VALUE);
        oMax.setMax(Integer.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMax();
        assert (!oMax.isValid());

        OpenAPINumberType<Integer> oMin = new OpenAPINumberType<Integer>("int", Integer.MIN_VALUE);
        oMin.setMax(Integer.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMax();
        assert (!oMin.isValid());
    }

    @Test
    void testMaxLong() {
        OpenAPINumberType<Long> oi = new OpenAPINumberType<Long>("long", 10L);
        oi.setMax(Long.MAX_VALUE);
        assert (oi.isValid());

        oi.setMax(10L);
        assert (oi.isValid());

        oi.setExcludeMax();
        assert (!oi.isValid());

        oi.setMax(11L);
        assert (oi.isValid());

        OpenAPINumberType<Long> oMax = new OpenAPINumberType<Long>("long", Long.MAX_VALUE);
        oMax.setMax(Long.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMax();
        assert (!oMax.isValid());

        OpenAPINumberType<Long> oMin = new OpenAPINumberType<Long>("long", Long.MIN_VALUE);
        oMin.setMax(Long.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMax();
        assert (!oMin.isValid());
    }

    @Test
    void testMaxFloat() {
        OpenAPINumberType<Float> oi = new OpenAPINumberType<Float>("float", 0.1f);
        oi.setMax(Float.MAX_VALUE);
        assert (oi.isValid());

        oi.setMax(0.1f);
        assert (oi.isValid());

        oi.setExcludeMax();
        assert (!oi.isValid());

        oi.setMax(0.1f + java.lang.Math.ulp(0.1f));
        assert (oi.isValid());

        OpenAPINumberType<Float> oMax = new OpenAPINumberType<Float>("float", Float.MAX_VALUE);
        oMax.setMax(Float.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMax();
        assert (!oMax.isValid());

        OpenAPINumberType<Float> oMin = new OpenAPINumberType<Float>("float", Float.MIN_VALUE);
        oMin.setMax(Float.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMax();
        assert (!oMin.isValid());
    }

    @Test
    void testMaxDouble() {
        OpenAPINumberType<Double> oi = new OpenAPINumberType<Double>("double", 0.1d);
        oi.setMax(Double.MAX_VALUE);
        assert (oi.isValid());

        oi.setMax(0.1d);
        assert (oi.isValid());

        oi.setExcludeMax();
        assert (!oi.isValid());

        oi.setMax(0.1d + java.lang.Math.ulp(0.1d));
        assert (oi.isValid());

        OpenAPINumberType<Double> oMax = new OpenAPINumberType<Double>("double", Double.MAX_VALUE);
        oMax.setMax(Double.MAX_VALUE);
        assert (oMax.isValid());

        oMax.setExcludeMax();
        assert (!oMax.isValid());

        OpenAPINumberType<Double> oMin = new OpenAPINumberType<Double>("double", Double.MIN_VALUE);
        oMin.setMax(Double.MIN_VALUE);
        assert (oMin.isValid());

        oMin.setExcludeMax();
        assert (!oMin.isValid());
    }

    @Test
    void testSetMultipleOfForInteger() {

        assert (!(new OpenAPINumberType<Integer>("int", 10)).setMultipleOf(0).isValid());

        OpenAPINumberType<Integer> oi = new OpenAPINumberType<Integer>("int", 10);

        oi.setMultipleOf(0);
        assert (!oi.isValid());

        oi.setMultipleOf(3);
        assert (!oi.isValid());

        oi.setMultipleOf(1);
        assert (oi.isValid());

        oi.setMultipleOf(2);
        assert (oi.isValid());

        oi.setMultipleOf(5);
        assert (oi.isValid());

        oi.setMultipleOf(10);
        assert (oi.isValid());

        OpenAPINumberType<Integer> oni = new OpenAPINumberType<Integer>("neg int", -10);

        oni.setMultipleOf(0);
        assert (!oni.isValid());

        oni.setMultipleOf(2);
        assert (oni.isValid());

        oni.setMultipleOf(1);
        assert (oni.isValid());

        oni.setMultipleOf(5);
        assert (oni.isValid());

    }

    @Test
    void testSetMultipleOfForLong() {

        assert (!(new OpenAPINumberType<Long>("long", 10L)).setMultipleOf(0L).isValid());

        OpenAPINumberType<Long> ol = new OpenAPINumberType<Long>("long", Integer.MAX_VALUE * 10L);

        ol.setMultipleOf(0L);
        assert (!ol.isValid());

        ol.setMultipleOf(3L);
        assert (!ol.isValid());

        ol.setMultipleOf(1L);
        assert (ol.isValid());

        ol.setMultipleOf(2L);
        assert (ol.isValid());

        ol.setMultipleOf(5L);
        assert (ol.isValid());

        ol.setMultipleOf(10L);
        assert (ol.isValid());

        OpenAPINumberType<Long> onl =
                new OpenAPINumberType<Long>("neg long", -10L * Integer.MAX_VALUE);

        onl.setMultipleOf(0L);
        assert (!onl.isValid());

        onl.setMultipleOf(2L);
        assert (onl.isValid());

        onl.setMultipleOf(1L);
        assert (onl.isValid());

        onl.setMultipleOf(5L);
        assert (onl.isValid());
    }
}
