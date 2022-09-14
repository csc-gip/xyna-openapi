package com.gip.xyna.openapi;

import org.junit.jupiter.api.Test;

public class OpenAPIStringTypeTest {
    @Test
    void testNullIsValid() {
        OpenAPIStringType st = new OpenAPIStringType("test", null);

        assert (!st.isValid());

        st.setNullable();
        assert (st.isValid());
    }

    @Test
    void testRequieredAndNullIsInvalid() {
        OpenAPIStringType st = new OpenAPIStringType("test", null);

        assert (!st.isValid());

        st.setRequiered();
        assert (!st.isValid());

        st.setNullable();
        assert (!st.isValid());
    }

    @Test
    void testRequieredIsValid() {
        OpenAPIStringType st = new OpenAPIStringType("test", "test");

        assert (st.isValid());

        st.setRequiered();
        assert (st.isValid());
    }

    @Test
    void testPatternIsValid() {
        OpenAPIStringType st = new OpenAPIStringType("test", "test");

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
        OpenAPIStringType st = new OpenAPIStringType("test", "");
        st.setMinLength(0);
        assert (st.isValid());

        st.setMinLength(1);
        assert (!st.isValid());

        OpenAPIStringType st2 = new OpenAPIStringType("test", "test");
        st2.setMinLength(4);
        assert (st2.isValid());

        st2.setMinLength(5);
        assert (!st2.isValid());
    }

    @Test
    void testMaxLength() {
        OpenAPIStringType st = new OpenAPIStringType("test", "");
        st.setMaxLength(0);
        assert (st.isValid());

        st.setMaxLength(1);
        assert (st.isValid());

        OpenAPIStringType st2 = new OpenAPIStringType("test", "test");
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
