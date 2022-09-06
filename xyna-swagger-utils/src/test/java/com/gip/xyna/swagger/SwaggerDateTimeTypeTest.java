package com.gip.xyna.swagger;

import org.junit.jupiter.api.Test;

public class SwaggerDateTimeTypeTest {
    // https://www.rfc-editor.org/rfc/rfc3339#section-5.8
    @Test
    void testIsValid() {
        SwaggerDateTimeType st1 = new SwaggerDateTimeType("test", "1985-04-12T23:20:50.52Z");
        assert (st1.isValid());

        SwaggerDateTimeType st2 = new SwaggerDateTimeType("test", "1996-12-19T16:39:57-08:00");
        assert (st2.isValid());

        SwaggerDateTimeType st3 = new SwaggerDateTimeType("test", "1990-12-31T23:59:60Z");
        assert (st3.isValid());

        SwaggerDateTimeType st4 = new SwaggerDateTimeType("test", "1990-12-31T15:59:60-08:00");
        assert (st4.isValid());

        SwaggerDateTimeType st5 = new SwaggerDateTimeType("test", "1937-01-01T12:00:27.87+00:20");
        assert (st5.isValid());
    }

    @Test
    void testLowercaseIsInvalid() {
        SwaggerDateTimeType st1 = new SwaggerDateTimeType("test", "1985-04-12t23:20:50.52Z");
        assert (!st1.isValid());

        SwaggerDateTimeType st2 = new SwaggerDateTimeType("test", "1985-04-12T23:20:50.52z");
        assert (!st2.isValid());
    }
}
