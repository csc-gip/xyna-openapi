package com.gip.xyna.swagger;

import org.junit.jupiter.api.Test;

public class SwaggerDateTypeTest {
    @Test
    void testNullIsValid() {
        SwaggerDateType st = new SwaggerDateType("test", null);

        assert (!st.isValid());

        st.setNullable();
        assert (st.isValid());
    }

    @Test
    void testDateIsValid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-08-01");

        assert (st.isValid());
    }

    @Test
    void testDateTimeIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-08-01T12:34:56Z");

        assert (!st.isValid());
    }

    @Test
    void testYearIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022");

        assert (!st.isValid());
    }

    @Test
    void testYearMonthIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-12");

        assert (!st.isValid());
    }

    @Test
    void testSingleDigitMonthIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-2-28");

        assert (!st.isValid());
    }

    @Test
    void testSingleDigitDayIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-12-2");

        assert (!st.isValid());
    }

    @Test
    void testInvalidMonthIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-13-2");

        assert (!st.isValid());
    }

    @Test
    void testInvalidDayIsInvalid() {
        SwaggerDateType st = new SwaggerDateType("test", "2022-04-32");

        assert (!st.isValid());
    }

    @Test
    void testFeburary() {
        SwaggerDateType st28 = new SwaggerDateType("test", "2022-02-28");

        assert (st28.isValid());

        SwaggerDateType st29 = new SwaggerDateType("test", "2022-02-29");

        assert (st29.isValid());

        SwaggerDateType st30 = new SwaggerDateType("test", "2022-02-30");

        assert (!st30.isValid());

        SwaggerDateType st31 = new SwaggerDateType("test", "2022-02-31");

        assert (!st31.isValid());
    }

    @Test
    void test30Days() {
        SwaggerDateType apr30 = new SwaggerDateType("test", "2022-04-30");
        assert (apr30.isValid());
        SwaggerDateType apr31 = new SwaggerDateType("test", "2022-04-31");
        assert (!apr31.isValid());

        SwaggerDateType jun30 = new SwaggerDateType("test", "2022-06-30");
        assert (jun30.isValid());
        SwaggerDateType jun31 = new SwaggerDateType("test", "2022-06-31");
        assert (!jun31.isValid());

        SwaggerDateType sep30 = new SwaggerDateType("test", "2022-09-30");
        assert (sep30.isValid());
        SwaggerDateType sep31 = new SwaggerDateType("test", "2022-09-31");
        assert (!sep31.isValid());

        SwaggerDateType nov30 = new SwaggerDateType("test", "2022-11-30");
        assert (nov30.isValid());
        SwaggerDateType nov31 = new SwaggerDateType("test", "2022-11-31");
        assert (!nov31.isValid());
    }

    @Test
    void test31Days() {
        SwaggerDateType jan = new SwaggerDateType("test", "2022-01-31");

        assert (jan.isValid());

        SwaggerDateType mar = new SwaggerDateType("test", "2022-03-31");

        assert (mar.isValid());

        SwaggerDateType mai = new SwaggerDateType("test", "2022-05-31");

        assert (mai.isValid());

        SwaggerDateType jul = new SwaggerDateType("test", "2022-07-31");

        assert (jul.isValid());

        SwaggerDateType aug = new SwaggerDateType("test", "2022-08-31");

        assert (aug.isValid());

        SwaggerDateType oct = new SwaggerDateType("test", "2022-10-31");

        assert (oct.isValid());

        SwaggerDateType dec = new SwaggerDateType("test", "2022-12-31");

        assert (dec.isValid());
    }
}
