package com.gip.xyna.swagger;

import org.junit.jupiter.api.Test;

public class SwaggerStringTypeTest {
    @Test
    void testNullIsValid() {
        SwaggerStringType st = new SwaggerStringType("test", null);

        assert (!st.isValid());

        st.setNullable();
        assert (st.isValid());
    }

    @Test
    void testRequieredAndNullIsInvalid() {
        SwaggerStringType st = new SwaggerStringType("test", null);

        assert (!st.isValid());

        st.setRequiered();
        assert (!st.isValid());

        st.setNullable();
        assert (!st.isValid());
    }

    @Test
    void testRequieredIsValid() {
        SwaggerStringType st = new SwaggerStringType("test", "test");

        assert (st.isValid());

        st.setRequiered();
        assert (st.isValid());
    }

    @Test
    void testPatternIsValid() {
        SwaggerStringType st = new SwaggerStringType("test", "test");

        assert (st.isValid());

        st.setPattern("^test$");
        assert (st.isValid());

        st.setPattern(".es.");
        assert (st.isValid());

        st.setPattern("es");
        assert (st.isValid());

        st.setPattern("tester");
        assert (!st.isValid());
    }

    @Test
    void testMinLength() {
        SwaggerStringType st = new SwaggerStringType("test", "");
        st.setMinLength(0);
        assert (st.isValid());

        st.setMinLength(1);
        assert (!st.isValid());

        SwaggerStringType st2 = new SwaggerStringType("test", "test");
        st2.setMinLength(4);
        assert (st2.isValid());

        st2.setMinLength(5);
        assert (!st2.isValid());
    }

    @Test
    void testMaxLength() {
        SwaggerStringType st = new SwaggerStringType("test", "");
        st.setMaxLength(0);
        assert (st.isValid());

        st.setMaxLength(1);
        assert (st.isValid());

        SwaggerStringType st2 = new SwaggerStringType("test", "test");
        st2.setMaxLength(0);
        assert (!st2.isValid());

        st2.setMaxLength(1);
        assert (!st2.isValid());

        st2.setMaxLength(4);
        assert (st2.isValid());

        st2.setMaxLength(5);
        assert (st2.isValid());
    }
}
