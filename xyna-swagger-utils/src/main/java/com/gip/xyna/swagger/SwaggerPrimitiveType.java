package com.gip.xyna.swagger;

public abstract class SwaggerPrimitiveType<T> extends SwaggerBaseType {

    final private T value;

    public SwaggerPrimitiveType(String name, T value) {
        super(name);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    boolean isNull() {
        return value == null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append("value: ").append(value != null ? String.valueOf(value) : "null").append("\n");

        return sb.toString();
    }

}
