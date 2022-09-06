package com.gip.xyna.swagger;

public abstract class SwaggerBaseType {
    private final String name;
    private boolean isNullable;
    private boolean isRequiered;

    public SwaggerBaseType(final String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.getClass().getName();
    }

    public SwaggerBaseType setNullable() {
        isNullable = true;
        return this;
    }

    public SwaggerBaseType setRequiered() {
        isRequiered = true;
        return this;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isRequiered() {
        return isRequiered;
    }

    public boolean isValid() {
        return (!isNullable() && !isNull() || isNullable())
                && (isRequiered() && !isNull() || !isRequiered());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append("type: ").append(this.getClass().getName()).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("isNull: ").append(String.valueOf(isNull())).append("\n");
        sb.append("isNullable: ").append(String.valueOf(isNullable())).append("\n");
        sb.append("isRequiered: ").append(String.valueOf(isRequiered())).append("\n");

        return sb.toString();
    }

    abstract boolean isNull();
}
