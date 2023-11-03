package edu.hw4.validate;

import java.util.Objects;

public abstract class ValidationError {
    public abstract int getCode();
    public abstract String getFieldName();

    @Override
    public int hashCode() {
        return Objects.hashCode(getCode());
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.hashCode(), obj.hashCode());
    }


}
