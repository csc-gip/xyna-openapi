package com.gip.xyna.swagger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwaggerStringType extends SwaggerPrimitiveType<String> {

    private Integer min;
    private Integer max;
    private String format;
    private String pattern;

    public SwaggerStringType setFormat(String f) {
        this.format = f;
        return this;
    }

    public SwaggerStringType setPattern(String p) {
        this.pattern = p;
        return this;
    }

    public SwaggerStringType(String name, String value) {
        super(name, value);
    }

    public SwaggerStringType setMinLength(Integer m) {
        this.min = m;
        return this;
    }

    public SwaggerStringType setMaxLength(Integer m) {
        this.max = m;
        return this;
    }

    @Override
    public boolean isValid() {
        if (!super.isValid())
            return false;

        if (isNull())
            return true;

        if (!checkMinLength())
            return false;

        if (!checkMaxLength())
            return false;

        if (!checkPattern())
            return false;

        return true;
    }

    private boolean checkPattern() {
        if (pattern == null)
            return true;

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(getValue());
        return m.find();
    }

    private boolean checkMaxLength() {
        return max == null || (max != null && getValue().length() <= max);
    }

    private boolean checkMinLength() {
        return min == null || (min != null && getValue().length() >= min);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append("min length: ").append(min != null ? String.valueOf(min) : "null").append("\n");
        sb.append("max length: ").append(min != null ? String.valueOf(max) : "null").append("\n");
        sb.append("format: ").append(format).append("\n");
        sb.append("pattern: ").append(pattern).append("\n");
        sb.append("pattern matches: ").append(String.valueOf(checkPattern())).append("\n");

        return sb.toString();
    }

}
