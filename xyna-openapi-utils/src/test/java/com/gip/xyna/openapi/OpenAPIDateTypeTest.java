package com.gip.xyna.openapi;

import org.junit.jupiter.api.Test;

public class OpenAPIDateTypeTest {
    @Test
    void testNullIsValid() {
        OpenAPIDateType st = new OpenAPIDateType("test", null);

        assert (!st.isValid());

        st.setNullable();
        assert (st.isValid());
    }

    @Test
    void testDateIsValid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-08-01");

        assert (st.isValid());
    }

    @Test
    void testDateTimeIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-08-01T12:34:56Z");

        assert (!st.isValid());
    }

    @Test
    void testYearIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022");

        assert (!st.isValid());
    }

    @Test
    void testYearMonthIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-12");

        assert (!st.isValid());
    }

    @Test
    void testSingleDigitMonthIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-2-28");

        assert (!st.isValid());
    }

    @Test
    void testSingleDigitDayIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-12-2");

        assert (!st.isValid());
    }

    @Test
    void testInvalidMonthIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-13-2");

        assert (!st.isValid());
    }

    @Test
    void testInvalidDayIsInvalid() {
        OpenAPIDateType st = new OpenAPIDateType("test", "2022-04-32");

        assert (!st.isValid());
    }

    @Test
    void testFeburary() {
        OpenAPIDateType st28 = new OpenAPIDateType("test", "2022-02-28");

        assert (st28.isValid());

        OpenAPIDateType st29 = new OpenAPIDateType("test", "2022-02-29");

        assert (st29.isValid());

        OpenAPIDateType st30 = new OpenAPIDateType("test", "2022-02-30");

        assert (!st30.isValid());

        OpenAPIDateType st31 = new OpenAPIDateType("test", "2022-02-31");

        assert (!st31.isValid());
    }

    @Test
    void test30Days() {
        OpenAPIDateType apr30 = new OpenAPIDateType("test", "2022-04-30");
        assert (apr30.isValid());
        OpenAPIDateType apr31 = new OpenAPIDateType("test", "2022-04-31");
        assert (!apr31.isValid());

        OpenAPIDateType jun30 = new OpenAPIDateType("test", "2022-06-30");
        assert (jun30.isValid());
        OpenAPIDateType jun31 = new OpenAPIDateType("test", "2022-06-31");
        assert (!jun31.isValid());

        OpenAPIDateType sep30 = new OpenAPIDateType("test", "2022-09-30");
        assert (sep30.isValid());
        OpenAPIDateType sep31 = new OpenAPIDateType("test", "2022-09-31");
        assert (!sep31.isValid());

        OpenAPIDateType nov30 = new OpenAPIDateType("test", "2022-11-30");
        assert (nov30.isValid());
        OpenAPIDateType nov31 = new OpenAPIDateType("test", "2022-11-31");
        assert (!nov31.isValid());
    }

    @Test
    void test31Days() {
        OpenAPIDateType jan = new OpenAPIDateType("test", "2022-01-31");

        assert (jan.isValid());

        OpenAPIDateType mar = new OpenAPIDateType("test", "2022-03-31");

        assert (mar.isValid());

        OpenAPIDateType mai = new OpenAPIDateType("test", "2022-05-31");

        assert (mai.isValid());

        OpenAPIDateType jul = new OpenAPIDateType("test", "2022-07-31");

        assert (jul.isValid());

        OpenAPIDateType aug = new OpenAPIDateType("test", "2022-08-31");

        assert (aug.isValid());

        OpenAPIDateType oct = new OpenAPIDateType("test", "2022-10-31");

        assert (oct.isValid());

        OpenAPIDateType dec = new OpenAPIDateType("test", "2022-12-31");

        assert (dec.isValid());
    }
}
