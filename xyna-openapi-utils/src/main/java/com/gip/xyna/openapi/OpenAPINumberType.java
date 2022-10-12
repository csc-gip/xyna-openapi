package com.gip.xyna.openapi;

public class OpenAPINumberType<N extends Number> extends OpenAPIPrimitiveType<N> {

    private boolean excludeMin;
    private boolean excludeMax;
    private N multipleOf;
    private N min;
    private N max;

    public OpenAPINumberType<N> setMin(N min) {
        this.min = min;
        return this;
    }

    public OpenAPINumberType<N> setMax(N max) {
        this.max = max;
        return this;
    }

    public OpenAPINumberType(final String name, final N value) {
        super(name, value);
    }

    public OpenAPINumberType<N> setMultipleOf(final N m) {
        this.multipleOf = m;
        return this;
    }

    public OpenAPINumberType<N> setExcludeMin() {
        excludeMin = true;
        return this;
    }

    public OpenAPINumberType<N> setExcludeMax() {
        excludeMax = true;
        return this;
    }

    @Override
    public boolean isValid() {
        if (!super.isValid())
            return false;

        if (!checkMultipleOf())
            return false;

        if (!checkMin())
            return false;

        if (!checkMax())
            return false;

        return true;
    }

    private boolean checkMultipleOf() {
        boolean valid = true;
        if (multipleOf != null) {
            if (multipleOf.intValue() == 0) {
                return getValue().doubleValue() == 0;
            }
            valid = !isNull();
            if (multipleOf instanceof Long) {
                valid = valid && (getValue().longValue() % multipleOf.longValue() == 0L);
            } else if (multipleOf instanceof Integer) {
                valid = valid && (getValue().intValue() % multipleOf.intValue() == (int) 0);
            } else if (multipleOf instanceof Double) {
                valid = valid
                        && (getValue().doubleValue() % multipleOf.doubleValue() == (double) 0.0);
            } else if (multipleOf instanceof Float) {
                valid = valid && (getValue().floatValue() % multipleOf.floatValue() == (float) 0.0);
            }
        }
        return valid;
    }

    private boolean checkMin() {
        boolean valid = true;
        if (min != null) {
            valid = !isNull();
            if (min instanceof Long) {
                valid = valid && (((min.longValue() <= getValue().longValue()) && !excludeMin)
                        || (min.longValue() < getValue().longValue()));
            } else if (min instanceof Integer) {
                valid = valid && (((min.intValue() <= getValue().intValue()) && !excludeMin)
                        || (min.intValue() < getValue().intValue()));
            } else if (min instanceof Double) {
                valid = valid && (((min.doubleValue() <= getValue().doubleValue()) && !excludeMin)
                        || (min.doubleValue() < getValue().doubleValue()));
            } else if (min instanceof Float) {
                valid = valid && (((min.floatValue() <= getValue().floatValue()) && !excludeMin)
                        || (min.floatValue() < getValue().floatValue()));
            }

        }
        return valid;
    }

    private boolean checkMax() {
        boolean valid = true;
        if (max != null) {
            valid = !isNull();
            if (max instanceof Long) {
                valid = valid && (((max.longValue() >= getValue().longValue()) && !excludeMax)
                        || (max.longValue() > getValue().longValue()));
            } else if (max instanceof Integer) {
                valid = valid && (((max.intValue() >= getValue().intValue()) && !excludeMax)
                        || (max.intValue() > getValue().intValue()));
            } else if (max instanceof Double) {
                valid = valid && (((max.doubleValue() >= getValue().doubleValue()) && !excludeMax)
                        || (max.doubleValue() > getValue().doubleValue()));
            } else if (max instanceof Float) {
                valid = valid && (((max.floatValue() >= getValue().floatValue()) && !excludeMax)
                        || (max.floatValue() > getValue().floatValue()));
            }

        }
        return valid;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append("min: ").append(min != null ? String.valueOf(min) : "null").append("\n");
        sb.append("max: ").append(max != null ? String.valueOf(max) : "null").append("\n");
        sb.append("multipleOf: ").append(multipleOf != null ? String.valueOf(multipleOf) : "null")
                .append("\n");

        return sb.toString();
    }

}
