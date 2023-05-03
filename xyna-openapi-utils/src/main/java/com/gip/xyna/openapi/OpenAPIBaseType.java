package com.gip.xyna.openapi;

public abstract class OpenAPIBaseType {
    private final String name;
    private boolean isNullable;
    private boolean isRequired;

    public OpenAPIBaseType(final String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.getClass().getName();
    }

    public OpenAPIBaseType setNullable() {
        isNullable = true;
        return this;
    }

    public OpenAPIBaseType setRequired() {
        isRequired = true;
        return this;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isValid() {
        return (!isNullable() && !isNull() || isNullable())
                && (isRequired() && !isNull() || !isRequired());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());

        sb.append("type: ").append(this.getClass().getName()).append("\n");
        sb.append("name: ").append(name).append("\n");
        sb.append("isNull: ").append(String.valueOf(isNull())).append("\n");
        sb.append("isNullable: ").append(String.valueOf(isNullable())).append("\n");
        sb.append("isRequired: ").append(String.valueOf(isRequired())).append("\n");

        return sb.toString();
    }

    abstract boolean isNull();
}
